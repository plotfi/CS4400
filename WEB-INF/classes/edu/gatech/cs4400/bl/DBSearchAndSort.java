/***************************************************************************
 *   Copyright (C) 2006 by Puyan Lotfi                                     *
 *   puyan@puyan.org                                                       *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 *   This program is distributed in the hope that it will be useful,       *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         *
 *   GNU General Public License for more details.                          *
 *                                                                         *
 *   You should have received a copy of the GNU General Public License     *
 *   along with this program; if not, write to the                         *
 *   Free Software Foundation, Inc.,                                       *
 *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.             *
 ***************************************************************************/

package edu.gatech.cs4400.bl;
import java.util.List;

/**
 * @author Puyan Lotfi
 * @param DB
 * @param currentTable
 * @param
 * @param
 * @param
 *
 */
public class DBSearchAndSort {
	private DataBackend DB;
	private String currentTable;
	private String currentSearchField;
	private String currentSortField; 
	private String currentSortOrder;
	
	public DBSearchAndSort(DataBackend DB) {
		this.DB = DB;
	}
	public DBSearchAndSort(DataBackend DB, String currentTable, String currentSearchField, String currentSortField, String currentSortOrder) {
		this.DB = DB;
		this.setFields( currentTable,  currentSearchField,  currentSortField,  currentSortOrder);
	}
	public void setFields(String currentTable, 
			String currentSearchField, 
			String currentSortField, String currentSortOrder) {
		this.currentTable = currentTable;
		this.currentSearchField = currentSearchField;
		this.currentSortField = currentSortField;
		this.currentSortOrder = currentSortOrder;
	}
	
	public List hqlSelectConsole() {
		return null;
	}
	
	public List hqlSelect(String query) {
		return DB.getSession().createQuery(query).list();
	}
	
	public List searchIncomplete( String searchQuery) {
		String tableVar = "tableVar";
		return DB.getSession().createQuery("from" + " " + 
				                                                  this.currentTable +  " " + 
				                                                  tableVar + " " + 
				                                                   "fetch all properties" + " " + 
				                                                   "where" + " " + 
				                                                   tableVar + "." + 
				                                                   this.currentSearchField + " " +
				                                                   "like" + " " + 
				                                                   "'%"+ searchQuery + "%'" + " " +
				                                                   "order by" + " " +
				                                                   tableVar + "." + 
				                                                   this.currentSortField + " " + 
				                                                   this.currentSortOrder).list();
	}
	public List searchComplete(String searchQuery) {
		String tableVar = "tableVar";
		return DB.getSession().createQuery("from" + " " + 
				                                                  this.currentTable +  " " + 
				                                                  tableVar + " " + 
				                                                   "order by" + " " +
				                                                   this.currentSortField + "." + 
				                                                   this.currentSearchField + " " + 
				                                                   this.currentSortOrder + " " +   
				                                                   "fetch all properties" + " " + 
				                                                   "where" + " " + 
				                                                   tableVar + "." + 
				                                                   this.currentSearchField + " " +
				                                                   "=" + " " + 
				                                                   searchQuery).list();
	}
	public List getSortedList() {
		String tableVar = "tableVar";
		return DB.getSession() .createQuery("from" + " " + 
																  this.currentTable  +  " " + 
																  tableVar + " " + 
				                                                   "order by" + " " +
				                                                   tableVar +  "." + 
				                                                   this.currentSortField + " " +
				                                                   this.currentSortOrder).list();
	}
	public String getCurentSortField() {
		return currentSortField;
	}
	public void setCurentSortField(String curentSortField) {
		this.currentSortField = curentSortField;
	}
	public String getCurrentSearchField() {
		return currentSearchField;
	}
	public void setCurrentSearchField(String currentSearchField) {
		this.currentSearchField = currentSearchField;
	}
	public String getCurrentTable() {
		return currentTable;
	}
	public void setCurrentTable(String currentTable) {
		this.currentTable = currentTable;
	}
	public DataBackend getDB() {
		return DB;
	}
	public void setDB(DataBackend db) {
		DB = db;
	}
	public String getCurrentSortOrder() {
		return currentSortOrder;
	}
	public void setCurrentSortOrder(String currentSortOrder) {
		this.currentSortOrder = currentSortOrder;
	}
}
