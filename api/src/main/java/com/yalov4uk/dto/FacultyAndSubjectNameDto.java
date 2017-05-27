package com.yalov4uk.dto;

public class FacultyAndSubjectNameDto {

    private Integer facultyId;
    private Integer subjectNameId;

    public FacultyAndSubjectNameDto() {
    }

    public FacultyAndSubjectNameDto(Integer facultyId, Integer subjectNameId) {
        this.facultyId = facultyId;
        this.subjectNameId = subjectNameId;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public Integer getSubjectNameId() {
        return subjectNameId;
    }

    public void setSubjectNameId(Integer subjectNameId) {
        this.subjectNameId = subjectNameId;
    }
}
