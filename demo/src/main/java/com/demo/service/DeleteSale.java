
package com.demo.service;

import com.demo.config.DatabaseContextHolder;
import com.demo.config.DatabaseType;
import com.demo.entity.Sale;
import com.demo.mapper.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteSale {

    @Autowired
    SaleMapper saleMapper;
    @Autowired
    Sale sale;

    public int delete(String saleno){
        DatabaseContextHolder.setDatabaseType(DatabaseType.lend_app);
        int a = 0;
        if(saleno.equals("")){
            a = saleMapper.deleteAllDevice();
        } else {
            sale.setSaleNo(saleno);
            a = saleMapper.deleteSaleByNO(sale);
        }
        return a;
    }
}
