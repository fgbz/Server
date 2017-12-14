package com.kotei.service;

import org.springframework.stereotype.Service;
/**
 * 接口实现类
 * @author langl
 *
 */

@Service
public class RandomEmailGenerator implements EmailGenerator {

	@Override
	public String generate() {
		return "feedback@kotei.com";
	}

}