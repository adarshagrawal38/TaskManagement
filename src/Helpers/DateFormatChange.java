package Helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatChange {

    public static String changeForMatTo_DD_MM_YY(String date) {
        String regex = "(\\d{4})-(\\d{2})-(\\d{2})";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(date);
        if (m.matches()) {
            String s = m.group(3) + "-" + m.group(2) + "-" + m.group(1).substring(2);
            //System.out.println("Date: " + s);
            return s;
        }
        return "";
    }

    public static String changeForMatTo_MM_DD_YYYY(String date) {
        String regex = "(\\d{2})-(\\d{2})-(\\d{2})";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(date);
        if (m.matches()) {
            String s = m.group(2) + "/" + m.group(1) + "/20" + m.group(3);
            //System.out.println("Date: " + s);
            return s;
        }

        return "";
    }

    public static String changeForMatTo_YYYY_MM_DD(String date) {


        String array[] = date.split("/");
        //System.out.println("Date: " +date);

        String newData = "20"+array[2] + "-" + array[0] + "-" + array[1];
        return newData;
    }


}
