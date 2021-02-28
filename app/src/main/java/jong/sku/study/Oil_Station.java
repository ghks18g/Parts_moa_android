package jong.sku.study;

public class Oil_Station {
    private String station_name;
    private String price;
    private String gis_x;
    private String gis_y;
    private String user_to_station;
    private String brand;
    private String station_code;

    public Oil_Station(){
        station_name = "";
        price = "";
        gis_x = "";
        gis_y = "";
        user_to_station = "";
        brand = "";
        station_code = "";
    }

    public Oil_Station(String station_name,String price, String gis_x, String gis_y, String user_to_station, String brand, String station_code){
        this.station_name = station_name;
        this.price = price;
        this.gis_x = gis_x;
        this.gis_y = gis_y;
        this.user_to_station = user_to_station;
        this.brand = brand;
        this.station_code = station_code;
    }

    public String getBrand() {
        return brand;
    }

    public String getStation_code() {
        return station_code;
    }

    public String getStation_name() {
        return station_name;
    }

    public String getPrice() {
        return price;
    }

    public String getGis_x() {
        return gis_x;
    }

    public String getGis_y() {
        return gis_y;
    }

    public String getUser_to_station() {
        return user_to_station;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setStation_code(String station_code) {
        this.station_code = station_code;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setUser_to_station(String user_to_station) {
        this.user_to_station = user_to_station;
    }

    public void setGis_x(String gis_x) {
        this.gis_x = gis_x;
    }

    public void setGis_y(String gis_y) {
        this.gis_y = gis_y;
    }
}
