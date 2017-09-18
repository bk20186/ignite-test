import java.io.Serializable;

public class CustomClass implements Serializable {
    public CustomSubClassInterface getCustomSubClass(String clazz) throws Exception{
        return  (CustomSubClassInterface) Class.forName(clazz).newInstance();
    }

    public String getClazzName(){
        return "CustomSubClass";
    }
}
