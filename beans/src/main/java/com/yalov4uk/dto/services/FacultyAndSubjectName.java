package com.yalov4uk.dto.services;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.SubjectName;

/**
 * Created by valera on 5/22/17.
 */
public class FacultyAndSubjectName {

    private Faculty faculty;
    private SubjectName subjectName;

    public FacultyAndSubjectName() {
    }

    public FacultyAndSubjectName(Faculty faculty, SubjectName subjectName) {
        this.faculty = faculty;
        this.subjectName = subjectName;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public SubjectName getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(SubjectName subjectName) {
        this.subjectName = subjectName;
    }
}
