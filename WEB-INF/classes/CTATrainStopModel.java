

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
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
    private Location location;
    @SerializedName(":@computed_region_awaf_s7ux")
    @Expose
    private String computedRegionAwafS7ux;
    @SerializedName(":@computed_region_6mkv_f3dw")
    @Expose
    private String computedRegion6mkvF3dw;
    @SerializedName(":@computed_region_vrxf_vc4k")
    @Expose
    private String computedRegionVrxfVc4k;
    @SerializedName(":@computed_region_bdys_3d7i")
    @Expose
    private String computedRegionBdys3d7i;
    @SerializedName(":@computed_region_43wa_7qmu")
    @Expose
    private String computedRegion43wa7qmu;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CTATrainStopModel() {
    }

    /**
     * 
     * @param computedRegion6mkvF3dw
     * @param computedRegionAwafS7ux
     * @param directionId
     * @param g
     * @param stopId
     * @param computedRegionBdys3d7i
     * @param pnk
     * @param o
     * @param computedRegion43wa7qmu
     * @param red
     * @param p
     * @param computedRegionVrxfVc4k
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
    public CTATrainStopModel(String stopId, String directionId, String stopName, String stationName, String stationDescriptiveName, String mapId, boolean ada, boolean red, boolean blue, boolean g, boolean brn, boolean p, boolean pexp, boolean y, boolean pnk, boolean o, Location location, String computedRegionAwafS7ux, String computedRegion6mkvF3dw, String computedRegionVrxfVc4k, String computedRegionBdys3d7i, String computedRegion43wa7qmu) {
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
        this.computedRegionAwafS7ux = computedRegionAwafS7ux;
        this.computedRegion6mkvF3dw = computedRegion6mkvF3dw;
        this.computedRegionVrxfVc4k = computedRegionVrxfVc4k;
        this.computedRegionBdys3d7i = computedRegionBdys3d7i;
        this.computedRegion43wa7qmu = computedRegion43wa7qmu;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getComputedRegionAwafS7ux() {
        return computedRegionAwafS7ux;
    }

    public void setComputedRegionAwafS7ux(String computedRegionAwafS7ux) {
        this.computedRegionAwafS7ux = computedRegionAwafS7ux;
    }

    public String getComputedRegion6mkvF3dw() {
        return computedRegion6mkvF3dw;
    }

    public void setComputedRegion6mkvF3dw(String computedRegion6mkvF3dw) {
        this.computedRegion6mkvF3dw = computedRegion6mkvF3dw;
    }

    public String getComputedRegionVrxfVc4k() {
        return computedRegionVrxfVc4k;
    }

    public void setComputedRegionVrxfVc4k(String computedRegionVrxfVc4k) {
        this.computedRegionVrxfVc4k = computedRegionVrxfVc4k;
    }

    public String getComputedRegionBdys3d7i() {
        return computedRegionBdys3d7i;
    }

    public void setComputedRegionBdys3d7i(String computedRegionBdys3d7i) {
        this.computedRegionBdys3d7i = computedRegionBdys3d7i;
    }

    public String getComputedRegion43wa7qmu() {
        return computedRegion43wa7qmu;
    }

    public void setComputedRegion43wa7qmu(String computedRegion43wa7qmu) {
        this.computedRegion43wa7qmu = computedRegion43wa7qmu;
    }

}
