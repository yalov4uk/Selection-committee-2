package com.yalov4uk.beans;

import com.yalov4uk.abstracts.Bean;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by valera on 4/30/17.
 */
@Entity
@Table(name = "subjects")
public class Subject extends Bean {

    private Integer value;

    private SubjectName subjectName;
    private User user;

    public Subject() {
    }

    public Subject(Integer value) {
        this.value = value;
    }

    @Column(name = "value")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @ManyToOne
    @JoinColumn(name = "subject_names_id")
    public SubjectName getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(SubjectName subjectName) {
        this.subjectName = subjectName;
    }

    @ManyToOne
    @JoinColumn(name = "users_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        if (!super.equals(o)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(value, subject.value) &&
                Objects.equals(subjectName, subject.subjectName) &&
                Objects.equals(user, subject.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value, subjectName, user);
    }

    @Override
    public String toString() {
        return "Subject{" +
                super.toString() +
                ", value=" + value +
                ", subjectName=" + subjectName +
                ", user=" + user +
                '}';
    }
}
