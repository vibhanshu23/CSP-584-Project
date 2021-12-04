import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
import com.mongodb.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoDBDataStoreUtilities {
	static DBCollection myReviews;
	static DBCollection myCTABus;
	static DBCollection myCTABusRoutes;
	
	public static DBCollection getConnection() {
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db = mongo.getDB("CustomerReviews");
		myReviews = db.getCollection("myReviews");
		
		DB db2 = mongo.getDB("CTABusData");
		myCTABus = db2.getCollection("CTABus");
		myCTABusRoutes = db2.getCollection("CTABusRoutes");
		
		
		return myReviews;
	}
	
	public static String insertReview(String productname, String username, String producttype, String productmaker,
	String reviewrating, String reviewdate, String reviewtext, String retailerpin, String price,
	String retailercity) {
		try {
			
			getConnection();
			BasicDBObject doc = new BasicDBObject("title", "myReviews").append("userName", username)
			.append("productName", productname).append("productType", producttype)
			.append("productMaker", productmaker).append("reviewRating", Integer.parseInt(reviewrating))
			.append("reviewDate", reviewdate).append("reviewText", reviewtext)
			.append("retailerpin", retailerpin).append("retailercity", retailercity)
			.append("price", price);
			myReviews.insert(doc);
			return "Successfull";
		} catch (Exception e) {
			e.printStackTrace();
			return "UnSuccessfull";
		}
		
	}
	
	public static HashMap<String, ArrayList<Review>> selectReview() {
		HashMap<String, ArrayList<Review>> reviews = null;
		
		try {
			
			getConnection();
			DBCursor cursor = myReviews.find();
			reviews = new HashMap<String, ArrayList<Review>>();
			while (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				
				if (!reviews.containsKey(obj.getString("productName"))) {
					ArrayList<Review> arr = new ArrayList<Review>();
					reviews.put(obj.getString("productName"), arr);
				}
				ArrayList<Review> listReview = reviews.get(obj.getString("productName"));
				Review review = new Review(obj.getString("productName"), obj.getString("userName"),
				obj.getString("productType"), obj.getString("productMaker"), obj.getString("reviewRating"),
				obj.getString("reviewDate"), obj.getString("reviewText"), obj.getString("retailerpin"),
				obj.getString("price"), obj.getString("retailercity"));
				// add to review hashmap
				listReview.add(review);
				
			}
			return reviews;
		} catch (Exception e) {
			reviews = null;
			return reviews;
		}
		
	}
	
	public static ArrayList<Bestrating> topProducts() {
		ArrayList<Bestrating> Bestrate = new ArrayList<Bestrating>();
		try {
			
			getConnection();
			int retlimit = 5;
			DBObject sort = new BasicDBObject();
			sort.put("reviewRating", -1);
			DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
			while (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				
				String prodcutnm = obj.get("productName").toString();
				String rating = obj.get("reviewRating").toString();
				Bestrating best = new Bestrating(prodcutnm, rating);
				Bestrate.add(best);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Bestrate;
	}
	
	
	
	public static ArrayList<Mostsoldzip> mostsoldZip() {
		ArrayList<Mostsoldzip> mostsoldzip = new ArrayList<Mostsoldzip>();
		try {
			
			getConnection();
			DBObject groupProducts = new BasicDBObject("_id", "$retailerpin");
			groupProducts.put("count", new BasicDBObject("$sum", 1));
			DBObject group = new BasicDBObject("$group", groupProducts);
			DBObject limit = new BasicDBObject();
			limit = new BasicDBObject("$limit", 5);
			
			DBObject sortFields = new BasicDBObject("count", -1);
			DBObject sort = new BasicDBObject("$sort", sortFields);
			AggregationOutput output = myReviews.aggregate(group, sort, limit);
			for (DBObject res : output.results()) {
				
				String zipcode = (res.get("_id")).toString();
				String count = (res.get("count")).toString();
				Mostsoldzip mostsldzip = new Mostsoldzip(zipcode, count);
				mostsoldzip.add(mostsldzip);
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return mostsoldzip;
	}
	
	public static ArrayList<Mostsold> mostsoldProducts() {
		ArrayList<Mostsold> mostsold = new ArrayList<Mostsold>();
		try {
			
			getConnection();
			DBObject groupProducts = new BasicDBObject("_id", "$productName");
			groupProducts.put("count", new BasicDBObject("$sum", 1));
			DBObject group = new BasicDBObject("$group", groupProducts);
			DBObject limit = new BasicDBObject();
			limit = new BasicDBObject("$limit", 5);
			
			DBObject sortFields = new BasicDBObject("count", -1);
			DBObject sort = new BasicDBObject("$sort", sortFields);
			AggregationOutput output = myReviews.aggregate(group, sort, limit);
			
			for (DBObject res : output.results()) {
				
				String prodcutname = (res.get("_id")).toString();
				String count = (res.get("count")).toString();
				Mostsold mostsld = new Mostsold(prodcutname, count);
				mostsold.add(mostsld);
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return mostsold;
	}
	
	// Get all the reviews grouped by product and zip code;
	public static ArrayList<Review> selectReviewForChart() {
		
		ArrayList<Review> reviewList = new ArrayList<Review>();
		try {
			
			getConnection();
			Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
			dbObjIdMap.put("retailerpin", "$retailerpin");
			dbObjIdMap.put("productName", "$productName");
			DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
			groupFields.put("count", new BasicDBObject("$sum", 1));
			DBObject group = new BasicDBObject("$group", groupFields);
			
			DBObject projectFields = new BasicDBObject("_id", 0);
			projectFields.put("retailerpin", "$_id");
			projectFields.put("productName", "$productName");
			projectFields.put("reviewCount", "$count");
			DBObject project = new BasicDBObject("$project", projectFields);
			
			DBObject sort = new BasicDBObject();
			sort.put("reviewCount", -1);
			
			DBObject orderby = new BasicDBObject();
			orderby = new BasicDBObject("$sort", sort);
			
			AggregationOutput aggregate = myReviews.aggregate(group, project, orderby);
			
			for (DBObject result : aggregate.results()) {
				
				BasicDBObject obj = (BasicDBObject) result;
				Object o = com.mongodb.util.JSON.parse(obj.getString("retailerpin"));
				BasicDBObject dbObj = (BasicDBObject) o;
				Review review = new Review(dbObj.getString("productName"), dbObj.getString("retailerpin"),
				obj.getString("reviewCount"), null);
				reviewList.add(review);
				
			}
			return reviewList;
			
		}
		
		catch (
		
		Exception e) {
			reviewList = null;
			
			return reviewList;
		}
		
	}
	
	public static ArrayList<CTABusObject> getDistinctRoutes() {
		ArrayList<CTABusObject> arrCTABus = new ArrayList<CTABusObject>();
		try {
			
			getConnection();
			DBCursor cursor = myCTABusRoutes.find();
			// distinct("categories", Object.class).iterator();
			// collection.distinct("countries", String.class);
			
			// Iterator<String> files = myCTABus.distinct("routeNumber").iterator();
			// while(files.hasNext()) {
				//   System.out.println(files.next());
				// }
				while (cursor.hasNext()) {
					BasicDBObject obj = (BasicDBObject) cursor.next();
					String id = obj.get("_id").toString();
					String routeNumber = obj.get("routeNumber").toString();
					String routeColour = obj.get("routeColour").toString();
					String routeName = obj.get("routeName").toString();
					String busRouteDirection = "";//obj.get("busRouteDirection").toString();
					String stopId = "";//obj.get("stopId").toString();
					String stopName = "";//obj.get("stopName").toString();
					String stoplat = "";//obj.get("stoplat").toString();
					String stoplon = "";//obj.get("stoplon").toString();
					CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
					arrCTABus.add(CTA);
					// System.out.println(routeNumber);
					// System.out.println(routeColour);
					// System.out.println(routeName);
					// System.out.println(busRouteDirection);
					// System.out.println(stopId);
					// System.out.println(stoplat);
					
					// String id = files.next().toString();
					// String routeNumber = files.next().toString();//obj.get("routeNumber").toString();
					// String routeColour = files.next().toString();//obj.get("routeColour").toString();
					// String routeName = files.next().toString();//obj.get("routeName").toString();
					// String busRouteDirection = files.next().toString();//obj.get("busRouteDirection").toString();
					// String stopId = files.next().toString();//obj.get("stopId").toString();
					// String stopName = files.next().toString();//obj.get("stopName").toString();
					// String stoplat = files.next().toString();//obj.get("stoplat").toString();
					// String stoplon = files.next().toString();//obj.get("stoplon").toString();
					// CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
					// arrCTABus.add(CTA);
				}
				// System.out.println("---------------------------");
				// System.out.println(arrCTABus);
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return arrCTABus;
			
		}
		
		public static HashMap<String,CTABusObject> getDistinctRoutesHashmap() {
			HashMap<String,CTABusObject> arrCTABus = new HashMap<String,CTABusObject>();
			try {
				
				getConnection();
				DBCursor cursor = myCTABusRoutes.find();
				while (cursor.hasNext()) {
					BasicDBObject obj = (BasicDBObject) cursor.next();
					String id = obj.get("_id").toString();
					String routeNumber = obj.get("routeNumber").toString();
					String routeColour = obj.get("routeColour").toString();
					String routeName = obj.get("routeName").toString();
					String busRouteDirection = "";//obj.get("busRouteDirection").toString();
					String stopId = "";//obj.get("stopId").toString();
					String stopName = "";//obj.get("stopName").toString();
					String stoplat = "";//obj.get("stoplat").toString();
					String stoplon = "";//obj.get("stoplon").toString();
					CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
					arrCTABus.put(routeNumber,CTA);
				}
				// System.out.println("---------------------------");
				// System.out.println(arrCTABus);
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return arrCTABus;
			
		}
		
		
		
		
		public static ArrayList<CTABusObject> getStopsForRouteNumber(String routeString) {
			ArrayList<CTABusObject> arrCTABus = new ArrayList<CTABusObject>();
			try {
				
				getConnection();
				BasicDBObject whereQuery = new BasicDBObject();
				whereQuery.put("routeNumber", routeString);
				var stringDir = "";
				Iterator<String> files = myCTABus.distinct("busRouteDirection",whereQuery).iterator();
				while(files.hasNext()) {
					stringDir = files.next();
					
				}
				
				
				DBCursor cursor = myCTABus.find(whereQuery);
				while(cursor.hasNext()) {
					BasicDBObject obj = (BasicDBObject) cursor.next();
					String busRouteDirection = obj.get("busRouteDirection").toString();
					
					if (stringDir.equals(busRouteDirection)){
						String id = obj.get("_id").toString();
						String routeNumber = obj.get("routeNumber").toString();
						String routeColour = obj.get("routeColour").toString();
						String routeName = obj.get("routeName").toString();
						String stopId = obj.get("stopId").toString();
						String stopName = obj.get("stopName").toString();
						String stoplat = obj.get("stoplat").toString();
						String stoplon = obj.get("stoplon").toString();
						CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
						arrCTABus.add(CTA);
					}
					
				}
				
				// System.out.println("---------------------------");
				// System.out.println(arrCTABus);
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return arrCTABus;
			
		}
		
		public static ArrayList<CTABusObject> getNearestStops() {
			ArrayList<CTABusObject> arrCTABus = new ArrayList<CTABusObject>();
			System.out.println("---------------------------");
			System.out.println("----reched nearest stops-----");
			try {
				
				getConnection();
				List<String> stopsID = myCTABus.distinct("stopId");
				
				for( String stop:stopsID){
					System.out.println("stopID" + stop);
					
					BasicDBObject whereQuery = new BasicDBObject();
					whereQuery.put("stopId", stop);
					DBCursor cursor = myCTABus.find(whereQuery);
					
					
					while (cursor.hasNext()) {
						BasicDBObject obj = (BasicDBObject) cursor.next();
						String id = obj.get("_id").toString();
						String routeNumber = obj.get("routeNumber").toString();
						String routeColour = obj.get("routeColour").toString();
						String routeName = obj.get("routeName").toString();
						String busRouteDirection = obj.get("busRouteDirection").toString();
						String stopId = obj.get("stopId").toString();
						String stopName = obj.get("stopName").toString();
						String stoplat = obj.get("stoplat").toString();
						String stoplon = obj.get("stoplon").toString();
						CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
						arrCTABus.add(CTA);
						
					}
				}
				
				System.out.println("---------------------------");
				System.out.println(arrCTABus);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return arrCTABus;
			
		}
		
		public static HashMap<String,Product> getDistinctRoutesHashmapForAutoComplete() {
			HashMap<String,Product> arrCTABus = new HashMap<String,Product>();
			try {
				getConnection();
				DBCursor cursor = myCTABusRoutes.find();
				while (cursor.hasNext()) {
					BasicDBObject obj = (BasicDBObject) cursor.next();
					String id = obj.get("_id").toString();
					String routeNumber = obj.get("routeNumber").toString();
					String routeColour = obj.get("routeColour").toString();
					String routeName = obj.get("routeName").toString();
					String busRouteDirection = "";//obj.get("busRouteDirection").toString();
					String stopId = "";//obj.get("stopId").toString();
					String stopName = "";//obj.get("stopName").toString();
					String stoplat = "";//obj.get("stoplat").toString();
					String stoplon = "";//obj.get("stoplon").toString();
					
					Product p = new Product(routeNumber,routeName,5.00,"",routeNumber,"New","Train",0);
					
					CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
					arrCTABus.put(routeNumber,p);
				}
				// System.out.println("---------------------------");
				// System.out.println(arrCTABus);
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return arrCTABus;
			
		}
		
		
		
		public static String addRoute(String productId,String productName)
		{
			
			String msg = "Route is added successfully";
			
			try {
				
				getConnection();
				BasicDBObject doc = new BasicDBObject("routeNumber", productId)
				.append("routeColour", "#66cc66").append("routeName", productName);
				myCTABusRoutes.insert(doc);
				return "Successfull";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "Error while adding the Route";
			}
			return msg;
		}
		public static String addStop(String productId,String productName,String busRouteDirection,String stopId,String stopName,String stoplat,String stoplon)
		{
			
			
			
			String msg = "Stop is added successfully";
			
			try {
				
				getConnection();
				BasicDBObject doc = new BasicDBObject("routeNumber", productId)
				.append("routeColour", "#cc3366").append("routeName", productName)
				.append("busRouteDirection", busRouteDirection).append("stopId", stopId)
				.append("stopName", stopName).append("stoplat", stoplat).append("stoplon", stoplon);
				myCTABus.insert(doc);
				return "Successfull";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "Error while adding the Stop";
			}
			return msg;
			
		}
		public static String updateRoute(String productId,String productName)
		{ 
			
			String msg = "Route is added successfully";
			
			try {
				
				getConnection();
				
				
				BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append("routeName", productName).append("routeNumber", productId));
			
				BasicDBObject doc = new BasicDBObject("routeNumber", productId)
				.append("routeColour", "#cc3366").append("routeName", productName);

				BasicDBObject searchQuery = new BasicDBObject().append("routeNumber", productId);
				
				myCTABusRoutes.update(searchQuery, doc);
				
				return "Successfull";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "Error while adding the Route";
			}
			return msg;
			
		}
		public static String updateStop(String productId,String productName,String busRouteDirection,String stopId,String stopName,String stoplat,String stoplon)
		{ 
			String msg = "Stop is added successfully";
			
			try {
				
				// BasicDBObject newDocument = new BasicDBObject();
				// // if(!productId.equals("")){
				// newDocument.append("$set", new BasicDBObject().append("stoplon", stoplon).append("stoplat", stoplat).append("stopName", stopName).append("stopId", stopId).append("busRouteDirection", busRouteDirection).append("productId", productId).append("productName", productName));

				BasicDBObject searchQuery = new BasicDBObject().append("routeNumber", productId).append("busRouteDirection", busRouteDirection);
				
				BasicDBObject doc = new BasicDBObject("routeNumber", productId)
				.append("routeColour", "#cc3366").append("routeName", productName)
				.append("busRouteDirection", busRouteDirection).append("stopId", stopId)
				.append("stopName", stopName).append("stoplat", stoplat).append("stoplon", stoplon);

				myCTABus.update(searchQuery, doc);
				
				return "Successfull";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "Error while adding the Stop";
			}
			return msg;
			// String updateProductQurey = "UPDATE Stops SET routeNumber=?,routeColour=?,routeName=?,busRouteDirection=?,stopId=?,stopName=?,stoplat=?,stoplon=? where stopId=? and busRouteDirection=?;" ;
			
			
			
		}
		public static String deleteRoute(String productId)
		{   String msg = "Product is deleted successfully";
		try
		{
			
			// BasicDBObject whereQuery = new BasicDBObject();
			// whereQuery.put("routeNumber", productId);
			
			// DBObject doc = myCTABusRoutes.find(whereQuery); //get first document
			// myCTABusRoutes.remove(doc);
			getConnection();
			
			myCTABusRoutes.remove(new BasicDBObject().append("routeNumber", productId));
			
		}
		catch(Exception e)
		{
			msg = "Proudct cannot be deleted";
		}
		return msg;
	}
	public static String deleteStop(String productId)
	{   String msg = "Product is deleted successfully";
	try
	{
		getConnection();
		
		myCTABus.remove(new BasicDBObject().append("stopId", productId));
		
		// getConnection();
		// String deleteproductsQuery ="Delete from Stops where stopId=?";
		// PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		// pst.setString(1,productId);
		
		// pst.executeUpdate();
	}
	catch(Exception e)
	{
		msg = "Proudct cannot be deleted";
	}
	return msg;
}
}