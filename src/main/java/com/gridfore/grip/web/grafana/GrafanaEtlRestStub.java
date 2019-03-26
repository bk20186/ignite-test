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

//    @RequestMapping(value = "/scenario/{scenarioId:.+}/etl/items", method = RequestMethod.GET)
//    public Map getItems(
//            @PathVariable("scenarioId") String scenarioId,
//            @RequestParam("from") Date from,
//            @RequestParam("to") Date to,
//            SpringDataWebProperties.Pageable pageable) {
//        return ;
//    }
//
//    @RequestMapping(value = "/scenario/{scenarioId:.+}/etl/items/{loadId:.+}", method = RequestMethod.GET)
//    public Map getSubsItems(
//            @PathVariable("scenarioId") String scenarioId,
//            @PathVariable("loadId") Long loadId,
//            @RequestParam("from") Date from,
//            @RequestParam("to") Date to,
//            SpringDataWebProperties.Pageable pageable) {
//        return ;
//    }
}
