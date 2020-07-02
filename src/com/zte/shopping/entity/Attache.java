package com.zte.shopping.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 上传附件表
 * 
 * @author liyan
 *
 */
public class Attache implements Serializable
{
	private static final long serialVersionUID = 1L;

	// 附件表主键
	private Integer attacheId;
	
	// 上传文件类型   附件类型为  头像 或者  生活照
	private String fileType;
	
	// 上传文件路径
	private String filePath;
	
	// 上传日期
	private Date createDate;

	// 谁上传的
	private Integer userId;
	
	public Attache()
	{
		super();
	}
	
	public Integer getAttacheId() 
	{
		return attacheId;
	}
	
	public void setAttacheId(Integer attacheId) 
	{
		this.attacheId = attacheId;
	}
	
	public String getFileType() 
	{
		return fileType;
	}
	
	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}
	
	public String getFilePath() 
	{
		return filePath;
	}
	
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
	
	public Date getCreateDate()
	{
		return createDate;
	}
	
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Integer getUser()
	{
		return userId;
	}

	public void setUser(Integer user)
	{
		this.userId = userId;
	}
	
}
