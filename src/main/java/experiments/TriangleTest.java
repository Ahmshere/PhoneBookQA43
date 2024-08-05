package experiments;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleTest {

    @Test
    public void testEquilateralTriangle() {
        Allure.description("Equilateral Triangle test");
        Assert.assertEquals(Triangle.getTriangleType(5, 5, 5), TriangleType.EQUILATERAL); // равносторонний
    }

    @Test // равнобедренный
    public void testIsoscelesTriangle() {
        Allure.description("Isosceles Triangle test");
         Assert.assertEquals(Triangle.getTriangleType(5, 5, 8), TriangleType.ISOSCELES);
    }

    @Test
    public void testScaleneTriangle() {
        Allure.description("Scalene Triangle test");
        Assert.assertEquals(Triangle.getTriangleType(3, 4, 5), TriangleType.SCALENE);
    }

    @Test
    public void testInvalidTriangleNegativeSides() {
        Assert.assertEquals(Triangle.getTriangleType(-1, 2, 3), TriangleType.INVALID);
    }

    @Test
    public void testInvalidTriangleZeroSides() {
        Assert.assertEquals(Triangle.getTriangleType(0, 2, 3), TriangleType.INVALID);
    }

    @Test
    public void testInvalidTriangleSumOfTwoSidesEqualsThird() {
        Assert.assertEquals(Triangle.getTriangleType(1, 2, 3), TriangleType.INVALID);
    }

    @Test
    public void testInvalidTriangleSumOfTwoSidesLessThanThird() {
        Assert.assertEquals(Triangle.getTriangleType(1, 2, 4), TriangleType.INVALID);
    }

}
