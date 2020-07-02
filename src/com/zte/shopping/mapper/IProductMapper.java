package com.zte.shopping.mapper;

import com.zte.shopping.entity.Product;
import com.zte.shopping.util.ProductParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IProductMapper {

    /**
     * 查询所有商品信息
     */
    public List<Product> selectAll();

    /**
     * 根据名称查询对应的商品类型信息
     */
    public Product selectByName(@Param("name")String name);

    /**
     * 添加商品类型信息
     */
    public void insertProduct(@Param("name") String product_name,
                              @Param("price")Integer price,
                              @Param("type")Integer product_type_id,
                              @Param("status")int status);

    /**
     * 根据 商品类型的id  修改  商品类型状态
     */
    public void updateStatus(int parseInt, int typeStatus);

    /**
     * 根据 商品类型的id  查询  商品类型信息
     */
    public Product selectById(@Param("id") Integer parseInt);

    /**
     * 根据 商品类型的id  修改  商品类型名称
     */
    public void updateName(int parseInt, String product_name, Integer price,Integer type);

    /**
     * 根据条件组合查询商品列表(支持模糊查询)
     */
    List<Product> selectByParamList(ProductParameter parameter);


}
