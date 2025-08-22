package jp.co.backend.dto.user;

import jp.co.backend.controllers.user.requests.CreateUserRequest;
import jp.co.backend.models.generated.User;
import lombok.Getter;

/**
 * 作成するユーザー
 */
@Getter
public class CreateUser {

	/**
	 * ログインID
	 */
	private final String loginId;
	
	/**
	 * 表示名
	 */
	private final String displayName;
	
	/**
	 * 暗号化されたパスワード
	 */
	private final String encryptedPassword;
	
	/**
	 * コンストラクタ
	 * 
	 * @param request {@link CreateUserRequest}
	 */
	public CreateUser(CreateUserRequest request) {
		this.loginId = request.getLoginId();
		this.displayName = request.getDisplayName();
		this.encryptedPassword = request.getPasswordEncoder().encode(request.getPassword());
	}
	
	/**
	 * {@link User}へ変換
	 * 
	 * @return {@link User}
	 */
	public User toUser() {
		User user = new User();
		user.setLoginId(this.loginId);
		user.setDisplayName(this.displayName);
		user.setPassword(this.encryptedPassword);
		
		return user;
	}
}
