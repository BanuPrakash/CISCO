package com.cisco.prj.web;

import com.cisco.prj.entity.Product;
import com.cisco.prj.repo.ProductDao;
import com.cisco.prj.repo.ProductDaoJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDaoJdbcImpl();
        Product p = new Product();
        p.setName(req.getParameter("name"));
        p.setPrice(Double.parseDouble(req.getParameter("price")));

        productDao.addProduct(p);// insert into db

        resp.sendRedirect("index.html");
    }
}
