package jp.co.backend.exceptions.resource.user;

import jp.co.backend.exceptions.resource.ResourceNotFoundException;
import lombok.Getter;

/**
 * ユーザーなし例外
 */
@Getter
public class UserNotFoundException extends ResourceNotFoundException{

	/**
	 * コンストラクタ
	 * 
	 * @param errorCode エラーコード
	 * @param target 	取得しようとしたユーザーを特定する情報
	 */
	public UserNotFoundException(String errorCode, String target) {
		super(errorCode, target);
	}
}
