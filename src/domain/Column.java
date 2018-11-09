package domain;

public class Column {
    private String key;
    private Object value;
    private Object file;
    private Object value1;
    //private Reader bir;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        if ( value != null ){
            return value;
        }else {
            return  "";
        }

    }
    public void setValue(Object value ) {
        this.value = value;
    }

    public Object getFile() {
        if ( file != null ){
            return file;
        }else {
            return  "";
        }

    }

    public void setFile(Object file) {
        this.file = file;
    }
    public Object getValue1() {
        return value1;
    }

    public void setValue1(Object value1) {
        this.value1 = value1;
    }


}

