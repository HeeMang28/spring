package com.ict.persistence;

import java.sql.Date;

import lombok.Data;

@Data
public class MovieVO {

	private int rnum;
	private String movieNm;
	private Date openDt;
	private int audiCnt;
	private int showCnt;
	private Date dataDate;
}
