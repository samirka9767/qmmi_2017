package utils;

import domain.UploadForm;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.olap4j.impl.Base64;
import web.*;

/**
 * Created by vugar on 22.02.2015.
 */
public class ftpUtility {
    FTPClient ftpClient1 = new FTPClient();
    FileOutputStream fos = null;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
    Log logger = LogFactory.getLog(getClass());
    protected static final String AB = "0123456789";
    protected static final String AB1 = "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";


    public String randomCode(int len){
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
    public boolean ftpConnect(FTPClient ftpClient){
        boolean rr =true;
        try {
            Properties prop = new Properties();
            InputStream inputStream = getClass().getResourceAsStream("FTPConn.properties");
            prop.load(inputStream);
            String url = prop.getProperty("ftp.url");
            String user = prop.getProperty("ftp.uname");
            String pass = prop.getProperty("ftp.pass");

            //  ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            int reply;
            ftpClient.connect(url);
//            if(ftpClient.login(user, pass)==false){
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)){
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            rr = ftpClient.login(user, pass);
//            }else{
//                rr=true;
//            }
        } catch (Exception ex){
            ex.printStackTrace();
        }finally{
            return rr;
        }
    }
    public UploadForm ftpUpload(HttpServletRequest request,int tip) throws Exception {
        UploadForm uploadForm= new UploadForm();
        Date dt =new Date();

        try {
            String dir ="";
            String dirYear =formatYear.format(dt)+"/";
            if(tip==2){
               dir = constant.ATT_QMMI+ constant.ATT_MEDIA;
            }
            if(tip==1){
               dir = constant.ATT_QMMI+ constant.ATT_PIC;
            }
            if(tip==3){
               dir = constant.ATT_QMMI+ constant.ATT_LIBRARY;
            }
            if(tip==4){
               dir = constant.ATT_QMMI+ constant.ATT_DOCUMENTS;
            }
            if(tip==5){
               dir = constant.ATT_QMMI+ constant.ATT_QRCODE;
            }
            if(tip==8){
               dir = constant.ATT_QMMI+ constant.ATT_EMPLOYEES;
            }
            if(tip==9){
               dir = constant.ATT_QMMI+ constant.ATT_CARRIERS;
            }


            try {
                ftpConnect(ftpClient1);
                ftpClient1.setControlEncoding("UTF-8");
                ftpClient1.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient1.enterLocalPassiveMode();
                HttpSession ss = request.getSession();
                InputStream input;
                DiskFileItemFactory factory = new DiskFileItemFactory();
                List<FileItem> items = new ServletFileUpload(factory).parseRequest(request);
                ftpClient1.makeDirectory(dir);
                for (FileItem item : items) {//logger.info("item="+item.getName()+"//item.isFormField()="+item.isFormField());
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("UTF-8");
//                    logger.info("1 fieldName="+fieldName+"//fieldValue="+fieldValue);
                        uploadForm.put(fieldName, fieldValue);
                    } else {
                        try {
                            String filename = FilenameUtils.getName(item.getName());
                            if(!"".equals(filename)) {
                                String extension = "";
                                int position = filename.lastIndexOf(".");
                                if(position > 0) {
                                    extension = filename.substring(position);
                                }


                                if("jpeg".equals(extension) || "png".equals(extension) || "jpg".equals(extension)){

                                    String nn = randomCode(6) +"_"+format.format(dt);

                                    input = item.getInputStream();
                                    BufferedImage imBuff = ImageIO.read(input);
                                    Image dimg =imBuff.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                                    // construct the buffered image
                                    BufferedImage bImage  = new BufferedImage(dimg.getWidth(null), dimg.getHeight(null), BufferedImage.TYPE_INT_RGB);

                                    Graphics2D bImageGraphics = bImage.createGraphics();
                                    bImageGraphics.drawImage(dimg, null, null);

                                    RenderedImage rImage  = bImage;
                                    ImageIO.write(rImage,"jpg", os);
                                    InputStream fis = new ByteArrayInputStream(os.toByteArray());

                                    String fname =dir + nn+ extension;

                                    uploadForm.setInp(fis);
                                    Boolean bl = ftpClient1.storeFile(fname, fis);
                                    input.close();
                                    fis.close();
                                    if(bl){
                                        logger.info(" Ftp-e fayl yuklendi !" );
                                    }else{
                                        logger.info(" Ftp-e fayl yuklenmedi !" );
                                    }
                                    String fieldName = item.getFieldName();
                                    String fieldValue = fname;
                                    uploadForm.put(fieldName, fieldValue);
                                }else {

                                    String nn = randomCode(6) +"_"+format.format(dt);

                                    input = item.getInputStream();
                                    String fname =dir + nn+ extension;
                                    uploadForm.setInp(input);
                                    Boolean bl = ftpClient1.storeFile(fname, input);
                                    input.close();
                                    if(bl){
                                        logger.info(" Ftp-e fayl yuklendi !" );
                                    }else{
                                        logger.info(" Ftp-e fayl yuklenmedi !" );
                                    }
                                    String fieldName = item.getFieldName();
                                    String fieldValue = fname;
                                    uploadForm.put(fieldName, fieldValue);


                                }


                            }else {
                                logger.info("Received file + cannot get File" );
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                            throw new Exception(e.getMessage());
                        } finally {

                        }
                    }
                }
                ftpClient1.logout();
                ftpClient1.disconnect();
            } catch (SocketException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                ftpClient1.disconnect();
            } catch (Exception e) {e.printStackTrace();
                logger.info(" IOException   2  "+e.toString());
            }
        }
        return uploadForm;
    }

    public UploadForm ftpUploadDocConvert(HttpServletRequest request,int tip) throws Exception {
        UploadForm uploadForm= new UploadForm();
        Date dt =new Date();

        try {
            String dir ="";
            String dirYear =formatYear.format(dt)+"/";
            if(tip==10) {
               dir = constant.ATT_QMMI+ constant.ATT_HTML;
            }
            try {
                ftpConnect(ftpClient1);
                ftpClient1.setControlEncoding("UTF-8");
                ftpClient1.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient1.enterLocalPassiveMode();
                HttpSession ss = request.getSession();
                InputStream input;
                DiskFileItemFactory factory = new DiskFileItemFactory();
                List<FileItem> items = new ServletFileUpload(factory).parseRequest(request);
                ftpClient1.makeDirectory(dir);
                for (FileItem item : items) {//logger.info("item="+item.getName()+"//item.isFormField()="+item.isFormField());
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("UTF-8");
//                    logger.info("1 fieldName="+fieldName+"//fieldValue="+fieldValue);
                        uploadForm.put(fieldName, fieldValue);
                    } else {
                        try {
                            String filename = FilenameUtils.getName(item.getName());
                            if(!"".equals(filename)) {
                                String nn = randomCode(6) +"_"+format.format(dt);

                                input = item.getInputStream();

                                DocConvert  dc = new DocConvert();
                                File html_file =  dc.DocConvert("23", input);//doc

                                InputStream inputHtml = new FileInputStream(html_file);
                                String filename1 = FilenameUtils.getName(html_file.getName());
                                String fname = dir + nn + filename1;

                                Boolean bl = ftpClient1.storeFile(fname, inputHtml);
                                input.close();
                                inputHtml.close();
                                if(bl){
                                    logger.info(" Ftp-e test fayli yuklendi !" );
                                }else{
                                    logger.info(" Ftp-e test fayli yuklenmedi !" );
                                }

                                String fieldName = "docFilePH";
                                String fieldValue = fname;
                                uploadForm.put(fieldName, fieldValue);

                            }else {
                                logger.info("Received file + cannot get File" );
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                            throw new Exception(e.getMessage());
                        } finally {

                        }
                    }
                }
                ftpClient1.logout();
                ftpClient1.disconnect();
            } catch (SocketException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                ftpClient1.disconnect();
            } catch (Exception e) {e.printStackTrace();
                logger.info(" IOException   2  "+e.toString());
            }
        }
        return uploadForm;
    }

    public File ftpDownload(String remoteFilePath) {
        File retFile = null;
        if (remoteFilePath.equals("") || remoteFilePath.equals("null") || remoteFilePath == null) {
            //File file = new File("s.jpg");
            return null;//new File("/jasper/resources/profile-picture.jpg");

        } else {
            try {
                try {
                    ftpConnect(ftpClient1);
                    ftpClient1.setControlEncoding("UTF-8");
                    ftpClient1.setFileType(FTP.BINARY_FILE_TYPE);
                    ftpClient1.enterLocalPassiveMode();
                    String fileString = remoteFilePath.substring(remoteFilePath.lastIndexOf("."));

                    retFile = File.createTempFile("temp_", RandomStringUtils.randomAlphanumeric(15) + fileString);
                    OutputStream output = new BufferedOutputStream(new FileOutputStream(retFile));
//
                    ftpClient1.retrieveFile(remoteFilePath, output);
                    output.flush();
                    output.close();
                    ftpClient1.logout();
                    ftpClient1.disconnect();
                } catch (SocketException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    ftpClient1.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info(" IOException   2  " + e.toString());
                }
            }
            return retFile;
        }
    }

    public void ftpDownload(String filename, HttpServletResponse response){
        try {
            try {
                ftpConnect(ftpClient1);
                System.out.println("Remote system is ***" + ftpClient1.getSystemName());
                ftpClient1.setControlEncoding("UTF-8");
                ftpClient1.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient1.enterLocalPassiveMode();

                ServletOutputStream output;
                output = response.getOutputStream();
//
                ftpClient1.retrieveFile(filename, output);
                response.flushBuffer();
                output.flush();
                output.close();

                ftpClient1.logout();
                ftpClient1.disconnect();
            } catch (SocketException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ftpClient1.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                logger.info(" IOException   2  "+e.toString());
            }
        }
    }

    public UploadForm ftpUpload(File file,int tip) throws Exception{
        UploadForm uploadForm= new UploadForm();
        Date dt =new Date();
        try {
            String dir ="";/*String dirYear =format2.format(dt)+"/";*/
            if(tip==5){
                dir = constant.ATT_QMMI+constant.ATT_QRCODE;
            }else if(tip==7){
                dir = constant.ATT_QMMI+constant.ATT_PASSPORT;
            }
            try {
                ftpConnect(ftpClient1);
                ftpClient1.setControlEncoding("UTF-8");
                ftpClient1.setFileType(FTP.BINARY_FILE_TYPE);//
                ftpClient1.enterLocalPassiveMode();
                InputStream input = new FileInputStream(file);
                ftpClient1.makeDirectory(dir);
                String filename = FilenameUtils.getName(file.getName());
                String nn = randomCode(6) +"_";
                String fname =dir + nn + filename;

                Boolean bl = ftpClient1.storeFile(fname, input);
                input.close();
                if(bl){
                    logger.info(" Ftp-e test fayli yuklendi !" );
                }else{
                    logger.info(" Ftp-e test fayli yuklenmedi !" );
                }

                String fieldName = "docFilePH";
                String fieldValue = fname;
                uploadForm.put(fieldName, fieldValue);

                ftpClient1.logout();
                ftpClient1.disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                ftpClient1.disconnect();
            } catch (Exception e) {e.printStackTrace();
                logger.info(" IOException   2  "+e.toString());
            }
        }
        return uploadForm;
    }
}
