package com.csc340.CIC.bill;

import java.util.Date;
import java.util.Map;
import java.util.List;

public class Bill {

    private String billId;
    private String billNumber;
    private String billSlug;
    private String title;
    private String introducedDate;
    private String sponsorName;
    private String sponsorParty;
    private String sponsorState;
    private String sponsorId;
    private String shortTitle;
    private String billUri;
    private int cosponsors;
    private Map<String, Integer> cosponsorsByParty;
    private String latestMajorAction;
    private Date latestMajorActionDate;
    private List<String> actions;

    public Bill() {
    }

    public Bill(String billId, String title, String sponsorId) {
        this.billId = billId;
        this.title = title;
        this.sponsorId = sponsorId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getBillSlug() {
        return billSlug;
    }

    public void setBillSlug(String billSlug) {
        this.billSlug = billSlug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroducedDate() {
        return introducedDate;
    }

    public void setIntroducedDate(String introducedDate) {
        this.introducedDate = introducedDate;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSponsorParty() {
        return sponsorParty;
    }

    public void setSponsorParty(String sponsorParty) {
        this.sponsorParty = sponsorParty;
    }

    public String getSponsorState() {
        return sponsorState;
    }

    public void setSponsorState(String sponsorState) {
        this.sponsorState = sponsorState;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getBillUri() {
        return billUri;
    }

    public void setBillUri(String billUri) {
        this.billUri = billUri;
    }

    public int getCosponsors() {
        return cosponsors;
    }

    public void setCosponsors(int cosponsors) {
        this.cosponsors = cosponsors;
    }

    public Map<String, Integer> getCosponsorsByParty() {
        return cosponsorsByParty;
    }

    public void setCosponsorsByParty(Map<String, Integer> cosponsorsByParty) {
        this.cosponsorsByParty = cosponsorsByParty;
    }

    public String getLatestMajorAction() {
        return latestMajorAction;
    }

    public void setLatestMajorAction(String latestMajorAction) {
        this.latestMajorAction = latestMajorAction;
    }

    public Date getLatestMajorActionDate() {
        return latestMajorActionDate;
    }

    public void setLatestMajorActionDate(Date latestMajorActionDate) {
        this.latestMajorActionDate = latestMajorActionDate;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }
}
