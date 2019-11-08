package online.vidacademica.services.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestResultGetResultsDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Double score;
    private Instant date;
    private String nameTest;
    private String nameStudent;
    private Double fullScore;

    public TestResultGetResultsDTO(){}

    public TestResultGetResultsDTO(Double score, Instant date, String nameTest, String nameStudent, Double fullScore) {
        this.score = score;
        this.date = date;
        this.nameTest = nameTest;
        this.nameStudent = nameStudent;
        this.fullScore = fullScore;
    }

    public double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getNameTest() {
        return nameTest;
    }

    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public Double getFullScore() {
        return fullScore;
    }

    public void setFullScore(Double fullScore) {
        this.fullScore = fullScore;
    }
}
