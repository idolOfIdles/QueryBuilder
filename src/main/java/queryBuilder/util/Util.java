package main.java.queryBuilder.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by safayat on 11/18/18.
 */
public class Util {

    public static DateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static char upper(char c){
        return  c >= 'a' && c <= 'z' ? (char)(c - ( 'a'-'A')) : c;
    }

    public static char lower(char c){
        return  c >= 'A' && c <= 'Z' ? (char)( c + ( 'a'-'A')) : c;
    }

    public static String tableName(Class clazz){
        char[] chars = clazz.getSimpleName().toCharArray();
        chars[0] = lower(chars[0]);
        return new String(chars);
    }

    public static String toMysqlString(Object ob) {

        if(ob instanceof Date){
            return toQuote(mysqlDateFormat.format((Date)ob));
        }

        if(ob == null){
            return "NULL";
        }

        return toQuote(ob.toString());
    }

    public static String toQuote(String str) {
        return "\"" + str + "\"";
    }



}
