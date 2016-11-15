package com.lo.deviscan.beans;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class PatientDao {
	private HibernateTemplate template;  
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	} 

	public void create(Patient patient){  
		Session session = template.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.save(patient); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	public Patient get(String id){
		Patient patient = null;
		Session session = template.getSessionFactory().openSession();
	      try{
	    	  patient = (Patient)session.load(Patient.class, Integer.parseInt(id));
	      }catch (HibernateException e) {
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return patient;
	}

    public void update(Patient patient){  
    	Session session = template.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.update(patient); 
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

	public List<Patient> getAll(){  
	    List<Patient> list=new ArrayList<Patient>();  
	    list=template.loadAll(Patient.class);  
	    return list;  
	}  
}
