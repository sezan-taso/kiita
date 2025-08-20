package jp.co.backend.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.backend.models.generated.User;

/**
 * {@link Users}テスト
 */
@SpringBootTest
class UsersTest {

	/**
	 * {@link Users#getDisplayNameMap()}テスト
	 */
	@Nested
	class GetDisplayNameMapTest {
		@Test
		void displayNameMap取得() {
			User user1 = new User();
			user1.setId(1);
			user1.setDisplayName("ユーザー１");
			User user2 = new User();
			user2.setId(2);
			user2.setDisplayName("ユーザー２");
			Users users = new Users(List.of(user1, user2));
			Map<Integer, String> displayNameMap = users.getDisplayNameMap();
			
			assertThat(displayNameMap).hasSize(2);
			assertThat(displayNameMap.get(1)).isEqualTo("ユーザー１");
			assertThat(displayNameMap.get(2)).isEqualTo("ユーザー２");
		}
	}

	/**
	 * {@link Users#getUserMap()}テスト
	 */
	@Nested
	class GetUserMapTest {
		@Test
		void userMap取得() {
			User user1 = new User();
			user1.setId(1);
			User user2 = new User();
			user2.setId(2);
			Users users = new Users(List.of(user1, user2));
			Map<Integer, User> displayNameMap = users.getUserMap();
			
			assertThat(displayNameMap).hasSize(2);
			assertThat(displayNameMap.get(1)).isEqualTo(user1);
			assertThat(displayNameMap.get(2)).isEqualTo(user2);
		}
	}
	
}
