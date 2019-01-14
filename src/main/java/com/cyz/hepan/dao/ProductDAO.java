package com.cyz.hepan.dao;

import com.cyz.hepan.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product,Integer>{
    Product getByPid(int pid);
}
