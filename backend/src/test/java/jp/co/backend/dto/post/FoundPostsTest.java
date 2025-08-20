package jp.co.backend.dto.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.backend.models.Posts;
import jp.co.backend.models.Users;
import jp.co.backend.models.generated.Post;
import jp.co.backend.models.generated.User;

/**
 * {@link FoudPosts}テスト
 */
@SpringBootTest
class FoundPostsTest {

	/**
	 * {@link FoundPosts#FoundPosts(Posts, Users)}テスト
	 */
	@Nested
	class FoundPostsConstructorWithArgmentTest {
		@Test
		void コンストラクター() {
			Post post1 = new Post();
			post1.setId(1);
			post1.setTitle("タイトル１");
			post1.setBody("ボディ１");
			post1.setPostedAt(LocalDateTime.of(2020, 10, 10, 10, 10));
			post1.setRepostAt(LocalDateTime.of(2021, 10, 10, 10, 10));
			post1.setIsDraft(false);
			post1.setAuthorId(10);
			User user1 = new User();
			user1.setId(10);
			user1.setDisplayName("ユーザー１０");
			Post post2 = new Post();
			post2.setId(2);
			post2.setTitle("タイトル２");
			post2.setBody("ボディ２");
			post2.setPostedAt(LocalDateTime.of(2022, 10, 10, 10, 10));
			post2.setRepostAt(LocalDateTime.of(2023, 10, 10, 10, 10));
			post2.setIsDraft(false);
			post2.setAuthorId(11);
			User user2 = new User();
			user2.setId(11);
			user2.setDisplayName("ユーザー１１");
			
			FoundPosts foundPosts = new FoundPosts(
					new Posts(List.of(post1, post2)),
					new Users(List.of(user1, user2))
			);
			
			assertThat(foundPosts.getValue()).hasSize(2);
			FoundPost foundPost1 = foundPosts.getValue().get(0);
			assertThat(foundPost1.getId()).isEqualTo(1);
			assertThat(foundPost1.getAuthorId()).isEqualTo(10);
			assertThat(foundPost1.getTitle()).isEqualTo("タイトル１");
			assertThat(foundPost1.getBody()).isEqualTo("ボディ１");
			assertThat(foundPost1.getPostedAt()).isEqualTo(LocalDateTime.of(2020, 10, 10, 10, 10));
			assertThat(foundPost1.getRepostAt()).isEqualTo(LocalDateTime.of(2021, 10, 10, 10, 10));
			assertThat(foundPost1.isDraft()).isFalse();
			assertThat(foundPost1.getAuthorName()).isEqualTo("ユーザー１０");
            
			FoundPost foundPost2 = foundPosts.getValue().get(1);
			assertThat(foundPost2.getId()).isEqualTo(2);
			assertThat(foundPost2.getAuthorId()).isEqualTo(11);
			assertThat(foundPost2.getTitle()).isEqualTo("タイトル２");
			assertThat(foundPost2.getBody()).isEqualTo("ボディ２");
			assertThat(foundPost2.getPostedAt()).isEqualTo(LocalDateTime.of(2022, 10, 10, 10, 10));
			assertThat(foundPost2.getRepostAt()).isEqualTo(LocalDateTime.of(2023, 10, 10, 10, 10));
			assertThat(foundPost2.isDraft()).isFalse();
			assertThat(foundPost2.getAuthorName()).isEqualTo("ユーザー１１");
		}
	}
	
	/**
	 * {@link FoundPosts#FoundPosts()}テスト
	 */
	@Nested
	class FoundPostsConstructorNoArgmentTest {
		@Test
		void コンストラクター() {
			FoundPosts foundPosts = new FoundPosts();
			assertThat(foundPosts.getValue()).isEmpty();
		}
	}
	
	/**
	 * {@link FoundPosts#isEmpty()}テスト
	 */
	@Nested
	class IsEmptyTest{
	
		@Test
		void 空でない場合() {
			Post post1 = new Post();
			post1.setId(1);
			post1.setTitle("タイトル１");
			post1.setBody("ボディ１");
			post1.setPostedAt(LocalDateTime.of(2020, 10, 10, 10, 10));
			post1.setRepostAt(LocalDateTime.of(2021, 10, 10, 10, 10));
			post1.setIsDraft(false);
			post1.setAuthorId(10);
			User user1 = new User();
			user1.setId(10);
			user1.setDisplayName("ユーザー１０");
			Post post2 = new Post();
			post2.setId(2);
			post2.setTitle("タイトル２");
			post2.setBody("ボディ２");
			post2.setPostedAt(LocalDateTime.of(2022, 10, 10, 10, 10));
			post2.setRepostAt(LocalDateTime.of(2023, 10, 10, 10, 10));
			post2.setIsDraft(false);
			post2.setAuthorId(11);
			User user2 = new User();
			user2.setId(11);
			user2.setDisplayName("ユーザー１１");
			
			FoundPosts foundPosts = new FoundPosts(
					new Posts(List.of(post1, post2)),
					new Users(List.of(user1, user2))
			);
			
			assertThat(foundPosts.isEmpty()).isFalse();
		}
		
		@Test
		void 空の場合() {
			FoundPosts foundPosts = new FoundPosts();
			
			assertThat(foundPosts.isEmpty()).isTrue();
		}
	}
	
}
