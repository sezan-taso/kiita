package jp.co.backend.dto.post;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import jp.co.backend.models.Posts;
import jp.co.backend.models.Users;
import jp.co.backend.models.generated.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * {@link FoundPost}のリストクラス
 */
@Getter
@EqualsAndHashCode
public class FoundPosts {

	/**
	 * 取得された記事
	 */
	private final List<FoundPost> value;
	
	/**
	 * コンストラクタ
	 * 
	 * @param {@link Posts} posts
	 * @param {@link Users} users
	 */
	public FoundPosts(Posts posts, Users users) {
		Map<Integer, User> userMap = users.getUserMap();
		this.value = posts
				.getValue()
				.stream()
				.map(post -> new FoundPost(post, userMap.get(post.getAuthorId())))
				.toList();
	}
	
	/**
	 * 空のコンストラクタ
	 */
	public FoundPosts() {
		this.value = Collections.emptyList();
	}
	
	/**
	 * 空判定
	 * 
	 * @return 判定結果
	 */
	public boolean isEmpty() {
		return value.isEmpty();
	}
}
