package jp.co.backend.controllers.post;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.backend.controllers.post.responses.GetPostResponse;
import jp.co.backend.dto.post.FoundPost;
import jp.co.backend.exceptions.resource.post.PostNotFoundException;
import jp.co.backend.services.post.SelectPostService;
import lombok.RequiredArgsConstructor;

/**
 * 記事に関するコントローラー
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	@NonNull
	private final SelectPostService selectPostService;
	
	/**
	 * 指定した記事IDを取得
	 * 
	 * @param postId 記事ID
	 * @return 記事
	 * @throws PostNotFoundException 指定したIDの記事なし例外
	 */
	@GetMapping("/{postId}")
	ResponseEntity<GetPostResponse> getPost(@PathVariable("postId") int postId) throws PostNotFoundException {
		FoundPost foundPost = selectPostService.findByPostId(postId);
		return ResponseEntity.ok(new GetPostResponse(foundPost));
	}
}
