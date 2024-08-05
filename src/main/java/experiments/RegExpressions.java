package experiments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpressions {

    public static void main(String[] args) {

        String text = "You can reach me out at +972531111111 or +97253 2222222 Have a good one!";
        String []regexp = {"\\+972\\d{9}", "\\+972\\d{2} \\d{7}"};

        for (String reg : regexp){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            System.out.println("The number is: "+matcher.group());
        }}


        /*String str = "khgdiytd YTS iy56 ytd abcd uitf69 0s86ogabcd986g abcd98";
        Pattern pattern = Pattern.compile("abcd");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
*/
    }
}
