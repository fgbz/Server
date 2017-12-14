/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity;

/**
 * @author yangw786
 *
 * 2017年3月8日下午2:15:27 请求服务返回标识
 * 操作结果
 */
public class OpResult {
	/* 操作成功 */
	public static final int Success = 200;
	/*操作失败*/
	public static final int Failed = 400;
    /*传递给服务端的某些参数错误、无效*/
    public static final int InvalidArgument = 417;
	/*完成服务操作的某些先决条件失败，比如新增的数据编号与现有的重复、保存时输入的案件批文号已存在等 */
	public static final int PreconditionFailed = 412;
	/*超出数据上限*/
	public static final int Overflow = 423;
	
	

     /// <summary>
     /// 操作返回码
     /// </summary>
    public int Code;

	public int getCode() {
		return Code;
	}

	public void setCode(int code) {
		Code = code;
	}
     
	 public OpResult(int code)
     {
         this.Code = code;
     }
    
}
