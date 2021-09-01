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

package edu.gatech.cs4400.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageNavigator {
	protected List           currentBook     = null;
	protected int            pageNumber      = 1;
	protected int            pageSize        = 10;

	public PageNavigator() {}

	//METHOD numPages
	public int numPages(List book) {
		return (int)Math.ceil((double)book.size() / (double)pageSize);
	}
	//METHOD pageStart
	public int pageIndexStart(List sanityCheck) {
		int startIndex = (pageNumber - 1) * pageSize;
		//if(startIndex >= sanityCheck.size()) {
		//	return sanityCheck.size() - this.pageSize;
		//} else {
			return startIndex;
		//}
	}
	//METHOD pageEnd
	public int pageIndexEnd(List sanityCheck) {
		int endIndex = this.pageIndexStart(sanityCheck) + pageSize;
		if(endIndex >= sanityCheck.size()) {
			return sanityCheck.size();
		} else {
			return endIndex;
		}
	}
	//METHOD resetPage
	private void adjustPageSize(int bookSize) {
		if(this.pageSize < 10 && this.pageNumber > 1) {
			if(bookSize < 10) {
				this.pageSize = bookSize;
			} else {
				this.pageSize = 10;
			}
			this.pageNumber = 1;
		}
	}
	
	/**
	 * Be careful how you use this!
	 * This is here to help reset a 
	 * page to what it used to be.
	 * It probably doesn't have any
	 * problems, but it was originally
	 * for valid numbers to be entered.
	 * @param resetedPageSize
	 * @param resetedPageNumber
	 * @return void
	 */
	//METHOD resetPage
	public void resetPageSize(int resetedPageSize, int resetedPageNumber) {
			this.pageSize   = resetedPageSize;
			this.pageNumber = resetedPageNumber;
	}
	
	//METHOD currentPage 
	public List currentPage(List listToPagify) {
		return listToPagify.subList(pageIndexStart(listToPagify), pageIndexEnd(listToPagify));
	}
	//METHOD getPage
	public List getPage(List listToPagify) {
		if(listToPagify.size() < 1) {
			return listToPagify;
		}
		
		if(pageNumber > numPages(listToPagify)) {
			pageNumber = numPages(listToPagify);
		} else if (pageNumber < 1) {
			pageNumber = 1;
		} 
		
		if(pageSize > listToPagify.size()) {
			pageSize = listToPagify.size();
		} else if (pageSize < 1) {
			pageSize = 1;
		}
		
		this.adjustPageSize(listToPagify.size());

		return this.currentPage(listToPagify);
	}
	//ACTION: prevPage
	public String jim_prev_page() {
		this.pageNumber--;
		return null;
	}
	//ACTION: nextPage
	public String jim_next_page() {
		this.pageNumber++;
		return null;
	}
	//ACTION firstPage
	public String jim_goto_first_page() {
		this.pageNumber=1;
		return null;
	}
	//ACTION lastPage
	public String jim_goto_last_page() {
		this.pageNumber=this.numPages(currentBook);
		return null;
	}
	
	//PROPERTY pageNumber
	public int getPageNumber() {
		return pageNumber;
	} public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	//PROPERTY pageSize
	public int getPageSize() {
		return pageSize;
	} public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getCurrentBook() {
		return currentBook;
	} public void setCurrentBook(List currentBook) {
		this.currentBook = currentBook;
	}	
}
