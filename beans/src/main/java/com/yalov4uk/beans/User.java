package com.yalov4uk.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yalov4uk.abstracts.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by valera on 4/30/17.
 */
@Entity
@Table(name = "users")
public class User extends Bean {

    private String name;
    private String login;
    private String password;

    private Role role;

    @JsonIgnore
    private Set<Subject> subjects;
    @JsonIgnore
    private Set<Faculty> faculties;
    @JsonIgnore
    private Set<Statement> statements;

    public Integer getAverageScore(Faculty faculty) {
        Integer result = 0;
        for (Subject subject : subjects) {
            if (faculty.getRequiredSubjects().contains(subject.getSubjectName())) {
                result += subject.getValue();
            }
        }
        return result;
    }

    public User() {
        subjects = new HashSet<>();
        faculties = new HashSet<>();
        statements = new HashSet<>();
    }

    public User(String name, String login, String password) {
        subjects = new HashSet<>();
        faculties = new HashSet<>();
        statements = new HashSet<>();
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name = "roles_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @ManyToMany(mappedBy = "registeredUsers", fetch = FetchType.LAZY)
    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public Set<Statement> getStatements() {
        return statements;
    }

    public void setStatements(Set<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, login, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                super.toString() +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
