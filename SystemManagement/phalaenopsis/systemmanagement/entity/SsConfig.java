package phalaenopsis.systemmanagement.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/***
 * 系统参数配置表
 * @author dongdongt
 *
 */
@TableName("SS_CONFIG")
public class SsConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键Id
     */
	private Long id;
	/**
	 * 配置项内容
	 */
	private String content;
	/**
	 * 模块名称
	 */
	private String modular;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 创建时间
	 */
	private Date creatime;
	/**
	 * 修改人
	 */
	private String modifier;
	/**
	 * 修改时间
	 */
	private Date modifitime;
	/**
	 * 类型
	 */
	private String type;
	
	private Integer count;//辅助字段
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getModular() {
		return modular;
	}

	public void setModular(String modular) {
		this.modular = modular;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatime() {
		return creatime;
	}

	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifitime() {
		return modifitime;
	}

	public void setModifitime(Date modifitime) {
		this.modifitime = modifitime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}