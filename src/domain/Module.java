package domain;


import java.io.Serializable;

public class Module implements Serializable {


    private long id;
    private String name;
    private String linkId;
    private String link;
    /*private List<ModuleOperation> operationList = new ArrayList<ModuleOperation>();*/
  //  private Grid grid;
    private boolean loadGrid;
    private int moduleGroup;
    private long moduleParent;
    private int moduleType;
    private Long rsourceId;
    /*private List<Operation> operationList;*/
    private String modulName_level2;

    public String getModulName_level2() {
        return modulName_level2;
    }

    public void setModulName_level2(String modulName_level2) {
        this.modulName_level2 = modulName_level2;
    }


    /*public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }*/



    public int getModuleType() {
        return moduleType;
    }

    public void setModuleType(int moduleType) {
        this.moduleType = moduleType;
    }

    public long getModuleParent() {
        return moduleParent;
    }

    public void setModuleParent(long moduleParent) {
        this.moduleParent = moduleParent;
    }

    public int getModuleGroup() {
        return moduleGroup;
    }

    public void setModuleGroup(int moduleGroup) {
        this.moduleGroup = moduleGroup;
    }

    public boolean isLoadGrid() {
        return loadGrid;
    }

    public void setLoadGrid(boolean loadGrid) {
        this.loadGrid = loadGrid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    /*public List<ModuleOperation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<ModuleOperation> operationList) {
        this.operationList = operationList;
    }*/

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

/*    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }*/

    public Long getRsourceId() {
        return rsourceId;
    }

    public void setRsourceId(Long rsourceId) {
        this.rsourceId = rsourceId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
