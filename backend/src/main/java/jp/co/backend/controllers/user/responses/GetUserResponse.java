package jp.co.backend.controllers.user.responses;

import jp.co.backend.dto.user.FoundUser;
import lombok.Getter;

/**
 * ユーザー取得レスポンス
 */
@Getter
public class GetUserResponse {

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
	 * @param foundUser {@link FoundUser}
	 */
	public GetUserResponse(FoundUser foundUser) {
		this.loginId = foundUser.getLoginId();
		this.displayName = foundUser.getDisplayName();
	}
}
