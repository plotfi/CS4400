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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Puyan Lotfi
 * @param sessionFactory
 * @param session
 * @param tx
 * 
 * The purpose of this class is to abstract the use of hibernate calls
 * so that if it is decided to use hibernate in a different way to do 
 * something (ie call a different method, or do a different HQL 
 * query) the it only requires a change in a method here. 
 */
public class DataBackend {
	private static SessionFactory sessionFactory = null;
	private Session session;
	private Transaction tx;
	
	//static initializer
	static {
		try {
			sessionFactory = new Configuration().
												  configure().
												  buildSessionFactory();
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
	public void start_session() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	}
	public void close_session() {
		tx.commit(); //TODO: Account for exceptions, and role back, try commit catch excepton -> role back
		session.close();
	}
	
	public void begintx() {
		tx = session.beginTransaction();
	}
	
	public void txcommit () {
		tx.commit();
	}
	
	public Session getSession() {
		return this.session;
	}
	public void dumpTable (List tableToDump) {
		for(int i=0; i<tableToDump.size();i++) {
				System.out.println(tableToDump.get(i));
		}
	}
	public List DBTableToListObject(String HQLTableName) {
		return session.createQuery("from" + " " + HQLTableName).list();
	}
	public Object[] DBTableToArrayObject(String HQLTableName) {
		return session.createQuery("from" + " " + HQLTableName).list().toArray();
	}
	public void addElement(Object inserted) {
		session.save(inserted);
	}
	public void removeElement(Object removed) {
		session.delete(removed);
	}
	public Object getByID(Class classType, Integer primarykey) {
		return session.get(classType, primarykey);
	}
	public void flush_objects() {
		session.flush();
	}
}
