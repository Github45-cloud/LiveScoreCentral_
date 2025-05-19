package com.LiveScore.model;

public class PowerRanking {
    private int id;
    private int rankPosition;
    private String teamName;
    private String recentResults;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getRankPosition() { return rankPosition; }
    public void setRankPosition(int rankPosition) { this.rankPosition = rankPosition; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getRecentResults() { return recentResults; }
    public void setRecentResults(String recentResults) { this.recentResults = recentResults; }
}
