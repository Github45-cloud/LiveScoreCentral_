package com.LiveScore.controller;

import com.LiveScore.dao.FeedbackDAO;
import com.LiveScore.model.FeedbackModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/submitFeedback")
public class SubmitFeedbackController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // Basic list of bad words (you can expand this list)
        List<String> badWords = Arrays.asList("ugly", "nasty", "bad");

        // Check for bad words (case-insensitive)
        boolean containsBadWords = badWords.stream()
            .anyMatch(word -> message.toLowerCase().contains(word));

        if (containsBadWords) {
            request.setAttribute("errorMessage", "Please avoid using inappropriate language.");
            request.getRequestDispatcher("/WEB-INF/pages/contact.jsp").forward(request, response);
            return;
        }

        FeedbackModel feedback = new FeedbackModel();
        feedback.setName(name);
        feedback.setEmail(email);
        feedback.setMessage(message);

        try {
            new FeedbackDAO().saveFeedback(feedback);
            request.setAttribute("successMessage", "Thank you for your feedback!");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error submitting feedback.");
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/pages/contact.jsp").forward(request, response);
    }
}
