package jp.co.backend.controllers.post.responses;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.backend.dto.post.FoundPost;
import jp.co.backend.models.generated.Post;
import jp.co.backend.models.generated.User;

/**
 * {@link GetPostResponse}テスト
 */
@SpringBootTest
class GetPostResponseTest {

	/**
	 * {@link GetPostResponse#GetPostResponse(jp.co.backend.dto.post.FoundPost)}テスト
	 */
	@Nested
	class GetPostResponseConstructorTest {
		@Test
		void 正常なコンストラクタ() {
			Post post = new Post();
			post.setId(1);
			post.setTitle("タイトル");
			post.setBody("ボディ");
			post.setPostedAt(LocalDateTime.of(2020, 10, 10, 10, 10));
			post.setRepostAt(LocalDateTime.of(2022, 10, 10, 10, 10));
			post.setIsDraft(false);
			post.setAuthorId(10);
			User user = new User();
			user.setId(10);
			user.setDisplayName("ユーザー１０");GetPostResponse response = new GetPostResponse(new FoundPost(post, user));
			
			assertThat(response.getId()).isEqualTo(1);
			assertThat(response.getTitle()).isEqualTo("タイトル");
			assertThat(response.getBody()).isEqualTo("ボディ");
			assertThat(response.getAuthorId()).isEqualTo(10);
			assertThat(response.getAuthorName()).isEqualTo("ユーザー１０");
			assertThat(response.getPostedAt()).isEqualTo(LocalDate.of(2020, 10, 10));
			assertThat(response.getRepostAt()).isEqualTo(LocalDate.of(2022, 10, 10));
		}	
	}

}
