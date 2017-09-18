import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;

import java.util.Optional;
import java.util.Set;

public class ClusterTest {
    public static void main(String[] args) {
        Ignite ignite = Ignition.start("ignite.xml");
        ClusterGroup dataNodes = ignite.cluster().forPredicate(node ->
                Optional.ofNullable(node.<Set<String>>attribute("roles"))
                        .map(roles -> roles.contains("data-node"))
                        .orElse(false));
        ignite.compute(dataNodes).execute(new FirstTask(), "first task");
        ignite.close();
    }
}
