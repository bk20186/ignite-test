package com.gridfore.grip.web.grafana;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/grafana")
public class GrafanaGoRestStub {
    private final static Gson gson = new Gson();

    @RequestMapping(value = "/scenario", method = RequestMethod.GET)
    public Map getScenarios(final @RequestParam("searchTerm") String searchTerm,
                            final SpringDataWebProperties.Pageable pageable) {
        return gson.fromJson("{\n" +
                                     "  \"scenarios\" : [ {\n" +
                                     "    \"name\" : \"SCENARIO.ODS_DM_CUST_DUE_TRAN_Load\",\n" +
                                     "    \"id\" : 1623,\n" +
                                     "    \"description\" : null,\n" +
                                     "    \"enabled\" : true,\n" +
                                     "    \"schedule\" : {\n" +
                                     "      \"cron\" : \"* * * * * *\"\n" +
                                     "    },\n" +
                                     "    \"businessPeriodDirection\" : \"ASC\",\n" +
                                     "    \"startDate\" : \"2019-03-03T08:00:00+0300\",\n" +
                                     "    \"period\" : 1440,\n" +
                                     "    \"periodUnit\" : \"MINUTE\",\n" +
                                     "    \"offset\" : 0,\n" +
                                     "    \"subscriptions\" : [ ],\n" +
                                     "    \"parameters\" : {\n" +
                                     "      \"MAX_FAILED_COUNT\" : \"1\",\n" +
                                     "      \"EXECUTION_DELAY_UNIT\" : \"SECONDS\",\n" +
                                     "      \"EXECUTION_DELAY_COUNT\" : \"30\"\n" +
                                     "    }\n," +
                                     "    \"createdAt\" : \"2019-03-04T13:47:40+0300\",\n" +
                                     "    \"updatedAt\" : null\n" +
                                     "  }],\n" +
                                     "  \"pages\" : 1\n" +
                                     "}", Map.class);

    }


    @RequestMapping(value = "/scenario/{id:.+}/interval", method = RequestMethod.GET)
    public Map getScenarioIntervals(final @PathVariable String id,
                                    final @RequestParam(value = "from") Date from,
                                    final @RequestParam(value = "to") Date to,
                                    final SpringDataWebProperties.Pageable pageable) {
        return gson.fromJson("{\n" +
                                     "  \"scenarioIntervals\" : [ \n" +
                                     "  {\n" +
                                     "    \"id\" : 1629,\n" +
                                     "    \"periodFrom\" : \"2019-03-07T08:00:00+0300\",\n" +
                                     "    \"periodTo\" : \"2019-03-08T08:00:00+0300\",\n" +
                                     "  \"failedAttemptCount\" : 0,\n" +
                                     "    \"status\" : \"OPEN\"\n" +
                                     "  },\n" +
                                     "  {\n" +
                                     "    \"id\" : 1628,\n" +
                                     "    \"periodFrom\" : \"2019-03-06T08:00:00+0300\",\n" +
                                     "    \"periodTo\" : \"2019-03-07T08:00:00+0300\",\n" +
                                     "  \"failedAttemptCount\" : 1,\n" +
                                     "    \"status\" : \"PARTIALLY_COMPLETED\"\n" +
                                     "  },\n" +
                                     "  {\n" +
                                     "    \"id\" : 1627,\n" +
                                     "    \"periodFrom\" : \"2019-03-05T08:00:00+0300\",\n" +
                                     "    \"periodTo\" : \"2019-03-06T08:00:00+0300\",\n" +
                                     "  \"failedAttemptCount\" : 2,\n" +
                                     "    \"status\" : \"FAILED\"\n" +
                                     "  },\n" +
                                     "  {\n" +
                                     "    \"id\" : 1626,\n" +
                                     "    \"periodFrom\" : \"2019-03-04T08:00:00+0300\",\n" +
                                     "    \"periodTo\" : \"2019-03-05T08:00:00+0300\",\n" +
                                     "  \"failedAttemptCount\" : 3,\n" +
                                     "    \"status\" : \"RUNNING\"\n" +
                                     "  },\n" +
                                     "  {\n" +
                                     "    \"id\" : 1625,\n" +
                                     "    \"periodFrom\" : \"2019-03-03T08:00:00+0300\",\n" +
                                     "    \"periodTo\" : \"2019-03-04T08:00:00+0300\",\n" +
                                     "  \"failedAttemptCount\" : 4,\n" +
                                     "    \"status\" : \"COMPLETED\"\n" +
                                     "  }\n" +
                                     "  ],\n" +
                                     "  \"pages\" : 1\n" +
                                     "}", Map.class);
    }

    @RequestMapping(value = "/scenario/enable", method = RequestMethod.POST)
    public Set<String> enableScenarios(final @RequestBody List<String> scenarioIds) {
        return new HashSet<>();
    }

    @RequestMapping(value = "/scenario/disable", method = RequestMethod.POST)
    public Set<String> disableScenarios(final @RequestBody List<String> scenarioIds) {
        return new HashSet<>();
    }


    @RequestMapping(value = "/scenario/{scenarioId:.+}/interval/{intervalId:.+}/rollback", method = RequestMethod.POST)
    public String rollbackScenario(final @PathVariable("scenarioId") String scenarioId,
                                   final @PathVariable("intervalId") Long intervalId) {
        return "success";
    }

    @RequestMapping(value = "/scenario/{scenarioId:.+}/interval/{intervalId:.+}/operation/{operationId:.+}/rollback", method = RequestMethod.POST)
    public String rollbackOperationRecursive(final @PathVariable("scenarioId") String scenarioId,
                                             final @PathVariable("operationId") String operationId,
                                             final @PathVariable("intervalId") Long intervalId,
                                             final @RequestParam(value = "recursiveRemove", defaultValue = "true") boolean recursiveRemove) {
        return "success";
    }

    @RequestMapping(value = "/scenario/{scenarioId:.+}/interval/{intervalId:.+}/operation/{operationId:.+}/cancel", method = RequestMethod.POST)
    public String cancelOperation(final @PathVariable("scenarioId") String scenarioId,
                                             final @PathVariable("operationId") String operationId,
                                             final @PathVariable("intervalId") Long intervalId) {
        return "success";
    }

    @RequestMapping(value = "/interval/{intervalId:.+}", method = RequestMethod.GET)
    public List getOperationIntervals(final @PathVariable("intervalId") Long intervalId) {
        return gson.fromJson("[ {\n" +
                                     "  \"name\" : \"SCENARIO.ODS_DM_CUST_DUE_TRAN_Load.load DM_CUST_LOAN_DUE\",\n" +
                                     "  \"task\" : \"com.gridfore.grip.realtime-load:SCENARIO.ODS_DM_CUST_DUE_TRAN_Load:4.0.1-SNAPSHOT:SCENARIO.ODS_DM_CUST_DUE_TRAN_Load.load DM_CUST_LOAN_DUE_ExecutionTask\",\n" +
                                     "  \"subscriptions\" : { },\n" +
                                     "  \"inputParams\" : {\n" +
                                     "    \"LOAD_ID\" : \"1625\",\n" +
                                     "    \"BUSINESS_PERIOD_START\" : \"2019-03-03T08:00:00.000+03:00\",\n" +
                                     "    \"SCENARIO_NAME\" : \"SCENARIO.ODS_DM_CUST_DUE_TRAN_Load\",\n" +
                                     "    \"BUSINESS_PERIOD\" : \"MINUTE\",\n" +
                                     "    \"OPERATION_ID\" : \"SCENARIO.ODS_DM_CUST_DUE_TRAN_Load.load DM_CUST_LOAN_DUE\",\n" +
                                     "    \"BUSINESS_DIRECTION\" : \"ASC\",\n" +
                                     "    \"BUSINESS_PERIOD_END\" : \"2019-03-04T08:00:00.000+03:00\"\n" +
                                     "  },\n" +
                                     "  \"outputParams\" : { },\n" +
                                     "  \"created\" : \"2019-03-04T13:47:41+0300\",\n" +
                                     "  \"updated\" : null,\n" +
                                     "  \"optional\" : false,\n" +
                                     "  \"status\" : \"COMPLETED\"\n" +
                                     "} ]", List.class);
    }

    @RequestMapping(value = "/scenario/{scenarioId:.+}/parameters", method = RequestMethod.POST)
    public String setScenarioParams(final @PathVariable("scenarioId") String scenarioId,
                                    final @RequestBody Map<String, String> parameters) {
        return "success";
    }
}