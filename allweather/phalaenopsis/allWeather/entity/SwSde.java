package phalaenopsis.allWeather.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import phalaenopsis.allWeather.enums.MobileSpotType;

import java.io.Serializable;



/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author chiz
 * @since 2017-07-21
 */
@TableName("SW_SDE")
public class SwSde implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 关联的MapSpot主键
	 */

	private Long mid;
	/**
	 * 图斑的空间坐标
	 */
	
	private String shape;

	/**
	 * 状态
	 */
	@JsonIgnore
	private Integer Node;

	/**
	 * 图斑类型
	 */
	private MobileSpotType spotType;

	/**
	 * 图斑号
	 */
	private String spotNumber;
	

	public Integer getNode() {
		return Node;
	}

	public void setNode(Integer node) {
		Node = node;
	}

	public MobileSpotType getSpotType() {
		return MobileSpotType.getSpotType(this.getNode());
		//return spotType;
	}

	public void setSpotType(MobileSpotType spotType) {
		this.spotType = spotType;
	}

	public String getSpotNumber() {
		return spotNumber;
	}

	public void setSpotNumber(String spotNumber) {
		this.spotNumber = spotNumber;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

}