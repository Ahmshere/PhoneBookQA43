package experiments;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleTest {

    @Test
    public void ISOSCELESTriangleTestPositive1(){
        Assert.assertEquals
                (Triangle.getTriangleType(5,5,5),
                        TriangleType.ISOSCELES);
    }
    @Test
    public void trangleTestPositive2(){
        Triangle.getTriangleType(0,0,0);
    }
    @Test
    public void trangleTestPositive3(){
        Triangle.getTriangleType(-0,0,0);
    }
    @Test
    public void trangleTestPositive4(){
        Triangle.getTriangleType(1,1,5);
    }
}
