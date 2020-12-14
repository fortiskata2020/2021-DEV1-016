package com.tictactoe.tictactoe;

import com.tictactoe.tictactoe.components.MyRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TictactoeApplicationTests {


	@MockBean
	private MyRunner myRunner;

	@Test
	void contextLoads() {
	}

}
