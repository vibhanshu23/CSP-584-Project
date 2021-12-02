import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;  
import java.net.*;
import java.util.*;
import java.io.*;
// import org.json.*;
import org.json.simple.*;
import org.json.simple.JSONObject;



@WebServlet("/Home")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class Home extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		System.out.println("get called Home");
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		utility.printHtml("Content.html");
		pw.println("<script type='text/javascript' src='UserLocation.js'></script>");
		pw.print("<input type='hidden' name='locationLat' id='locationLat'/>"+
		"<input type='hidden' name='locationLon' id='locationLon'/>");
		// RequestDispatcher rd=request.getRequestDispatcher("DealMatchesUtilities");
		// rd.include(request,response);
		utility.printHtml("Footer.html");

		// MongoDBDataStoreUtilities.getNearestStops();


	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		// String UserLat = request.getParameter("locationLat");
		// String UserLong = request.getParameter("locationLon");
		System.out.println("post called Home");

		WebHandler.userLat = request.getParameter("locationLat");
		WebHandler.userLong = request.getParameter("locationLon");

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		utility.printHtml("Content.html");
			
		// RequestDispatcher rd=request.getRequestDispatcher("DealMatchesUtilities");
		// rd.include(request,response);
		utility.printHtml("Footer.html");

	}
    
}


		