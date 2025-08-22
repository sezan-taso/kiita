package jp.co.backend.services.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jp.co.backend.controllers.user.requests.CreateUserRequest;
import jp.co.backend.dao.UserMapper;
import jp.co.backend.dto.user.CreateUser;
import jp.co.backend.exceptions.resource.user.DuplicatedLoginIdException;

/**
 * {@link CreateUserService}テスト
 */
@SpringBootTest
@Transactional
class CreateUserServiceTest {

	@InjectMocks
	private CreateUserService createUserService;
	
	@Mock
	private UserMapper userMapper;
	
	/**
	 * {@link CreateUserService#createUser(jp.co.backend.dto.user.CreateUser)}テスト
	 */
	@Nested
	class CreateUserTest {
		
		/**
		 * 各テストで共通して利用するcreateを取得　　
		 * 
		 * @return 共通で利用するCreateUser
		 */
		private CreateUser buildCreateUser() {
			CreateUserRequest request = new CreateUserRequest();
			request.setLoginId("user1");
			request.setDisplayName("ユーザー１");
			request.setPassword("password");
			
			return new CreateUser(request);
		}
		
		@Test
		void ログインＩＤの重複なし() throws Exception {
			CreateUser createUser = buildCreateUser();
			when(userMapper.countByLoginId("user1")).thenReturn(0L);
			when(userMapper.insertSelective(createUser.toUser())).thenReturn(1);
			assertThat(createUserService.createUser(createUser)).isEqualTo(1);
			verify(userMapper).countByLoginId("user1");
			verify(userMapper).insertSelective(createUser.toUser());
		}
		
		@Test
		void ログインIDの重複あり() {
			CreateUser createUser = buildCreateUser();
			when(userMapper.countByLoginId("user1")).thenReturn(1L);
			assertThatThrownBy(() -> createUserService.createUser(createUser))
				.isInstanceOfSatisfying(
						DuplicatedLoginIdException.class,
						(e) -> {
							assertThat(e.getErrorCode()).isEqualTo("E0005");
							assertThat(e.getTarget()).isEqualTo("user1");
						}
				);
			verify(userMapper).countByLoginId("user1");
			verify(userMapper, times(0)).insertSelective(any());
		}
	}
	
}
