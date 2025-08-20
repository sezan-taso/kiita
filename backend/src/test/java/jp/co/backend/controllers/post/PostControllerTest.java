package jp.co.backend.controllers.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.backend.dto.post.FoundPost;
import jp.co.backend.exceptions.resource.post.PostNotFoundException;
import jp.co.backend.models.generated.Post;
import jp.co.backend.models.generated.User;
import jp.co.backend.services.post.SelectPostService;
import lombok.Data;

/**
 * {@link PostController}テスト
 */
@SpringBootTest
class PostControllerTest {

	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@MockitoBean
	private SelectPostService selectPostService;
	
	/**
	 * {@link PostController#getPost(int)}テスト
	 */
	@Nested
	class GetPostTest {
		
		/**
		 * レスポンスボディマッピング用のクラス
		 */
		@Data
		static class MappedGetPostResponse {
			int id;
			String title;
			String body;
			int authorId;
			String authorName;
			String postedAt;
			String repostAt;
		}
		
		@Test
		void 指定ID記事あり() throws Exception {
			Post mockPost = new Post();
			mockPost.setId(1);
			mockPost.setTitle("タイトル１");
			mockPost.setBody("ボディ１");
			mockPost.setAuthorId(10);
			mockPost.setPostedAt(LocalDateTime.of(2020, 10, 10, 10, 10));
			mockPost.setRepostAt(LocalDateTime.of(2021, 10, 10, 10, 10));
			mockPost.setIsDraft(false);
			User mockUser = new User();
			mockUser.setId(10);
			mockUser.setDisplayName("ユーザー１０");
			FoundPost mockFoundPost = new FoundPost(mockPost, mockUser);
			when(selectPostService.findByPostId(1)).thenReturn(mockFoundPost);
			
			String returnContent = mockMvc.perform(get("/api/posts/1"))
					.andExpect(status().isOk())
					.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
			MappedGetPostResponse response = new ObjectMapper().readValue(returnContent, MappedGetPostResponse.class);
			
			assertThat(response.getId()).isEqualTo(1);
			assertThat(response.getTitle()).isEqualTo("タイトル１");
			assertThat(response.getBody()).isEqualTo("ボディ１");
			assertThat(response.getAuthorId()).isEqualTo(10);
			assertThat(response.getAuthorName()).isEqualTo("ユーザー１０");
			assertThat(response.getPostedAt()).isEqualTo("2020年10月10日");
			assertThat(response.getRepostAt()).isEqualTo("2021年10月10日");
		}
		
		@Test
		void 指定IDの記事なし() throws Exception {
			PostNotFoundException mockException = new PostNotFoundException("E0004", "1");
			when(selectPostService.findByPostId(1)).thenThrow(mockException);
			String returnContent = mockMvc.perform(get("/api/posts/1"))
					.andExpect(status().isNotFound())
					.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
			
			assertThat(returnContent).isEqualTo("指定したID(1)の投稿が見つかりませんでした。");
		}
	}
	
	
	void test() {
		fail("Not yet implemented");
	}

}
