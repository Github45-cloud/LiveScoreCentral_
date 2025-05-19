package com.LiveScore.model;

public class Highlights {
    private int id;
    private String topGoalscorer;
    private String topAssists;
    private String mostSaves;
    private String mostInterceptions;
    private String mostYellowCards;
    private String mostRedCards;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTopGoalscorer() { return topGoalscorer; }
    public void setTopGoalscorer(String topGoalscorer) { this.topGoalscorer = topGoalscorer; }

    public String getTopAssists() { return topAssists; }
    public void setTopAssists(String topAssists) { this.topAssists = topAssists; }

    public String getMostSaves() { return mostSaves; }
    public void setMostSaves(String mostSaves) { this.mostSaves = mostSaves; }

    public String getMostInterceptions() { return mostInterceptions; }
    public void setMostInterceptions(String mostInterceptions) { this.mostInterceptions = mostInterceptions; }

    public String getMostYellowCards() { return mostYellowCards; }
    public void setMostYellowCards(String mostYellowCards) { this.mostYellowCards = mostYellowCards; }

    public String getMostRedCards() { return mostRedCards; }
    public void setMostRedCards(String mostRedCards) { this.mostRedCards = mostRedCards; }
}
