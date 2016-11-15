package com.lo.deviscan.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Factory {
	
	public static Object getBean(String beanName){
		Resource r=new ClassPathResource("/com/lo/deviscan/beans/applicationContext.xml");  
	    BeanFactory factory=new XmlBeanFactory(r);
	    return  factory.getBean(beanName);
	}

}
