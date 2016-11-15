package com.lo.deviscan.beans;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class BillDao {
	private HibernateTemplate template;  
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	} 

	public void create(Bill bill){  
		Session session = template.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.save(bill); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	public Bill get(String id){
		Bill bill = null;
		Session session = template.getSessionFactory().openSession();
	      try{
	    	  bill = (Bill)session.load(Bill.class, Integer.parseInt(id));
	      }catch (HibernateException e) {
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return bill;
	}

    public void update(Bill bill){  
    	Session session = template.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.update(bill); 
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

	public List<Bill> getAll(){  
	    List<Bill> list=new ArrayList<Bill>();  
	    list=template.loadAll(Bill.class);  
	    return list;  
	} 
	
	public Double getTodaysCollection(User loggedinUser){
		List<Double> amounts = new ArrayList<Double>();
		Session session = template.getSessionFactory().openSession();
	      try{
	    	  String hql = "Select sum(bill.amount) from Bill bill where bill.createdBy=:createdBy";      
	    	  Query query = session.createQuery(hql);
	    	  query.setParameter(":createdBy", loggedinUser);
	    	  amounts = query.list();
	      }catch (HibernateException e) {
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return amounts.get(0);
	}
}
