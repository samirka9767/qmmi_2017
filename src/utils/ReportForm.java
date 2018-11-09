package utils;

import domain.*;
import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellAlignment;
import service.loadDataService;
import service.loadDataServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by Narmin on 4/2/2016.
 */
public class ReportForm {

    String[] hours = new String[]{"09:00-10:20", "10:30-11:50", "12:10-13:30", "14:00-15:20", "15:30-16:50", "17:10-18:30"};
    String[] c = new String[]{"", "", "", "", "", ""};

}
