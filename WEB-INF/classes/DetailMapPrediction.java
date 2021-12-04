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
import com.mongodb.*;

@WebServlet("/DetailMapPrediction")
public class DetailMapPrediction extends HttpServlet {
    
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
        String stopName = request.getParameter("Stop Name");
        String routeNumber = request.getParameter("rt");
        String dir = request.getParameter("dir");
        
        System.out.println("------------input data--------------");
        System.out.println("stp nm" + stopName);
        System.out.println("rt" + routeNumber);
        System.out.println("dir" + dir);
        
        ArrayList <CTABusObject> arrCTABusTemp = MongoDBDataStoreUtilities.getStopsForRouteNumber(routeNumber);
        
        URL url = new URL("http://www.ctabustracker.com/bustime/api/v2/getpredictions?key=zGUVrNRusiVnxcsQt2kMqcBEK&format=json&rt="+routeNumber+"&dir="+dir+"&stpid="+stopName);
        
        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        // Now it's "open", we can set the request method, headers etc.
        // connection.setRequestProperty("accept", "application/json");
        
        // This line makes the request
        InputStream inputStream = connection.getInputStream();
        BufferedReader bR = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        
        StringBuilder responseStrBuilder = new StringBuilder();
        
        
        while((line =  bR.readLine()) != null){
            
            responseStrBuilder.append(line.trim());
            
        }
        System.out.println("----------resonse----------");
        System.out.println(responseStrBuilder.toString());
        
        Gson gson = new Gson();
        
        CTABustParentModel parent = gson.fromJson(responseStrBuilder.toString(), CTABustParentModel.class);
        CTABustMiddleModel middle = parent.getBustimeResponse();
        
        
        
        
        
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        // utility.printHtml("LeftNavigationBar.html");
        
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Prediction of Route: "+routeNumber+"</a></h2>"
        + "<div class='entry'>");
        
        // pw.println("<div id='map' style='width: 500px; height: 400px;'>");
        // pw.println("</div>");
        // pw.println("<script type='text/javascript' src='DetailMap.js'></script>");
        
        
        System.out.println("-------- prediction data --------- ");
        System.out.println("size "+middle.getPrd().size());
        System.out.println("object "+parent);
        pw.print("<div id='entryContent' class='entry'><table id='bestseller'>");
        
        for(CTABustPredictionModel obj:middle.getPrd()){
            
            int i = middle.getPrd().indexOf(obj)+1;
            
            if(i%3==1) pw.print("<tr>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>"+"Predicted at "+obj.getPrdtm()+"   </h3>");
            pw.print("<h3>"+"At Stop: "+obj.getStpnm()+"   </h3>");
            if(i%3==0 || i == middle.getPrd().size()-1) pw.print("</tr>");
            i++;
            pw.println("</div>");
            
        }	
        pw.print("</table>");		
        
        pw.println("</div></div></div></div>");
        
        
        // System.out.println("----------bus info----------");
        // pw.print("<form method='post' action='DetailMapPrediction'>"
        // + "<table style='width:100%'><tr><td>"
        // + "<select name='Stop Name' class='input'>");
        
        
        // for(CTABusObject obj1:arrCTABusTemp){
            //     // System.out.println(obj1.getstopName());
            //     // System.out.println(obj1.getstoplat());
            //     // System.out.println(obj1.getstopId());
            //     // System.out.println(obj1.getbusRouteDirection());
            //     // System.out.println(obj1.getrouteNumber());
            //     if(arrCTABusTemp.indexOf(obj1) == 0) pw.print("<option value="+ obj1.getstopId() +"selected>"+obj1.getstopName()+"</option>");
            //     else pw.print("<option value="+ obj1.getstopId() +">"+obj1.getstopName()+"</option>");
            
            // }
            // pw.print("</select>"
            // + "<input type='submit' class='btnbuy' value='Get Predictions' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
            // + "</td></tr><tr><td></td><td></table>"
            // + "</form>");
            // pw.println("</div></div></div>");
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
    