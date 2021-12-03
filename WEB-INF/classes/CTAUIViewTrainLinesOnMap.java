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
import com.google.gson.*;

@WebServlet("/CTAUIViewTrainLinesOnMap")

public class CTAUIViewTrainLinesOnMap extends HttpServlet {
	
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
			
			System.out.println("******************************************************************");
			
			
			Utilities utility = new Utilities(request,pw);
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>CTA Train - "+RouteName+ "Line</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			pw.println("<script type='text/javascript' src='DetailMapArrival.js'></script>");
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
            
            String reviewJson = new Gson().toJson(array);
			HashMap<String, String> hm = new HashMap<String, String>();



            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


	}

	
}