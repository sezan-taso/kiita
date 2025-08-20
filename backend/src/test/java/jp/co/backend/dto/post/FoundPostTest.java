package jp.co.backend.dto.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.backend.models.generated.Post;
import jp.co.backend.models.generated.User;

/**
 * {@link FoundPost}テスト
 */
@SpringBootTest
class FoundPostTest {

	@Nested
	class FoundPostConstructorClass {
		@Test
		void コンストラクターテスト() {
			Post post = new Post();
			post.setId(1);
			post.setTitle("タイトル");
			post.setBody("ボディ");
			post.setAuthorId(10);
			post.setPostedAt(LocalDateTime.of(2020, 10, 10, 10, 10));
			post.setRepostAt(LocalDateTime.of(2022, 10, 10, 10, 10));
			post.setIsDraft(false);
			User user = new User();
			user.setId(10);
			user.setDisplayName("ユーザー10");
			FoundPost foundPost = new FoundPost(post, user);
			
			assertThat(foundPost.getId()).isEqualTo(1);
			assertThat(foundPost.getTitle()).isEqualTo("タイトル");
			assertThat(foundPost.getBody()).isEqualTo("ボディ");
			assertThat(foundPost.getAuthorId()).isEqualTo(10);
			assertThat(foundPost.getPostedAt()).isEqualTo(LocalDateTime.of(2020, 10, 10, 10, 10));
			assertThat(foundPost.getRepostAt()).isEqualTo(LocalDateTime.of(2022, 10, 10, 10, 10));
			assertThat(foundPost.isDraft()).isFalse();
			assertThat(foundPost.getAuthorName()).isEqualTo("ユーザー10");
		}	
	}
}
