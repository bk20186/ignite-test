import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.jooq.lambda.Unchecked;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Dbcp2Test {

    private ExecutorService service = Executors.newFixedThreadPool(1000);

    private DataSource ds;

    private GenericObjectPool<PoolableConnection> pool;

    @Before
    public void setup() throws Exception {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "system");
        connectionProps.put("password", "Oradoc_db1");
        ConnectionFactory connectionFactory =
                new DriverManagerConnectionFactory("jdbc:oracle:thin:@grf-grip-dev02.gridfore.com:1921:ORCLCDB", connectionProps);
        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);
        poolableConnectionFactory.setMaxOpenPrepatedStatements(100);
        GenericObjectPoolConfig cfg = new GenericObjectPoolConfig();
        cfg.setMaxTotal(100);
        cfg.setMaxIdle(20);
        cfg.setMinIdle(10);

        pool = new GenericObjectPool<>(poolableConnectionFactory,cfg);
        poolableConnectionFactory.setPool(pool);
        ds = new PoolingDataSource<>(pool);
    }

    @After
    public void teardown() {
        pool.close();
    }

    @Test
    public void test() throws Exception {
//        AtomicInteger ai = new AtomicInteger(0);
        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 0; i < 1000; i++) {
            service.execute(Unchecked.runnable(() -> {
//                int num = ai.incrementAndGet();
                Connection connection = ds.getConnection();
//                System.out.println("open connection - " + num);
                PreparedStatement ps = connection.prepareStatement("select 1 from HELP");
                ps.execute();
                Thread.sleep(1000);
                ps.close();
                if (!connection.isClosed()) connection.close();
//                System.out.println("close connection - " + num);
//                System.out.println("active - " + pool.getNumActive());
//                System.out.println("idle - " + pool.getNumIdle());
//                System.out.println("waiters - " + pool.getNumWaiters());
//                System.out.println("########################");
            }));
        }
        while (((ThreadPoolExecutor) service).getActiveCount() > 0){

        }
        sw.stop();
        System.out.println(sw.prettyPrint());
        System.out.println("########### Result #############");
        System.out.println("active - " + pool.getNumActive());
        System.out.println("idle - " + pool.getNumIdle());
        System.out.println("waiters - " + pool.getNumWaiters());
    }
}
