import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskName;
import org.apache.ignite.compute.ComputeTaskSplitAdapter;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class FirstTask extends ComputeTaskSplitAdapter<String, Boolean> {

    @IgniteInstanceResource
    private Ignite ignite;

    @Override
    protected Collection<? extends ComputeJob> split(int gridSize, String arg) throws IgniteException {
        System.out.println("split " + arg);
        CustomClass customClass = new CustomClass();
        return Collections.singletonList(new FirstJob(customClass));
    }

    @Nullable
    @Override
    public Boolean reduce(List<ComputeJobResult> results) throws IgniteException {

//        results.forEach(result-> );
        return true;
    }
}
