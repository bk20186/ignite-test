import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.jooq.lambda.Unchecked;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class UCPTest {
    private ExecutorService service = Executors.newFixedThreadPool(1000);

    private PoolDataSource pds;

    @Before
    public void setup() throws Exception {
        pds = PoolDataSourceFactory.getPoolDataSource();
        pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
        pds.setURL("jdbc:oracle:thin:@grf-grip-dev02.gridfore.com:1921:ORCLCDB");
        pds.setUser("system");
        pds.setPassword("Oradoc_db1");
        pds.setInitialPoolSize(100);
        pds.setMaxPoolSize(100);
        pds.setMinPoolSize(10);
        pds.setMaxStatements(100);
        pds.setConnectionWaitTimeout(60000);
    }

    @After
    public void teardown() {

    }

    @Test
    public void test() throws Exception {
//        AtomicInteger ai = new AtomicInteger(0);
        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 0; i < 1000; i++) {
            service.execute(Unchecked.runnable(() -> {
//                int num = ai.incrementAndGet();
                Connection connection = pds.getConnection();
//                System.out.println("open connection - " + num);
                PreparedStatement ps = connection.prepareStatement("select 1 from HELP");
                ps.execute();
                Thread.sleep(1000);
                ps.close();
                connection.close();
                connection=null;
//                System.out.println("close connection - " + num);
//                System.out.println("Statistics - " + pds.getStatistics());
//                System.out.println("########################");
            }));
        }
        while (((ThreadPoolExecutor) service).getActiveCount() > 0) {

        }
        sw.stop();
        System.out.println(sw.prettyPrint());
        System.out.println("########### Result #############");
        System.out.println("Statistics - " + pds.getStatistics());
    }
}
