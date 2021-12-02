import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CTABusList")

public class CTABusList extends HttpServlet {

	/* Console Page Displays all the Consoles and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String,Console> allconsoles = new HashMap<String,Console> ();


		/* Checks the Tablets type whether it is microsft or sony or nintendo */
		try{
		     allconsoles = MySqlDataStoreUtilities.getConsoles();
		}
		catch(Exception e)
		{
			
		}
		
		HashMap<String, Console> hm = new HashMap<String, Console>();
		if(CategoryName==null){
			hm.putAll(allconsoles);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("microsoft"))
		   {
			 for(Map.Entry<String,Console> entry : allconsoles.entrySet())
			 {
				 
				if(entry.getValue().getRetailer().equals("Microsoft"))
				 {
					
						
	//				hm.put(entry.getKey(),entry.getValue());
					hm.put(entry.getValue().getId(),entry.getValue());
					 
				 }
			 }
				name = "Route 1";
		   }
		   else if(CategoryName.equals("sony"))
		    {
			for(Map.Entry<String,Console> entry : allconsoles.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Sony"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Route 2";
			}
			else if(CategoryName.equals("nintendo"))
			{
				for(Map.Entry<String,Console> entry : allconsoles.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Nintendo"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Route 3";
			}
		}
		
		/* Header, Left Navigation Bar are Printed.

		All the Console and Console information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>CTA "+name+" Bus</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		ArrayList <CTABusObject> arrCTABus = MongoDBDataStoreUtilities.getDistinctRoutes();
		int i = 1; int size= arrCTABus.size();

		for(CTABusObject obj:arrCTABus){
		

		// }
		// for(Map.Entry<String, Console> entry : hm.entrySet())
		// {
			// Console console = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+"   "+obj.getrouteNumber()+"   </h3>");
			pw.print("<strong>"+obj.getrouteName()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/consoles/consoles.png' alt='' /></li>");
			// if(i%2==1)pw.print("<h3>"+"   $"+"4.00"+"   </h3>");
			// else if(i%5==1)pw.print("<h3>"+"   $"+"2.00"+"   </h3>");
			// else if(i%7==1)pw.print("<h3>"+"   $"+"7.00"+"   </h3>");
			// else 
			pw.print("<h3>"+"   $"+"5.00"+"   </h3>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='rt' value='"+obj.getrouteNumber()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					// "<input type='hidden' name='maker' value='"+obj.getrouteName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Ticket'></form></li>");
			pw.print("<li><form method='get' action='DetailMap'>" +
			"<input type='hidden' name='rt' value='"+obj.getrouteNumber()+"'>"+
			"<input type='submit' class='btnbuy' value='Show Routes'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='rt' value='"+obj.getrouteNumber()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+obj.getrouteName()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='rt' value='"+obj.getrouteName()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					// "<input type='hidden' name='maker' value='"+obj.getrouteName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
		
	}
}
