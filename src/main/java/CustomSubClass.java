import java.io.Serializable;

public class CustomSubClass implements Serializable, CustomSubClassInterface{
    private String name;

    public CustomSubClass(){
        name = "name1231231";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CustomSubClass{" +
                "name='" + name + '\'' +
                '}';
    }
}
