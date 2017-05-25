package dto;


import abstracts.Dto;

/**
 * Created by valera on 5/17/17.
 */
public class FacultyDto extends Dto {

    private String name;
    private Integer maxSize;

    public FacultyDto() {
    }

    public FacultyDto(String name, Integer maxSize) {
        this.name = name;
        this.maxSize = maxSize;
    }

    public FacultyDto(Integer id, String name, Integer maxSize) {
        super(id);
        this.name = name;
        this.maxSize = maxSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }
}
