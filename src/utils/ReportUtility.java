package utils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

/*import org.apache.log4j.Logger;*/

public class ReportUtility {

    /*protected static Logger log = Logger.getLogger("ReportUtility");*/
    private String jasperFilePath;

    public ReportUtility(String jasperFilePath){
        this.jasperFilePath = jasperFilePath;
    }
    public JasperPrint getReport( String jasperFilePath, HashMap jasperParameter ) throws Exception {
        System.setProperty("java.awt.headless", "true");
        dbFunction dbFunctio =new dbFunction();
        Connection connection = dbFunctio.connectDB();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jasper/" + jasperFilePath );
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
        return JasperFillManager.fillReport(jasperReport, jasperParameter , connection);
        //return null;
    }
    public JasperPrint getReport( String jasperFilePath, HashMap jasperParameter, JRDataSource dataSource ) throws Exception {
        System.setProperty("java.awt.headless", "true");
        //Connection connection = DBUtility.getConnection();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jasper/" + jasperFilePath);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
        return JasperFillManager.fillReport(jasperReport, jasperParameter, dataSource);
        /*return null;*/
    }
    public File getPdfReport( HashMap jasperParameter ) throws Exception {
        JasperPrint jasperPrint = getReport( jasperFilePath, jasperParameter);
        File destFile = File.createTempFile("report_", ".pdf");

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));

        exporter.exportReport();
        return destFile;
    }
    public File getExcelReport( HashMap jasperParameter ) throws Exception {
        JRXlsxExporter exporter = new JRXlsxExporter();
        JasperPrint jasperPrint = getReport(jasperFilePath, jasperParameter);
        File destFile = File.createTempFile("report_", ".xlsx");
        exporter.setExporterInput( new SimpleExporterInput(jasperPrint) );
        exporter.setExporterOutput( new SimpleOutputStreamExporterOutput(destFile) );
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(false);
        exporter.setConfiguration(configuration);

        exporter.exportReport();

        return destFile;
    }
    public File getWordReport(HashMap jasperParameter) throws Exception {
        JasperPrint jasperPrint = getReport(jasperFilePath, jasperParameter);

        File destFile = File.createTempFile("report_", ".docx");

        JRDocxExporter exporter = new JRDocxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));

        exporter.exportReport();

        return destFile;
    }
    public File getPptReport(HashMap jasperParameter) throws Exception {
        JasperPrint jasperPrint = getReport(jasperFilePath, jasperParameter);

        File destFile = File.createTempFile("report_", ".pptx");

        JRPptxExporter exporter = new JRPptxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));

        exporter.exportReport();

        return destFile;
    }
    public File getPdfReport( HashMap jasperParameter,JRDataSource dataSource ) throws Exception {
        JasperPrint jasperPrint = getReport( jasperFilePath, jasperParameter,dataSource);
        File destFile = File.createTempFile("report_", ".pdf");
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
        exporter.exportReport();
        return destFile;
    }


    public byte[] getPdfReport1( HashMap jasperParameter,JRDataSource dataSource ) throws Exception {
        JasperPrint jasperPrint = getReport( jasperFilePath, jasperParameter,dataSource);
        File destFile = File.createTempFile("report_", ".pdf");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        outputStream.close();
        return outputStream.toByteArray();
    }

    public byte [] getHtmlReport1(HashMap jasperParameter,JRDataSource dataSource ) throws Exception {
        JasperPrint jasperPrint = getReport(jasperFilePath, jasperParameter, dataSource);
        //File destFile = File.createTempFile("report_", ".html");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlExporter exporter = new HtmlExporter();

        //SimpleHtmlReportConfiguration configuration = new SimpleHtmlReportConfiguration();
        //configuration.setRemoveEmptySpaceBetweenRows(true);
        //exporter.setConfiguration(configuration);
        SimpleHtmlExporterConfiguration co = new SimpleHtmlExporterConfiguration();
        co.setHtmlHeader("<html><head><meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\">" +
                "<script src=\"assets/plugins/jquery-1.8.2/jquery-1.8.2.min.js\"></script>" +
                "<script src=\"js/print.js\"></script>" +
                "</head><body>");
        System.out.println(co.getHtmlHeader());
        exporter.setConfiguration(co);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
        exporter.exportReport();
        outputStream.flush();
        outputStream.close();
        return outputStream.toByteArray();
    }

    public File getExcelReport( HashMap jasperParameter,JRDataSource dataSource ) throws Exception {
        JasperPrint jasperPrint = getReport( jasperFilePath, jasperParameter,dataSource);
        File destFile = File.createTempFile("report_", ".xlsx");
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput( new SimpleExporterInput(jasperPrint) );
        exporter.setExporterOutput( new SimpleOutputStreamExporterOutput(destFile) );
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        return destFile;
    }
    public byte[] getExcelReport1( HashMap jasperParameter,JRDataSource dataSource ) throws Exception {
        JasperPrint jasperPrint = getReport( jasperFilePath, jasperParameter,dataSource);
        File destFile = File.createTempFile("report_", ".xlsx");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput( new SimpleExporterInput(jasperPrint) );
        exporter.setExporterOutput( new SimpleOutputStreamExporterOutput(outputStream) );
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(false);
        exporter.exportReport();
        outputStream.close();
        return outputStream.toByteArray();
    }

    public File getWordReport(HashMap jasperParameter, JRDataSource dataSource ) throws Exception {
        JasperPrint jasperPrint = getReport(jasperFilePath, jasperParameter, dataSource );
        File destFile = File.createTempFile("report_", ".docx");
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
        exporter.exportReport();
        return destFile;
    }
    public byte[] getWordReport1( HashMap jasperParameter,JRDataSource dataSource ) throws Exception {
        JasperPrint jasperPrint = getReport( jasperFilePath, jasperParameter,dataSource);
        File destFile = File.createTempFile("report_", ".docx");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput( new SimpleExporterInput(jasperPrint) );
        exporter.setExporterOutput( new SimpleOutputStreamExporterOutput(outputStream) );
        exporter.exportReport();
        outputStream.close();
        return outputStream.toByteArray();
    }
    public File getPptReport( HashMap jasperParameter ,JRDataSource dataSource ) throws Exception {
        JasperPrint jasperPrint = getReport(jasperFilePath, jasperParameter, dataSource);
        File destFile = File.createTempFile("report_", ".pptx");
        JRPptxExporter exporter = new JRPptxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
        exporter.exportReport();
        return destFile;
    }


}
