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

import edu.gatech.cs4400.bl.DataBackend;

public class DBSessionManager {
	private DataBackend DB; //DataBackendHandle
	
	public DBSessionManager (DataBackend DB) {
		this.DB = DB;
		DB.start_session();
	}
	
	public DBSessionManager () {
		DB = new DataBackend();
		DB.start_session();
	}

	public void closeSession() {
		DB.close_session();
	}
	public DataBackend fetchHandel() {
		return DB;
	}
	public List update_list(List to_update) {
		System.out.println("Size: " + to_update.size());
		for(int i=0;i<to_update.size();i++) {
			DB.getSession().refresh(to_update.get(i));
		}
		return to_update;
	}
}
