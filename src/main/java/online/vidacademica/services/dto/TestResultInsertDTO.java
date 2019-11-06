package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Course;
import online.vidacademica.services.entities.TestResult;

import java.io.Serializable;
import java.time.Instant;

public class TestResultInsertDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private double score;
    private Instant date;
    private Long testId;
    private Long userId;

    public TestResultInsertDTO(){}

    public TestResultInsertDTO(double score, Instant date, Long testId, Long userId) {
        this.score = score;
        this.date = date;
        this.testId = testId;
        this.userId = userId;
    }

    public TestResultInsertDTO(TestResult entity){
        if(entity.getTest() == null){
            throw new IllegalArgumentException("Test was null");
        }
        this.score = entity.getScore();
        this.date = entity.getDate();
        this.testId = entity.getTest().getId();
        this.userId = entity.getUser().getId();
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

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
