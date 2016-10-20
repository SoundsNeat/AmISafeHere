package edu.csupomona.cs480.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by william on 10/20/16.
 */
public class MathHelperTest {
    MathHelper mathHelper;
    @Before
    public void Setup() {
        mathHelper = new MathHelper();
    }
    @Test
    public void getAverage() {
        double[] arr = {1.0, 2.0, 3.0,  4.0};
        Assert.assertEquals(mathHelper.getAverage(arr), 2.5, 0.0001);
    }
}
