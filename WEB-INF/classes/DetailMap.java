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

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        // utility.printHtml("LeftNavigationBar.html");

        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Data Visualization</a></h2>"
                + "<div class='entry'>");
            
        // pw.print("<h3><button id='btnGetChartData'>View Chart</h3>");
        pw.println("<div id='map' style='width: 500px; height: 400px;'>");
        pw.println("</div></div></div></div>");
        pw.println("<script type='text/javascript' src='DetailMap.js'></script>");
        
        utility.printHtml("Footer.html");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
			System.out.println(name);


		ArrayList <CTABusObject> arrCTABusTemp = MongoDBDataStoreUtilities.getStopsForRouteNumber(name);
			// for(CTABusObject obj1:arrCTABusTemp){
			// 	System.out.println(obj1.getstopName());
			// 	System.out.println(obj1.getstoplat());
			// 	System.out.println(obj1.getbusRouteDirection());
			// 	System.out.println(obj1.getrouteNumber());
			// 	System.out.println("---------------------------");

			// }
            // ArrayList<Review> reviews = MongoDBDataStoreUtilities.selectReviewForChart();
            // ArrayList<Review> topReviewsPerCity = getTop3InEveryCity(reviews);
            
            String reviewJson = new Gson().toJson(arrCTABusTemp);

            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        // utility.printHtml("LeftNavigationBar.html");

        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Data Visualization</a></h2>"
                + "<div class='entry'>");
            
        // pw.print("<h3><button id='btnGetChartData'>View Chart</h3>");
        pw.println("<div id='map' style='width: 500px; height: 400px;'>");
        pw.println("</div></div></div></div>");
        pw.println("<script type='text/javascript'>");
        // pw.println("console.log('"+reviewJson+"')");
        pw.println("function createDataTable() {");
        // response.setContentType("application/JSON");
        //     response.setCharacterEncoding("UTF-8");
            pw.println("var parsedData = $.parseJSON('"+reviewJson+"'');");
            // pw.println("var locations = [");
            // pw.println("['Bondi Beach', -33.890542, 151.274856, 4],");
            // pw.println("['Coogee Beach', -33.923036, 151.259052, 5],");
            //   pw.println("['Cronulla Beach', -34.028249, 151.157507, 3],");
            //   pw.println("['Manly Beach', -33.80010128657071, 151.28747820854187, 2],");
            //   pw.println("['Maroubra Beach', -33.950198, 151.259302, 1]");
            //   pw.println("];");
            //   pw.println("for(i=0; i < parsedData.length;i++)");
            //   pw.println("{");
            //     pw.println("locations.push([parsedData[i].stopName, Double.parseDouble(parsedData[i].stoplat), Double.parseDouble(parsedData[i].stoplon), i+1]);");
            //     pw.println("}");
                pw.println("var map = new google.maps.Map(document.getElementById('map'), {");
                    pw.println("zoom: 10,");
                    pw.println("center: new google.maps.LatLng(-33.92, 151.25),");
                    pw.println("mapTypeId: google.maps.MapTypeId.ROADMAP");
                    pw.println(" });");
          
                    pw.println("var infowindow = new google.maps.InfoWindow();");
          
                    pw.println("var marker, i;");
          
                    pw.println("for (i = 0; i < parsedData.length; i++) {");
                        pw.println("marker = new google.maps.Marker({");
                            pw.println("position: new google.maps.LatLng(Double.parseDouble(parsedData[i].stoplat), Double.parseDouble(parsedData[i].stoplon)),");
                            pw.println("map: map");
                            pw.println("});");
          
                            pw.println("google.maps.event.addListener(marker, 'click', (function (marker, i) {");
                                pw.println("return function () {");
                                    pw.println("infowindow.setContent(parsedData[i].stopName);");
                                    pw.println("infowindow.open(map, marker);");
                                    pw.println("}");
                                    pw.println("})(marker, i));");
                                    pw.println("}");
                                    pw.println("}");
                                    pw.println("window.onload = createDataTable;");
        pw.println("</script>");
        
        utility.printHtml("Footer.html");

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);

        } catch (Exception ex) {
            
        }

    }

    // //This method takes all the reviews and returns top 3 review in every city;
    // private static ArrayList<Review> getTop3InEveryCity(ArrayList<Review> reviewList){

    //     //sorting the list in ascending order;
    //     Collections.sort(reviewList, new Comparator<Review>(){
    //         public int compare(Review r1, Review r2){
    //             return Integer.parseInt(r2.getReviewRating()) - Integer.parseInt(r1.getReviewRating());
    //         }
    //     });

    //    HashMap<String,Review> reviewMap = new HashMap<>();

    //    //Get list of cities in all reviews;
    //    ArrayList<String> zipCodeList = new ArrayList<>();
    //    for(Review review:reviewList){
    //         String zipCode = review.getStoreZipcode();
    //         if(!(zipCodeList.contains(zipCode))){
    //             zipCodeList.add(zipCode);
    //         }
    //    } 

    //    //get top 3 reviews for every city;
    //    ArrayList<Review> top3Reviews = new ArrayList<>();
    //    for(String zipCode:zipCodeList){
    //         ArrayList<Review> top3ReviewsCity = new ArrayList<>();
    //         for(Review review:reviewList){
    //             if(review.getStoreZipcode().equals(zipCode) && top3ReviewsCity.size()<3){
    //                 top3ReviewsCity.add(review);
    //             }
    //         }
    //         top3Reviews.addAll(top3ReviewsCity); 
    //    }

    //     return top3Reviews;
    // }

}
