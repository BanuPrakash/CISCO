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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDaoJdbcImpl();
        Product p = new Product();
        p.setName(req.getParameter("name"));
        p.setPrice(Double.parseDouble(req.getParameter("price")));

        productDao.addProduct(p);// insert into db

        // client side redirection
        resp.sendRedirect("index.html");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter(); // opens character stream to client [ Browser ]
        ProductDao productDao = new ProductDaoJdbcImpl();
        List<Product> products = productDao.getProducts();
        req.setAttribute("list", products); // place the data in request
        // server side redirection
        req.getRequestDispatcher("list.jsp").forward(req, resp);


//        out.print("<html><body>");
//            out.print("<table>");
//                out.print("<thead>");
//                    out.print("<tr>");
//                        out.print("<th>ID</th>");
//                        out.print("<th>NAME</th>");
//                        out.print("<th>PRICE</th>");
//                    out.print("</tr>");
//                out.print("</thead>");
//                out.print("<tbody>");
//                    for(Product p : products) {
//                        out.print("<tr>");
//                            out.print("<td>" + p.getId() + "</td>");
//                            out.print("<td>" + p.getName() + "</td>");
//                            out.print("<td>" + p.getPrice() + "</td>");
//                        out.print("</tr>");
//                    }
//                out.print("</tbody>");
//            out.print("</table>");
//            out.print("<a href=\"index.html\">Back</a>");
//        out.print("</body></html>");
    }
}
