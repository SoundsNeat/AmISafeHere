package edu.csupomona.cs480.util;

import edu.csupomona.cs480.controller.WebController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by bechin on 10/25/16.
 */
public class WebControllerTest {

    private WebController wb = new WebController();

    @Test
    public void testCompare(){
        int comparison = wb.compare("cityA", "cityB");
        Assert.assertTrue(comparison < 2);
        Assert.assertTrue(comparison > -2);
    }

}
