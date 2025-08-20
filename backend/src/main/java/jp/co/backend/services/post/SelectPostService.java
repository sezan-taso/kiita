package jp.co.backend.services.post;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.backend.dao.PostMapper;
import jp.co.backend.dao.UserMapper;
import jp.co.backend.dto.post.FoundPost;
import jp.co.backend.exceptions.resource.post.PostNotFoundException;
import jp.co.backend.models.generated.Post;
import jp.co.backend.models.generated.User;
import lombok.RequiredArgsConstructor;

/**
 * 記事取得サービス
 */
@Service
@RequiredArgsConstructor
public class SelectPostService {

	@NonNull
	private final PostMapper postMapper;
	
	@NonNull
	private final UserMapper userMapper;
	
	/**
	 * 指定したIDの記事を取得
	 * 
	 * @param postId 記事ID
	 * @return 記事
	 * @throws PostNotFoundException 取得しようとした記事が見つからない例外
	 */
	@Transactional(readOnly = true)
	public FoundPost findByPostId(int postId) throws PostNotFoundException {
		Post selectedPost = postMapper.selectByPrimaryKey(postId)
				.orElseThrow(() -> new PostNotFoundException("E0004", String.valueOf(postId)));
		User author = userMapper.selectByPrimaryKey(selectedPost.getAuthorId())
				.orElseThrow(() -> new IllegalStateException("存在しないユーザーIDが投稿に設定されています。"));
		
		return new FoundPost(selectedPost, author);
	}
}
