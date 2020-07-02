package com.zte.shopping.mapper;

import com.zte.shopping.entity.Sequence;

public interface ISequenceMapper
{
	/**
	 * 根据name查询对应的Sequence信息
	 */
	Sequence selectByName(String name);

	void insertSequence(Sequence sequ);
	
	void updateSquenceValue(String productNoPrefix, String value);

	

}
