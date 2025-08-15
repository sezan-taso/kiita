package jp.co.backend.controllers.user.responses;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.backend.dto.user.FoundUser;
import jp.co.backend.models.generated.User;

/**
 * ｛@link GetUserResponse｝テスト
 */
@SpringBootTest
class GetUserResponseTest {

	/**
	 * {@link GetUserResponse#GetUserResponse(jp.co.backend.dto.user.FoundUser)}テスト
	 */
	@Nested
	class GetUserResponseContructorTest {
		@Test
		void コンストラクタテスト() {
			User user = new User();
			user.setLoginId("test");
			user.setDisplayName("テスト１");
			GetUserResponse response = new GetUserResponse(new FoundUser(user));
			
			assertThat(response.getLoginId()).isEqualTo("test");
			assertThat(response.getDisplayName()).isEqualTo("テスト１");
		}	
	}
}
