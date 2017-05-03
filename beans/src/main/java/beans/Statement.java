package beans;

import abstracts.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by valera on 4/30/17.
 */
@Entity
@Table(name = "statements")
public class Statement extends Bean implements Comparable<Object> {

    private Date date;

    private User user;
    private Faculty faculty;

    @Override
    public int compareTo(Object o) {
        return user.getAverageScore(faculty).compareTo(((Statement) o).getUser().getAverageScore(faculty));
    }

    public Statement() {
        this.date = new Date();
    }

    public Statement(Date date) {
        this.date = date;
    }

    @Column(name = "date")
    @Temporal(value = TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "users_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "faculties_id")
    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Statement)) return false;
        if (!super.equals(o)) return false;
        Statement statement = (Statement) o;
        return Objects.equals(date, statement.date) &&
                Objects.equals(user, statement.user) &&
                Objects.equals(faculty, statement.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, user, faculty);
    }

    @Override
    public String toString() {
        return "Statement{" +
                super.toString() +
                ", date=" + date +
                ", user=" + user +
                ", faculty=" + faculty +
                '}';
    }
}
