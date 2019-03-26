package com.gridfore.grip.web.grafana;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/grafana")
public class GrafanaCacheMetricsRestStub {
    @RequestMapping(value = "metrics/caches", method = RequestMethod.GET)
    public String getCaches() {
        return "[ {\n" +
                "  \"name\" : \"dq_service_example\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 2048\n" +
                "}, {\n" +
                "  \"name\" : \"ODS_DM.CUST_LOAD_DUE\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 4096\n" +
                "}, {\n" +
                "  \"name\" : \"dq_service_info\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 2048\n" +
                "}, {\n" +
                "  \"name\" : \"ODS_DM.SALES_LLC_REG\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 4096\n" +
                "}, {\n" +
                "  \"name\" : \"ODS_TECH.MAP_ACC2CUST\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 4096\n" +
                "}, {\n" +
                "  \"name\" : \"ODS_TECH.TREFERENCETERMINAL\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 4096\n" +
                "}, {\n" +
                "  \"name\" : \"sequence\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 2048\n" +
                "}, {\n" +
                "  \"name\" : \"ODS_DM.SALES_ENT_REG\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 4096\n" +
                "}, {\n" +
                "  \"name\" : \"ODS_DM.CUST_DUE_TRAN\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 4096\n" +
                "}, {\n" +
                "  \"name\" : \"ODS_TECH.REF_TLG_SPLIT\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 2048\n" +
                "}, {\n" +
                "  \"name\" : \"ODS_TECH.MAP_PAN2CUST\",\n" +
                "  \"cacheSize\" : 0,\n" +
                "  \"heapEntriesCount\" : 0,\n" +
                "  \"totalPartitionsCount\" : 4096\n" +
                "} ]";
    }

    @RequestMapping(value = "control/cache/{cacheName:.+}/rebalance", method = RequestMethod.POST)
    public String rebalanceCache(@PathVariable("cacheName") final String cacheName) {
        return "success";
    }

    @RequestMapping(value = "metrics/cache/{cacheName:.+}/tx")
    public String getCacheTxMetrics(@PathVariable("cacheName") final String cacheName) {
        return "{\n" +
                "  \"cacheTxCommits\" : 10,\n" +
                "  \"averageTxCommitTime\" : 11.1,\n" +
                "  \"cacheTxRollbacks\" : 12,\n" +
                "  \"averageTxRollbackTime\" : 12.1,\n" +
                "  \"txCommitQueueSize\" : 13,\n" +
                "  \"txPrepareQueueSize\" : 14\n" +
                "}";
    }

    @RequestMapping(value = "metrics/cache/{cacheName:.+}/operations")
    public String getCacheOperationsMetrics(@PathVariable("cacheName") final String cacheName) {
        return "{\n" +
                "  \"cacheGets\" : 10,\n" +
                "  \"averageGetTime\" : 10.0,\n" +
                "  \"cachePuts\" : 7,\n" +
                "  \"averagePutTime\" : 12941.261,\n" +
                "  \"cacheRemovals\" : 2,\n" +
                "  \"averageRemoveTime\" : 2.0\n" +
                "}";
    }

    @RequestMapping(value = "metrics/cache/{cacheName:.+}/rebalance")
    public String getCacheRebalanceMetrics(@PathVariable("cacheName") final String cacheName) {
        return "";
    }
}
