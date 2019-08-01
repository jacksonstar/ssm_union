package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> findAll() throws Exception {
        return iProductDao.findAll();

    }

    @Override
    public void save(Product product) {
        iProductDao.save(product);
    }
}
