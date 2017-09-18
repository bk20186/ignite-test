import org.apache.ignite.IgniteException;
import org.apache.ignite.compute.ComputeJobAdapter;

public class FirstJob extends ComputeJobAdapter {
    private CustomClass customClass;

    public FirstJob(CustomClass customClass) {
        this.customClass = customClass;
    }

    @Override
    public CustomClass execute() throws IgniteException {
        try {
            System.out.println("first job " + customClass.getCustomSubClass(customClass.getClazzName()).getName());
        }catch (Exception e){
            e.printStackTrace();
        }
        return customClass;
    }
}