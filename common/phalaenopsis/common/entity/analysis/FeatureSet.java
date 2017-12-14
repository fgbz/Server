/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import phalaenopsis.satellitegraph.entity.SpatialReference;

/**
 * @author yangw786
 *
 * 2017年5月5日下午4:31:26
 */
public class FeatureSet {

	public String displayFieldName;

	public List<Graphic> features;

	public Map<String, String> fieldAliases;

	public List<FieldItem> fields;

	public String globalIdFieldName;

	public String geometryType;

	public String objectIdFieldName;

	public SpatialReference spatialReference;

	public String getDisplayFieldName() {
		return displayFieldName;
	}

	public void setDisplayFieldName(String displayFieldName) {
		this.displayFieldName = displayFieldName;
	}

	public List<Graphic> getFeatures() {
		return features;
	}

	public void setFeatures(List<Graphic> features) {
		this.features = features;
	}

	public Map<String, String> getFieldAliases() {
		return fieldAliases;
	}

	public void setFieldAliases(Map<String, String> fieldAliases) {
		this.fieldAliases = fieldAliases;
	}

	public List<FieldItem> getFields() {
		return fields;
	}

	public void setFields(List<FieldItem> fields) {
		this.fields = fields;
	}

	public String getGeometryType() {
		return geometryType;
	}

	public void setGeometryType(String geometryType) {
		this.geometryType = geometryType;
	}

	public String getGlobalIdFieldName() {
		return globalIdFieldName;
	}

	public void setGlobalIdFieldName(String globalIdFieldName) {
		this.globalIdFieldName = globalIdFieldName;
	}

	public String getObjectIdFieldName() {
		return objectIdFieldName;
	}

	public void setObjectIdFieldName(String objectIdFieldName) {
		this.objectIdFieldName = objectIdFieldName;
	}

	public SpatialReference getSpatialReference() {
		return spatialReference;
	}

	public void setSpatialReference(SpatialReference spatialReference) {
		this.spatialReference = spatialReference;
	}

	public FeatureSet() {
		this.features = new ArrayList<Graphic>();
		this.fieldAliases = new HashMap<String, String>();
	}
}
