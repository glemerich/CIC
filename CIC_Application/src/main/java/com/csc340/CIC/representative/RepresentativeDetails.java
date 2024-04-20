package com.csc340.CIC.representative;


public class RepresentativeDetails {

    private String id;
    private String firstName;
    private String lastName;
    private String party;
    private String leadershipRole;
    private String nextElection;
    private String chamber;
    private String url;
    private String state;
    private String district;
    private String biography;

    // Constructors
    public RepresentativeDetails() {
    }

    public RepresentativeDetails(String id, String firstName, String lastName, String party, String leadershipRole, String nextElection, String chamber, String url, String state, String district, String biography) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.party = party;
        this.leadershipRole = leadershipRole;
        this.nextElection = nextElection;
        this.chamber = chamber;
        this.url = url;
        this.state = state;
        this.district = district;
        this.biography = biography;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
