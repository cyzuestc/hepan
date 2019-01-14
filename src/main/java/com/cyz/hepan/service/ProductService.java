package com.cyz.hepan.service;

import com.cyz.hepan.dao.ProductDAO;
import com.cyz.hepan.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;

    public boolean get(int pid){
        if (productDAO.getByPid(pid) == null)return false;
        return true;
    }
    public void add(Product p){
        productDAO.save(p);
    }
}
