package com.zte.shopping.mapper;

import com.zte.shopping.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDeptManagerMapper {

    /**
     * 查询所有商品信息
     */
    public List<Dept> selectAll();

    Dept selectByName(@Param("name") String name);

    int insertDept(@Param("name") String name,@Param("remark")String remark,
                   @Param("deptNum")String deptNum,@Param("fatherDeptId")Integer fatherDeptId,
                   @Param("deptStatusEnable") int deptStatusEnable);

    public Dept selectById(@Param("id") int i);

    public int updateName(@Param("id") int i, @Param("name") String name,@Param("duty")  String duty);

    public int updateStatus(@Param("id")int i,@Param("deptStatus") int deptStatus);

    public List<Dept> selectDeptAll();

}
