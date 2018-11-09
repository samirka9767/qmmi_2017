package domain;

import java.io.Serializable;

public class address1 implements Serializable {
    private long ID; //address id
    private long perAddrID; //person address id
    private long countryID;
    private long regionID;
    private long subRegionID;
    private long villageID; 
    private long streetID;
    private String countryName;
    private String regionName;
    private String subRegionName;
    private String villageName;
    private String streetName;
    private String address;
    private long addrTypeID;
    private String addrTypeName;
    private String postCode;
    private long state;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getPerAddrID() {
        return perAddrID;
    }

    public void setPerAddrID(long perAddrID) {
        this.perAddrID = perAddrID;
    }
    
    
    public long getCountryID() {
        return countryID;
    }

    public void setCountryID(long countryID) {
        this.countryID = countryID;
    }

    public long getRegionID() {
        return regionID;
    }

    public void setRegionID(long regionID) {
        this.regionID = regionID;
    }

    public long getSubRegionID() {
        return subRegionID;
    }

    public void setSubRegionID(long subRegionID) {
        this.subRegionID = subRegionID;
    }

    public long getVillageID() {
        return villageID;
    }

    public void setVillageID(long villageID) {
        this.villageID = villageID;
    }

    public long getStreetID() {
        return streetID;
    }

    public void setStreetID(long streetID) {
        this.streetID = streetID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getSubRegionName() {
        return subRegionName;
    }

    public void setSubRegionName(String subRegionName) {
        this.subRegionName = subRegionName;
    }

    public String getVillageName() {
        return villageName;
    }

     public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAddrTypeName() {
        return addrTypeName;
    }

    public void setAddrTypeName(String addrTypeString) {
        this.addrTypeName = addrTypeString;
    }
    
    public long getAddrTypeID() {
        return addrTypeID;
    }

    public void setAddrTypeID(long addrTypeID) {
        this.addrTypeID = addrTypeID;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}

