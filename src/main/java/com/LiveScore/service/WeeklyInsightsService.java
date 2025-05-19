package com.LiveScore.service;

import com.LiveScore.dao.WeeklyInsightsDAO;
import com.LiveScore.model.*;

import java.util.List;

public class WeeklyInsightsService {
    private WeeklyInsightsDAO dao = new WeeklyInsightsDAO();

    // Result Distribution
    public void saveResultDistribution(ResultDistribution result) {
        dao.insertResultDistribution(result);
    }

    public ResultDistribution getLatestResultDistribution() {
        return dao.getLatestResultDistribution();
    }

    // Power Rankings
    public void savePowerRankings(List<PowerRanking> rankings) {
        dao.insertPowerRankings(rankings);
    }

    public List<PowerRanking> getPowerRankings() {
        return dao.getPowerRankings();
    }

    // Highlights
    public void saveHighlights(Highlights highlights) {
        dao.insertHighlights(highlights);
    }

    public Highlights getLatestHighlights() {
        return dao.getLatestHighlights();
    }


public WeeklyInsightsModel getWeeklyInsights() {
    WeeklyInsightsModel model = new WeeklyInsightsModel();
    model.setResultDistribution(getLatestResultDistribution());
    model.setPowerRankings(getPowerRankings());
    model.setHighlights(getLatestHighlights());
    return model;
	}
}
