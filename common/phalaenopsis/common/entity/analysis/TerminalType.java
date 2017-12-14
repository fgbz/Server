/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

/**
 * @author yangw786
 *
 * 2017年5月4日下午4:06:30
 * 终端类型
 */
public enum TerminalType {
	
	None(0),Pad(2),Phone(3),Borwser(1);
	
	private TerminalType(int v) {
		this.value = v;
	}
	
	private int value;
	
	public static TerminalType getTerminalType(int v){
		for (TerminalType type : TerminalType.values()) {
			if (type.value == v){
				return type;
			}
		}
		return null;
	}
}
