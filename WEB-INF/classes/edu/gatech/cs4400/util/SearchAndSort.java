package edu.gatech.cs4400.util;

import java.util.ArrayList;
import java.util.List;

public class SearchAndSort {
	private String searchString = "";
	public SearchAndSort() {
		
	}
	public List sort(List inventoryPage) {
		
		return null;
	}
	
	/*public List search(List inventoryList) {
		List filterInventory = new ArrayList();
		for(int i=0;i<inventoryList.size();i++) {
			if(-1 != ((PersistOps)inventoryList.get(i)).toString().indexOf(this.getSearchString())) {
				filterInventory.add(inventoryList.get(i));
			} else {
				continue;
			}
		}
		this.jim_clear_search();
		return filterInventory;
	}*/
	//ACTION: clear_search
	public String jim_clear_search() {
		this.searchString = "";
		return null;
	}
	//PROPERTY searchString
	public String getSearchString() {
		return this.searchString;
	} public void setSearchString(String searchString) {
		this.searchString = searchString;
	}	
}
