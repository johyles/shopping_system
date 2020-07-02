package com.zte.shopping.action;

import com.zte.shopping.constant.ResponseCodeConstant;
import com.zte.shopping.util.CartVo;
import com.zte.shopping.exception.RequestParameterException;
import com.zte.shopping.exception.UserNotLoginException;
import com.zte.shopping.service.ICartService;
import com.zte.shopping.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @program: shopping
 * @description: 购物车
 * @author: SkyCloud
 * @create: 2020-07-01 09:27
 **/
@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ICartService iCartService;
    /**
     * 添加购物车
     */
    @ResponseBody
    @RequestMapping("/addCart")
    public ResponseResult addCart(Integer productId, HttpSession session)
    {
        ResponseResult result = new ResponseResult();

        CartVo cartVo = (CartVo)session.getAttribute("cart");
        try
        {
            iCartService.addCart(cartVo, productId);

            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

        } catch (RequestParameterException e)
        {
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);

        } catch (UserNotLoginException e)
        {
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_LOGIN_TIMEOUT);

        } catch (Exception e)
        {
            e.printStackTrace();
            result.setMessage("服务器内部异常");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
        }

        return result;
    }

    /**
     *  修改 "我的购物车" 中 商品数量
     */
    @ResponseBody
    @RequestMapping("/modifyNum")
    public ResponseResult modifyNum(String productId, String num, HttpSession session)
    {
        ResponseResult result = new ResponseResult();

        CartVo cartVo = (CartVo)session.getAttribute("cart");

        System.out.println(cartVo.getItems().get(0).getNum());

        try
        {
            // 修改 "我的购物车" 中 商品数量
            iCartService.modifyNum(productId, num, cartVo);
            //session.setAttribute("cartvo",cartVo1);
            System.out.println("修改成功");
            System.out.println("数量"+cartVo.getItems().get(0).getNum()+"\n"+cartVo.getItems().get(1).getNum());

            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

        } catch (Exception e)
        {
            e.printStackTrace();
            result.setMessage("服务器内部异常");
            System.out.println("服务器内部异常");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
        }

        return result;
    }


    //我的购物车" 中  根据商品ID  删除  "我的购物车" 中的某个商品信息
    @ResponseBody
    @RequestMapping("/removeByProductId")
    public ResponseResult removeByProductId(String productId, HttpSession session)
    {
        ResponseResult result = new ResponseResult();

        CartVo cartVo = (CartVo)session.getAttribute("cart");

        try
        {
            iCartService.removeByProductId(productId, cartVo);

            result.setMessage("成功");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

        } catch (RequestParameterException e)
        {
            result.setMessage(e.getMessage());
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);

        } catch (Exception e)
        {
            e.printStackTrace();
            result.setMessage("服务器内部异常");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
        }

        return result;
    }


    //"我的购物车" 中 删除选中项(支持多个商品删除)
    @ResponseBody
    @RequestMapping("/removeByProductIds")
    public ModelAndView removeByProductIds(String[] productIds, HttpSession session)
    {
        ModelAndView modelAndView = new ModelAndView();

        CartVo cartVo = (CartVo)session.getAttribute("cart");

        try
        {

            for (String productId : productIds)
            {
                iCartService.removeByProductId(productId, cartVo);
            }
        } catch (RequestParameterException e)
        {
            modelAndView.addObject("cartMsg", e.getMessage());
        } catch (Exception e)
        {
            e.printStackTrace();
            modelAndView.addObject("cartMsg", "服务器内部异常");
        }

        modelAndView.setViewName("redirect:show");

        return modelAndView;
    }


    //"我的购物车" 中  删除我的购物车
    @RequestMapping("/clearCart")
    public String clearCart(HttpSession session)
    {
        //  创建一个空的购物车  覆盖原来session中同名的key("cart")
        session.setAttribute("cart", new CartVo());

        return "cart";  // 跳转到cart.jsp页面
    }


    //"我的购物车" 中  结算
    @RequestMapping("/settleAccounts")
    public String settleAccounts()
    {
        return "order";   // 跳转到order.jsp
    }
}
