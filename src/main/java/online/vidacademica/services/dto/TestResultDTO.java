package online.vidacademica.services.dto;

import online.vidacademica.services.entities.TestResult;

import java.io.Serializable;
import java.time.Instant;

public class TestResultDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private double score;
    private Instant date;
    private String name;
    private Double fullScore;
    private Long userId;

    public TestResultDTO(){}

    public TestResultDTO(double score, Instant date, String name, Double fullScore,Long userId) {
        this.score = score;
        this.date = date;
        this.name = name;
        this.fullScore = fullScore;
        this.userId = userId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFullScore() {
        return fullScore;
    }

    public void setFullScore(Double fullScore) {
        this.fullScore = fullScore;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
