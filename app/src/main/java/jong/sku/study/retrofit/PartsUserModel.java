package jong.sku.study.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;

public class PartsUserModel implements Serializable {
    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("carid")
    @Expose
    private String carId;

    @SerializedName("carinfo")
    private String carInfo;

    @SerializedName("imgprofile")   //서버로 보내는 경우 사용.
    private File imgProfile;

    @SerializedName("imgcar")   // 서버로 보내는 경우 사용.
    private File imgCar;

    @SerializedName("profilebm")
    private byte[] profileBitmapBytes;

    @SerializedName("carbm")
    private byte[] carBitmapBytes;

    @SerializedName("profileTempPath")
    private String profileTempPath;

    @SerializedName("carTempPath")
    private String carTempPath;



    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCarId() {
        return carId;
    }

    public String getCarInfo() { return carInfo; }

    public File getImgProfile() {
        return imgProfile;
    }

    public File getImgCar() {
        return imgCar;
    }

    public byte[] getProfileBitmapBytes() {
        return profileBitmapBytes;
    }

    public byte[] getCarBitmapBytes() {
        return carBitmapBytes;
    }

    public String getProfileTempPath() {
        return profileTempPath;
    }

    public String getCarTempPath() {
        return carTempPath;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setCarInfo(String carInfo) { this.carInfo = carInfo; }

    public void setImgProfile(File imgProfile) {
        this.imgProfile = imgProfile;
    }

    public void setImgCar(File imgCar) {
        this.imgCar = imgCar;
    }

    public void setProfileBitmapBytes(byte[] profileBitmapBytes) {
        this.profileBitmapBytes = profileBitmapBytes;
    }

    public void setCarBitmapBytes(byte[] carBitmapBytes) {
        this.carBitmapBytes = carBitmapBytes;
    }

    public void setProfileTempPath(String profileTempPath) {
        this.profileTempPath = profileTempPath;
    }

    public void setCarTempPath(String carTempPath) {
        this.carTempPath = carTempPath;
    }
}
