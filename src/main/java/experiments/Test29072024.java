package experiments;

import helpers.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.SecureRandom;
import java.time.LocalDate;

public class Test29072024 {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void generateRandomStringTestPositive(){
       Assert.assertEquals(generateRandomString(10).length(), 12);
    }


    public static void main(String[] args) {

        DayOfWeek today = DayOfWeek.FRIDAY;

       System.out.println("Today is " + today.getName());
        switch (today){
            case SUNDAY:
                break;
            case MONDAY :
                System.out.println("Today is monday");
                break;
            case FRIDAY:
                System.out.println("Today is Friday");
                break;
            default:
                System.out.println("Smthings wrong...");
                break;

        }



      //  System.out.println(generateRandomString(37));

      //  System.out.println("INTEGER "+add(12, 12));
       // System.out.println("DOUBLE "+add(12.5, 12.2));
      //  int[] numbers = {1,2,3,4,6,7,8};
       /* for (int i = 0; i < 10; i++){
            System.out.println("Number "+i);
        }*/

       // dayPicker(1);
       // System.out.println("My \u001B[34mfirst test!");

  /*      byte b1 = 127;
        short s = 32767;
        int i = 76538653;
    boolean t = true;
    char a = 'a';
    char b = 'b';
*/

    }
    /**
     * Generates a random string of the specified length using
     * uppercase letters and digits.
     *
     * @param length the length of the random string to generate.
     * Must be between 1 and 1000 inclusive.
     * @return a random string of the specified length.
     * @throws IllegalArgumentException if length is less than 1
     * or greater than 1000.
     */
    public static String generateRandomString(int length){
        if(length <= 0 || length > 1000){
            throw new IllegalArgumentException("Length must be between 1 and 1000 inclusive.");
        }
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomString = new StringBuilder(length);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++){
            int randomIndex = random.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }
        return  randomString.toString();
    }
    public static int add(int a, int b){
        return a+b;
    }
    public static double add(double a, double b ){
        return a+b;
    }

// My comment

    /**
     *
     * @param a description
     * @param b description
     */
    public static void theMethodGeneratesAString(int a, int b){
        int res = a+b;
    }

    public static  void dayPicker(int day){

        switch (day){
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Invalid day...");
                break;
        }
    }



}
