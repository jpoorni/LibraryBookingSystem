package biz;

import java.util.ArrayList;
import java.util.List;

import model.Items;
import dao.DAOFactory;

public class itemsHandler {

	public static List<Items> findItems(String searchBy,String searchValue){
		
		List<Items> itemsList=new ArrayList<Items>();
		
		if(searchBy!=null){
			if(searchBy.equalsIgnoreCase("Category")){
				itemsList= DAOFactory.CreateItemDao().searchItemsByCategory(searchBy, searchValue);	
			} else if(searchBy.equalsIgnoreCase("Author")){
				itemsList= DAOFactory.CreateItemDao().searchItemsByAuthor(searchBy, searchValue);	
			} else if(searchBy.equalsIgnoreCase("Publisher")){
				itemsList= DAOFactory.CreateItemDao().searchItemsByPublisher(searchBy, searchValue);	
			}
		}
		
		return itemsList;
			
	}
	
	public static Items findItemById(String itemId){		
		return DAOFactory.CreateItemDao().findItemById(itemId);
	}
	public static List<String> searchItemsForSelection(String searchValue){
		return DAOFactory.CreateItemDao().searchItemsForSelection(searchValue);		
	}
	public static List<Items> findMultipleItemById(List<String> requestItemsIdList){
		System.out.println("itemId List  "+requestItemsIdList);
		//Items itemsDto=null;	
		
		List<String> requestItemsIdList_temp= new ArrayList<String>();
		String str=null;
		for(int i=0; i<requestItemsIdList.size();i++){
			  str=requestItemsIdList.get(i);
		       str=str.replace(str.substring(str.length()-1), "");//remove unwanted / from end of itemid
		       requestItemsIdList_temp.add(str);
		}
		
		System.out.println(" after cleaning itemId List  "+requestItemsIdList);
		return DAOFactory.CreateItemDao().findMultipleItemById(requestItemsIdList_temp);
	}
	
}
