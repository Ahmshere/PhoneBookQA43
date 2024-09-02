package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdExtractor {

    public static void main(String[] args) {
        System.out.println("My id: "+getId("Contact was added! ID: da34c790-f91c-425e-a8d2-1463ccaeb07b"));
    }

    public static String getId(String str){
        Pattern pattern = Pattern.compile("ID: (\\S+)");
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()){
            return matcher.group(1);
        }else {
            return null;
        }
    }


}
