import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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

public class HikariCpTest {

    private HikariDataSource ds;

    private ExecutorService service = Executors.newFixedThreadPool(1000);

    @Before
    public void setup() throws Exception {
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl("jdbc:oracle:thin:@grf-grip-dev02.gridfore.com:1921:ORCLCDB");
        cfg.setUsername("system");
        cfg.setPassword("Oradoc_db1");
        cfg.setMinimumIdle(10);
        cfg.setMaximumPoolSize(100);
        cfg.setConnectionTimeout(60000);
        cfg.addDataSourceProperty("cachePrepStmts", true);
        cfg.addDataSourceProperty("prepStmtCacheSize", "250");
        cfg.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(cfg);
    }

    @After
    public void teardown() {
        ds.close();
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
                connection.close();
//                System.out.println("close connection - " + num);
//                System.out.println("busy - " + ds.getHikariPoolMXBean().getActiveConnections());
//                System.out.println("idle - " + ds.getHikariPoolMXBean().getIdleConnections());
//                System.out.println("all - " + ds.getHikariPoolMXBean().getTotalConnections());
//                System.out.println("########################");
            }));
        }
        while (((ThreadPoolExecutor) service).getActiveCount() > 0){

        }
        sw.stop();
        System.out.println(sw.prettyPrint());
        System.out.println("########### Result #############");
        System.out.println("busy - " + ds.getHikariPoolMXBean().getActiveConnections());
        System.out.println("idle - " + ds.getHikariPoolMXBean().getIdleConnections());
        System.out.println("all - " + ds.getHikariPoolMXBean().getTotalConnections());
    }
}
