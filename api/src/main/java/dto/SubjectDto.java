package dto;


import abstracts.Dto;

/**
 * Created by valera on 5/17/17.
 */
public class SubjectDto extends Dto {

    private Integer value;

    private SubjectNameDto subjectName;
    private UserDto user;

    public SubjectDto() {
    }

    public SubjectDto(Integer value) {
        this.value = value;
    }

    public SubjectDto(Integer value, SubjectNameDto subjectName, UserDto user) {
        this.value = value;
        this.subjectName = subjectName;
        this.user = user;
    }

    public SubjectDto(Integer id, Integer value, SubjectNameDto subjectName, UserDto user) {
        super(id);
        this.value = value;
        this.subjectName = subjectName;
        this.user = user;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public SubjectNameDto getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(SubjectNameDto subjectName) {
        this.subjectName = subjectName;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
