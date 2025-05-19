package com.LiveScore.model;

public class ResultDistribution {
    private int id;
    private int homeWins;
    private int awayWins;
    private int draws;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getHomeWins() { return homeWins; }
    public void setHomeWins(int homeWins) { this.homeWins = homeWins; }

    public int getAwayWins() { return awayWins; }
    public void setAwayWins(int awayWins) { this.awayWins = awayWins; }

    public int getDraws() { return draws; }
    public void setDraws(int draws) { this.draws = draws; }
}
