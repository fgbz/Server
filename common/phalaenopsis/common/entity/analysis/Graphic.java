/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

import java.util.Map;

import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * @author yangw786
 *
 * 2017年5月5日下午4:36:13
 */
public class Graphic {
	private Polygon geometry;

    public Map<String, Object> attributes;
 
	public Polygon getGeometry() {
		return geometry;
	}

	public void setGeometry(Polygon geometry) {
		this.geometry = geometry;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}
