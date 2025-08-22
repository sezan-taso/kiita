package jp.co.backend.dto.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.backend.controllers.user.requests.CreateUserRequest;
import jp.co.backend.models.generated.User;

/**
 * {@link CreateUser}テスト
 */
@SpringBootTest
class CreateUserTest {

	/**
	 * {@link CreateUser#CreateUser(jp.co.backend.controllers.user.requests.CreateUserRequest)}テスト
	 */
	@Nested
	class CreateUserConstructorTest {
		
		@Test
		void コンストラクター() {
			CreateUserRequest request = new CreateUserRequest();
			request.setLoginId("user1");
			request.setDisplayName("ユーザー１");
			request.setPassword("Password1!");
			CreateUser createUser = new CreateUser(request);
			
			assertThat(createUser.getLoginId()).isEqualTo("user1");
			assertThat(createUser.getDisplayName()).isEqualTo("ユーザー１");
			assertThat(createUser.getEncryptedPassword()).isNotBlank();
			assertThat(createUser.getEncryptedPassword()).isNotEqualTo(request.getPassword());
		}
	}
	
	/**
	 * {@link CreateUser#toUser()}テスト
	 */
	@Nested
	class ToUserTest {
		
		@Test
		void toUser() {
			CreateUserRequest request = new CreateUserRequest();
			request.setLoginId("user1");
			request.setDisplayName("ユーザー１");
			request.setPassword("Password1!");
			CreateUser createUser = new CreateUser(request);
			User user = createUser.toUser();
			
			assertThat(user.getLoginId()).isEqualTo("user1");
			assertThat(user.getDisplayName()).isEqualTo("ユーザー１");
			assertThat(user.getPassword()).isNotBlank();
			assertThat(user.getPassword()).isNotEqualTo(request.getPassword());
		}
	}

}
