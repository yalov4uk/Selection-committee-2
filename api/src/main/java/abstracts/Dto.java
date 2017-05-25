package abstracts;

/**
 * Created by valera on 5/17/17.
 */
public abstract class Dto {

    protected Integer id;

    public Dto() {
    }

    public Dto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
