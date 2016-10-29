package edu.csupomona.cs480.util;

import edu.csupomona.cs480.controller.WebController;
import org.junit.Assert;
import org.junit.Test;
/**
 * @author Ben Chin
 */
public class WebControllerTest {

    private WebController wb = new WebController();

    @Test
    public void testCompare(){
        wb.initRandom();
        int comparison = wb.compare("cityA", "cityB");
        Assert.assertTrue(comparison < 2);
        Assert.assertTrue(comparison > -2);
    }

}
