package jp.co.backend.dto.post;

import java.time.LocalDateTime;

import jp.co.backend.models.generated.Post;
import jp.co.backend.models.generated.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 取得された記事
 */
@Getter
@EqualsAndHashCode
public class FoundPost {

	/**
	 * 記事ID
	 */
	private final int id;
	
	/**
	 * 記事作成したユーザーID
	 */
	private final int authorId;
	
	/**
	 * タイトル
	 */
	private final String title;
	
	/**
	 * 内容
	 */
	private final String body;
	
	/**
	 * 投稿日時
	 */
	private final LocalDateTime postedAt;
	
	
	/**
	 * 更新日時
	 */
	private final LocalDateTime repostAt;
	
	/**
	 * 下書きフラグ
	 */
	private final boolean isDraft;
	
	/**
	 * 記事の著者名
	 */
	private final String authorName;
	
	/**
	 * コンストラクタ
	 * 
	 * @param {@link Post} post
	 * @param {@link User} user
	 */
	public FoundPost(Post post, User user) {
		this.id = post.getId();
		this.authorId = user.getId();
		this.title = post.getTitle();
		this.body = post.getBody();
		this.postedAt = post.getPostedAt();
		this.repostAt = post.getRepostAt();
		this.isDraft = post.getIsDraft();
		this.authorName = user.getDisplayName();
	}
}
