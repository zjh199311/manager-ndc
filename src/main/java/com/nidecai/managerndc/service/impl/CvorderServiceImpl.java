package com.nidecai.managerndc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nidecai.managerndc.entity.Cvorder;
import com.nidecai.managerndc.entity.OrderAddress;
import com.nidecai.managerndc.mapper.CvorderMapper;
import com.nidecai.managerndc.mapper.OrderAddressMapper;
import com.nidecai.managerndc.service.CvorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author river
 * @title: CvorderServiceImpl
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/6/2414:59
 */
@Service
public class CvorderServiceImpl implements CvorderService {
    @Autowired
    private CvorderMapper cvorderMapper;
    @Autowired
    private OrderAddressMapper orderAddressMapper;

    @Override
    public PageInfo<Cvorder> getOrderList(Cvorder cvorder, int page, int size) {
        PageHelper.startPage(page, size);
        List<Cvorder> cvorderList = cvorderMapper.select(cvorder);
        for (Cvorder cvCvorder : cvorderList) {
            OrderAddress orderAddress = new OrderAddress();
            Example example = new Example(OrderAddress.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("riderSn",cvCvorder.getOrderSn());
            List<OrderAddress> orderAddresses = orderAddressMapper.selectByExample(example);
            for (OrderAddress address : orderAddresses){
                cvCvorder.setOrderAddress(address);
            }
        }
        return  new PageInfo<Cvorder>(cvorderList);
    }
}