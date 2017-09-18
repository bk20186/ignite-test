import org.apache.ignite.IgniteException;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskSplitAdapter;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SecondTask extends ComputeTaskSplitAdapter<String, Boolean> {
    private CustomClass customClass;

    public SecondTask(CustomClass customClass) {
        this.customClass = customClass;
    }


    @Override
    public Boolean reduce(List<ComputeJobResult> results) throws IgniteException {
        System.out.println("reduce second task");
        results.forEach(r -> {
            CustomClass customClass = r.getData();
            try {
                System.out.println(customClass.getCustomSubClass(customClass.getClazzName()).getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("#################################");
        return true;
    }

    @Override
    protected Collection<? extends ComputeJob> split(int gridSize, String arg) throws IgniteException {
        System.out.println("split " + arg);
        return Collections.singletonList(new SecondJob(customClass));
    }
}
