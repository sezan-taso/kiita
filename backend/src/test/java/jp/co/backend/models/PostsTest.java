package jp.co.backend.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.backend.models.generated.Post;

/**
 * {@link Posts}テスト
 */
@SpringBootTest
class PostsTest {
	
	/**
	 * {@link Posts#getAuthorIds()}テスト
	 */
	@Nested
	class GetAuthorIdsTest {
		@Test
		void authorIdリスト取得() {
			Post post1 = new Post();
			post1.setAuthorId(1);
			Post post2 = new Post();
			post2.setAuthorId(2);
			Posts posts = new Posts(List.of(post1, post2));
			List<Integer> authorIds = posts.getAuthorIds();
			
			assertThat(authorIds).hasSize(2);
			assertThat(authorIds.get(0)).isEqualTo(1);
			assertThat(authorIds.get(1)).isEqualTo(2);
		}
	}
	
	/**
	 * {@link Posts#isEmpty()}テスト
	 */
	@Nested
	class IsEmpty {
		@Test
		void 空のリスト() {
			Posts posts = new Posts(Collections.emptyList());
			assertThat(posts.isEmpty()).isTrue();
		}
		
		@Test
		void 空でないリスト() {
			Post post1 = new Post();
			post1.setAuthorId(1);
			Post post2 = new Post();
			post2.setAuthorId(2);
			Posts posts = new Posts(List.of(post1, post2));
			
			assertThat(posts.isEmpty()).isFalse();
		}
	}
}
