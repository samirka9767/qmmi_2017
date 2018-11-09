package web;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.lowagie.text.pdf.BaseFont;
import domain.*;
import service.loadDataService;
import service.loadDataServiceImpl;
import service.saveDataService;
import service.saveDataServiceImpl;
import utils.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


/**
 * Created by vugar on 9/1/2015.
 */
@WebServlet(name = "ReportServlet")
public class ReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = "";
        String address = "";
        Boolean forward = false;
        saveDataService svService = new saveDataServiceImpl();
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        ServletOutputStream outStream = response.getOutputStream();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "utf-8"));
        try {
            loadDataService service = new loadDataServiceImpl();
            usersInfo loggedUser = (usersInfo) request.getSession().getAttribute(constant.USER);
            if (action.equals("mainGridReport")) {
                forward = null;
                String jasperFilePath = "";
                String gType = request.getParameter("gridType");
                if (gType.equals("1")) {
                    jasperFilePath = "grid/employees.jasper";
                } else if (gType.equals("2")) {
                    jasperFilePath = "grid/carriers.jasper";
                } else if (gType.equals("3")) {
                    jasperFilePath = "grid/organization.jasper";
                } else if (gType.equals("4")) {
                    jasperFilePath = "grid/examples.jasper";
                } else if (gType.equals("7")) {
                    jasperFilePath = "grid/users.jasper";
                }
                List<Row> data = service.getSelectContent("mainGrid", request.getParameterMap(), loggedUser);
                ReportContent reportContent = new ReportContent(data);
                ReportUtility reportUtility = new ReportUtility(jasperFilePath);
                String reportType = request.getParameter("reportType");
                HashMap parameters = new HashMap();
                if (reportType.equals("ppt")) {
                    File file = reportUtility.getPptReport(parameters, reportContent);
                    Helper.downloadFile(response, file);
                } else if (reportType.equals("docx")) {
                    byte[] bytes = reportUtility.getWordReport1(parameters, reportContent);
                    Helper.download(response, bytes, "docx_report.docx");
                } else if (reportType.equals("xlsx")) {
                    byte[] bytes = reportUtility.getExcelReport1(parameters, reportContent);
                    Helper.download(response, bytes, "xlsx_report.xlsx");
                } else if (reportType.equals("pdf")) {
                    byte[] bytes = reportUtility.getPdfReport1(parameters, reportContent);
                    Helper.download(response, bytes, "pdf_report.pdf");
                }
            } else if (action.equals("FullMundericatReport")) {
                response.setContentType("application/pdf");
                String[] myJsonData = request.getParameterValues("json[]");
                try {
                    Document document = new Document(PageSize.A4, 0, 0, 0, 0);
                    com.itextpdf.text.pdf.BaseFont bf;
                    bf = com.itextpdf.text.pdf.BaseFont.createFont(request.getServletContext().getRealPath("/Fonts11/arial.ttf"), com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
                    OutputStream out1 = response.getOutputStream();
                    PdfWriter writer = PdfWriter.getInstance(document, out1);
                    document.open();
                    List<listInfo> categoryDetail = service.loadStrukturList("1010", 1000);

                    // document.add(new Phrase("\n"));

                    List<String> qrDsl = (List<String>) session.getAttribute(constant.SES_CATST);

                    PdfPTable table6 = new PdfPTable(1);
                    table6.getDefaultCell().setBorder(0);
                    table6.getDefaultCell().setBorderWidthRight(Rectangle.NO_BORDER);
                    table6.getDefaultCell().setBorderWidthLeft(Rectangle.NO_BORDER);

                    table6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                    //  for (int i = 0; i< data.length; i++) {
                    //  System.out.println(data[i]);


                    String str = qrDsl.get(0);
                    String[] parts = str.split("---");
                    String x = "";
                    String oName = "";
                    String oIndex = "";
                    PdfPCell pdfCell1 = null;
                    for (int i = 0; i < parts.length; i++) {

                        if (!parts[i].equals("")) {
                            parts[i] = " " + parts[i];
                            parts[i] = parts[i].replace("1012***"," ");
                            parts[i] = parts[i].replace("1013***","     ");
                            parts[i] = parts[i].replace("1014***","           ");
                            parts[i] = parts[i].replace("1017***","               ");
                            parts[i] = parts[i].replace("1015***","                     ");
                            oName = parts[i].substring(0, parts[i].lastIndexOf("@@"));
                            pdfCell1 = new PdfPCell(new Phrase("\n"+oName, new Font(bf,10, Font.BOLD)));
                            pdfCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                            pdfCell1.setBorder(Rectangle.NO_BORDER);
                            table6.addCell(pdfCell1);
                        }
                    }

                    document.add(table6);
                    document.close();
                    response.flushBuffer();
                    out1.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                address = null;
                forward = null;
            } else if (action.equals("createPassport")) {
                forward = null;
                String jasperFilePath = "";
                jasperFilePath = "passport/report1.jasper";
                List<Row> data = service.getSelectContent("createPassport", request.getParameterMap(), loggedUser);
                for (Row row : data) {
                    Column column = row.getColumn("FILEPATH");
                    ftpUtility utility = new ftpUtility();
                    File file = utility.ftpDownload((String) column.getValue());

                    if (file == null) {
                        //InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jasper/resources/profile-picture.jpg");
                        //file = new File();
                    }
                    column.setKey("FILE");
                    column.setValue(file);

                }
                ReportContent reportContent = new ReportContent(data);
                ReportUtility reportUtility = new ReportUtility(jasperFilePath);
                String reportType = request.getParameter("reportType");
                HashMap parameters = new HashMap();
                if (reportType.equals("ppt")) {
                    File file = reportUtility.getPptReport(parameters, reportContent);
                    Helper.downloadFile(response, file);
                } else if (reportType.equals("docx")) {

                } else if (reportType.equals("xlsx")) {
                    byte[] bytes = reportUtility.getExcelReport1(parameters, reportContent);
                    Helper.download(response, bytes, "xlsx_report.xlsx");
                } else if (reportType.equals("pdf")) {

                    File file = reportUtility.getPdfReport(parameters, reportContent);
                    Helper.downloadFile(response, file);

                    ftpUtility ftpU = new ftpUtility();
                    UploadForm uForm = ftpU.ftpUpload(file, 7);
                    String filePath = uForm.get("docFilePH");
                    String exampleID = request.getParameter("exampleID");
                    svService.updatePassportPath(exampleID, filePath, 1);
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (forward != null) {
                if (forward) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(address);
                }
            } else {
                out.flush();
                out.close();
                outStream.close();
            }
        }

    }
}





