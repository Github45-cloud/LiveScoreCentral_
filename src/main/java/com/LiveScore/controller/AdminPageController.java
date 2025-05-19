package com.LiveScore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {
    "/admin/createMatchPage",
    "/admin/updateMatchPage",
    "/admin/deleteMatchPage"
})
public class AdminPageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/admin/createMatchPage":
                request.getRequestDispatcher("/WEB-INF/admin/create-match.jsp").forward(request, response);
                break;

            case "/admin/updateMatchPage":
                request.getRequestDispatcher("/WEB-INF/admin/update-match.jsp").forward(request, response);
                break;

            case "/admin/deleteMatchPage":
                request.getRequestDispatcher("/WEB-INF/admin/delete-match.jsp").forward(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
