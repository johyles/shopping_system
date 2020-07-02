package com.zte.shopping.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.shopping.constant.DictConstant;
import com.zte.shopping.constant.ResponseCodeConstant;
import com.zte.shopping.entity.Dept;
import com.zte.shopping.exception.DeptExistException;
import com.zte.shopping.exception.RequestParameterException;
import com.zte.shopping.service.IDeptManagerService;
import com.zte.shopping.service.IProductService;
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
 * @description: 部门管理
 * @author: SkyCloud
 * @create: 2020-06-24 15:25
 **/
@Controller
@RequestMapping("/dept")
public class DeptManagerController {

    @Autowired
    private IDeptManagerService iDeptManagerService;
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
        List<Dept> typeList = iDeptManagerService.findAll();
        // 将查询出的所有数据进行分页设置,封装为PageInfo对象
        PageInfo<Dept> pageDeptList = new PageInfo<Dept>(typeList);

        if (pageDeptList.getList().isEmpty() && pageDeptList.getPageNum() != -1)
        {
            for(int i = 0; i < pageDeptList.getList().size(); i++)
            {
                System.out.println(pageDeptList.getList().get(i).getDeptName() + "---------");
            }
        }

        modelAndView.addObject("pageDeptList", pageDeptList);

        modelAndView.setViewName("backend/deptManager");

        return modelAndView;
    }



    @ResponseBody
    @RequestMapping("/addDept")
    public ResponseResult addDept(String deptName,String remark,String deptNum,Integer fatherDeptId)
    {
        ResponseResult result = new ResponseResult();

        try
        {
            iDeptManagerService.addDept(deptName,remark,deptNum,fatherDeptId);

            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

        } catch (DeptExistException e)
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
     * 根据 商品类型ID 查询   商品类型信息
     */
    @ResponseBody
    @RequestMapping("/findById")
    public ResponseResult findById(String id)
    {
        ResponseResult  result = new ResponseResult();


        try
        {
            Dept dept = iDeptManagerService.findById(id);

            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
            result.setReturnObject(dept);
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

    /**
     * 根据 商品类型ID 修改   商品类型信息
     */
    @ResponseBody
    @RequestMapping("/modifyName")
    public ResponseResult modifyName(String id, String name,String duty)
    {
        ResponseResult  result = new ResponseResult();

        try
        {
            iDeptManagerService.modifyName(id,name,duty);

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


    /**
     * 改变商品类型的状态:启用/禁用
     *    当选择的商品类型   "启用"  ---> "禁用"
     *    当选择的商品类型   "禁用"  ---> "启用"
     */
    @RequestMapping("/modifyDeptStatus")
    public ModelAndView modifyStatus(String id, String status, String pageNo)
    {
        ModelAndView  modelAndView = new ModelAndView();

        iDeptManagerService.modifyStatus(id, status);

        modelAndView.setViewName("redirect:findAll?pageNo=" + pageNo);

        return modelAndView;
    }
}
