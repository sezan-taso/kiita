package jp.co.backend.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jp.co.backend.models.generated.User;

/**
 * ｛@link UserMapper｝テスト
 */
@SpringBootTest
@Transactional
class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * {@link UserMapper#selectByIds(java.util.List)}テスト
	 */
	@Nested
	class SelectByIdsTest {
		@Test
		void 指定したIDでレコードを取得() {
			List<User> selectedUsers = userMapper.selectByIds(List.of(1,3));
			
			assertThat(selectedUsers).hasSize(2);
			
			User selectedUser1 = selectedUsers.get(0);
			
			assertThat(selectedUser1.getId()).isEqualTo(1);
			assertThat(selectedUser1.getLoginId()).isEqualTo("user1");
			assertThat(selectedUser1.getPassword()).isEqualTo("password");
			assertThat(selectedUser1.getDisplayName()).isEqualTo("テスト１");
			assertThat(selectedUser1.getDelFlag()).isFalse();
			
			User selectedUser2 = selectedUsers.get(1);
			
			assertThat(selectedUser2.getId()).isEqualTo(3);
		}	
	}
}
