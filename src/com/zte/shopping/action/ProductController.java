package com.zte.shopping.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.shopping.constant.DictConstant;
import com.zte.shopping.constant.ResponseCodeConstant;
import com.zte.shopping.entity.Product;
import com.zte.shopping.util.ProductParameter;
import com.zte.shopping.entity.ProductType;
import com.zte.shopping.exception.ProductTypeExistException;
import com.zte.shopping.exception.RequestParameterException;
import com.zte.shopping.service.IProductService;
import com.zte.shopping.service.IProductTypeService;
import com.zte.shopping.util.ParameterUtil;
import com.zte.shopping.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @program: shopping
 * @description: 商品管理
 * @author: SkyCloud
 * @create: 2020-06-24 14:45
 **/
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private IProductTypeService iProductTypeService;

    /**
     * 查询商品列表
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(String pageNo, String pageSize)
    {
        ModelAndView modelAndView = new ModelAndView();
        if (ParameterUtil.isnull(pageNo))
        {
            pageNo = DictConstant.PAGE_NO;
        }
        if (ParameterUtil.isnull(pageSize))
        {
            pageSize = DictConstant.PAGE_SIZE;
        }
        // 设置分页
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        // 查询所有数据
        List<Product> productList = iProductService.findAll();
        // 将查询出的所有数据进行分页设置,封装为PageInfo对象
        PageInfo<Product> pageProductList = new PageInfo<Product>(productList);

        if (pageProductList.getList().isEmpty() && pageProductList.getPageNum() != -1)
        {
            for(int i = 0; i < pageProductList.getList().size(); i++)
            {
                System.out.println(pageProductList.getList().get(i).getName() + "---------");
            }
        }
        List<ProductType> TypeList = iProductTypeService.findAll();
        /*List<String> typeList = new ArrayList<String>();
        for (int i=0;i<TypeList.size();i++){
            typeList.add(i,TypeList.get(i).getName());
            typeList.
        }*/

        modelAndView.addObject("pageProductList", pageProductList);
        modelAndView.addObject("TypeList", TypeList);

        modelAndView.setViewName("backend/productManager");

        return modelAndView;
    }

    /**
     * 添加商品类型(采用ajax来添加)
     *
     * 我们需要让SpringMVC知道,我们需要返回的是数据模型 ---> 采用@ResponseBoby注解标注在当前方法addType上
     *
     * @RequestMapping注解中的produces属性表示:设置响应体的格式
     * 即在此方法上加上  @RequestMapping(value = "/addType",produces="text/html;charset=utf-8") 为了解决此方法的响应数据乱码问题
     *
     */
    @ResponseBody
    @RequestMapping(value = "/addProduct")
    public ResponseResult addProduct(String product_name,Integer price,Integer product_type_id )
    {
        ResponseResult result = new ResponseResult();

        try
        {
            iProductService.addProduct(product_name,price,product_type_id);

            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

        } catch (ProductTypeExistException e)
        {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);

        } catch (RequestParameterException e)
        {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);

        }  catch (Exception e)
        {
            e.printStackTrace();
            result.setMessage("服务器内部异常");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);

        }

        return result;
    }

    /**
     * 改变商品类型的状态:启用/禁用
     *    当选择的商品类型   "启用"  ---> "禁用"
     *    当选择的商品类型   "禁用"  ---> "启用"
     */
    @RequestMapping("/modifyProductStatus")
    public ModelAndView modifyStatus(String id, String status, String pageNo)
    {
        ModelAndView  modelAndView = new ModelAndView();

        iProductService.modifyStatus(id, status);

        modelAndView.setViewName("redirect:findAll?pageNo" + pageNo);

        return modelAndView;
    }

    // 根据 商品类型ID 查询   商品类型信息
    @ResponseBody
    @RequestMapping("/findById")
    public ResponseResult findById(String id)
    {
        ResponseResult  result = new ResponseResult();

        try
        {
            Product product = iProductService.findById(id);

            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
            result.setReturnObject(product);
        } catch (RequestParameterException e)
        {
            result.setMessage(e.getMessage());

            // 响应状态码为请求参数有误,值为:3
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);

        } catch (Exception e)
        {
            e.printStackTrace();
            result.setMessage("服务器内部异常");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);

        }

        return result;
    }

    //根据 商品类型ID 修改   商品类型信息
    @ResponseBody
    @RequestMapping("/modifyName")
    public ResponseResult modifyName(String id,String product_name, Integer price,Integer product_type)
    {
        ResponseResult  result = new ResponseResult();

        try
        {
            iProductService.modifyName(id, product_name,price,product_type);

            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
        } catch (Exception e)
        {
            e.printStackTrace();
            result.setMessage("服务器内部异常");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
        }

        return result;
    }






}
