import java.io.Serializable;

public class CustomClass implements Serializable {
    public CustomSubClass getCustomSubClass(String clazz) throws Exception{
        return  (CustomSubClass) Class.forName(clazz).newInstance();
    }

    public String getClazzName(){
        return CustomSubClass.class.getName();
    }
}
