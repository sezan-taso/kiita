package jp.co.backend.services.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jp.co.backend.dao.PostMapper;
import jp.co.backend.dao.UserMapper;
import jp.co.backend.dto.post.FoundPost;
import jp.co.backend.exceptions.resource.post.PostNotFoundException;
import jp.co.backend.models.generated.Post;
import jp.co.backend.models.generated.User;

/**
 * {@link SelectPostService}テスト
 */
@SpringBootTest
@Transactional
class SelectPostServiceTest {

	@InjectMocks
	private SelectPostService selectPostService;
	
	@Mock
	private PostMapper postMapper;
	
	@Mock
	private UserMapper userMapper;
	
	/**
	 * {@link SelectPostService#findByPostId(int)}テスト
	 */
	@Nested
	class FindByPostIdTsst {
		
		@Test
		void 指定IDの記事なし() {
			when(postMapper.selectByPrimaryKey(1)).thenReturn(Optional.empty());
			
			assertThatThrownBy(() -> selectPostService.findByPostId(1))
				.isInstanceOfSatisfying(
					PostNotFoundException.class,
					(e) -> {
						assertThat(e.getErrorCode()).isEqualTo("E0004");
						assertThat(e.getTarget()).isEqualTo("1");
					}
				);
			verify(postMapper).selectByPrimaryKey(1);
			verify(userMapper, times(0)).selectByPrimaryKey(any());
		}
		
		@Test
		void 指定IDの記事ありユーザーが存在しない() {
			Post mockSelectedPost = new Post();
			mockSelectedPost.setId(1);
			mockSelectedPost.setAuthorId(10);
			when(postMapper.selectByPrimaryKey(1)).thenReturn(Optional.of(mockSelectedPost));
			when(userMapper.selectByPrimaryKey(10)).thenReturn(Optional.empty());
			
			assertThatThrownBy(() -> selectPostService.findByPostId(1))
				.isInstanceOfSatisfying(
						IllegalStateException.class,
						(e) -> {
							assertThat(e.getMessage()).isEqualTo("存在しないユーザーIDが投稿に設定されています。");
						}
				);
			verify(postMapper).selectByPrimaryKey(1);
			verify(userMapper).selectByPrimaryKey(10);
		}
		
		@Test
		void 指定IDの記事ありユーザーあり() throws Exception {
			Post mockSelectedPost = new Post();
			mockSelectedPost.setId(1);
			mockSelectedPost.setAuthorId(10);
			mockSelectedPost.setTitle("タイトル");
			mockSelectedPost.setBody("ボディ");
			mockSelectedPost.setPostedAt(LocalDateTime.of(2020, 10, 10, 10, 10));
			mockSelectedPost.setRepostAt(LocalDateTime.of(2022, 10, 10, 10, 10));
			mockSelectedPost.setIsDraft(false);
			when(postMapper.selectByPrimaryKey(1)).thenReturn(Optional.of(mockSelectedPost));
			
			User mockSelectedUser = new User();
			mockSelectedUser.setId(10);
			mockSelectedUser.setDisplayName("ユーザー１０");
			when(userMapper.selectByPrimaryKey(10)).thenReturn(Optional.of(mockSelectedUser));
			
			assertThat(selectPostService.findByPostId(1)).isEqualTo(new FoundPost(mockSelectedPost, mockSelectedUser));
			verify(postMapper).selectByPrimaryKey(1);
			verify(userMapper).selectByPrimaryKey(10);
		}
	}
	

}
