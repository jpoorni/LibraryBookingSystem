package dao;

import java.util.List;

import model.Items;

public interface ItemsDao {

	public List<Items> searchItems(String searchBy, String searchValue);
	public Items findItemById(String itemId);
	public List<String> searchItemsForSelection(String searchValue);
	public List<Items> searchItemsByCategory(String searchBy, String searchValue);
	public List<Items> searchItemsByAuthor(String searchBy, String searchValue);
	public List<Items> searchItemsByPublisher(String searchBy, String searchValue);
	public List<Items> findMultipleItemById(List<String> requestItemsIdList);
}
