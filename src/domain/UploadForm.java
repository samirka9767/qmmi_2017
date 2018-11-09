package domain;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UploadForm {
    private File file;
    private InputStream inp;
    private Map<String, String> parameterMap;

    public UploadForm() {
        parameterMap = new HashMap<String, String>();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void put(String paramName, String paramValue) {
        parameterMap.put(paramName, paramValue);
    }
    
    public String get(String paramName) {
        return parameterMap.get(paramName);
    }    

    public InputStream getInp() {
        return inp;
    }

    public void setInp(InputStream inp) {
        this.inp = inp;
    }
}
