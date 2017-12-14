package phalaenopsis.common.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Ss_HITS {
    
	private BigDecimal id;

	private String sessionid;

	private Date hittime;

	private String url;

	private String useragent;

	private String side;

	private BigDecimal processtime;

	private BigDecimal concurrent;

	private String username;
	
	
	
	private String ip;
	
	

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public Date getHittime() {
		return hittime;
	}

	public void setHittime(Date hittime) {
		this.hittime = hittime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public BigDecimal getProcesstime() {
		return processtime;
	}

	public void setProcesstime(BigDecimal processtime) {
		this.processtime = processtime;
	}

	public BigDecimal getConcurrent() {
		return concurrent;
	}

	public void setConcurrent(BigDecimal concurrent) {
		this.concurrent = concurrent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	

	
}