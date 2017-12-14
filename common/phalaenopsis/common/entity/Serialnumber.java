package phalaenopsis.common.entity;

import java.util.Date;
import java.io.Serializable;


/**
 * 流水单号实体
 * @author dongdongt
 *
 */
public class Serialnumber implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 流水单号
     */

	private String serialnum;
    /**
     * 年份
     */

	private Integer year;
    /**
     * 类型 模块
     */

	private String type;
    /**
     * 创建人
     */

	private String creator;
    /**
     * 创建时间
     */

	private Date creationtime;
    /**
     * 修改人
     */

	private String modifier;
    /**
     * 修改时间
     */

	private Date modifiertime;
    /**
     * 主键id
     */

	private Long id;


	public String getSerialnum() {
		return serialnum;
	}

	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifiertime() {
		return modifiertime;
	}

	public void setModifiertime(Date modifiertime) {
		this.modifiertime = modifiertime;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}