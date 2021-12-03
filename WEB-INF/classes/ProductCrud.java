import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			
			String msg = "good";
			String producttype= "",productId="",productName="",busRouteDirection="",stopId="",stopName="",stoplat = "",stoplon = "";
			double productPrice=0.0,productDiscount = 0.0;
			HashMap<String,Console> allconsoles = new HashMap<String,Console> ();
			HashMap<String,Tablet> alltablets = new HashMap<String,Tablet> ();
			HashMap<String,Game> allgames = new HashMap<String,Game> ();
			HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
			if (action.equals("add") || action.equals("update"))
			{	
				 producttype = request.getParameter("producttype");
				 productId   = request.getParameter("productId");
				 productName = request.getParameter("productName"); 
				 busRouteDirection   = request.getParameter("busRouteDirection");
				 stopId = request.getParameter("stopId");
				 stopName   = request.getParameter("stopName");
				 stoplat = request.getParameter("stoplat");
				 stoplon   = request.getParameter("stoplon");
				//  productPrice = Double.parseDouble(request.getParameter("productPrice"));
				//  productImage = request.getParameter("productImage");
				//  productManufacturer = request.getParameter("productManufacturer");
				//  productCondition = request.getParameter("productCondition");
				//  productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
				 
			}
			else{
				producttype = request.getParameter("producttype");
				productId   = request.getParameter("productId");
				productName = request.getParameter("productName"); 
				busRouteDirection   = request.getParameter("busRouteDirection");
				stopId = request.getParameter("stopId");
				stopName   = request.getParameter("stopName");
				stoplat = request.getParameter("stoplat");
				stoplon   = request.getParameter("stoplon");

			}	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");

			if(action.equals("Add Route"))
			{
				msg = "good";
			//   		if(producttype.equals("consoles")){
			// 	  allconsoles = MySqlDataStoreUtilities.getConsoles();
			// 	  if(allconsoles.containsKey(productId)){
			// 		  msg = "Product already available";
					 
			// 	  }
					  
			//   }else if(producttype.equals("games"))
			//   {
			// 	  allgames = MySqlDataStoreUtilities.getGames();
			// 	  if(allgames.containsKey(productId)){
			// 		  msg = "Product already available";
			// 	  }
			//   }else if (producttype.equals("tablets"))
			//   {
			// 	  alltablets = MySqlDataStoreUtilities.getTablets();
			// 	  if(alltablets.containsKey(productId)){
			// 		  msg = "Product already available";
			// 	  }
			//   }else if (producttype.equals("accessories"))
			//   {  
			// 		if(!request.getParameter("product").isEmpty())
			// 			{
			// 				prod = request.getParameter("product");
			// 				allconsoles = MySqlDataStoreUtilities.getConsoles();
			// 				if(allconsoles.containsKey(prod))
			// 				{
			// 					allaccessories = MySqlDataStoreUtilities.getAccessories();
			// 					if(allaccessories.containsKey(productId)){
			// 						msg = "Product already available";
			// 					}
			// 				}else{
			// 					msg = "The product related to accessories is not available";
			// 				}
						
						
			// 			}else{
			// 				msg = "Please add the prodcut name";
			// 			}
				  
			//   }	
			  if (msg.equals("good"))
			  {  
				  System.out.println(" msg.equals"+productName);
				  try
				  {
					  msg = MySqlDataStoreUtilities.addRoute(productId,productName);
				  }
				  catch(Exception e)
				  { 
					msg = "Route cannot be inserted";
				  }
				  msg = "Route has been successfully added";
			  }					
			}
			else if(action.equals("Add Stop"))
			{
				msg = "good";
			
			  if (msg.equals("good"))
			  {  
				  try
				  {
					  msg = MySqlDataStoreUtilities.addStop(productId,productName,busRouteDirection,stopId,stopName,stoplat,stoplon);
				  }
				  catch(Exception e)
				  { 
					msg = "Stop cannot be inserted";
				  }
				  msg = "Stop has been successfully added";
			  }					
			}
			else if(action.equals("Update Route"))
			{
				msg = "good";
			//   if(producttype.equals("consoles")){
			// 	  allconsoles = MySqlDataStoreUtilities.getConsoles();
			// 	  if(!allconsoles.containsKey(productId)){
			// 		  msg = "Product not available";
			// 	  }
					  
			//   }else if(producttype.equals("games"))
			//   {
			// 	  allgames = MySqlDataStoreUtilities.getGames();
			// 	  if(!allgames.containsKey(productId)){
			// 		  msg = "Product not available";
			// 	  }
			//   }else if (producttype.equals("tablets"))
			//   {
			// 	  alltablets = MySqlDataStoreUtilities.getTablets();
			// 	  if(!alltablets.containsKey(productId)){
			// 		  msg = "Product not available";
			// 	  }
			//   }else if (producttype.equals("accessories"))
			//   {
			// 	  allaccessories = MySqlDataStoreUtilities.getAccessories();
			// 	  if(!allaccessories.containsKey(productId)){
			// 		  msg = "Product not available";
			// 	}
			//   }	
			  if (msg.equals("good"))
			  {		
				
				  try
				  {
					msg = MySqlDataStoreUtilities.updateRoute(productId,productName);
				  }
				  catch(Exception e)
				  { 
					msg = "Route cannot be updated";
				  }
				  msg = "Route has been successfully updated";
			  } 
			}
			else if(action.equals("Update Stop"))
			{
				msg = "good";
			  if (msg.equals("good"))
			  {		
				
				  try
				  {
					msg = MySqlDataStoreUtilities.updateStop(productId,productName,busRouteDirection,stopId,stopName,stoplat,stoplon);
				  }
				  catch(Exception e)
				  { 
					msg = "Stop cannot be updated";
				  }
				  msg = "Stop has been successfully updated";
			  } 
			}
			else if(action.equals("Delete Route"))
			{
				  msg = "good";
				//   allconsoles = MySqlDataStoreUtilities.getConsoles();
				//   if(allconsoles.containsKey(productId)){
				// 	  msg = "good";
					  
				//   }
					  
			  
				//   allgames = MySqlDataStoreUtilities.getGames();
				//   if(allgames.containsKey(productId)){
				// 	  msg = "good";
				//   }
			  
				//   alltablets = MySqlDataStoreUtilities.getTablets();
				//   if(alltablets.containsKey(productId)){
				// 	  msg = "good";
				//   }
			  
				//   allaccessories = MySqlDataStoreUtilities.getAccessories();
				//   if(allaccessories.containsKey(productId)){
				// 	  msg = "good";
				// }
		       		
				  if (msg.equals("good"))
				  {		
					
					  try
					  {  
						
						 msg = MySqlDataStoreUtilities.deleteRoute(productId);
					  }
					  catch(Exception e)
					  { 
						msg = "Route cannot be deleted";
					  }
					   msg = "Route has been successfully deleted";
				  }else{
					  msg = "Route not available";
				  }
			}	
			else if(action.equals("Delete Stop"))
			{
				  msg = "good";
				  if (msg.equals("good"))
				  {		
					
					  try
					  {  
						
						 msg = MySqlDataStoreUtilities.deleteStop(productId);
					  }
					  catch(Exception e)
					  { 
						msg = "Stop cannot be deleted";
					  }
					   msg = "Stop has been successfully deleted";
				  }else{
					  msg = "Stop not available";
				  }
			}	
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
			
	}
}