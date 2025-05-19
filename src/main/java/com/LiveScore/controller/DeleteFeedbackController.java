package com.LiveScore.controller;

import com.LiveScore.dao.FeedbackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/deleteFeedback")
public class DeleteFeedbackController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("feedbackId");

        try {
            int feedbackId = Integer.parseInt(idStr);
            new FeedbackDAO().deleteFeedbackById(feedbackId);
        } catch (Exception e) {
            e.printStackTrace(); // You could log this instead
        }

        response.sendRedirect(request.getContextPath() + "/adminFeedback");
    }
}
