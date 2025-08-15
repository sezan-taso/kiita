package jp.co.backend.services.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jp.co.backend.dao.UserMapper;
import jp.co.backend.dto.user.FoundUser;
import jp.co.backend.exceptions.resource.user.UserNotFoundException;
import jp.co.backend.models.generated.User;

@SpringBootTest
@Transactional
class SelectUserServiceTest {

	@InjectMocks
	private SelectUserService selectUserService;
	
	@Mock
	private UserMapper userMapper;
	
	/**
	 * {@link SelectUserService#findByUserId(int)}テスト
	 */
	@Nested
	class FindByUserIdTest {
		@Test
		void 指定ユーザーあり() throws Exception {
			User mockUser = new User();
			mockUser.setId(1);
			mockUser.setDisplayName("ユーザー１");
			when(userMapper.selectByPrimaryKey(1)).thenReturn(Optional.of(mockUser));
			assertThat(selectUserService.findByUserId(1)).isEqualTo(new FoundUser(mockUser));
			verify(userMapper).selectByPrimaryKey(1);
		}
		
		@Test
		void 指定ユーザーなし() throws Exception {
			when(userMapper.selectByPrimaryKey(1)).thenReturn(Optional.empty());
			assertThatThrownBy(() -> selectUserService.findByUserId(1))
				.isInstanceOfSatisfying(
						UserNotFoundException.class,
						(e) -> {
							assertThat(e.getErrorCode()).isEqualTo("E0026");
							assertThat(e.getTarget()).isEqualTo("1");
						}
					);
			verify(userMapper).selectByPrimaryKey(1);
		}
	}

}
