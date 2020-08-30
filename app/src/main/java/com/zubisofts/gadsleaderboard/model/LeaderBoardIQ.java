package com.zubisofts.gadsleaderboard.model;

import com.google.gson.annotations.SerializedName;

public class LeaderBoardIQ {

    @SerializedName("name")
    private String name;
    @SerializedName("score")
    private Integer score;
    @SerializedName("country")
    private String country;
    @SerializedName("badgeUrl")
    private String badgeUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

}