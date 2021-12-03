import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;	
import java.io.*;
// import org.json.*;
// import org.json.simple.*;
// import org.json.simple.JSONObject;
import java.net.*;
import com.google.gson.Gson;
import com.mongodb.util.JSON;


@WebServlet("/CTAUIViewTrainArrivalOnMap")

public class CTAUIViewTrainArrivalOnMap extends HttpServlet {
	
	/* Games Page Displays all the Games and their Information in Game Speed */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			
			String RouteName = request.getParameter("name");
			
			WebHandler.index = request.getParameter("indexInArray");
			
			System.out.println("-----index------");
			System.out.println(WebHandler.index);
			System.out.println(request.getParameter("indexInArray"));
			
			
			String UserLat = request.getParameter("locationLat");
			String UserLong = request.getParameter("locationLon");
			System.out.println("******************************************************************");
			System.out.println("Route Name" + RouteName);
			System.out.println("UserLat" + UserLat);
			System.out.println("UserLong" + UserLong);
			
			ArrayList<CTATrainStopModel> arrToUse = WebHandler.arrTrainStopsCurrentlyInUse;
			
			System.out.println("******************************************************************");
			
			
			CTATrainStopModel currentModel = arrToUse.get(Integer.parseInt(WebHandler.index));
			
			
			URL url = new URL("http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=7568261512d4478089e18f6f95a91847&mapid="+currentModel.getMapId() +"&outputType=JSON");
			
			// Open a connection(?) on the URL(??) and cast the response(???)
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			// Now it's "open", we can set the request method, headers etc.
			connection.setRequestProperty("accept", "application/json");
			
			// This line makes the request
			InputStream inputStream = connection.getInputStream();
			BufferedReader bR = new BufferedReader(  new InputStreamReader(inputStream));
			String line = "";
			
			StringBuilder responseStrBuilder = new StringBuilder();
			
			
			while((line =  bR.readLine()) != null){
				
				responseStrBuilder.append(line);
				
			}
			System.out.println("----------resonse----------");
			System.out.println(responseStrBuilder);
			WebHandler.strJsonTrainArrival = responseStrBuilder.toString();
			
			Gson gson = new Gson();
			
			CTAArivalModelParent parent = gson.fromJson(responseStrBuilder.toString(), CTAArivalModelParent.class);
			System.out.println("----------parentparent----------");
			System.out.println(parent.getCTAArivalModelMiddle());
			CTAArivalModelMiddle midModel =  parent.getCTAArivalModelMiddle();
			ArrayList<CTAArrivalItem> tmp = new ArrayList<CTAArrivalItem>(midModel.getEta());
			ArrayList<CTAArrivalItem> tmpScheduled= new ArrayList<CTAArrivalItem>();
			ArrayList<CTAArrivalItem> tmpDueToArrive  = new ArrayList<CTAArrivalItem>();
			
			
			for(CTAArrivalItem item:tmp){
				System.out.println("----------getIsSch----------");
				System.out.println(item.getIsSch());				
				System.out.println(item.getLat());
				
				if(item.getLat() == null){
					tmpScheduled.add(item);
					
				}
				else{
					tmpDueToArrive.add(item);
				}
			}
			midModel.setArrDueTrains(tmpDueToArrive);
			midModel.setArrScheduledTrains(tmpScheduled);
			
			System.out.println("----------tmpDueToArrive.size----------");
			System.out.println(tmpDueToArrive.size());
			System.out.println("----------tmpDueToArrive.size----------");
			System.out.println(tmpScheduled.size());
			System.out.println("----------midModel----------");
			System.out.println(midModel.getEta());
			System.out.println("----------parentparent----------");
			System.out.println(parent.getCTAArivalModelMiddle());
			
			
			// arrObjects.addAll(Arrays.asList());
			inputStream.close();
			
			
			Utilities utility = new Utilities(request,pw);
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'>");
			
			
			
			pw.println("<script type='text/javascript' src='DetailMapStops.js'></script>");
			// pw.print("<h3><button id='btnGetArrivalData'>View Arrival Information On Map</h3>");
			
			if(midModel.arrDueTrains.size() >0){
				pw.print("<a style='font-size: 24px;'> Due For Arrival at "+ currentModel.getStationDescriptiveName() +"</a>");
				pw.print("<div id='entryContent' class='entry'><table id='bestseller'>");
				
				for(CTAArrivalItem obj:midModel.arrDueTrains){
					
					int i = midModel.arrDueTrains.indexOf(obj);
					
					if(i%3==1) pw.print("<tr>");
					pw.print("<td><div id='shop_item'>");
					pw.print("<h3>"+"Due at "+obj.getArrT()+"   </h3>");
					pw.print("<h3>"+"Station Name: "+obj.getStaNm()+"   </h3>");
					if(i%3==0 || i == midModel.arrDueTrains.size()) pw.print("</tr>");
					i++;
				}	
				pw.print("</table>");		
	
			}
			if(midModel.arrScheduledTrains.size() >0){
				pw.print("<a style='font-size: 24px;'> Sceduled For Arrival at "+ currentModel.getStationDescriptiveName() +"</a>");
				pw.print("<div id='entryContent' class='entry'><table id='bestseller'>");

				for(CTAArrivalItem obj:midModel.arrScheduledTrains){
					
					int i = midModel.arrScheduledTrains.indexOf(obj);
					
					if(i%3==1) pw.print("<tr>");
					pw.print("<td><div id='shop_item'>");
					pw.print("<h3>"+"Due at "+obj.getArrT()+"   </h3>");
					pw.print("<h3>"+"Station Name: "+obj.getStaNm()+"   </h3>");
					if(i%3==0 || i == midModel.arrScheduledTrains.size()) pw.print("</tr>");
					i++;
				}		
				pw.print("</table>");		

			}

			if(midModel.arrScheduledTrains.size() == 0 && midModel.arrDueTrains.size() == 0){
				pw.print("<a style='font-size: 24px;'> No trains scheduled</a>");
			}
			pw.print("</div></div></div>");		
			pw.print("<input id='btnGetArrivalData' type='submit' class='btnbuy' onclick=callPostForArrivalData() value='View Arrival Information On Map'>");

			
			
			
			
			
			pw.println("<div id='map' style='width: 900px; height: 900px;'>");
			pw.println("</div>");
			utility.printHtml("Footer.html");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		} 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ArrayList<CTATrainStopModel> array = WebHandler.arrTrainStopsCurrentlyInUse;
			CTATrainStopModel currentModel = array.get(Integer.parseInt(WebHandler.index));
			System.out.println("******Arrival do post called*******");
			System.out.println(WebHandler.strJsonTrainArrival);

			URL url = new URL("http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=7568261512d4478089e18f6f95a91847&mapid="+currentModel.getMapId() +"&outputType=JSON");
			
			// Open a connection(?) on the URL(??) and cast the response(???)
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			// Now it's "open", we can set the request method, headers etc.
			connection.setRequestProperty("accept", "application/json");
			
			// This line makes the request
			InputStream inputStream = connection.getInputStream();
			BufferedReader bR = new BufferedReader(  new InputStreamReader(inputStream));
			String line = "";
			
			StringBuilder responseStrBuilder = new StringBuilder();
			
			
			while((line =  bR.readLine()) != null){
				
				responseStrBuilder.append(line);
				
			}
			System.out.println("----------resonse----------");
			System.out.println(responseStrBuilder);
			WebHandler.strJsonTrainArrival = responseStrBuilder.toString();

			Gson gson = new Gson();
			
			CTAArivalModelParent parent = gson.fromJson(WebHandler.strJsonTrainArrival.toString(), CTAArivalModelParent.class);
			System.out.println("----------parentparent----------");
			System.out.println(parent.getCTAArivalModelMiddle());
			CTAArivalModelMiddle midModel =  parent.getCTAArivalModelMiddle();
			ArrayList<CTAArrivalItem> tmp = new ArrayList<CTAArrivalItem>(midModel.getEta());
			ArrayList<CTAArrivalItem> tmpScheduled= new ArrayList<CTAArrivalItem>();
			ArrayList<CTAArrivalItem> tmpDueToArrive  = new ArrayList<CTAArrivalItem>();
			
			
			for(CTAArrivalItem item:tmp){
				System.out.println("----------getIsSch----------");
				System.out.println(item.getIsSch());
				
				
				if(item.getLat() == null){
					tmpScheduled.add(item);
					
				}
				else{
					tmpDueToArrive.add(item);
				}
				
			}
			midModel.setArrDueTrains(tmpDueToArrive);
			midModel.setArrScheduledTrains(tmpScheduled);
			
			
			String reviewJson = new Gson().toJson(midModel);
			response.setContentType("application/JSON");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(reviewJson);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
	}
	
	
}