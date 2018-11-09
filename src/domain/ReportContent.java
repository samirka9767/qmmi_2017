package domain;


import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.List;


public class ReportContent implements JRDataSource {

    private List<Row> reportContent;
    private int index = -1;

    public ReportContent(List<Row> reportContent){
        this.reportContent = reportContent;
    }

    @Override
    public boolean next() throws JRException {
        index++;
        return (index < reportContent.size() );
    }

    @Override
    public Object getFieldValue( JRField jrField ) throws JRException {
        return reportContent.get(index).getColumn(jrField.getName()).getValue();
    }
}
