package edu.csupomona.cs480.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;

/**
 * @author Wai Phyo
 */
@RestController
public class CityCrimeController {

    @RequestMapping(value = "/getCityStatistics", method = RequestMethod.POST)
    String getCityStatistics(@RequestParam("location") String location) throws JsonParseException, IOException {
        String[] address = location.split("-");
        String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(jsonString);
        return "{\"city\":\"" + address[0] + "\",\"state\":\""+address[1]+"\"}";
    }
}
