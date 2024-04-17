package com.csc340.CIC.representative;

public class Representative {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String party;
    private String leadershipRole;
    private String nextElection;
    private String chamber;
    private String url;
    private String state;
    private String district;

    // Constructors

    public Representative() {
    }

    public Representative(String id, String title, String firstName, String lastName, String party, String leadershipRole, String nextElection, String chamber, String url) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.party = party;
        this.leadershipRole = leadershipRole;
        this.nextElection = nextElection;
        this.chamber = chamber;
        this.url = url;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getLeadershipRole() {
        return leadershipRole;
    }

    public void setLeadershipRole(String leadershipRole) {
        this.leadershipRole = leadershipRole;
    }

    public String getNextElection() {
        return nextElection;
    }

    public void setNextElection(String nextElection) {
        this.nextElection = nextElection;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
