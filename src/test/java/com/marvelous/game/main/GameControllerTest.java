package com.marvelous.game.main;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.marvelous.game.MarvelousGameApplication;


@SpringBootTest(classes = MarvelousGameApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
class GameControllerTest {


	private final String CONTENT_TYPE = "application/json";
	
	@Autowired
	private MockMvc mockMvc;

	
	@Test
	@Order(1)
	void testCallCharacter() {
		System.out.println("\n\nTest 1 call an existing character from api.");
		String expectedResult = "{\"Vision\":{\"name\":\"Vision\",\"power\":50,\"frequency\":1}}";
		try {
			this.mockMvc.perform(get("/callCharacter/Vision")).
			andExpect(status().isOk()).
			andExpect(content().contentType(CONTENT_TYPE)).
			andExpect(content().json(expectedResult));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Caught an exception while executing testcase 1....");
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	void testPowerCall() {
		try {
		System.out.println("\n\nTest 2 get power of existing character from api.");
			this.mockMvc.perform(get("/getPower/Vision"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(CONTENT_TYPE))
			.andExpect(content().json("50"));
			
		} 
		catch (Exception e) {
			//TODO Auto-generated catch block
			System.out.println("Caught an exception while executing testcase 2....");
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	void testCallCharacterNegativeTest() {
		System.out.println("\n\nTest 3 call wrong character");
		String expectedResult = "{}";
		try {
			this.mockMvc.perform(get("/callCharacter/Mamta")).
			andExpect(status().isOk()).
			andExpect(content().contentType(CONTENT_TYPE)).
			andExpect(content().json(expectedResult));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Caught an exception while executing testcase 3....");
			e.printStackTrace();
		}
	}

	@Test
	@Order(4)
	void testPowerCallNegativeTest() {
		try {
		System.out.println("\n\nTest 4 get power of wrong character.");
			this.mockMvc.perform(get("/getPower/Mamta"))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("text/plain;charset=UTF-8"))
			.andExpect(content().string("Wrong parameters"));
			
		} 
		catch (Exception e) {
			//TODO Auto-generated catch block
			System.out.println("Caught an exception while executing testcase 4....");
			e.printStackTrace();
		}
	}

}

