
package com.LiveScore.model;

import java.util.List;

public class WeeklyInsightsModel {
    private ResultDistribution resultDistribution;
    private List<PowerRanking> powerRankings;
    private Highlights highlights;

    public ResultDistribution getResultDistribution() {
        return resultDistribution;
    }

    public void setResultDistribution(ResultDistribution resultDistribution) {
        this.resultDistribution = resultDistribution;
    }

    public List<PowerRanking> getPowerRankings() {
        return powerRankings;
    }

    public void setPowerRankings(List<PowerRanking> powerRankings) {
        this.powerRankings = powerRankings;
    }

    public Highlights getHighlights() {
        return highlights;
    }

    public void setHighlights(Highlights highlights) {
        this.highlights = highlights;
    }
}
