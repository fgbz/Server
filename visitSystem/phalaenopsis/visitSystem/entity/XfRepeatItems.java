package phalaenopsis.visitSystem.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * 重复信访实体
 * @author dongdongt
 *
 */
@TableName("XF_REPEAT_ITEMS")
public class XfRepeatItems implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

	private Long id;
    /**
     * 信访方式
     */

	private String xftype;
    /**
     * 信访日期
     */

	private Date xfdate;
    /**
     * 信访人数
     */

	private Integer xfpeopelcount;
    /**
     * 备注
     */

	private String xfremark;
    /**
     * 外键
     */

	private Long xfregisterid;
	/**
	 * 信访方式名称
	 */
	private String xftypename;
	/**
	 * 信访次数（辅助字段）
	 */
	private Integer xfCount;
	public String getXftypename() {
		return xftypename;
	}

	public void setXftypename(String xftypename) {
		this.xftypename = xftypename;
	}

	public Integer getXfCount() {
		return xfCount;
	}

	public void setXfCount(Integer xfCount) {
		this.xfCount = xfCount;
	}

	public Long getId() {
		return id;
	}
	public String getXftype() {
		return xftype;
	}

	public void setXftype(String xftype) {
		this.xftype = xftype;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getXfdate() {
		return xfdate;
	}

	public void setXfdate(Date xfdate) {
		this.xfdate = xfdate;
	}
	public Integer getXfpeopelcount() {
		return xfpeopelcount;
	}

	public void setXfpeopelcount(Integer xfpeopelcount) {
		this.xfpeopelcount = xfpeopelcount;
	}

	public String getXfremark() {
		return xfremark;
	}

	public void setXfremark(String xfremark) {
		this.xfremark = xfremark;
	}

	public Long getXfregisterid() {
		return xfregisterid;
	}

	public void setXfregisterid(Long xfregisterid) {
		this.xfregisterid = xfregisterid;
	}

}