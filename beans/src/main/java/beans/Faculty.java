package beans;

import abstracts.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by valera on 4/30/17.
 */
@Entity
@Table(name = "faculties")
public class Faculty extends Bean {

    private String name;
    private Integer maxSize;

    private Set<SubjectName> requiredSubjects = new HashSet<>();
    private Set<User> registeredUsers = new HashSet<>();

    public Faculty() {
    }

    public Faculty(String name, Integer maxSize) {
        this.name = name;
        this.maxSize = maxSize;
    }

    public Faculty(Integer id, String name, Integer maxSize) {
        super(id);
        this.name = name;
        this.maxSize = maxSize;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "maxSize")
    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    @ManyToMany
    @JoinTable(name = "faculties_has_subject_names", joinColumns =
            {@JoinColumn(name = "faculties_id")}, inverseJoinColumns = {@JoinColumn(name = "subject_names_id")})
    public Set<SubjectName> getRequiredSubjects() {
        return requiredSubjects;
    }

    public void setRequiredSubjects(Set<SubjectName> requiredSubjects) {
        this.requiredSubjects = requiredSubjects;
    }

    @ManyToMany
    @JoinTable(name = "faculties_has_users", joinColumns =
            {@JoinColumn(name = "faculties_id")}, inverseJoinColumns = {@JoinColumn(name = "users_id")})
    public Set<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(Set<User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;
        if (!super.equals(o)) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(name, faculty.name) &&
                Objects.equals(maxSize, faculty.maxSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, maxSize);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                super.toString() +
                ", name='" + name + '\'' +
                ", maxSize=" + maxSize +
                '}';
    }
}
