package gaidadym.javaForTesters;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Task3 {
    @Test
    public void testDistance() {
        Point p1= new Point();
        Point p2 = new Point();
        p1.x = 5;
        p1.y = 4;
        p2.x = 10;
        p2.y = 11;
        Assert.assertEquals(p1.distance(p2),8.602325267042627);
        p1.x = 0;
        Assert.assertEquals(p1.distance(p2),12.206555615733702);
        p2.y = 4;
        Assert.assertEquals(p1.distance(p2),10.0);

    }

}
