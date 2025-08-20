package jp.co.backend.models;

import java.util.List;

import org.springframework.util.CollectionUtils;

import jp.co.backend.models.generated.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@link Post}のリストクラス
 */
@Getter
@AllArgsConstructor
public class Posts {

	/**
	 * 記事のリスト
	 */
	private List<Post> value;
	
	/**
	 * 記事作成したユーザーIDを取得
	 * 
	 * @return ユーザーID
	 */
	public List<Integer> getAuthorIds() {
		return this.value
				.stream()
				.map(Post::getAuthorId)
				.toList();
	}
	
	/**
	 * 空判定
	 * 
	 * @return 判定結果
	 */
	public boolean isEmpty() {
		return CollectionUtils.isEmpty(this.value);
	}
}
