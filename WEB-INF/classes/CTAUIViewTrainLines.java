import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.math.*;


@WebServlet("/CTAUIViewTrainLines")

public class CTAUIViewTrainLines extends HttpServlet {
	
	/* Games Page Displays all the Games and their Information in Game Speed */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			
			String RouteName = request.getParameter("name");
			String UserLat = request.getParameter("locationLat");
			String UserLong = request.getParameter("locationLon");
			System.out.println("******************************************************************");
			System.out.println("Route Name" + RouteName);
			System.out.println("UserLat" + UserLat);
			System.out.println("UserLong" + UserLong);
			
			
			
			
			ArrayList<CTATrainStopModel> listStops = WebHandler.getAPIDataForCTATrainStops();
			ArrayList<CTATrainStopModel> listStopsRed = new ArrayList<CTATrainStopModel>();
			ArrayList<CTATrainStopModel> listStopsBlue = new ArrayList<CTATrainStopModel>();
			ArrayList<CTATrainStopModel> listStopsGreen = new ArrayList<CTATrainStopModel>();
			ArrayList<CTATrainStopModel> listStopsBrown= new ArrayList<CTATrainStopModel>();
			ArrayList<CTATrainStopModel> listStopsPurple= new ArrayList<CTATrainStopModel>();
			ArrayList<CTATrainStopModel> listStopsYellow= new ArrayList<CTATrainStopModel>();
			ArrayList<CTATrainStopModel> listStopsPink= new ArrayList<CTATrainStopModel>();
			ArrayList<CTATrainStopModel> listStopsOranage= new ArrayList<CTATrainStopModel>();
			ArrayList<CTATrainStopModel> listStopsPurpleExpress= new ArrayList<CTATrainStopModel>();
			
			
			for (CTATrainStopModel stopModel : listStops) {
				if (stopModel.isRed()){
					listStopsRed.add(stopModel);
				}
				if (stopModel.isBlue()){
					listStopsBlue.add(stopModel);
				}
				if (stopModel.isG()){
					listStopsGreen.add(stopModel);
				}
				if (stopModel.isBrn()){
					listStopsBrown.add(stopModel);
				}
				if (stopModel.isP()){
					listStopsPurple.add(stopModel);
				}
				if (stopModel.isPexp()){
					listStopsPurpleExpress.add(stopModel);
				}
				if (stopModel.isY()){
					listStopsYellow.add(stopModel);
				}
				if (stopModel.isPnk()){
					listStopsPink.add(stopModel);
				}
				if (stopModel.isO()){
					listStopsOranage.add(stopModel);
				}
				setCurrentDistanceAccordingToCurrentLocation(stopModel);
				
			}
			
			ArrayList<CTATrainStopModel> arrToUse = new ArrayList<CTATrainStopModel>();
			if(RouteName.equals("Red")){
				arrToUse = listStopsRed ;
			}
			else if(RouteName.equals("Blue")){
				arrToUse = listStopsBlue ;
			}
			else if(RouteName.equals("Green")){
				arrToUse = listStopsGreen ;
			}
			else if(RouteName.equals("Brown")){
				arrToUse = listStopsBrown ;
			}
			else if(RouteName.equals("Purple")){
				arrToUse = listStopsPurple ;
			}
			else if(RouteName.equals("Yellow")){
				arrToUse = listStopsYellow ;
			}
			else if(RouteName.equals("Pink")){
				arrToUse = listStopsPink ;
			}
			else if(RouteName.equals("Oranage")){
				arrToUse = listStopsOranage ;
			}
			else if(RouteName.equals("PurpleExpress")){
				arrToUse = listStopsPurpleExpress ;
			}
			

			System.out.println("******************************************************************");
			
			System.out.println("listStops" + listStops.size());
			System.out.println("listStopsRed" + listStopsRed.size());
			System.out.println("listStopsGreen" + listStopsGreen.size());
			System.out.println("arrToUse" + arrToUse.size());
			
			
			Utilities utility = new Utilities(request,pw);
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>CTA Train - "+RouteName+ "Line</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			pw.println("<script type='text/javascript' src='DetailMapArrival.js'></script>");

			try{
				Collections.sort(arrToUse, new StockComparator());
				// Collections.sort(arrToUse, Comparator.comparing(CTATrainStopModel::getDistance));
			}
			catch(Exception e)
			{
				System.out.println(e.getLocalizedMessage());
			} 
			
			
			// pw.print("<h3>"+"long "+WebHandler.userLong+"</h3>");
			// pw.print("<h3>"+"lat "+WebHandler.userLat+"</h3>"
			// +"<input type='submit' value='View Stops on Map' class='btnreview'>");
			pw.print("<li><form method='get' action='CTAUIViewTrainLinesOnMap'>" +
						"<input type='hidden' name='type' value='train'>"+
						"<input type='hidden' name='name' value="+RouteName+">"+
						"<input id='btnGetNearestData' onclick=callPostForNearestData() type='submit' class='btnbuy' value='View Nearest Stops on Map'></form></li>");
			
			System.out.println("-------distance -------");
			WebHandler.arrTrainStopsCurrentlyInUse = new ArrayList<>();

			for( CTATrainStopModel model:arrToUse){
				int i = arrToUse.indexOf(model);
				
				if(i == 5) break;


				WebHandler.arrTrainStopsCurrentlyInUse.add(model);
				System.out.println(model.getDistance());

				if(i%3==1) pw.print("<tr>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+model.getStopName()+"</h3>");
				pw.print("<strong>"+ "$" + model.getStationDescriptiveName() + "</strong><ul>");
				pw.print("<strong>"+ "Distance " + model.getDistance() + "</strong><ul>");
				// pw.print("<li id='item'><img src='images/games/"+game.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='get' action='CTAUIViewTrainArrivalOnMap'>" +
				"<input type='hidden' name='indexInArray' value='"+String.valueOf(i)+"'>"+
				"<input type='hidden' name='name' value='"+RouteName+"'>"+
						"<input type='submit' class='btnbuy' value='View Arrival Information'></form></li>");
				// pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				// 		"<input type='hidden' name='type' value='games'>"+
				// 		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
				// 		"<input type='hidden' name='price' value='"+game.getPrice()+"'>"+
				// 		"<input type='hidden' name='access' value=''>"+
				// 	    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				// pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
				// 		"<input type='hidden' name='type' value='games'>"+
				// 		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
				// 		"<input type='hidden' name='access' value=''>"+
				// 	    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
				pw.print("</ul></div></td>");
				if(i%3==0 || i == arrToUse.size()) pw.print("</tr>");
				i++;
			}		
			pw.print("</table></div></div></div>");		
			utility.printHtml("Footer.html");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		} 
	}
	
	protected void setCurrentDistanceAccordingToCurrentLocation(CTATrainStopModel model){
		
		CTALocationChild stopLoc  = model.getLocation();
		
		String[] args = {WebHandler.userLat,WebHandler.userLong,String.valueOf(stopLoc.getCoordinates().get(0)),String.valueOf(stopLoc.getCoordinates().get(1))};
		
		final int R = 6371; // Radious of the earth
		Double lat1 = Double.parseDouble(args[0]);
		Double lon1 = Double.parseDouble(args[1]);
		Double lat2 = Double.parseDouble(args[2]);
		Double lon2 = Double.parseDouble(args[3]);
		Double latDistance = toRad(lat2-lat1);
		Double lonDistance = toRad(lon2-lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
		Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * 
		Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double distance = R * c;
		model.setDistance(distance);
	}
	
	private static Double toRad(Double value) {
		return value * Math.PI / 180;
	}
	
	
}


class StockComparator implements Comparator<CTATrainStopModel> {
	
	@Override
	public int compare(CTATrainStopModel s1, CTATrainStopModel s2)
	{
		if (s1.getDistance() == s2.getDistance())
		return 0;
		else if (s1.getDistance() > s2.getDistance())
		return -1;
		else
		return 1;
	}
	
}