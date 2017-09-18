import java.io.Serializable;

public class CustomSubClass implements Serializable{
    private String name;

    public CustomSubClass(){
        name = "name1231231";
    }

    public String getName() {
        return name;
    }
}
