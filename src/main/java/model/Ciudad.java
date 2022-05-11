package model;
import java.io.Serializable;

public class Ciudad implements Serializable {
    private String name;
    private double lat;
    private double lon;
    private String img;

    public Ciudad(String name,double lat,double lon,String img){
        this.name=name;
        this.lat=lat;
        this.lon=lon;
        this.img=img;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return getName();
    }
}