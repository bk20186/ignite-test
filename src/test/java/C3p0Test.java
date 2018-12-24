import com.mchange.v2.c3p0.ComboPooledDataSource;
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

public class C3p0Test {

    private ComboPooledDataSource cpds;

    private ExecutorService service = Executors.newFixedThreadPool(1000);

    @Before
    public void setup() throws Exception {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("oracle.jdbc.driver.OracleDriver");
        cpds.setJdbcUrl("jdbc:oracle:thin:@grf-grip-dev02.gridfore.com:1921:ORCLCDB");
        cpds.setUser("system");
        cpds.setPassword("Oradoc_db1");
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(100);
        cpds.setMaxIdleTime(1);
        cpds.setMaxStatements(100);
    }

    @After
    public void teardown() {
        cpds.close();
    }

    @Test
    public void test() throws Exception {
//        AtomicInteger ai = new AtomicInteger(0);
        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 0; i < 1000; i++) {
            service.execute(Unchecked.runnable(() -> {
//                int num = ai.incrementAndGet();
                Connection connection = cpds.getConnection();
//                System.out.println("open connection - " + num);
                PreparedStatement ps = connection.prepareStatement("select 1 from HELP");
                ps.execute();
                Thread.sleep(1000);
                ps.close();
                connection.close();
//                System.out.println("close connection - " + num);
//                System.out.println("busy - " + cpds.getNumBusyConnectionsAllUsers());
//                System.out.println("idle - " + cpds.getNumIdleConnectionsAllUsers());
//                System.out.println("all - " + cpds.getNumConnectionsAllUsers());
//                System.out.println("########################");
            }));
        }
        while (((ThreadPoolExecutor) service).getActiveCount() > 0){

        }
        sw.stop();
        System.out.println(sw.prettyPrint());
        System.out.println("########### Result #############");
        System.out.println("busy - " + cpds.getNumBusyConnectionsAllUsers());
        System.out.println("idle - " + cpds.getNumIdleConnectionsAllUsers());
        System.out.println("all - " + cpds.getNumConnectionsAllUsers());
    }
}
