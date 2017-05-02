package beans;

import abstracts.Bean;

import javax.persistence.*;
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

    private Set<Subject> subjects;

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
    }

    public User(String name, String login, String password, Integer roleId) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(Integer id, String name, String login, String password, Integer roleId) {
        super(id);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(subjects, user.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, login, password, role, subjects);
    }

    @Override
    public String toString() {
        return "User{" +
                super.toString() +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", subjects=" + subjects +
                '}';
    }
}
