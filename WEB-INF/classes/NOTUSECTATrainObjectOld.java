import java.util.*;
import java.util.Map;



public class NOTUSECTATrainObjectOld {

	private String routeDirection;
	private String stopId;
	private String stopName;
	private String stoplat;
	private String stoplon;
	private String stopDisplayName;
	private String map_id;
	
	private String routeNumber;
	private String routeColour;

	

	public NOTUSECTATrainObjectOld(String map_id,String routeNumber, String routeColour, String stopDisplayName, String routeDirection,String stopId,String stopName,String stoplat,String stoplon){
		this.map_id=map_id;
		this.routeNumber=routeNumber;
		this.routeColour=routeColour;
		this.stopDisplayName=stopDisplayName;
		this.routeDirection = routeDirection;
		this.stopId=stopId;
		this.stopName=stopName;
		this.stoplat = stoplat;
		this.stoplon = stoplon;
		
	}

	// {
    //     "stop_id": "30022",
    //     "direction_id": "N",
    //     "stop_name": "35th/Archer (Loop-bound)",
    //     "station_name": "35th/Archer",
    //     "station_descriptive_name": "35th/Archer (Orange Line)",
    //     "map_id": "40120",
        
	// 	"ada": true,
    //     "red": false,
    //     "blue": false,
    //     "g": false,
    //     "brn": false,
    //     "p": false,
    //     "pexp": false,
    //     "y": false,
    //     "pnk": false,
    //     "o": true,

    //     "location": {
    //         "type": "Point",
    //         "coordinates": [
    //             -87.680622,
    //             41.829353
    //         ]
    //     },
    //     ":@computed_region_awaf_s7ux": "26",
    //     ":@computed_region_6mkv_f3dw": "14924",
    //     ":@computed_region_vrxf_vc4k": "56",
    //     ":@computed_region_bdys_3d7i": "719",
    //     ":@computed_region_43wa_7qmu": "1"
    // }

	public NOTUSECTATrainObjectOld(){
		
	}
	public String getmap_id() {
		return map_id;
	}
	public void setmap_id(String map_id) {
		this.map_id = map_id;
	}
	public String getrouteNumber() {
		return routeNumber;
	}
	public void setrouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}
	public String getstopName() {
		return stopName;
	}
	public void setstopName(String stopName) {
		this.stopName =stopName;
	}


	public String getrouteColour() {
		return routeColour;
	}
	public void setrouteColour(String routeColour) {
		this.routeColour = routeColour;
	}
	public String getstopDisplayName() {
		return stopDisplayName;
	}
	public void setstopDisplayName(String stopDisplayName) {
		this.stopDisplayName = stopDisplayName;
	}
	public String getrouteDirection() {
		return routeDirection;
	}
	public void setrouteDirection(String routeDirection) {
		this.routeDirection = routeDirection;
	}


	public String getstopId() {
		return stopId;
	}

	public void setstopId(String stopId) {
		this.stopId = stopId;
	}

	public String getstoplat() {
		return stoplat;
	}

	public void setstoplat(String stoplat) {
		this.stoplat = stoplat;
	}
	public String getstoplon() {
		return stoplon;
	}

	public void setstoplon(String stoplon) {
		this.stoplon = stoplon;
	}
	
}
