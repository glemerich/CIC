package com.csc340.CIC.representative;

import jakarta.persistence.*;

@Entity
@Table(name = "Representatives")
public class Representative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "representative_id")
    private String id;

    @Column(name = "user_id")
    private int userId;

    
    private String district;

    
    private String party_affiliation;

    
    private String biography;

    public Representative() {
    }

 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getParty_Affiliation() {
        return party_affiliation;
    }

    public void setParty_Affiliation(String party_affiliation) {
        this.party_affiliation = party_affiliation;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
