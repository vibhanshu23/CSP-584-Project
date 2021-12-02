import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.mongodb.AggregationOutput;


@WebServlet("/DetailMap")
public class DetailMap extends HttpServlet {
    
    static DBCollection myReviews;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayPage(request, response, pw);
    }
    
    protected void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
    throws ServletException, IOException {
        String RouteNumber = request.getParameter("rt");
        WebHandler.currentBusRouteNumber = RouteNumber;
        System.out.println("Stored in Handle" + WebHandler.currentBusRouteNumber);
        
        ArrayList <CTABusObject> arrCTABusTemp = MongoDBDataStoreUtilities.getStopsForRouteNumber(RouteNumber);
        
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        // utility.printHtml("LeftNavigationBar.html");
        
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Data Visualization</a></h2>"
        + "<div class='entry'>");
        
        pw.println("<div id='map' style='width: 500px; height: 400px;'>");
        pw.println("</div>");
        pw.println("<script type='text/javascript' src='DetailMap.js'></script>");
        
        
        
        
        
        // System.out.println("----------bus info----------");
        pw.print("<form method='get' action='DetailMapPrediction'>"
        + "<table style='width:100%'><tr><td><input type='hidden' name='rt' value="+RouteNumber+">"
        + "<select name='Stop Name' class='input'>");
        for(CTABusObject obj1:arrCTABusTemp){
            // System.out.println(obj1.getstopName());
            // System.out.println(obj1.getstoplat());
            // System.out.println(obj1.getstopId());
            // System.out.println(obj1.getbusRouteDirection());
            // System.out.println(obj1.getrouteNumber());
            if(arrCTABusTemp.indexOf(obj1) == 0) pw.print("<option value="+ obj1.getstopId() +"selected>"+obj1.getstopName()+"</option>");
            else pw.print("<option value="+ obj1.getstopId() +">"+obj1.getstopName()+"</option>");
            
            // pw.print("<option value="+ obj1.getstopId() +"selected>"+obj1.getstopName()+"</option>"+ "<input type='hidden' name='dir' value="+obj1.getbusRouteDirection()+">");
            
        }
        pw.print("</select>"
        + "<input type='submit' class='btnbuy' value='Get Predictions' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
        + "</td></tr><tr><td></td><td></table>"
        + "</form>");
        pw.println("</div></div></div>");
        utility.printHtml("Footer.html");
        
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            String name = WebHandler.currentBusRouteNumber;
            System.out.println(name);
            
            System.out.println("Handle data " + WebHandler.currentBusRouteNumber);
            
            System.out.println("----------post detail called----------");
            
            ArrayList <CTABusObject> arrCTABusTemp = MongoDBDataStoreUtilities.getStopsForRouteNumber(name);
            for(CTABusObject obj1:arrCTABusTemp){
                System.out.println(obj1.getstopName());
                System.out.println(obj1.getstoplat());
                System.out.println(obj1.getbusRouteDirection());
                System.out.println(obj1.getrouteNumber());
                System.out.println("---------------------------");
                
            }
            System.out.println("---------- reviewJson detail ----------");
            String reviewJson = new Gson().toJson(arrCTABusTemp);
            System.out.println(reviewJson);
            
            PrintWriter pw = response.getWriter();
            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);
            
        } catch (Exception ex) {
            
        }
        
    }
    
    
}
