package com.gridfore.grip.web.grafana;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/grafana")
public class GrafanaEtlRestStub {
    private final static Gson gson = new Gson();

    @RequestMapping(value = "/scenario/{scenarioId:.+}/etl/items", method = RequestMethod.GET)
    public String getItems(
            @PathVariable("scenarioId") String scenarioId,
            @RequestParam(value = "from", required = false) Date from,
            @RequestParam(value = "to", required = false) Date to,
            SpringDataWebProperties.Pageable pageable) {
        return "{\n" +
                "  \"items\" : [ {\n" +
                "    \"load\" : 240,\n" +
                "    \"name\" : \"SCENARIO.ADDITIVE_ORACLE_DIM_HIVE_CACHED.additive_load_oracle_dim\",\n" +
                "    \"type\" : \"JDBC\",\n" +
                "    \"status\" : \"SUCCESS\",\n" +
                "    \"periodFrom\" : \"2019-04-22T13:28:47+0300\",\n" +
                "    \"periodTo\" : \"2019-04-22T13:29:19+0300\",\n" +
                "    \"extracted\" : 0,\n" +
                "    \"processed\" : 10000,\n" +
                "    \"position\" : 0,\n" +
                "    \"path\" : null,\n" +
                "    \"hash\" : null,\n" +
                "    \"aggregate\" : true,\n" +
                "    \"extractMode\" : \"INTERVAL\",\n" +
                "    \"additive\" : false,\n" +
                "    \"snapshot\" : false,\n" +
                "    \"persisted\" : true,\n" +
                "    \"created\" : \"2019-04-22T13:40:43+0300\",\n" +
                "    \"successAttempt\" : \"2019-04-22T13:41:08+0300\",\n" +
                "    \"failedAttempt\" : null,\n" +
                "    \"wrongLine\" : null\n" +
                "  }],\n" +
                "  \"pages\" : 1\n" +
                "}";
    }

    @RequestMapping(value = "/scenario/{scenarioId:.+}/etl/items/{loadId:.+}", method = RequestMethod.GET)
    public String getSubsItems(
            @PathVariable("scenarioId") String scenarioId,
            @PathVariable("loadId") Long loadId,
            @RequestParam(value = "from", required = false) Date from,
            @RequestParam(value = "to", required = false) Date to,
            SpringDataWebProperties.Pageable pageable) {
        return "{\n" +
                "  \"items\" : [ {\n" +
                "    \"load\" : 198,\n" +
                "    \"name\" : \"SCENARIO.ADDITIVE_ORACLE_DIM_HIVE_CACHED.additive_load_oracle_dim.0\",\n" +
                "    \"type\" : \"JDBC\",\n" +
                "    \"status\" : \"SUCCESS\",\n" +
                "    \"periodFrom\" : \"2019-04-22T13:23:39+0300\",\n" +
                "    \"periodTo\" : \"2019-04-22T13:23:39+0300\",\n" +
                "    \"extracted\" : 0,\n" +
                "    \"processed\" : 10000,\n" +
                "    \"position\" : 0,\n" +
                "    \"path\" : null,\n" +
                "    \"hash\" : null,\n" +
                "    \"aggregate\" : false,\n" +
                "    \"extractMode\" : \"INTERVAL\",\n" +
                "    \"additive\" : false,\n" +
                "    \"snapshot\" : false,\n" +
                "    \"persisted\" : true,\n" +
                "    \"created\" : \"2019-04-22T13:27:19+0300\",\n" +
                "    \"successAttempt\" : \"2019-04-22T13:27:51+0300\",\n" +
                "    \"failedAttempt\" : null,\n" +
                "    \"wrongLine\" : null\n" +
                "  } ],\n" +
                "  \"pages\" : 1\n" +
                "}";
    }
}
