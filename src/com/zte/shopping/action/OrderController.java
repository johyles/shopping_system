package com.zte.shopping.action;

import com.zte.shopping.util.CartVo;
import com.zte.shopping.entity.Item;
import com.zte.shopping.entity.Order;
import com.zte.shopping.entity.User;
import com.zte.shopping.service.IItemService;
import com.zte.shopping.service.IOrderService;
import com.zte.shopping.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

//=====================================================================全部用
@Controller
@RequestMapping("/order")
public class OrderController {
//    查看订单编号页面myOrders.jsp
//    订单详情页面orderDetail.jsp
    @Autowired
    private IItemService itemService;
    @Autowired
    private IOrderService orderService;

    //先写订单详情,默认用户名id=1,以及订单编号为SD1001或WQ1002
    @RequestMapping(value = "/orderDetail")
    public ModelAndView selectOrder(String orderno,HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        //List<Item> list=itemService.selectItem("SD1001");
        List<Item> list=itemService.selectItem(orderno);
        Double sumPrice=0.00;
        if(list.size()>0){
            System.out.println("有数据哦");
            for(Item item:list){
                sumPrice+=item.getPrice();
                System.out.println("--"+item.getId()+"--"+item.getProduct().getName()+"--");
            }
            mv.addObject("itemList",list);
            mv.addObject("count",list.size());
            mv.addObject("sumprice",sumPrice);
            mv.addObject("orderNo",orderno);
        }else{
            System.out.println("没有数据哦");
        }
        mv.setViewName("orderDetail");
        return mv;
    }

    //第二步，来到我的订单页面
    //从登录界面应该传用户的session，这里直接赋session的user为1
    @RequestMapping(value = "/myOrders")
    public ModelAndView goOrder(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        if (session.getAttribute("user")!=null){
             User user= (User) session.getAttribute("user");
            System.out.println("user="+user.getUserName()+"="+user.getUserId());
            List<Order> list=orderService.selectOrderByUser(user.getUserId());
            modelAndView.addObject("orderList",list);
        }
        modelAndView.setViewName("myOrders");
        return modelAndView;
    }

    /**对于购物车部分我的想法是
     * 1.加入购物车即存入item,存入一个固定订单id等于用户id
     * 2.在结算之前，不在order中生成订单编号
     * 3.修改删除都在item中完成
     * 4.结算时先算总价，如果提交订单了，就在order中生成编号，并且将订单编号都等于用户id的item
     * 里面的数据都提出来，重新插入item中这次订单id由随机数生成，
     * 然后清空item中用户id为订单的数据，这样购物车就清空了
     */

    //第三步：写我的购物车界面
    //
    @RequestMapping(value = "/createOrder")
    @ResponseBody
    public String createOrder(HttpSession session)
    {
        CartVo cart = (CartVo)session.getAttribute("cart");
        User user = (User)session.getAttribute("user");
        System.out.println(user.getUserName()+"=========="+cart.getItems().get(0).getNum());
        String no = orderService.createOrder(user, cart);
        System.out.println("no"+no);
        session.setAttribute("cart", new CartVo());

        return no;
    }

}
