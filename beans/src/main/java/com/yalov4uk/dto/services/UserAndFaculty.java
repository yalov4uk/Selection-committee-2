package com.yalov4uk.dto.services;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.User;

/**
 * Created by valera on 5/17/17.
 */
public class UserAndFaculty {

    private User user;
    private Faculty faculty;

    public UserAndFaculty() {
    }

    public UserAndFaculty(User user, Faculty faculty) {
        this.user = user;
        this.faculty = faculty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
