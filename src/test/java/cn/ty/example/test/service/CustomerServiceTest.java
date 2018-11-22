package cn.ty.example.test.service;

import cn.ty.example.entity.Customer;
import cn.ty.example.serivce.CustomerService;
import cn.ty.example.utils.DatabaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* CustomerService Tester. 
* 
* @author <Authors name> 
* @since <pre>十一月 11, 2018</pre> 
* @version 1.0 
*/ 
public class CustomerServiceTest {
    private CustomerService customerService;

    public CustomerServiceTest(){
        this.customerService= new CustomerService();
    }

    @Before
    public void init() throws Exception {
        String file="sql/customer_init.sql";
        DatabaseUtil.excuteSqlFile(file);
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: getCustomerList(String keyword)
    *
    */
    @Test
    public void testGetCustomerList() throws Exception {
    //TODO: Test goes here...
        List<Customer> list=customerService.getCustomerList("");
        Assert.assertEquals(2,((List) list).size());
    }

    /**
    *
    * Method: getCustomer(long id)
    *
    */
    @Test
    public void testGetCustomer() throws Exception {
    //TODO: Test goes here...
        long id=1;
        Assert.assertNotNull(customerService.getCustomer(id));
    }

    /**
    *
    * Method: createCustomer(Map<String, Object> fieldMap)
    *
    */
    @Test
    public void testCreateCustomer() throws Exception {
    //TODO: Test goes here...
        Map<String, Object> map= new HashMap();
        map.put("name","customer3");
        map.put("contact","John");
        map.put("telephone","1111111111");
        boolean result =customerService.createCustomer(map);
        Assert.assertTrue(result);
    }

    /**
    *
    * Method: updateCustomer(long id, Map<String, Object> fieldMap)
    *
    */
    @Test
    public void testUpdateCustomer() throws Exception {
    //TODO: Test goes here...
        long id=1;
        Map<String, Object> map= new HashMap<String, Object>();
        map.put("contact","John2");
        Assert.assertTrue(customerService.updateCustomer(id,map));
    }

    /**
    *
    * Method: deleteCustomer(long id)
    *
    */
    @Test
    public void testDeleteCustomer() throws Exception {
    //TODO: Test goes here...
        long id=1;
        Assert.assertTrue(customerService.deleteCustomer(id));
    }


} 
