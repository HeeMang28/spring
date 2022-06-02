package com.ict.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// 테스트코드 기본세팅(RunWith, ContextConfiguration, Log4j)해주세요.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	"file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	// 이 테스트코드 내에서는 Mapper테스트를 담당합니다.
	// 따라서 BoardMapper내부의 메서드를 실행할 예정이고
	// BoardMapper 타입의 변수가 필요하니
	// 선언해주시고 자동 주입으로 넣어주세요.
	@Autowired
	private BoardMapper mapper;
	
	// 테스트용 메서드의 이름은 testGetList입니다.
	// 테스트 코드가 실행될 수 있도록 만들어주세요.
	@Test
	public void testGetList() {
		// mapper 내부의 getList 메서드를 호출하려면?
		log.info(mapper.getList());
	}

}
