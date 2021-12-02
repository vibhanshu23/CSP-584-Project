

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CTATrainStopModel {

    @SerializedName("stop_id")
    @Expose
    private String stopId;
    @SerializedName("direction_id")
    @Expose
    private String directionId;
    @SerializedName("stop_name")
    @Expose
    private String stopName;
    @SerializedName("station_name")
    @Expose
    private String stationName;
    @SerializedName("station_descriptive_name")
    @Expose
    private String stationDescriptiveName;
    @SerializedName("map_id")
    @Expose
    private String mapId;
    private Double distance;
    @SerializedName("ada")
    @Expose
    private boolean ada;
    @SerializedName("red")
    @Expose
    private boolean red;
    @SerializedName("blue")
    @Expose
    private boolean blue;
    @SerializedName("g")
    @Expose
    private boolean g;
    @SerializedName("brn")
    @Expose
    private boolean brn;
    @SerializedName("p")
    @Expose
    private boolean p;
    @SerializedName("pexp")
    @Expose
    private boolean pexp;
    @SerializedName("y")
    @Expose
    private boolean y;
    @SerializedName("pnk")
    @Expose
    private boolean pnk;
    @SerializedName("o")
    @Expose
    private boolean o;
    @SerializedName("location")
    @Expose
    private CTALocationChild location;
 
    /**
     * No args constructor for use in serialization
     * 
     */
    public CTATrainStopModel() {
    }

    /**
     * 
     * @param directionId
     * @param g
     * @param stopId
     * @param pnk
     * @param o
     * @param red
     * @param p
     * @param blue
     * @param pexp
     * @param y
     * @param stationName
     * @param mapId
     * @param location
     * @param stationDescriptiveName
     * @param stopName
     * @param ada
     * @param brn
     */
    public CTATrainStopModel(String stopId, String directionId, String stopName, String stationName, String stationDescriptiveName, String mapId, boolean ada, boolean red, boolean blue, boolean g, boolean brn, boolean p, boolean pexp, boolean y, boolean pnk, boolean o, CTALocationChild location) {
        super();
        this.stopId = stopId;
        this.directionId = directionId;
        this.stopName = stopName;
        this.stationName = stationName;
        this.stationDescriptiveName = stationDescriptiveName;
        this.mapId = mapId;
        this.ada = ada;
        this.red = red;
        this.blue = blue;
        this.g = g;
        this.brn = brn;
        this.p = p;
        this.pexp = pexp;
        this.y = y;
        this.pnk = pnk;
        this.o = o;
        this.location = location;

    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getDirectionId() {
        return directionId;
    }

    public void setDirectionId(String directionId) {
        this.directionId = directionId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationDescriptiveName() {
        return stationDescriptiveName;
    }

    public void setStationDescriptiveName(String stationDescriptiveName) {
        this.stationDescriptiveName = stationDescriptiveName;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public boolean isAda() {
        return ada;
    }

    public void setAda(boolean ada) {
        this.ada = ada;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public boolean isBlue() {
        return blue;
    }

    public void setBlue(boolean blue) {
        this.blue = blue;
    }

    public boolean isG() {
        return g;
    }

    public void setG(boolean g) {
        this.g = g;
    }

    public boolean isBrn() {
        return brn;
    }

    public void setBrn(boolean brn) {
        this.brn = brn;
    }

    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public boolean isPexp() {
        return pexp;
    }

    public void setPexp(boolean pexp) {
        this.pexp = pexp;
    }

    public boolean isY() {
        return y;
    }

    public void setY(boolean y) {
        this.y = y;
    }

    public boolean isPnk() {
        return pnk;
    }

    public void setPnk(boolean pnk) {
        this.pnk = pnk;
    }

    public boolean isO() {
        return o;
    }

    public void setO(boolean o) {
        this.o = o;
    }

    public CTALocationChild getLocation() {
        return location;
    }

    public void setLocation(CTALocationChild location) {
        this.location = location;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }



}
