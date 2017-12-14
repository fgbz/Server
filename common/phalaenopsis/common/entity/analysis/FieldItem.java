/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

import java.util.List;

/**
 * @author yangw786
 *
 * 2017年5月9日下午3:28:03
 */
public class FieldItem {
	private final String esriFieldType = "esriFieldType";

    public String alias;

    public boolean Editable;

    public int Length;

    public String name;

    public boolean Nullable;

    public String type;

    private FieldItem() {
        this.type = esriFieldType;
    } 
    
	public String getAlias() {
		return alias;
	}
 
	public void setAlias(String alias) {
		this.alias = alias;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public String getType() {
		return type;
	}
 
	public void setType(String type) {
		this.type = type;
	}
 
	public static FieldItem FromList(List<FieldItem> list, String name) {
		FieldItem field = null;
		for (FieldItem fieldItem : list) {
			if (fieldItem.name != name) {
				continue;
			}
			field = fieldItem;
			break;
		}
		return field;
	}
    
    public class FieldType {
        public static final short Integer = 0;
        public static final short SmallInteger = 1;
        public static final short Double = 2;
        public static final short Single = 3;
        public static final short String = 4;
        public static final short Date = 5;
        public static final short Geometry = 6;
        public static final short OID = 7;
        public static final short Blob = 8;
        public static final short GlobalID = 9;
        public static final short Raster = 10;
        public static final short GUID = 11;
        public static final short XML = 12;
        public static final short Unknown = 13;
    }
}
