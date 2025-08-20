package jp.co.backend.exceptions.resource.post;

import jp.co.backend.exceptions.resource.ResourceNotFoundException;
import lombok.Getter;

/**
 * 記事なし例外
 */
@Getter
public class PostNotFoundException extends ResourceNotFoundException {

	/**
	 * コンストラクタ
	 * 
	 * @param errorCode エラーコード
	 * @param target	取得しようとした記事の情報
	 */
	public PostNotFoundException(String errorCode, String target) {
		super(errorCode, target);
	}
}
