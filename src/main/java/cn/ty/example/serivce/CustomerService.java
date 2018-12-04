package cn.ty.example.serivce;

import cn.ty.example.entity.Customer;
import cn.ty.example.utils.DatabaseUtil;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class CustomerService {



    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);






    public List<Customer> getCustomerList(){
            String sql="select * from customer";
            return DatabaseUtil.queryEntityList(Customer.class, sql);
    }


    public Customer getCustomer(long id){
        String sql="select * from customer where id =?";
        List<Object> list =new ArrayList<Object>();
        list.add(id);
        Object [] params =list.toArray();
        return DatabaseUtil.queryEntity(Customer.class, sql, params);

    }


    public boolean createCustomer(Map<String, Object> fieldMap){
        return DatabaseUtil.insertEntity(Customer.class, fieldMap);
    }

    public boolean updateCustomer(long id, Map<String, Object> fieldMap){

         return DatabaseUtil.updateEntity(Customer.class, id, fieldMap);
    }

    public boolean deleteCustomer(long id){
        return DatabaseUtil.deleteEntity(Customer.class, id);
    }


}
