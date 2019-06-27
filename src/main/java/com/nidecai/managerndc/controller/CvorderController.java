package com.nidecai.managerndc.controller;

import com.github.pagehelper.PageInfo;
import com.nidecai.managerndc.common.annoation.ConvenientStore;
import com.nidecai.managerndc.common.codeutil.GsonUtil;
import com.nidecai.managerndc.common.codeutil.ResultUtil;
import com.nidecai.managerndc.entity.Cvorder;
import com.nidecai.managerndc.entity.OrderAddress;
import com.nidecai.managerndc.service.CvorderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

/**
 * @author river
 * @title: CvorderController
 * @projectName manager-ndc
 * @description: 订单列表的分页显示
 * @date 2019/6/2013:30
 */
@RestController
@RequestMapping(value = "/orderList", method = RequestMethod.GET)
public class CvorderController {
    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(CvorderController.class);
    @Autowired
    private CvorderService cvorderService;

    //查询全部订单
    @RequestMapping(value = "/pageList")
    @ConvenientStore(value = "marketStatistics")
    public String listOrder(HttpServletRequest request) {
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("size");
        int page = Integer.parseInt(pageStr);
        int size = Integer.parseInt(sizeStr);
        Cvorder cvorder = new Cvorder();
        PageInfo<Cvorder> orderList = cvorderService.getOrderList(cvorder, page, size);
        return GsonUtil.GsonString(ResultUtil.getSuccess(orderList));
    }

    //修改订单
}
