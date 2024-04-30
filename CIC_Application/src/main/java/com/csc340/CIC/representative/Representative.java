package com.csc340.CIC.representative;

import jakarta.persistence.*;

@Entity
@Table(name = "Representatives")
public class Representative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "representative_id")
    private String id;

    
    private int user_id;

    
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

    public int getUser_Id() {
        return user_id;
    }

    public void setUser_Id(int user_id) {
        this.user_id = user_id;
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
