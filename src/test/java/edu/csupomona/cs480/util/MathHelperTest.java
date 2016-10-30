package edu.csupomona.cs480.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by william on 10/20/16
 * Additional tests by Connor
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

    @Test
    public void testPercentIncrease1() {
        double d = mathHelper.percentIncrease(100, 70);
        Assert.assertEquals(-30, d, .002);
    }

    @Test
    public void testPercentIncrease2() {
        double d = mathHelper.percentIncrease(1000, 800);
        Assert.assertEquals(-20, d, .002);
    }

    @Test
    public void testPercentIncrease3() {
        double d = mathHelper.percentIncrease(800, 1000);
        Assert.assertEquals(25, d, .002);
    }

    @Test
    public void testPercentIncrease4() {
        double d = mathHelper.percentIncrease(2, 71);
        Assert.assertEquals(3450, d, .002);
    }

    @Test
    public void testPercentIncrease5() {
        double d = mathHelper.percentIncrease(71, 2);
        Assert.assertEquals(-97.1831, d, .002);
    }
}
