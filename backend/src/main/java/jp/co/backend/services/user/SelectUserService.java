package jp.co.backend.services.user;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.backend.dao.UserMapper;
import jp.co.backend.dto.user.FoundUser;
import jp.co.backend.exceptions.resource.user.UserNotFoundException;
import jp.co.backend.models.generated.User;
import lombok.RequiredArgsConstructor;

/**
 * ユーザー取得サービス
 */
@Service
@RequiredArgsConstructor
public class SelectUserService {

	@NonNull
	private final UserMapper userMapper;
	
	/**
	 * 指定したIDのユーザーを取得
	 * 
	 * @param userId
	 * @return ユーザー
	 * 
	 * @throws UserNotFoundException 取得しようとしたユーザーが見つからない例外
	 */
	@Transactional(readOnly = true)
	public FoundUser findByUserId(int userId) throws UserNotFoundException {
		User user = userMapper.selectByPrimaryKey(userId)
					.orElseThrow(() -> new UserNotFoundException("E0026", String.valueOf(userId)));
		
		return new FoundUser(user);
	}
}
