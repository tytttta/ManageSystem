package cn.ty.example.controller;

import cn.ty.example.entity.Customer;
import cn.ty.example.serivce.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer")
public class CustomerCreateServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException{
        customerService = new CustomerService();
    }

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        List<Customer> list = customerService.getCustomerList();
        request.setAttribute("customerList", list);
        request.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(request,response);
    }


    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse  response)
        throws ServletException, IOException{
        // TODO
    }
}
