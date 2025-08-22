package jp.co.backend.services.user;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.backend.dao.UserMapper;
import jp.co.backend.dto.user.CreateUser;
import jp.co.backend.exceptions.resource.DuplicatedResourceFoundException;
import jp.co.backend.exceptions.resource.user.DuplicatedLoginIdException;
import lombok.RequiredArgsConstructor;

/**
 * ユーザー作成サービス
 */
@Service
@RequiredArgsConstructor
public class CreateUserService {

	@NonNull
	private final UserMapper userMapper;
	
	/**
	 * ユーザーを作成する
	 * 
	 * @param createUser 作成するユーザー
	 * @return 作成件数
	 * @throws DuplicatedLogiunIdException ログインID重複例外
	 */
	@Transactional(rollbackFor = DuplicatedResourceFoundException.class)
	public int createUser(CreateUser createUser) throws DuplicatedLoginIdException {
		String loginId = createUser.getLoginId();
		if (userMapper.countByLoginId(loginId) > 0) {
			throw new DuplicatedLoginIdException("E0005", loginId);
		}
		
		return userMapper.insertSelective(createUser.toUser());
	}
}
