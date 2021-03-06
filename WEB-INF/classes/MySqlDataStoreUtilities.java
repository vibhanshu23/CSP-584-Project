import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","root1234");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static void Insertproducts()
{
	try{
		
		
		getConnection();
		
		
		String truncatetableacc = "delete from Product_accessories;";
		PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
		pstt.executeUpdate();
		
		String truncatetableprod = "delete from  Productdetails;";
		PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
		psttprod.executeUpdate();
		
				
		
		String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
		"VALUES (?,?,?,?,?,?,?,?);";
		for(Map.Entry<String,Accessory> entry : SaxParserDataStore.accessories.entrySet())
		{   
			String name = "accessories";
	        Accessory acc = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,acc.getId());
			pst.setString(3,acc.getName());
			pst.setDouble(4,acc.getPrice());
			pst.setString(5,acc.getImage());
			pst.setString(6,acc.getRetailer());
			pst.setString(7,acc.getCondition());
			pst.setDouble(8,acc.getDiscount());
			
			pst.executeUpdate();
			
			
		}
		
		for(Map.Entry<String,Console> entry : SaxParserDataStore.consoles.entrySet())
		{   
	        Console con = entry.getValue();
			String name = "consoles";
			
						
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,con.getId());
			pst.setString(3,con.getName());
			pst.setDouble(4,con.getPrice());
			pst.setString(5,con.getImage());
			pst.setString(6,con.getRetailer());
			pst.setString(7,con.getCondition());
			pst.setDouble(8,con.getDiscount());
			
			pst.executeUpdate();
			try{
			HashMap<String,String> acc = con.getAccessories();
			String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
			"VALUES (?,?);";
			for(Map.Entry<String,String> accentry : acc.entrySet())
			{
				PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
				pstacc.setString(1,con.getId());
				pstacc.setString(2,accentry.getValue());
				pstacc.executeUpdate();
			}
			}catch(Exception et){
				et.printStackTrace();
			}
		}
		for(Map.Entry<String,Game> entry : SaxParserDataStore.games.entrySet())
		{   
			String name = "games";
	        Game game = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,game.getId());
			pst.setString(3,game.getName());
			pst.setDouble(4,game.getPrice());
			pst.setString(5,game.getImage());
			pst.setString(6,game.getRetailer());
			pst.setString(7,game.getCondition());
			pst.setDouble(8,game.getDiscount());
			
			pst.executeUpdate();
			
			
		}
		for(Map.Entry<String,Tablet> entry : SaxParserDataStore.tablets.entrySet())
		{   
			String name = "tablets";
	        Tablet tablet = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,tablet.getId());
			pst.setString(3,tablet.getName());
			pst.setDouble(4,tablet.getPrice());
			pst.setString(5,tablet.getImage());
			pst.setString(6,tablet.getRetailer());
			pst.setString(7,tablet.getCondition());
			pst.setDouble(8,tablet.getDiscount());
			
			pst.executeUpdate();
			
			
		}
		
	}catch(Exception e)
	{
  		e.printStackTrace();
	}
} 

public static HashMap<String,Console> getConsoles()
{	
	HashMap<String,Console> hm=new HashMap<String,Console>();
	try 
	{
		getConnection();
		
		String selectConsole="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectConsole);
		pst.setString(1,"consoles");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Console con = new Console(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				
				try
				{
				String selectaccessory = "Select * from Product_accessories where productName=?";
				PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
				pstacc.setString(1,rs.getString("Id"));
				ResultSet rsacc = pstacc.executeQuery();
				
				HashMap<String,String> acchashmap = new HashMap<String,String>();
				while(rsacc.next())
				{    
					if (rsacc.getString("accessoriesName") != null){
					
					 acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
					 con.setAccessories(acchashmap);
					}
					 
				}					
				}catch(Exception e)
				{
						e.printStackTrace();
				}
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Tablet> getTablets()
{	
	HashMap<String,Tablet> hm=new HashMap<String,Tablet>();
	try 
	{
		getConnection();
		
		String selectTablet="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTablet);
		pst.setString(1,"tablets");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Tablet tab = new Tablet(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), tab);
				tab.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Game> getGames()
{	
	HashMap<String,Game> hm=new HashMap<String,Game>();
	try 
	{
		getConnection();
		
		String selectGame="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectGame);
		pst.setString(1,"games");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
				Game game = new Game(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), game);
				game.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


public static HashMap<String,Accessory> getAccessories()
{	
	HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
	try 
	{
		getConnection();
		
		String selectAcc="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		pst.setString(1,"accessories");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), acc);
				acc.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static String addRoute(String productId,String productName)
{
	String msg = "Route is added successfully";
	try{
		
		getConnection();
		String addProductQurey = "INSERT INTO  Routes(routeNumber,routeColour,routeName)" +
		"VALUES (?,?,?);";
		   
			// String name = producttype;
	        			System.out.println( "product" + productName);
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			// pst.setString(1,name);
			pst.setString(1,productId);
			pst.setString(2,"#66cc66");
			pst.setString(3,productName);
			// pst.setString(5,productImage);
			// pst.setString(6,productManufacturer);
			// pst.setString(7,productCondition);
			// pst.setDouble(8,productDiscount);
			
			pst.executeUpdate();
			// try{
			// 	if (!prod.isEmpty())
			// 	{
			// 		String addaprodacc =  "INSERT INTO  Product_accessories(productName,accessoriesName)" +
			// 		"VALUES (?,?);";
			// 		PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
			// 		pst1.setString(1,prod);
			// 		pst1.setString(2,productId);
			// 		pst1.executeUpdate();
					
			// 	}
			// }catch(Exception e)
			// {
			// 	msg = "Erro while adding the product";
			// 	e.printStackTrace();
		
			// }
	}
	catch(Exception e)
	{
		msg = "Erro while adding the Route";
		e.printStackTrace();
		
	}
	return msg;
}
public static String addStop(String productId,String productName,String busRouteDirection,String stopId,String stopName,String stoplat,String stoplon)
{
	String msg = "Stop is added successfully";
	try{
		
		getConnection();
		String addProductQurey = "INSERT INTO  Stops(routeNumber,routeColour,routeName,busRouteDirection,stopId,stopName,stoplat,stoplon)" +
		"VALUES (?,?,?,?,?,?,?,?);";
		   
			// String name = producttype;
	        			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			// pst.setString(1,name);
			pst.setString(1,productId);
			pst.setString(2,"#cc3366");
			pst.setString(3,productName);
			pst.setString(4,busRouteDirection);
			pst.setString(5,stopId);
			pst.setString(6,stopName);
			pst.setString(7,stoplat);
			pst.setString(8,stoplon);
			// pst.setString(5,productImage);
			// pst.setString(6,productManufacturer);
			// pst.setString(7,productCondition);
			// pst.setDouble(8,productDiscount);
			
			pst.executeUpdate();
			// try{
			// 	if (!prod.isEmpty())
			// 	{
			// 		String addaprodacc =  "INSERT INTO  Product_accessories(productName,accessoriesName)" +
			// 		"VALUES (?,?);";
			// 		PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
			// 		pst1.setString(1,prod);
			// 		pst1.setString(2,productId);
			// 		pst1.executeUpdate();
					
			// 	}
			// }catch(Exception e)
			// {
			// 	msg = "Erro while adding the product";
			// 	e.printStackTrace();
		
			// }
	}
	catch(Exception e)
	{
		msg = "Erro while adding the Stop";
		e.printStackTrace();
		
	}
	return msg;
}
public static String updateRoute(String productId,String productName)
{ 
    String msg = "Route is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE Routes SET routeNumber=?,routeColour=?,routeName=? where routeNumber=?;" ;
		
		   
				        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productId);
			pst.setString(2,"#66cc66");
			pst.setString(3,productName);
			pst.setString(4,productId);
			// pst.setString(4,productManufacturer);
			// pst.setString(5,productCondition);
			// pst.setDouble(6,productDiscount);
			// pst.setString(7,productId);
			pst.executeUpdate();
			
			
		
	}
	catch(Exception e)
	{
		msg = "Route cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}
public static String updateStop(String productId,String productName,String busRouteDirection,String stopId,String stopName,String stoplat,String stoplon)
{ 
    String msg = "Stop is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE Stops SET routeNumber=?,routeColour=?,routeName=?,busRouteDirection=?,stopId=?,stopName=?,stoplat=?,stoplon=? where stopId=? and busRouteDirection=?;" ;
		
		   
				        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productId);
			pst.setString(2,"#66cc66");
			pst.setString(3,productName);
			pst.setString(4,busRouteDirection);
			pst.setString(5,stopId);
			pst.setString(6,stopName);
			pst.setString(7,stoplat);
			pst.setString(8,stoplon);
			pst.setString(9,stopId);
			pst.setString(10,busRouteDirection);
			// pst.setString(4,productManufacturer);
			// pst.setString(5,productCondition);
			// pst.setDouble(6,productDiscount);
			// pst.setString(7,productId);
			pst.executeUpdate();
			
			
		
	}
	catch(Exception e)
	{
		msg = "Stop cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}
public static String deleteRoute(String productId)
{   String msg = "Product is deleted successfully";
	try
	{
		
		getConnection();
		String deleteproductsQuery ="Delete from Routes where routeNumber=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);
		
		pst.executeUpdate();
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
		String deleteproductsQuery ="Delete from Stops where stopId=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);
		
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Proudct cannot be deleted";
	}
	return msg;
}
public static void deleteOrder(int orderId,String orderName)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,orderName);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo)
{
	try
	{
	
		getConnection();
		System.out.println("-------"+ userAddress);
		String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,customerName,creditCardNo) "
		+ "VALUES (?,?,?,?,?,?);";	
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setDouble(4,orderPrice);
		pst.setString(5,userAddress);
		pst.setString(6,creditCardNo);
		pst.execute();
	}
	catch(Exception e)
	{
		System.out.println("exception "+ e.getLocalizedMessage());
	}		
}

public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from customerorders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("OrderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
			

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("customerName"),rs.getString("creditCardNo"));
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}


public static void insertUser(String username,String password,String repassword,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
		+ "VALUES (?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  productdetails";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), p);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return hm;			
	}


	
	

// public static HashMap<String,Product> getStopData()
// 	{
// 		HashMap<String,Product> hm=new HashMap<String,Product>();
// 		try
// 		{
// 			getConnection();
// 			Statement stmt=conn.createStatement();
// 			String selectCustomerQuery="select * from Stops";
// 			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
// 			while(rs.next())
// 			{	Product p = new Product(rs.getString("routeNumber"),rs.getString("routeName"),rs.getDouble("5.00"),rs.getString("routeColor"),rs.getString(""),rs.getString(""),rs.getString(""),rs.getDouble(""));//rs.getString(""productManufacturer""),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("productDiscount"));
// 				hm.put(rs.getString("Id"), p);
// 			}
// 		}
// 		catch(Exception e)
// 		{
// 		e.printStackTrace();	
// 		}
// 		return hm;			
// 	}


// 	public static HashMap<String,CTABusObject> getDistinctRoutesHashmap() {
// 		HashMap<String,CTABusObject> arrCTABus = new HashMap<String,CTABusObject>();
// 		try {

// 			getConnection();
// 			Statement stmt=conn.createStatement();
// 			String selectCustomerQuery="select * from Stops";
// 			ResultSet rs = stmt.executeQuery(selectCustomerQuery);

// 			while(rs.next())
// 			{	
				
// 				String id = "";//rs.getString("_id").toString();
// 				String routeNumber = rs.getString("routeNumber");
// 				String routeColour = rs.getString("routeColour").toString();
// 				String routeName = rs.getString("routeName").toString();
// 				String busRouteDirection = "";//rs.getString("busRouteDirection").toString();
// 				String stopId = "";//rs.getString("stopId").toString();
// 				String stopName = "";//rs.getString("stopName").toString();
// 				String stoplat = "";//rs.getString("stoplat").toString();
// 				String stoplon = "";//rs.getString("stoplon").toString();
// 				CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
// 				arrCTABus.put(routeNumber,CTA);
				
				
// 			}
// 			// System.out.println("---------------------------");
// 			// System.out.println(arrCTABus);


// 		} catch (Exception e) {
// 			System.out.println(e.getMessage());
// 		}
// 		return arrCTABus;

// 	}
	
// 	public static ArrayList<CTABusObject> getDistinctRoutes() {
// 		ArrayList<CTABusObject> arrCTABus = new ArrayList<CTABusObject>();
// 		try {

// 			// getConnection();
// 			// DBCursor cursor = myCTABusRoutes.find();
// 			// // distinct("categories", Object.class).iterator();
// 			// // collection.distinct("countries", String.class);

// 			// // Iterator<String> files = myCTABus.distinct("routeNumber").iterator();
// 			// // while(files.hasNext()) {
// 			// //   System.out.println(files.next());
// 			// // }
// 			// while (cursor.hasNext()) {
// 				getConnection();
// 				Statement stmt=conn.createStatement();
// 				String selectCustomerQuery="select * from Stops";
// 				ResultSet rs = stmt.executeQuery(selectCustomerQuery);
	
// 				while(rs.next())
// 				{	
// 				// BasicDBObject obj = (BasicDBObject) cursor.next();
// 				String id = rs.getString("_id").toString();
// 				String routeNumber = rs.getString("routeNumber").toString();
// 				String routeColour = rs.getString("routeColour").toString();
// 				String routeName = rs.getString("routeName").toString();
// 				String busRouteDirection = "";//rs.getString("busRouteDirection").toString();
// 				String stopId = "";//rs.getString("stopId").toString();
// 				String stopName = "";//rs.getString("stopName").toString();
// 				String stoplat = "";//rs.getString("stoplat").toString();
// 				String stoplon = "";//rs.getString("stoplon").toString();
// 				CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
// 				arrCTABus.add(CTA);
// 				// System.out.println(routeNumber);
// 				// System.out.println(routeColour);
// 				// System.out.println(routeName);
// 				// System.out.println(busRouteDirection);
// 				// System.out.println(stopId);
// 				// System.out.println(stoplat);

// 				// String id = files.next().toString();
// 				// String routeNumber = files.next().toString();//rs.getString("routeNumber").toString();
// 				// String routeColour = files.next().toString();//rs.getString("routeColour").toString();
// 				// String routeName = files.next().toString();//rs.getString("routeName").toString();
// 				// String busRouteDirection = files.next().toString();//rs.getString("busRouteDirection").toString();
// 				// String stopId = files.next().toString();//rs.getString("stopId").toString();
// 				// String stopName = files.next().toString();//rs.getString("stopName").toString();
// 				// String stoplat = files.next().toString();//rs.getString("stoplat").toString();
// 				// String stoplon = files.next().toString();//rs.getString("stoplon").toString();
// 				// CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
// 				// arrCTABus.add(CTA);
// 			}
// 			// System.out.println("---------------------------");
// 			// System.out.println(arrCTABus);


// 		} catch (Exception e) {
// 			System.out.println(e.getMessage());
// 		}
// 		return arrCTABus;

// 	}

// 	// public static HashMap<String,CTABusObject> getDistinctRoutesHashmap() {
// 	// 	HashMap<String,CTABusObject> arrCTABus = new HashMap<String,CTABusObject>();
// 	// 	try {

// 	// 		// getConnection();
// 	// 		// DBCursor cursor = myCTABusRoutes.find();
// 	// 		// while (cursor.hasNext()) {
// 	// 			getConnection();
// 	// 			Statement stmt=conn.createStatement();
// 	// 			String selectCustomerQuery="select * from Stops";
// 	// 			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
	
// 	// 			while(rs.next())
// 	// 			{	
// 	// 			BasicDBObject obj = (BasicDBObject) cursor.next();
// 	// 			String id = rs.getString("_id").toString();
// 	// 			String routeNumber = rs.getString("routeNumber").toString();
// 	// 			String routeColour = rs.getString("routeColour").toString();
// 	// 			String routeName = rs.getString("routeName").toString();
// 	// 			String busRouteDirection = "";//rs.getString("busRouteDirection").toString();
// 	// 			String stopId = "";//rs.getString("stopId").toString();
// 	// 			String stopName = "";//rs.getString("stopName").toString();
// 	// 			String stoplat = "";//rs.getString("stoplat").toString();
// 	// 			String stoplon = "";//rs.getString("stoplon").toString();
// 	// 			CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
// 	// 			arrCTABus.put(routeNumber,CTA);
// 	// 		}
// 	// 		// System.out.println("---------------------------");
// 	// 		// System.out.println(arrCTABus);


// 	// 	} catch (Exception e) {
// 	// 		System.out.println(e.getMessage());
// 	// 	}
// 	// 	return arrCTABus;

// 	// }

// 	public static ArrayList<CTABusObject> getStopsForRouteNumber(String routeString) {
// 		ArrayList<CTABusObject> arrCTABus = new ArrayList<CTABusObject>();
// 		try {

// 			getConnection();
// 			Statement stmt=conn.createStatement();
// 			String selectCustomerQuery="select * from Stops where routeNumber = "+routeString+" and (busRouteDirection = 'Westbound' or busRouteDirection = 'Northbound')";
// 			ResultSet rs = stmt.executeQuery(selectCustomerQuery);

// 			while(rs.next())
// 			{	
// 			// getConnection();
// 			// BasicDBObject whereQuery = new BasicDBObject();
// 			// whereQuery.put("routeNumber", routeString);
// 			// var stringDir = "";
// 			// Iterator<String> files = myCTABus.distinct("busRouteDirection",whereQuery).iterator();
// 			// while(files.hasNext()) {
// 			// 	stringDir = files.next();

// 			// }

		
// 			// DBCursor cursor = myCTABus.find(whereQuery);
// 			// while(cursor.hasNext()) {
// 			// 	BasicDBObject obj = (BasicDBObject) cursor.next();
// 				String busRouteDirection = rs.getString("busRouteDirection").toString();

// 				// if (stringDir.equals(busRouteDirection)){
// 					String id = rs.getString("_id").toString();
// 					String routeNumber = rs.getString("routeNumber").toString();
// 					String routeColour = rs.getString("routeColour").toString();
// 					String routeName = rs.getString("routeName").toString();
// 					String stopId = rs.getString("stopId").toString();
// 					String stopName = rs.getString("stopName").toString();
// 					String stoplat = rs.getString("stoplat").toString();
// 					String stoplon = rs.getString("stoplon").toString();
// 					CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
// 					arrCTABus.add(CTA);
// 				// }
				
// 			}
			
// 			// System.out.println("---------------------------");
// 			// System.out.println(arrCTABus);

			
// 		} catch (Exception e) {
// 			System.out.println(e.getMessage());
// 		}
// 		return arrCTABus;

// 	}

// 	public static ArrayList<CTABusObject> getNearestStops() {
// 		ArrayList<CTABusObject> arrCTABus = new ArrayList<CTABusObject>();
// 		System.out.println("---------------------------");
// 		System.out.println("----reched nearest stops-----");
// 		try {

// 			// getConnection();
// 			// List<String> stopsID = myCTABus.distinct("stopId");
// 			getConnection();
// 			Statement stmt=conn.createStatement();
// 			String selectCustomerQuery="select * from Stops";
// 			ResultSet rs = stmt.executeQuery(selectCustomerQuery);

// 			while(rs.next())
// 			{	
// 			for( String stop:stopsID){
// 				System.out.println("stopID" + stop);

// 				BasicDBObject whereQuery = new BasicDBObject();
// 				whereQuery.put("stopId", stop);
// 				DBCursor cursor = myCTABus.find(whereQuery);
				  

// 				while (cursor.hasNext()) {
// 					BasicDBObject obj = (BasicDBObject) cursor.next();
// 					String id = rs.getString("_id").toString();
// 					String routeNumber = rs.getString("routeNumber").toString();
// 					String routeColour = rs.getString("routeColour").toString();
// 					String routeName = rs.getString("routeName").toString();
// 					String busRouteDirection = rs.getString("busRouteDirection").toString();
// 					String stopId = rs.getString("stopId").toString();
// 					String stopName = rs.getString("stopName").toString();
// 					String stoplat = rs.getString("stoplat").toString();
// 					String stoplon = rs.getString("stoplon").toString();
// 					CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
// 					arrCTABus.add(CTA);
					
// 				}
// 			}
			
// 			System.out.println("---------------------------");
// 			System.out.println(arrCTABus);

// 		} catch (Exception e) {
// 			System.out.println(e.getMessage());
// 		}
// 		return arrCTABus;

// 	}

// 	public static HashMap<String,Product> getDistinctRoutesHashmapForAutoComplete() {
// 		HashMap<String,Product> arrCTABus = new HashMap<String,Product>();
// 		try {
// 			getConnection();
// 			Statement stmt=conn.createStatement();
// 			String selectCustomerQuery="select * from Stops";
// 			ResultSet rs = stmt.executeQuery(selectCustomerQuery);

// 			while(rs.next())
// 			{	
// 			// getConnection();
// 			// DBCursor cursor = myCTABusRoutes.find();
// 			// while (cursor.hasNext()) {
// 				// BasicDBObject obj = (BasicDBObject) cursor.next();
// 				String id = rs.getString("_id").toString();
// 				String routeNumber = rs.getString("routeNumber").toString();
// 				String routeColour = rs.getString("routeColour").toString();
// 				String routeName = rs.getString("routeName").toString();
// 				String busRouteDirection = "";//rs.getString("busRouteDirection").toString();
// 				String stopId = "";//rs.getString("stopId").toString();
// 				String stopName = "";//rs.getString("stopName").toString();
// 				String stoplat = "";//rs.getString("stoplat").toString();
// 				String stoplon = "";//rs.getString("stoplon").toString();
				
// 				Product p = new Product(routeNumber,routeName,5.00,"",routeNumber,"New","Train",0);
				
// 				CTABusObject CTA = new CTABusObject(id, routeNumber, routeColour, routeName, busRouteDirection, stopId, stopName, stoplat, stoplon);
// 				arrCTABus.put(routeNumber,p);
// 			}
// 			// System.out.println("---------------------------");
// 			// System.out.println(arrCTABus);


// 		} catch (Exception e) {
// 			System.out.println(e.getMessage());
// 		}
// 		return arrCTABus;

	// }

}
