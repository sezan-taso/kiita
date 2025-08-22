package jp.co.backend.exceptions.resource.user;

import jp.co.backend.exceptions.resource.DuplicatedResourceFoundException;
import lombok.Getter;

/**
 * ログインID重複例外
 */
@Getter
public class DuplicatedLoginIdException extends DuplicatedResourceFoundException {

	/**
	 * コンストラクタ
	 * 
	 * @param errorCode エラーコード
	 * @param target	ユーザーを特定する情報
	 */
	public DuplicatedLoginIdException(String errorCode, String target) {
		super(errorCode, target);
	}
}
