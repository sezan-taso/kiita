package jp.co.backend.controllers.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.backend.dto.user.FoundUser;
import jp.co.backend.exceptions.resource.user.UserNotFoundException;
import jp.co.backend.models.generated.User;
import jp.co.backend.services.user.SelectUserService;
import lombok.Data;

/**
 * {@link UserController}テスト
 */
@SpringBootTest
class UserControllerTest {

	
	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;
	
	@MockBean
	private SelectUserService selectUserService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	/**
	 * {@link UserController#getUser(int)}テスト
	 */
	@Nested
	class GetUserTest {

		/**
		 * レスポンスボディ マッピング用のクラス
		 */
		@Data
		static class MappedGetUserResponse {
			String loginId;
			String displayName;
		}
		
		@Test
		void 指定IDユーザーあり() throws Exception {
			User mockUser = new User();
			mockUser.setId(1);
			mockUser.setDisplayName("テスト１");
			mockUser.setDelFlag(false);
			mockUser.setPassword("password");
			mockUser.setLoginId("test");
			
			FoundUser mockFoundUser = new FoundUser(mockUser);
			when(selectUserService.findByUserId(1)).thenReturn(mockFoundUser);
			
			String returnContent = mockMvc.perform(get("/api/users/1"))
					.andExpect(status().isOk())
					.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
			MappedGetUserResponse response = new ObjectMapper().readValue(returnContent, MappedGetUserResponse.class);
					
			assertThat(response.getLoginId()).isEqualTo("test");
			assertThat(response.getDisplayName()).isEqualTo("テスト１");
		}
		
		@Test
		void 指定IDユーザーなし() throws Exception {
			UserNotFoundException mockException = new UserNotFoundException("E0026", "1");
			when(selectUserService.findByUserId(1)).thenThrow(mockException);
			
			String returnContent = mockMvc.perform(get("/api/users/1"))
					.andExpect(status().isNotFound())
					.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
			
			assertThat(returnContent).isEqualTo("指定したID(1)のユーザーが見つかりませんでした。");
		}
	}
}
