package com.zte.shopping.mapper;


import com.zte.shopping.entity.Attache;
import org.apache.ibatis.annotations.Param;

public interface FileUploadMapper
{


	public int deleteById(@Param("id") Integer id);

	public int addPath(@Param("id")Integer id, @Param("fangWenPath")String fangWenPath);

	public Attache selectById(@Param("userId")Integer userId);
}
