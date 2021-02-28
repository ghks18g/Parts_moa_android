package jong.sku.study.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RepairShopModel implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("rdnmadr")
    @Expose
    private String rdnmadr;

    @SerializedName("lnmadr")
    @Expose
    private String lnmadr;

    @SerializedName("lat")
    @Expose
    private String lat;

    @SerializedName("lng")
    @Expose
    private String lng;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("openTime")
    @Expose
    private String openTime;

    @SerializedName("closeTime")
    @Expose
    private String closeTime;

    public String getName() {
        return name;
    }

    public String getRdnmadr() {
        return rdnmadr;
    }

    public String getLnmadr() {
        return lnmadr;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getPhone() {
        return phone;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRdnmadr(String rdnmadr) {
        this.rdnmadr = rdnmadr;
    }

    public void setLnmadr(String lnmadr) {
        this.lnmadr = lnmadr;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
}
