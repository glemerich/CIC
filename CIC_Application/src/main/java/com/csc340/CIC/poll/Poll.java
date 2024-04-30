package com.csc340.CIC.poll;

import jakarta.persistence.*;

@Entity
@Table(name = "polls")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poll_id")
    private int pollId;

    @Column(name = "representative_id")
    private String representativeId;

    @Column(name = "poll_question")
    private String pollQuestion;

    @Column(name = "poll_option1")
    private String pollOption1;

    @Column(name = "poll_option2")
    private String pollOption2;

    @Column(name = "poll_option3")
    private String pollOption3;

    @Column(name = "poll_option4")
    private String pollOption4;

    @Column(name = "option_vote_count1")
    private int optionVoteCount1;

    @Column(name = "option_vote_count2")
    private int optionVoteCount2;

    @Column(name = "option_vote_count3")
    private int optionVoteCount3;

    @Column(name = "option_vote_count4")
    private int optionVoteCount4;

    public Poll() {
    }

    public Poll(String representativeId, String pollQuestion, String pollOption1, String pollOption2, String pollOption3, String pollOption4) {
        this.representativeId = representativeId;
        this.pollQuestion = pollQuestion;
        this.pollOption1 = pollOption1;
        this.pollOption2 = pollOption2;
        this.pollOption3 = pollOption3;
        this.pollOption4 = pollOption4;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public String getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeId(String representativeId) {
        this.representativeId = representativeId;
    }

    public String getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(String pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public String getPollOption1() {
        return pollOption1;
    }

    public void setPollOption1(String pollOption1) {
        this.pollOption1 = pollOption1;
    }

    public String getPollOption2() {
        return pollOption2;
    }

    public void setPollOption2(String pollOption2) {
        this.pollOption2 = pollOption2;
    }

    public String getPollOption3() {
        return pollOption3;
    }

    public void setPollOption3(String pollOption3) {
        this.pollOption3 = pollOption3;
    }

    public String getPollOption4() {
        return pollOption4;
    }

    public void setPollOption4(String pollOption4) {
        this.pollOption4 = pollOption4;
    }

    public int getOptionVoteCount1() {
        return optionVoteCount1;
    }

    public void setOptionVoteCount1(int optionVoteCount1) {
        this.optionVoteCount1 = optionVoteCount1;
    }

    public int getOptionVoteCount2() {
        return optionVoteCount2;
    }

    public void setOptionVoteCount2(int optionVoteCount2) {
        this.optionVoteCount2 = optionVoteCount2;
    }

    public int getOptionVoteCount3() {
        return optionVoteCount3;
    }

    public void setOptionVoteCount3(int optionVoteCount3) {
        this.optionVoteCount3 = optionVoteCount3;
    }

    public int getOptionVoteCount4() {
        return optionVoteCount4;
    }

    public void setOptionVoteCount4(int optionVoteCount4) {
        this.optionVoteCount4 = optionVoteCount4;
    }
}
