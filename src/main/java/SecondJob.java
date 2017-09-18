import org.apache.ignite.IgniteException;
import org.apache.ignite.compute.ComputeJobAdapter;

public class SecondJob extends ComputeJobAdapter {

    private CustomClass customClass;

    public SecondJob(CustomClass customClass) {
        this.customClass = customClass;
    }

    @Override
    public CustomClass execute() throws IgniteException {
        try {
            System.out.println("second job " + Class.forName("CustomSubClass").newInstance().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customClass;
    }
}
