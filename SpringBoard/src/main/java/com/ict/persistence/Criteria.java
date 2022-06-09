package com.ict.persistence;

import lombok.Data;

@Data
public class Criteria {
	
	private int page;
	private int number;
	
	// 페이지 * 페이지당 숫자가 실제 limit구문에 들어갈 시작점이 됩니다.
	// mybatis는 getter를 가져다 쓸 수 있습니다.
	public int getPageStart() {
		return (this.page - 1) * number;
	}

}
