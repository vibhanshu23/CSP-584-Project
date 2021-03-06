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


@WebServlet("/WebHandler")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class WebHandler extends HttpServlet{
	public static String userLat;
	public static String userLong;
	public static String index;

	public static String currentBusRouteNumber;


	public static String strJsonTrainArrival;


	
	public static ArrayList<CTATrainStopModel> arrTrainStopsCurrentlyInUse;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			
			Utilities utility = new Utilities(request,pw);
			
					
		}
	
	
	  //ArrayList<HashMap<String,ArrayList>> getAPIForCTATrainStops(){
		public static ArrayList<CTATrainStopModel> getAPIDataForCTATrainStops() throws ServletException, IOException {
			ArrayList<CTATrainStopModel> arrObjects = new ArrayList<>();

			try {
				URL url = new URL("https://data.cityofchicago.org/resource/8mj8-j3c4.json");
	
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
					
					String temp = "";

					// if(line.charAt(0) == '['){
					// 	temp = line.substring(1);
					// }
					// else if(line.charAt(line.length()-1) == ']'){
					// 	temp = line.substring(1, line.length()-2);
					// }
					// else if(line.charAt(0) == ','){
					// 	temp = line.substring(1);
					// }
					
					// System.out.println("******************************************************************");
					// System.out.println(line);
					responseStrBuilder.append(line);

				}
				Gson gson = new Gson();
				arrObjects.addAll(Arrays.asList(gson.fromJson(responseStrBuilder.toString(), CTATrainStopModel[].class)));
				inputStream.close();
	
				

// 				JSONArray test = JSON.parse(txt);
// 				System.out.println(test[1]);

// 				Product p = new Product(); 
// p.setId( jsonArray.getJSONObject(i).getInt("id") );
// p.setName( jsonArray.getJSONObject(i).getString("name") );
// list.add(p)

				// ArrayList reviewJson = new Gson().toJson(txt);
	
				// Finally we have the response
				System.out.println("******************************************************************");
				System.out.println(arrObjects.size());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			return arrObjects;

		}
}


