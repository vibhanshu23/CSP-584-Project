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

@WebServlet("/CTAUIViewTrainStops")

public class CTAUIViewTrainStops extends HttpServlet {

	/* Games Page Displays all the Games and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();



		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>CTA Train"+"</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");

		ArrayList<String> arr = new ArrayList<String>();

		arr.add("Red"); 
		arr.add("Blue"); 
		arr.add("Green");
		arr.add("Brown");
		arr.add("Purple");
		arr.add("Yellow");
		arr.add("Pink");
		arr.add("Oranage");
		arr.add("PurpleExpress");

		for(String model:arr){
			int i = arr.indexOf(model)+1;
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+model+" Line</h3>");
			// pw.print("<strong>"+ "Enter secondary text" + "</strong><ul>");
			pw.print("<li id='item'><img src='images/games/CTATrain.jpeg' alt='' /></li>");
			pw.print("<li><form method='get' id='locationForm' action='CTAUIViewTrainLines'>" +
					"<input type='hidden' name='name' value='"+model+"'>"+
					"<input type='hidden' name='locationLat' id='locationLat'/>"+
					"<input type='hidden' name='locationLon' id='locationLon'/>"+
					"<input type='submit' class='btnbuy' value='View Nearest Stops'></form></li>");
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
			if(i%3==0 || i == arr.size()-1) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
		
	}

}
