package com.LiveScore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.LiveScore.dao.FeedbackDAO;
import com.LiveScore.model.FeedbackModel;

@WebServlet("/adminFeedback")
public class AdminFeedbackController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<FeedbackModel> feedbackList = new FeedbackDAO().getAllFeedback();
            request.setAttribute("feedbackList", feedbackList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/admin/admin-feedback.jsp").forward(request, response);
    }
}
