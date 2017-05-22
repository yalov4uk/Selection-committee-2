package com.yalov4uk.dto.services;

import com.yalov4uk.dto.FacultyDto;
import com.yalov4uk.dto.SubjectNameDto;

/**
 * Created by valera on 5/22/17.
 */
public class FacultyAndSubjectNameDto {

    private FacultyDto faculty;
    private SubjectNameDto subjectName;

    public FacultyAndSubjectNameDto() {
    }

    public FacultyAndSubjectNameDto(FacultyDto faculty, SubjectNameDto subjectName) {
        this.faculty = faculty;
        this.subjectName = subjectName;
    }

    public FacultyDto getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDto faculty) {
        this.faculty = faculty;
    }

    public SubjectNameDto getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(SubjectNameDto subjectName) {
        this.subjectName = subjectName;
    }
}
