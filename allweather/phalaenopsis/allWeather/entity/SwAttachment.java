package phalaenopsis.allWeather.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.io.Serializable;


/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author dongdongt
 * @since 2017-07-13
 */
@TableName("SW_ATTACHMENT")
public class SwAttachment implements Serializable {
    private static final long serialVersionUID = 1L;

	private Date timing;
    /**
     * 附件附加信息
     */

	private String extrainfo;
    /**
     * 最后更新时间
     */

	private Date lastupdate;
    /**
     * 照片方位角
     */

	private Double angle;
    /**
     * 照片Y坐标（纬度）
     */

	private Double y;
    /**
     * 照片X坐标（经度）
     */

	private Double x;
    /**
     * 附件来源（0：客户端（内业）上传附件，1：移动端（外业）巡查拍照）
     */

	private Double source;
    /**
     * 附件所属模块
     */

	private String module;
    /**
     * 附件上传时间
     */

	private Date uploadtime;
    /**
     * 说明
     */

	private String explain;
    /**
     * 服务器存储的缩略图文件名
     */

	private String thumbfile;
    /**
     * 附件关联的业务数据ID
     */

	private String bizid;
    /**
     * 服务器存储的实际文件名
     */

	private String actualfile;
    /**
     * 附件大小（字节）
     */

	private Double filesize;
    /**
     * 附件的扩展名，不带“.”（附件类型）
     */

	private String fileext;
    /**
     * 附件名称
     */

	private String filename;
    /**
     * 主键ID（附件ID）
     */

	private String id;
	
	/**
	 * 是否有效
	 */
	private Integer isDelete;
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getTiming() {
		return timing;
	}

	public void setTiming(Date timing) {
		this.timing = timing;
	}

	public String getExtrainfo() {
		return extrainfo;
	}

	public void setExtrainfo(String extrainfo) {
		this.extrainfo = extrainfo;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	public Double getAngle() {
		return angle;
	}

	public void setAngle(Double angle) {
		this.angle = angle;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getSource() {
		return source;
	}

	public void setSource(Double source) {
		this.source = source;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getThumbfile() {
		return thumbfile;
	}

	public void setThumbfile(String thumbfile) {
		this.thumbfile = thumbfile;
	}

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public String getActualfile() {
		return actualfile;
	}

	public void setActualfile(String actualfile) {
		this.actualfile = actualfile;
	}

	public Double getFilesize() {
		return filesize;
	}

	public void setFilesize(Double filesize) {
		this.filesize = filesize;
	}

	public String getFileext() {
		return fileext;
	}

	public void setFileext(String fileext) {
		this.fileext = fileext;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}