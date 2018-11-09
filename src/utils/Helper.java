package utils;

import domain.Column;
import domain.Row;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;


public class Helper {

    private HttpServletRequest request;

    public Helper(){

    }

    public Helper(HttpServletRequest request){
        this.request = request;
    }


    public static String toMD5(String pass) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte [] data = pass.getBytes();
        m.update(data,0,data.length);
        BigInteger i = new BigInteger(1,m.digest());
        return String.format("%1$032X", i);
    }



    /*public static String getLocaleText(HttpServletRequest request, long resId, long langId ){
        try{
            //User user=(User)request.getSession().getAttribute(Constants.USER);
            Map<Long , Resource> langMap = ( Map<Long , Resource> ) request.getServletContext().getAttribute(Constants.RESOURCES_LOCALE);
            return langMap.get( resId ).getLocaleValues().get(langId);
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }
    }*/

    /*public long getCurrentLangId(){
        User loggedUser = (User) request.getSession().getAttribute(Constants.USER);
        if ( loggedUser != null ){
            return loggedUser.getCurrentLang();
        }
        Map<Long , String> constants = (Map<Long, String>) request.getServletContext().getAttribute(Constants.APPLICATION_CONSTANTS);
        return Long.parseLong(constants.get(1000L));
    }*/

    public Properties getProperties(String type){
        Properties props = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream( request.getServletContext().getInitParameter(type));
        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static void downloadFile(HttpServletResponse response, File file) throws IOException {
        response.setHeader("Expires", "0");
        response.setHeader("charset", "=UTF-8");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Pragma", "public");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0 ){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    public static void download(HttpServletResponse response, byte [] bytes , String fileName) throws IOException {
        response.setHeader("Expires", "0");
        response.setHeader("charset", "=UTF-8");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Pragma", "public");
        OutputStream out = response.getOutputStream();
        //FileInputStream in = new FileInputStream(file);
        /*byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0 ){
            out.write(buffer, 0, length);
        }*/

        out.write(bytes);
        //in.close();
        out.flush();
        out.close();
    }

    public static void downloadFileTestInline(HttpServletResponse response, File file) throws IOException {
        response.setHeader("Expires", "0");
        response.setHeader("charset", "=UTF-8");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-disposition", "inline; filename=\"" + file.getName() + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Pragma", "public");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0 ){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
    public static void downloadFile(HttpServletResponse response, File file, String responseFileName ) throws IOException {
        response.setHeader("Expires", "0");
        response.setHeader("charset", "=UTF-8");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-disposition", "inline; filename=\"" + responseFileName + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Pragma", "public");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0 ){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    public static void download(HttpServletResponse response, ByteArrayOutputStream outputStream, String responceFileName) throws IOException {
        response.setHeader("Expires", "0");
        response.setHeader("charset", "=UTF-8");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-disposition", "inline; filename=\"" + responceFileName + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Pragma", "public");
        OutputStream out = response.getOutputStream();
        out.write(outputStream.toByteArray());
        out.flush();
    }

    public static String separateText(String text, int length){
        String retText = text;
        if (text.length() > length){
            retText = text.replaceFirst(" " , "</br>");
        }
        return retText;
    }

    /*public File convertFromBase64DataUrl( String base64DataUrl ) throws IOException, WrongFileTypeException {

        Properties properties = getProperties("whiteList");

        String mType = base64DataUrl.substring(base64DataUrl.indexOf("data:")
                                                + "data:".length(), base64DataUrl.indexOf(";base64,"));
        if (properties.getProperty(mType) == null){
            throw new WrongFileTypeException( mType + " file type is not allowed");
        }

        String base64File = base64DataUrl.substring( base64DataUrl.indexOf(";base64,") + ";base64,".length() );
        byte[] bytes = org.apache.commons.net.util.Base64.decodeBase64(base64File);
        File file = File.createTempFile( "ftp_" , properties.getProperty(mType) );
        FileUtils.writeByteArrayToFile(file, bytes);
        return file;
    }*/

    /*public static String convertToBase64( File file ) throws IOException {
        Path path = file.toPath();
        byte[] bytes = Files.readAllBytes(path);
        byte[] eBytes = org.apache.commons.net.util.Base64.encodeBase64(bytes);
        return new String(eBytes);
    }*/

    /*public static String convertToBase64( byte[] bytes ) throws IOException {
        byte[] eBytes = org.apache.commons.net.util.Base64.encodeBase64(bytes);
        return new String(eBytes);
    }*/
    /*public static byte[] convertFromBase64DataUrl( String base64DataUrl ) throws IOException {

        String mType = base64DataUrl.substring( base64DataUrl.indexOf("data:") + "data:".length() , base64DataUrl.indexOf(";base64,") );
        String base64File = base64DataUrl.substring( base64DataUrl.indexOf(";base64,") + ";base64,".length() );
        byte[] bytes = org.apache.commons.net.util.Base64.decodeBase64(base64File);
        //File file = File.createTempFile( "email_attach_", "_" + fileName.substring( fileName.indexOf(".") ) );
        //FileUtils.writeByteArrayToFile(file, bytes);


        return bytes;
    }*/

    public static JSONObject properties2Json(Properties properties){
        JSONObject jsonObject = new JSONObject();
        Enumeration e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            try {
                jsonObject.put(key, properties.getProperty(key));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        return jsonObject;
    }

    public static JSONObject rows2Json(List<Row> params) throws JSONException {
        JSONObject content = new JSONObject();
        JSONArray rows = new JSONArray();
        for ( Row row : params ) {
            JSONObject r = new JSONObject();
            for(Column gp: row.getColumns()){
                r.put(gp.getKey().toLowerCase(), gp.getValue());
            }
            rows.put(r);
        }
        content.put("options", rows );
        return content;
    }



}
