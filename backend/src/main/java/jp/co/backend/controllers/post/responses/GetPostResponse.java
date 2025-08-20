package jp.co.backend.controllers.post.responses;

import static jp.co.backend.utils.LocalDateUtils.convertToLocalDate;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.backend.dto.post.FoundPost;
import lombok.Getter;

/**
 * 記事取得レスポンス
 */
@Getter
public class GetPostResponse {

	/**
	 * 記事投稿の日付パターン
	 */
	private static final String POSTED_AT_DATE_PATTERN = "yyyy年MM月dd日";
	
	/**
	 * 更新日の日付パターン
	 */
	private static final String REPOST_AT_DATE_PATTERN = "yyyy年MM月dd日";
	
	/**
	 * 記事ID
	 */
	private final int id;
	
	/**
	 * タイトル
	 */
	private final String title;
	
	/**
	 * 内容
	 */
	private final String body;
	
	/**
	 * 投稿者のユーザーID
	 */
	private final int authorId;
	
	/**
	 * 投稿者の名前
	 */
	private final String authorName;
	
	/**
	 * 投稿日
	 */
	@JsonFormat(pattern = POSTED_AT_DATE_PATTERN)
	private final LocalDate postedAt;
	
	/**
	 * 更新日
	 */
	@JsonFormat(pattern = REPOST_AT_DATE_PATTERN)
	private final LocalDate repostAt;
	
	/**
	 * コンストラクタ
	 * 
	 * @param foundPost {@link FoundPost}
	 */
	public GetPostResponse(FoundPost foundPost) {
		this.id = foundPost.getId();
		this.title = foundPost.getTitle();
		this.body = foundPost.getBody();
		this.authorId = foundPost.getAuthorId();
		this.authorName = foundPost.getAuthorName();
		this.postedAt = convertToLocalDate(foundPost.getPostedAt());
		this.repostAt = convertToLocalDate(foundPost.getRepostAt());
	}
	
}
