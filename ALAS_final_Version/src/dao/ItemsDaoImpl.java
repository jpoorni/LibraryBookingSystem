package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Items;
import model.LoanTxnDto;

public class ItemsDaoImpl  implements ItemsDao {

	@Override
	public List<Items> searchItems(String searchBy, String searchValue) {
		
		List<Items> itemsList= new ArrayList<Items>();
		String sqlQ= "select * from items where upper(status)='Available' ";
		Items itemsDto=null;
		
		try{
			Connection conn=DbManager.getConnection();
			if(searchBy!=null){
				if(searchBy.equalsIgnoreCase("Category")){
					if((searchValue!=null && searchValue.equalsIgnoreCase("All") )){
						 sqlQ= sqlQ+ " AND TypeId in (select TypeId from itemtype)";
					}else{
						 sqlQ= sqlQ+ " AND TypeId = (select ? from itemtype)";
					}
				} else if(searchBy.equalsIgnoreCase("Author")){
					//sqlQ= "select * from items where upper(status)='Available' ";
				} else if(searchBy.equalsIgnoreCase("Publisher")){
					
				}
			}	
			
			PreparedStatement  ps = conn.prepareStatement(sqlQ);
			if(! (searchValue!=null && searchValue.equalsIgnoreCase("All"))){
				ps.setString(1, searchValue);
			}
			
			System.out.println("sqlQuery[ "+sqlQ+" ]");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
		    	  itemsDto=new Items();
		    	  itemsDto.setItemId(rs.getString("ItemId"));
		    	  itemsDto.setTypeId(rs.getInt("TypeId"));
		    	  itemsDto.setItemName(rs.getString("ItemName"));
		    	  itemsDto.setIsbn(rs.getInt("ISBN"));
		    	  itemsDto.setPublisher(rs.getString("Publisher"));
		    	  itemsDto.setPublishedYear(rs.getDate("PublishedYear")); //  convert java.sql.Date to java.util.Date
		    	  itemsDto.setAuthor(rs.getString("Author"));
		    	  itemsDto.setStatus(rs.getString("Status"));
		    	  itemsDto.setLocId(rs.getInt("LocId"));
		    	  System.out.println(itemsDto);
		    	  itemsList.add(itemsDto);	  
		      }
		    System.out.println("Number of items  found are "+itemsList.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return itemsList;
	}

	public Items findItemById(String itemId){
		Items itemsDto=null;
		
		try{
			//1.  DB connection
			Connection conn=DbManager.getConnection();
			String  sqlQ= "select * from items where Itemid= ?";
			PreparedStatement  ps = conn.prepareStatement(sqlQ);
			ps.setString(1, itemId);
			ResultSet rs = ps.executeQuery();
			 while(rs.next()){
		    	  itemsDto=new Items();
		    	  itemsDto.setItemId(rs.getString("ItemId"));
		    	  itemsDto.setTypeId(rs.getInt("TypeId"));
		    	  itemsDto.setItemName(rs.getString("ItemName"));
		    	  itemsDto.setIsbn(rs.getInt("ISBN"));
		    	  itemsDto.setPublisher(rs.getString("Publisher"));
		    	  itemsDto.setPublishedYear(rs.getDate("PublishedYear"));  // convert java.sql.Date to java.util.Date
		    	  itemsDto.setAuthor(rs.getString("Author"));
		    	  itemsDto.setStatus(rs.getString("Status"));
		    	  itemsDto.setLocId(rs.getInt("LocId"));
		    	  System.out.println(itemsDto);
		    	  
		      }
			
		}catch(Exception e){
			e.printStackTrace();
			return itemsDto;
		}
		 System.out.println(itemsDto);
		return itemsDto;
		
	}

	public List<String> searchItemsForSelection(String searchValue){
		 
		 String sqlQuery=null;
		 boolean isCat=false;
		 boolean isAuth=false;
		 boolean isPub=false;
		 
		 System.out.println("searchValue-->" + searchValue);
		 List<String> itemList=new ArrayList<String>();
		
		 if (searchValue.equalsIgnoreCase("Category")){
			 isCat=true;
			 sqlQuery= "select DISTINCT(Typename)  from ItemType";
		 }else if (searchValue.equalsIgnoreCase("Author")){
			 isAuth=true;
			 sqlQuery= "select DISTINCT(AUTHOR)  from items";
		 } else if (searchValue.equalsIgnoreCase("Publisher")){
			 isPub=true;
			 sqlQuery= " select DISTINCT(Publisher) from items";
		 }
		 System.out.println("Query-->"+sqlQuery);
		 
		 try{
			Connection conn=DbManager.getConnection();
			PreparedStatement  ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 if(isCat)
					 itemList.add(rs.getString("Typename"));
				 else if(isAuth)
					 itemList.add(rs.getString("Author"));
				 else if(isPub)
					 itemList.add(rs.getString("Publisher"));
		      }
			
		}catch(Exception e){
			e.printStackTrace();			
		}
		 System.out.println("No of items returned "+ itemList.size());
		return itemList;
		
	}

	public List<Items> searchItemsByCategory(String searchBy, String searchValue){
		Items itemsDto=null;
		List<Items> itemsList= new ArrayList<Items>();
		String sqlQ= "select * from items where upper(status)='Available' ";
		boolean isAll=(searchValue!=null && searchValue.equalsIgnoreCase("All") ) ? true : false;
			
		try{
			Connection conn=DbManager.getConnection();
			if(isAll){
				 sqlQ= sqlQ+ " AND TypeId in (select TypeId from itemtype)";
			}else{
				 sqlQ= sqlQ+ " AND TypeId = (select TypeId from itemtype where upper(typeName)=? )";
			}
			
			PreparedStatement  ps = conn.prepareStatement(sqlQ);
			if(!isAll){
				ps.setString(1, searchValue.toUpperCase());
			}
			
			System.out.println("sqlQuery[ "+sqlQ+" ]");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
		    	  itemsDto=new Items();
		    	  itemsDto.setItemId(rs.getString("ItemId"));
		    	  itemsDto.setTypeId(rs.getInt("TypeId"));
		    	  itemsDto.setItemName(rs.getString("ItemName"));
		    	  itemsDto.setIsbn(rs.getInt("ISBN"));
		    	  itemsDto.setPublisher(rs.getString("Publisher"));
		    	  itemsDto.setPublishedYear(rs.getDate("PublishedYear")); //  convert java.sql.Date to java.util.Date
		    	  itemsDto.setAuthor(rs.getString("Author"));
		    	  itemsDto.setStatus(rs.getString("Status"));
		    	  itemsDto.setLocId(rs.getInt("LocId"));
		    	  System.out.println(itemsDto);
		    	  itemsList.add(itemsDto);	  
		      }
		    System.out.println("Number of items  found are "+itemsList.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return itemsList;
	}
	public List<Items> searchItemsByAuthor(String searchBy, String searchValue){
		boolean isAll=(searchValue!=null && searchValue.equalsIgnoreCase("All") )?true:false;
		List<Items> itemsList= new ArrayList<Items>();
		String sqlQ= "select * from items where upper(status)='Available' ";
		Items itemsDto=null;		
		try{
			Connection conn=DbManager.getConnection();
			if(!isAll){
				 sqlQ= sqlQ+ " AND upper(author) = ?";
			}
			
			PreparedStatement  ps = conn.prepareStatement(sqlQ);
			if(!isAll){
				ps.setString(1, searchValue.toUpperCase());
			}
			
			System.out.println("sqlQuery[ "+sqlQ+" ]");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
		    	  itemsDto=new Items();
		    	  itemsDto.setItemId(rs.getString("ItemId"));
		    	  itemsDto.setTypeId(rs.getInt("TypeId"));
		    	  itemsDto.setItemName(rs.getString("ItemName"));
		    	  itemsDto.setIsbn(rs.getInt("ISBN"));
		    	  itemsDto.setPublisher(rs.getString("Publisher"));
		    	  itemsDto.setPublishedYear(rs.getDate("PublishedYear")); //  convert java.sql.Date to java.util.Date
		    	  itemsDto.setAuthor(rs.getString("Author"));
		    	  itemsDto.setStatus(rs.getString("Status"));
		    	  itemsDto.setLocId(rs.getInt("LocId"));
		    	  System.out.println(itemsDto);
		    	  itemsList.add(itemsDto);	  
		      }
		    System.out.println("Number of items  found are "+itemsList.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return itemsList;
	}
	public List<Items> searchItemsByPublisher(String searchBy, String searchValue){
		boolean isAll=(searchValue!=null && searchValue.equalsIgnoreCase("All") ) ? true:false;
		List<Items> itemsList= new ArrayList<Items>();
		String sqlQ= "select * from items where upper(status)='Available' ";
		Items itemsDto=null;		
		try{
			Connection conn=DbManager.getConnection();
			if(!isAll){
				 sqlQ= sqlQ+ " AND upper(publisher) = ?";
			}
			
			PreparedStatement  ps = conn.prepareStatement(sqlQ);
			if(!isAll){
				ps.setString(1, searchValue.toUpperCase());
			}
			
			System.out.println("sqlQuery[ "+sqlQ+" ]");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
		    	  itemsDto=new Items();
		    	  itemsDto.setItemId(rs.getString("ItemId"));
		    	  itemsDto.setTypeId(rs.getInt("TypeId"));
		    	  itemsDto.setItemName(rs.getString("ItemName"));
		    	  itemsDto.setIsbn(rs.getInt("ISBN"));
		    	  itemsDto.setPublisher(rs.getString("Publisher"));
		    	  itemsDto.setPublishedYear(rs.getDate("PublishedYear"));   //convert java.sql.Date to java.util.Date
		    	  itemsDto.setAuthor(rs.getString("Author"));
		    	  itemsDto.setStatus(rs.getString("Status"));
		    	  itemsDto.setLocId(rs.getInt("LocId"));
		    	  System.out.println(itemsDto);
		    	  itemsList.add(itemsDto);	  
		      }
		    System.out.println("Number of items  found are "+itemsList.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return itemsList;
	}

	
	public List<Items> findMultipleItemById(List<String> requestItemsIdList){
		System.out.println("Number of items  found are "+requestItemsIdList);
		Items itemsDto=null;	
		StringBuffer itemid_str= new StringBuffer("");
		List<Items> itemsList= new ArrayList<Items>();
		
		for(int i=0; i<requestItemsIdList.size();i++){
				String str=requestItemsIdList.get(i);
				//   str=str.replace(str.substring(str.length()-1), "");
				itemid_str.append("'"+str+"'");
				
				if(i<requestItemsIdList.size()-1){
					itemid_str.append(",");				
				}
			}
		
			System.out.println("itemid_str >>> "+itemid_str);
			String sqlQ= "select * from items where itemId in ("+itemid_str+ ") ";
			System.out.println("sqlQuery[ "+sqlQ+" ]");
			
			try{
				Connection conn=DbManager.getConnection();
				PreparedStatement  ps = conn.prepareStatement(sqlQ);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
			    	  itemsDto=new Items();
			    	  itemsDto.setItemId(rs.getString("ItemId"));
			    	  itemsDto.setTypeId(rs.getInt("TypeId"));
			    	  itemsDto.setItemName(rs.getString("ItemName"));
			    	  itemsDto.setIsbn(rs.getInt("ISBN"));
			    	  itemsDto.setPublisher(rs.getString("Publisher"));
			    	  itemsDto.setPublishedYear(rs.getDate("PublishedYear"));   //convert java.sql.Date to java.util.Date
			    	  itemsDto.setAuthor(rs.getString("Author"));
			    	  itemsDto.setStatus(rs.getString("Status"));
			    	  itemsDto.setLocId(rs.getInt("LocId"));
			    	  System.out.println(itemsDto);
			    	  itemsList.add(itemsDto);	  
			      }
			    System.out.println("Number of items  found are "+itemsList.size());
				
			}catch(Exception e){
				e.printStackTrace();
			}
		
			return itemsList;
	}
	

}
