package com.packagename.myapp.backend.entity;

public class Game extends AbstractEntity implements  Cloneable{

    private String gameName;
    private double costPerSingleRoll;
    private double costPerIGC;
    private String gameID;

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public double getCostPerIGC() {
        return costPerIGC;
    }

    public void setCostPerIGC(double costPerIGC) {
        this.costPerIGC = costPerIGC;
    }

    public double getCostPerSingleRoll() {
        return costPerSingleRoll;
    }

    public void setCostPerSingleRoll(double costPerSingleRoll) {
        this.costPerSingleRoll = costPerSingleRoll;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
