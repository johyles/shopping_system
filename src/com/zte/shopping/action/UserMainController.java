package com.zte.shopping.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.shopping.constant.DictConstant;
import com.zte.shopping.entity.Product;
import com.zte.shopping.entity.ProductType;
import com.zte.shopping.service.IProductService;
import com.zte.shopping.service.IProductTypeService;
import com.zte.shopping.util.ParameterUtil;
import com.zte.shopping.util.ProductParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @program: shopping
 * @description: 前端主页
 * @author: SkyCloud
 * @create: 2020-06-30 10:17
 **/
@Controller
@RequestMapping("main")
public class UserMainController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private IProductTypeService iProductTypeService;

    /**
     * 前台--产品管理页面
     *
     * 根据条件查询商品信息
     *
     * @param      minPrice	          最低价格
     * @param      maxPrice	          最高价格
     * @param      productTypeId      商品类型
     * @param      status             商品类型的状态		必带条件
     */
    @RequestMapping("/showMain")
    public ModelAndView findProductFuzzyParamList(ProductParameter parameter, String pageNo, String pageSize)
    {
        ModelAndView  modelAndView = new ModelAndView();

        if (ParameterUtil.isnull(pageNo))
        {
            pageNo = DictConstant.PAGE_NO;
        }

        if (ParameterUtil.isnull(pageSize))
        {
            pageSize = DictConstant.INDEX_PAGE_SIZE;
        }

        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));


        List<Product> productList = iProductService.findProductFuzzyParamList(parameter);

        PageInfo<Product> pageProductList = new PageInfo<Product>(productList);

        modelAndView.addObject("pageProductList", pageProductList);
        modelAndView.addObject("parameter", parameter);

        List<ProductType> typeList = iProductTypeService.findAll();
        modelAndView.addObject("typeList", typeList);

        modelAndView.setViewName("main");

        return modelAndView;
    }
//
//    //我的订单
//    @RequestMapping("/myOrders")
//    public String myOrders()
//    {
//        return "/myOrders";
//    }

    //订单
    @RequestMapping("/order")
    public String order()
    {
        return "/order";
    }

    //购物车
    @RequestMapping("/cart")
    public String cart()
    {
        return "/cart";
    }

    //会员中心
    @RequestMapping("/center")
    public String center()
    {
        return "/center";
    }
}
