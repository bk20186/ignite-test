import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobResult;
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
        ClusterGroup dataNodes = ignite.cluster().forPredicate(node ->
                Optional.ofNullable(node.<Set<String>>attribute("roles"))
                        .map(roles -> roles.contains("data-node"))
                        .orElse(false));
        results.forEach(result-> ignite.compute(dataNodes).execute(new SecondTask(result.getData()), "second task"));
        return true;
    }
}
