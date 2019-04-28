package com.swe.service.impl;

import com.swe.service.CustomerService;
import org.springframework.stereotype.Service;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Override
    public void saveCustomer() {
        System.out.println("保存客户");
    }

    @Override
    public void updateCustomer(int i) {
        System.out.println("更新客户");
    }

    @Override
    public void deleteCustomer() {
        System.out.println("删除客户");
    }
}


