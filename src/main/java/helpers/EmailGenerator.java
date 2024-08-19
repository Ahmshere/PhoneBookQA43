package helpers;

public class EmailGenerator {
    // TASK
    public enum EmailType{
        VALID,
        WITHOUT_AT,
        WITHOUT_USERNAME,
        WITHOUT_DOMAIN, WITHOUT_DOT,
        WITH_IVALID_CHAR
    }
    public static void main(String[] args) {

        //System.out.println(generateEmail(10,5,3));
        System.out.println(randomNumberAndChar());
    }

    public static String generateEmail(int a, int b , int c){
        if (a<=0 || b<=0 || c<=0){
            throw new IllegalArgumentException("Length must be positive!");
        }
        StringBuilder email = new StringBuilder();
        for (int i=0; i<a;i++){
            email.append(randomChar());
        }
        email.append("@");
        for (int i=0; i<b;i++){
            email.append(randomChar());
        }
        email.append(".");
        for (int i=0; i<c;i++){
            email.append(randomChar());
        }
        return email.toString();
    }

    private static char randomInvalidChar(){
        String invalidChars = "';,?!@#$%^&*()~\"\\";
        return invalidChars.charAt((int)(Math.random()*invalidChars.length()));
    }
    public static  String generateEmailWithInvalidChar(int a, int b , int c){
        StringBuilder email = new StringBuilder();
        for (int i=0; i<a;i++){
            email.append(randomInvalidChar());
        }
        email.append("@");
        for (int i=0; i<b;i++){
            email.append(randomChar());
        }
        email.append(".");
        for (int i=0; i<c;i++){
            email.append(randomChar());
        }
        return email.toString();
    }

    private static char randomChar(){
        return (char) ('a'+Math.random()*('z'-'a'));

    }
    private static char randomNumberAndChar(){
        int rand = (int)(Math.random()*3);
        switch (rand){
            case 0: return (char) ('0'+Math.random()*10);
            case 1: return (char) ('A'+Math.random()*25);
           case 2:
            default: return (char) ('a'+Math.random()*25);
        }
    }







}
