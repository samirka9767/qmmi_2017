package domain;

import java.io.Serializable;
import java.util.Map;


public class Resource implements Serializable {
    private long resourseId;
    private Map<Long, String> localeValues;

    public long getResourseId() {
        return resourseId;
    }

    public void setResourseId(long resourseId) {
        this.resourseId = resourseId;
    }

    public Map<Long, String> getLocaleValues() {
        return localeValues;
    }

    public void setLocaleValues(Map<Long, String> localeValues) {
        this.localeValues = localeValues;
    }
}
