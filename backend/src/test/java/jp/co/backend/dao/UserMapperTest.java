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
			assertThat(selectedUser1.getPassword()).isEqualTo("$2a$10$uBB55DqSI9lRbNHkzQj7q.CMotDKFqbmE.AHsEY2tF2VmhP3cocpC");
			assertThat(selectedUser1.getDisplayName()).isEqualTo("ユーザー１");
			assertThat(selectedUser1.getDelFlag()).isFalse();
			
			User selectedUser2 = selectedUsers.get(1);
			
			assertThat(selectedUser2.getId()).isEqualTo(3);
		}	
	}
	
	/**
	 * {@link UserMapper#countByLoginId(String)}テスト
	 */
	@Nested
	class CountByLoginIdTest {
		
		@Test
		void 指定したloginIdのレコードあり() {
			long count = userMapper.countByLoginId("user1");
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		void 指定したloginIdのレコードなし() {
			long count = userMapper.countByLoginId("xxx");
			assertThat(count).isEqualTo(0);
		}
	}
}
