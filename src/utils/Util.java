package utils;

import org.json.JSONObject;
import web.constant;
import domain.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

public class Util {
    public static String decode(String param ){
        Map<String , String> map= new HashMap<String, String>();
        map.put("00000","Ə"); map.put("00001","ə"); map.put("00010","Ü"); map.put("00100","ü");
        map.put("01000","Ö"); map.put("10000","ö"); map.put("10001","I"); map.put("10010","ı");
        map.put("10100","Ğ"); map.put("11000","ğ"); map.put("11001","Ç"); map.put("11010","ç");
        map.put("11100","11100"); map.put("11110","ş");
       /*var array  = [
       *//*00000 -  Ə*//*{"key":"00000","value":"Ə"},
        *//*00001  -ə*//*{"key":"00001","value":"ə"},
        *//*00010  -Ü*//*{"key":"00010","value":"Ü"},
        *//*00100 - ü*//*{"key":"00100","value":"ü"},
        *//*01000 - Ö*//*{"key":"01000","value":"Ö"},
        *//*10000 - ö*//*{"key":"10000","value":"ö"},
        *//*10001 - I*//*{"key":"10001","value":"I"},
        *//*10010 - ı*//*{"key":"10010","value":"ı"},
        *//*10100 - Ğ*//*{"key":"10100","value":"Ğ"},
        *//*11000 - ğ*//*{"key":"11000","value":"ğ"},
        *//*11001 - Ç*//*{"key":"11001","value":"Ç"},
        *//*11010 - ç*//*{"key":"11010","value":"ç"},
        *//*11100 - Ş*//*{"key":"11100","value":"Ş"},
        *//*11110 - ş*//*{"key":"11110","value":"ş"}
        ];*/
        String word = "";
        Set keys = map.keySet();
        String [] sp = param.split("/---/");

        for(int j  = 0; j < sp.length; j++){
            boolean t = false;
            String value = "";
            for (Iterator i = keys.iterator(); i.hasNext();) {
                String key = (String) i.next();
                if (sp[j].equals(key)){
                    t = true;
                    value = map.get(key);
                }
            }
            if (!t){
                word  += sp[j];
            }else{
                word += value;
            }
        }
        return word;
    }

  /*  public static String ArrayToStr(List<UtilId> list , long status , String split_param){
        String returnd_array= "";
        for(UtilId utilId : list){
            if (utilId.getStatus() == status){
                returnd_array += utilId.getNumber() + split_param;
            }
        }
      return returnd_array;
    }*/

    public static BufferedReader strToBuffReader(String str) {
        InputStream is = new ByteArrayInputStream(str.getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br;
    }

    public static Reader strToReader(String str) {
        Reader reader = new StringReader(str);
        return reader;
    }

    public static String buffReaderToStr(BufferedReader reader) throws Exception {

        StringBuilder builder = new StringBuilder();
        String aux = "";

        while ((aux = reader.readLine()) != null) {
            builder.append(aux);
        }

        return builder.toString();
    }

    public static String separateText(String text, int length) {
        String retText = text;
        if (text.length() > length){
            retText = text.replaceFirst(" " , "</br>");
        }
        return retText;
    }


    public static String getLocaleText(HttpServletRequest request, long resId) {
        try{
            usersInfo user=(usersInfo)request.getSession().getAttribute(constant.USER);
            Map<Long , Resource> langMap = ( Map<Long , Resource> ) request.getServletContext().getAttribute(constant.RESOURCES_LOCALE);
            return langMap.get(resId).getLocaleValues().get(user.getCurrLangId());
        }catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static JSONObject getLocaleTexts( HttpServletRequest request ) {
        try{

            JSONObject object = new JSONObject();
            usersInfo user=(usersInfo)request.getSession().getAttribute(constant.USER);
            Map<Long , Resource> langMap = ( Map<Long , Resource> ) request.getServletContext().getAttribute(constant.RESOURCES_LOCALE);
            for (Long key : langMap.keySet()) {
                Resource resource = langMap.get(key);
                object.put(String.valueOf(key), resource.getLocaleValues().get(user.getCurrLangId()));
            }
            return object;
        }catch (Exception ex){
            ex.printStackTrace();
            return new JSONObject();
        }
    }



}
