package com.lo.deviscan.beans;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class CenterDao {
	private HibernateTemplate template;  
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	} 

	public void create(Center center){  
		Session session = template.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.save(center); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	public Center get(String id){
		Center center = null;
		Session session = template.getSessionFactory().openSession();
	      try{
	    	  center = (Center)session.load(Center.class, Integer.parseInt(id));
	      }catch (HibernateException e) {
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return center;
	}

    public void update(Center center){  
    	Session session = template.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.update(center); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }  
	} 

	public void delete(String id){  
		Session session = template.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.delete(get(id)); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }  
	}  

	public List<Center> getAll(){  
	    List<Center> list=new ArrayList<Center>();  
	    list=template.loadAll(Center.class);  
	    return list;  
	}  
}
