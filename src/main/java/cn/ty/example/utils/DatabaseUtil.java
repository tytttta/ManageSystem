package cn.ty.example.utils;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DatabaseUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    private static final QueryRunner QUERY_RUNNER;

    private static final  ThreadLocal<Connection> CONNECTION_HOLDER;

    private static final BasicDataSource DATA_SOURCE;

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    static {
        CONNECTION_HOLDER = new ThreadLocal<Connection>();
        QUERY_RUNNER = new QueryRunner();

        Properties conf= PropsUtils.loadProps("config.properties");
        DRIVER =conf.getProperty("jdbc.driver");
        URL= conf.getProperty("jdbc.url");
        USERNAME=conf.getProperty("jdbc.username");
        PASSWORD= conf.getProperty("jdbc.password");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);

    }

    /**
     * 获取数据库连接
     */
    public  static Connection getConnection(){
        Connection conn=CONNECTION_HOLDER.get();
        if(conn==null) {
            try {
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("get Connection fail", e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return  conn;
    }

/*
    /**
     *使用数据库连接池不需要手动关闭
     * 关闭数据库连接


    public static void closeConnection(){
        Connection conn= CONNECTION_HOLDER.get();
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                LOGGER.error("close connect fail", e);
            }
            finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }
*/


    /**
     * 查询实体列表
     */
    public static<T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params){
        List<T> entityList ;
        try{
            Connection conn = getConnection();
            entityList=QUERY_RUNNER.query(conn,sql, new BeanListHandler<T>(entityClass), params);
        }catch (SQLException e){
            LOGGER.error("query entity list fail", e);
            throw  new RuntimeException(e);
        }
        return  entityList;
    }

    /**
     * 查询实体
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object ...params){
        T entity ;
        try{
            Connection connection = getConnection();
            entity= QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), params);
        }catch (SQLException e){
            LOGGER.error("query entity failure",e);
            throw  new RuntimeException(e);
        }
        return  entity;
    }

    /**
     * 执行查询语句
     */
    public static List<Map<String, Object>> excuteQuery(String sql , Object ... params){
        List<Map<String, Object>> list;
        try{
            Connection connection = getConnection();
            list = QUERY_RUNNER.query(connection,sql, new MapListHandler(), params);
        }catch (SQLException e){
            LOGGER.error("excute query failure", e);
            throw  new RuntimeException(e);
        }
        return  list;
    }

    /**
     * 执行更新语句(包括update， insert， delete)
     */
    public static int executeUpdate(String sql, Object ...params){
        int row;
        try{
            Connection connection = getConnection();
            row = QUERY_RUNNER.update(connection, sql, params);
        }catch (SQLException e){
            LOGGER.error("update sql failure", e);
            throw  new RuntimeException(e);
        }
        return  row;

    }

    /**
     * 插入实体
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("insert a null entity: fileldMap is null!");
            return  false;
        }
        String sql="insert into " + getTableName(entityClass);
        StringBuilder columns= new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for(String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + "VAlues " + values;

        Object [] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;
    }

    /**
     * 更新实体
     * @param entityClass
     * @param id
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static<T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("update the entity fail: fieldMap is empty");
            return false;
        }

        String sql = "update " +getTableName(entityClass) + " set ";
        StringBuilder columns = new StringBuilder();
        for(String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append("= ?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " where id = ?";

        List<Object> paramList =new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);

        Object [] param=paramList.toArray();

        return executeUpdate(sql, param) == 1;
    }


    /**
     * 删除实体
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */
    public static<T> boolean deleteEntity(Class<T> entityClass, long id){
        String sql="delete from " + getTableName(entityClass) + " where id =?";
        return executeUpdate(sql, id) ==1;
    }

    public static String getTableName(Class<?> entityClass){
        //linux 下mysql表名大小写敏感
        return entityClass.getSimpleName().toLowerCase();
    }

    public static void excuteSqlFile(String filePath){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String sql;
            while ((sql = reader.readLine()) != null) {
                executeUpdate(sql);
            }
        }catch (Exception e){
            LOGGER.error("init the dataBase fail", e);
            throw new RuntimeException(e);
        }

    }

}
