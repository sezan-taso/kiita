package jp.co.backend.dto.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.backend.models.generated.User;

/**
 * {@link FoundUser}テスト
 */
@SpringBootTest
class FoundUserTest {

	@Nested
	class FoundUserConstructorTest {
		@Test
		void コンストラクターテスト() {
			User user = new User();
			user.setLoginId("test");
			user.setDisplayName("テスト１");
			FoundUser foundUser = new FoundUser(user);
			
			assertThat(foundUser.getLoginId()).isEqualTo("test");
			assertThat(foundUser.getDisplayName()).isEqualTo("テスト１");
		}	
	}

}
