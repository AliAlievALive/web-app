package ru.halal.servlet;

import ru.halal.service.ProductService;
import ru.halal.service.FileService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CatalogServlet extends HttpServlet {
    private ProductService productService;
    private FileService fileService;


    @Override
    public void init() throws ServletException {
        InitialContext context = null;
        try {
            context = new InitialContext();
            productService = (ProductService) context.lookup("java:/comp/env/bean/product-service");
            fileService = (FileService) context.lookup("java:/comp/env/bean/file-service");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("items", productService.getAll());
            req.getRequestDispatcher("/WEB-INF/catalog.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var name = req.getParameter("name");
            var description = req.getParameter("description");
            var part = req.getPart("image");

            var image = fileService.writeFile(part);

            productService.create(name, description, image);
            resp.sendRedirect(String.join("/", req.getContextPath(), req.getServletPath()));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
