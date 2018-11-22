package cn.ty.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet {

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // TODO
    }


    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse  response)
        throws ServletException, IOException{
        // TODO
    }
}
