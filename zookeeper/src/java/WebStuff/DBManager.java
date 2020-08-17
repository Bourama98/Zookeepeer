/*
 * CS-499
 * 5-2 Milestone Four: Enhancement Three: Databases
 * Bourama Mangara
 * 02 August 2020
 */
package WebStuff;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.SessionFactory;


/**
 *
 * @author Bourama Mangara
 */
public class DBManager {
    
    SessionFactory sessionFactory;
    Session session;
    Query query;
    Zookeeper zookeeper = new Zookeeper();

    public DBManager() {
      try{
      sessionFactory = new Configuration().configure().buildSessionFactory();
      session = sessionFactory.openSession();
      } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
public Zookeeper getOneUser(String name){// Retrieve a single user info by name 
    
    session.beginTransaction(); // Start a connection
    String queryOne = "From Zookeeper where name = :x";
    query = session.createQuery(queryOne);
    System.out.println(query.toString());
    query.setString("x", name);
    Object aUser = query.uniqueResult();
    Zookeeper akeeper = (Zookeeper) aUser;
    session.getTransaction().commit(); // commit transaction 
    return akeeper;
   
} 
public DBManager getDBManager(){
return this;
}   
    
}
