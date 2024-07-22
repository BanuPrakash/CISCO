package com.cisco.prj.web;

import com.cisco.prj.dao.PersistenceException;
import com.cisco.prj.dao.ProductDao;
import com.cisco.prj.dao.ProductDaoJdbcImpl;
import com.cisco.prj.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDao = new ProductDaoJdbcImpl();
        List<Product> products = productDao.getProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("list.jsp").forward(req, resp);

       //        resp.setContentType("text/html"); // MIME
//        PrintWriter out = resp.getWriter(); // Character Stream to the Browser
//        out.println("<html><body>");
//        out.println("<table border=\'1\'>");
//        out.println("<tr><th>ID</th><th>Name</th><th>Price</th></tr>");
//        ProductDao productDao = new ProductDaoJdbcImpl();
//        List<Product> products = productDao.getProducts();
//        for(Product p : products) {
//            out.println("<tr>");
//                out.println("<td>" + p.getId() + "</td>");
//                out.println("<td>" + p.getName() + "</td>");
//                out.println("<td>" + p.getPrice() + "</td>");
//            out.println("</tr>");
//        }
//        out.println("</table></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = Product.builder().
                name(req.getParameter("name")).
                price(Double.parseDouble(req.getParameter("price"))).
                build();

        ProductDao productDao = new ProductDaoJdbcImpl();
        try {
            productDao.addProduct(p);
            resp.sendRedirect("index.html");
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}

