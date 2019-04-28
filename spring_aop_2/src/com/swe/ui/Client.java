package com.swe.ui;

import com.swe.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        CustomerService customerService = (CustomerService) ac.getBean("customerService");
        customerService.saveCustomer();
        customerService.updateCustomer(1);
        customerService.deleteCustomer();
    }
}
