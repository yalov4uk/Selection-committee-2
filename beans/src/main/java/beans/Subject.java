package beans;

import abstracts.Bean;

import javax.persistence.*;

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

    public Subject(Integer id, Integer value) {
        super(id);
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

        if (value != subject.value) return false;
        if (subjectName != null ? !subjectName.equals(subject.subjectName) : subject.subjectName != null) return false;
        return user != null ? user.equals(subject.user) : subject.user == null;
    }

    @Override
    public int hashCode() {
        Integer result = super.hashCode();
        result = 31 * result + value;
        result = 31 * result + (subjectName != null ? subjectName.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
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
