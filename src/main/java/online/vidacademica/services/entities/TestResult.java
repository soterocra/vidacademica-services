package online.vidacademica.services.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import online.vidacademica.services.entities.pk.TestResultPK;

@Entity
@Table(name = "tb_test_result")
public class TestResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TestResultPK id = new TestResultPK();

    private double score;
    private Instant date;

    public TestResult() {
    }

    public TestResult(User user, Test test, double score, Instant date) {
        super();
        id.setUser(user);
        id.setTest(test);
        this.score = score;
        this.date = date;
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

    public User getUser() {
        return id.getUser();
    }

    public void setUser(User user) {
        id.setUser(user);
    }

    public Test getTest() {
        return id.getTest();
    }

    public void setTest(Test test) {
        id.setTest(test);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        long temp;
        temp = Double.doubleToLongBits(score);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TestResult other = (TestResult) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (Double.doubleToLongBits(score) != Double.doubleToLongBits(other.score))
            return false;
        return true;
    }
}


