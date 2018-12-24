import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.compute.ComputeJobAdapter;
import org.apache.ignite.resources.IgniteInstanceResource;

import java.util.Optional;
import java.util.Set;

public class FirstJob extends ComputeJobAdapter {
    private CustomClass customClass;
    @IgniteInstanceResource
    private Ignite ignite;

    public FirstJob(CustomClass customClass) {
        this.customClass = customClass;
    }

    @Override
    public CustomClass execute() throws IgniteException {
        try {
            ClusterGroup dataNodes = ignite.cluster().forPredicate(node ->
                    Optional.ofNullable(node.<Set<String>>attribute("roles"))
                            .map(roles -> roles.contains("data-node"))
                            .orElse(false));
            System.out.println("first job " + customClass.getCustomSubClass(customClass.getClazzName()).getName());
            ignite.compute(ignite.cluster().forLocal()).execute(new SecondTask(customClass), "second task");
        }catch (Exception e){
            e.printStackTrace();
        }
        return customClass;
    }
}
