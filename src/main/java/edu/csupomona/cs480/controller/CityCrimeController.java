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
        if (address.length != 3) {
            throw new RuntimeException("Incorrect format Type");
        }
        System.out.println(address[0] + address[1]);
        cityStatsManager.execute(address[0], address[1]);
        System.out.println(cityStatsManager.getCityStats().toJson());
         //return crimeStatisticsApi(address[0], address[1]);
        String result = "{"
                + "\"city\":\"" + address[0] + "\","
                + "\"state\":\"" + address[1] + "\","
                + "\"result\":true,"
                + "\"index\":3,"
                + "\"average\":2015.75,"
                + "\"years\":[2014,2013,2012]"
                + "}";
        return result;
        /*
        return "{\"city\":\"Pomona\",\"state\":\"California\",\"result\":true,\"index\":3,\"average\":2015.75,"
                + "\"years\":[2014,2013,2012],"
                + "\"category\":["
                + "{\"numMurders\":[17,29,18]}"
                + "{\"numRapes\":[17,29,18]}"
                + "{\"numRobberies\":[17,29,18]}"
                + "{\"numAssaults\":[17,29,18]}"
                + "{\"numAutoThefts\":[17,29,18]}"
                + "{\"numArsons\":[17,29,18]}"
                + "{\"murderStats\":[17,29,18]}"
                + "]}";
                */
    }
}
