package com.LiveScore.controller;

import com.LiveScore.model.Highlights;
import com.LiveScore.service.WeeklyInsightsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/updateHighlights")
public class UpdateHighlightsController extends HttpServlet {
    private WeeklyInsightsService service = new WeeklyInsightsService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String[] fieldNames = {
            "topGoalscorer", "topAssists", "mostSaves",
            "mostInterceptions", "mostYellowCards", "mostRedCards"
        };

        for (String field : fieldNames) {
            String value = request.getParameter(field);
            if (value == null || !value.matches("^[A-Za-z0-9 ]+$")) {
                session.setAttribute("error", "Field '" + field + "' must be alphanumeric.");
                response.sendRedirect(request.getContextPath() + "/admin/updateWeeklyInsights");
                return;
            }
        }

        Highlights h = new Highlights();
        h.setTopGoalscorer(request.getParameter("topGoalscorer"));
        h.setTopAssists(request.getParameter("topAssists"));
        h.setMostSaves(request.getParameter("mostSaves"));
        h.setMostInterceptions(request.getParameter("mostInterceptions"));
        h.setMostYellowCards(request.getParameter("mostYellowCards"));
        h.setMostRedCards(request.getParameter("mostRedCards"));

        service.saveHighlights(h);

        session.setAttribute("success", "Highlights updated successfully.");
        response.sendRedirect(request.getContextPath() + "/admin/updateWeeklyInsights");
    }
}
