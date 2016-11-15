package com.lo.deviscan.beans;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class DoctorDao {
	private HibernateTemplate template;  
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	} 

	public void create(Doctor doctor){  
		Session session = template.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.save(doctor); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	public Doctor get(String id){
		Doctor doctor = null;
		Session session = template.getSessionFactory().openSession();
	      try{
	    	  doctor = (Doctor)session.load(Doctor.class, Integer.parseInt(id));
	      }catch (HibernateException e) {
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return doctor;
	}

    public void update(Doctor doctor){  
    	Session session = template.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.update(doctor); 
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

	public List<Doctor> getAll(){  
	    List<Doctor> list=new ArrayList<Doctor>();  
	    list=template.loadAll(Doctor.class);  
	    return list;  
	}  
}
