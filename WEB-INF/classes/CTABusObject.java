import java.util.*;
import java.util.Map;



public class CTABusObject {
	private String id;
	private String routeNumber;
	private String routeColour;
	private String routeName;
	private String busRouteDirection;
	private String stopId;
	private String stopName;
	private String stoplat;
	private String stoplon;
	

	public CTABusObject(String id,String routeNumber, String routeColour, String routeName, String busRouteDirection,String stopId,String stopName,String stoplat,String stoplon){
		this.id=id;
		this.routeNumber=routeNumber;
		this.routeColour=routeColour;
		this.routeName=routeName;
		this.busRouteDirection = busRouteDirection;
		this.stopId=stopId;
		this.stopName=stopName;
		this.stoplat = stoplat;
		this.stoplon = stoplon;
		
	}

	public CTABusObject(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getrouteName() {
		return routeName;
	}
	public void setrouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getbusRouteDirection() {
		return busRouteDirection;
	}
	public void setbusRouteDirection(String busRouteDirection) {
		this.busRouteDirection = busRouteDirection;
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
