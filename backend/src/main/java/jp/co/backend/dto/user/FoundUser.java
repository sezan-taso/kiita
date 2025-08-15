package jp.co.backend.dto.user;

import jp.co.backend.models.generated.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 取得されたユーザー
 */
@Getter
@EqualsAndHashCode
public class FoundUser {

	/**
	 * ログインID
	 */
	private final String loginId;
	
	/**
	 * 表示名
	 */
	private final String displayName;
	
	/**
	 * コンストラクタ
	 * 
	 * @param user {@link User}
	 */
	public FoundUser(User user) {
		this.loginId = user.getLoginId();
		this.displayName = user.getDisplayName();
	}
}
