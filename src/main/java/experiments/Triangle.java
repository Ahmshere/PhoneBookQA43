package experiments;

import io.qameta.allure.Allure;

public class Triangle {

    public static TriangleType getTriangleType(int a, int b, int c){
        Allure.step("STEP 1");
        if (a <= 0 || b <= 0 || c <= 0) {
            return TriangleType.INVALID;
        }
        if (a == b && b == c) {
            return TriangleType.EQUILATERAL; //Равносторонний
        }
        if (a == b || b == c) {
            return TriangleType.ISOSCELES; // Равнобедренный
        }
        if (a + b > c && b + c > a && c + a > b) {
            return TriangleType.SCALENE; // Разносторонний
        }
        return TriangleType.INVALID;

    }

    public static TriangleType getTriangleTypeFixed(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || b + c <= a || c + a <= b) {
            return TriangleType.INVALID;
        }
        if (a == b && b == c) {
            return TriangleType.EQUILATERAL; //Равносторонний
        }
        if (a == b || b == c || c == a) {
            return TriangleType.ISOSCELES; // Равнобедренный
        }
        return TriangleType.SCALENE; // Разносторонний
    }
}
