package domain;

public class PagingObject {
    private int pageNumber;
    private int pageSize;
    private String sortIndex;
    private String sortOrder;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "PagingObject{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", sortIndex='" + sortIndex + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                '}';
    }
}
