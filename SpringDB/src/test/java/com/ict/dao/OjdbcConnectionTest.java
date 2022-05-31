package com.ict.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class OjdbcConnectionTest {
	
	static {
<<<<<<< HEAD
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (Exception e) {
		e.printStackTrace();
	}

=======
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			e.printStackTrace();
		}
>>>>>>> 8cc671c2c54393b78818241c2f165a9990c144e3
	}
	
	@Test
	public void testConnection() {
		
<<<<<<< HEAD
		try(Connection con = 
				DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521/XEPDB1",
				"mytest",
				"mytest"
				)) {
			log.info(con);
			log.info("정상적으로 연결되었습니다.");
	} catch( Exception e) {
		fail(e.getMessage());
	}
		
}
=======
		try(Connection con =
					DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521/XEPDB1",
							"mytest",
							"mytest"
							)){
			log.info(con);
			log.info("정상적으로 연결되었습니다.");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
>>>>>>> 8cc671c2c54393b78818241c2f165a9990c144e3
}
