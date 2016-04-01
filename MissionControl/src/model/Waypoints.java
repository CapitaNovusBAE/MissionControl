package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Waypoints {

    private final IntegerProperty longitude;
    private final IntegerProperty latitude;
    private final IntegerProperty altitude;
    private final IntegerProperty speed;

  //********************

    public Waypoints() {
        this(0,0,0,0);
    }

    public Waypoints(int longitude, int latitude, int altitude, int speed) {
        this.longitude 	= new SimpleIntegerProperty(longitude);
        this.latitude 	= new SimpleIntegerProperty(latitude);
        this.altitude 	= new SimpleIntegerProperty(altitude);
        this.speed 		= new SimpleIntegerProperty(speed);
    }

    //********************

    public int getLongitude(){
        return longitude.get();
    }

    public void setLongitude(int longitude){
    	this.longitude.set(longitude);
    }

    public IntegerProperty longitudeProperty() {
        return longitude;
    }

    //********************

    public int getLatitude(){
        return latitude.get();
    }

    public void setLatitude(int latitude){
    	this.longitude.set(latitude);
    }

    public IntegerProperty latitudeProperty() {
        return latitude;
    }

    //********************

    public int getAltitude(){
        return altitude.get();
    }

    public void setAltitude(int altitude){
    	this.altitude.set(altitude);
    }

    public IntegerProperty AltitudeProperty() {
        return altitude;
    }

  //********************

    public int getSpeed(){
        return speed.get();
    }

    public void setSpeed(int speed){
    	this.speed.set(speed);
    }

    public IntegerProperty SpeedProperty() {
        return speed;
    }
}