package edu.csupomona.cs480.controller;

import com.fasterxml.jackson.core.JsonParseException;
import edu.csupomona.cs480.util.CrimeStats.CityStatsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Wai Phyo
 */


@RestController
public class CityCrimeController {
    @Autowired
    private CityStatsManager cityStatsManager;
    /**
     *
     * @param location format: "City-State-Country" NOTE: '_' is replaced with space. Need to replace it back
     * @return JSON
     * @throws JsonParseException
     * @throws IOException
     *
     * Raw JSON format
     * {
     *     "city":"<city name>",
     *     "state":"<state name>",
     *     "result":<true/false>,
     *     "index":<0 to 5>,
     *     "average":<Double>,
     *     "years":[
     *          2014,2013,2012
     *     ],
     *     "category":[
     *          {
     *              "<name>":[number,number,number]
     *          }
     *          <repeat for all crime categories>
     *     ]
     * }
     *
     */
    @RequestMapping(value = "/getCityStatistics", method = RequestMethod.POST)
    String getCityStatistics(@RequestParam("location") String location) throws JsonParseException, IOException {
        String[] address = location.replaceAll("_", " ").split("-");
        if (address.length != 3 && !location.contains("Washington")) {
            throw new RuntimeException("Incorrect address format");
        }
        cityStatsManager.execute(address[0], address[1]);
        System.out.println(cityStatsManager.getCityStats().toJson());
         return cityStatsManager.getCityStats().toJson();
    }
}
