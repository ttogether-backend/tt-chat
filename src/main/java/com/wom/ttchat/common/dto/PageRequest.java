package com.wom.ttchat.common.dto;

import com.wom.ttchat.common.Utils.CommonUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageRequest {
	private Integer pageNo;
	private Integer pageSize;

	private static final int MAX_SIZE = 200000;

	public static PageRequest of(Integer pageNo, Integer pageSize) {
		return new PageRequest(
			CommonUtils.isEmpty(pageNo) ? null : (pageNo < 0 ? 1 : pageNo - 1),
			CommonUtils.isEmpty(pageSize) ? null : (pageSize >= MAX_SIZE ? MAX_SIZE : pageSize)
		);
	}

	public boolean isEmpty() {
		return CommonUtils.isEmpty(pageNo) || CommonUtils.isEmpty(pageSize);
	}


}
