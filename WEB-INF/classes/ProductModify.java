import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductModify")

public class ProductModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		if(action.equals("AddBusRoute"))
		{
			
			
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Add Bus Route</a></h2>"
					+ "<div class='entry'>");
				
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					// + "<h3>Product Type</h3></td><td><select name='producttype' class='input'><option value='consoles' selected>Console</option><option value='games'>Games</option><option value='tablets'>Tablet</option><option value='accessories'>Accessory</option></select>"
					// + "</td></tr><tr><td>"
					// +"<h3>Product</h3></td><td><input type='text' name='product' placeholder='Please mention product if adding accessories' value='' class='input'></input>"
					// + "</td></tr><tr><td>"
					+ "<h3>Route Number</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Route Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					// + "<h3>Product Price</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productPrice' value='' class='input' required></input>"
					// + "</td></tr><tr><td>"
					// + "<h3>Product Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					// + "</td></tr><tr><td>"
					// + "<h3>Product Manufacturer</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
					// + "</td></tr><tr><td>"
					// + "<h3>Product Condition</h3></td><td><input type='text' name='productCondition' value='' class='input' required></input>"
					// + "</td></tr><tr><td>"
					// + "<h3>Product Discount</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productDiscount' value='' class='input' required></input>"
					// + "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='Add Route' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		else if(action.equals("AddBusStop"))
		{
			
			
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Add Bus Stop</a></h2>"
					+ "<div class='entry'>");
				
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<h3>Route Number</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Route Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Bus Route Direction</h3></td><td><input type='text' name='busRouteDirection' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Stop Id</h3></td><td><input type='text' name='stopId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Stop Name</h3></td><td><input type='text' name='stopName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Stop Latitute</h3></td><td><input type='text' name='stoplat' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Stop Logitute</h3></td><td><input type='text' name='stoplon' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='Add Stop' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		else if (action.equals("UpdateBusRoute"))
		{
		     pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Update Bus Route</a></h2>"
					+ "<div class='entry'>");
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					// + "<h3>Product Type</h3></td><td><select name='producttype' class='input'><option value='consoles' selected>Console</option><option value='games'>Games</option><option value='tablets'>Tablet</option><option value='accessories'>Accessory</option></select>"
					// + "</td></tr><tr><td>"
					+ "<h3>Route Number</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Route Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					// + "<h3>Product Price</h3></td><td><input type='number' step='any' name='productPrice' value='' class='input' required></input>"
					// + "</td></tr><tr><td>"
					// + "<h3>Product Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					// + "</td></tr><tr><td>"
					// + "<h3>Product Manufacturer</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
					// + "</td></tr><tr><td>"
					// + "<h3>Product Condition</h3></td><td><input type='text' name='productCondition' value='' class='input' required></input>"
					// + "</td></tr><tr><td>"
					// + "<h3>Product Discount</h3></td><td><input type='number' step='any' name='productDiscount' value='' class='input' required></input>"
					// + "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='Update Route' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");	

		}
		else if (action.equals("UpdateBusStop"))
		{
		     pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Update Bus Stop</a></h2>"
						+ "<div class='entry'>");
				
				pw.print("<form method='get' action='ProductCrud'>"
				+ "<table id='bestseller'><tr><td>"
				+ "<h3>Route Number</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Route Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Bus Route Direction</h3></td><td><input type='text' name='busRouteDirection' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Stop Id</h3></td><td><input type='text' name='stopId' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Stop Name</h3></td><td><input type='text' name='stopName' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Stop Latitute</h3></td><td><input type='text' name='stoplat' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Stop Logitute</h3></td><td><input type='text' name='stoplon' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<input type='submit' class='btnbuy' name='button' value='Update Stop' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</td></tr><tr><td></td><td>"
				
				+ "</td></tr></table>"
				+ "</form>" + "</div></div></div>");

		}
		else if (action.equals("DeleteBusRoute"))
		{
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Delete Bus Route</a></h2>"
					+ "<div class='entry'>");
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<h3>RouteId</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='Delete Route' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		else if (action.equals("DeleteBusStop"))
		{
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Delete Bus Stop</a></h2>"
					+ "<div class='entry'>");
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<h3>StopId</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='Delete Stop' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}