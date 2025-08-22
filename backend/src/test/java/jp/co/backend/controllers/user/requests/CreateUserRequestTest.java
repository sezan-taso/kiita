package jp.co.backend.controllers.user.requests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

/**
 * {@link CreateUserRequest}テスト
 */
@SpringBootTest
@Transactional
class CreateUserRequestTest {

	/**
	 * 単項バリデーションテスト
	 */
	@Nested
	class ValidationTest {
		
		private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		
		/**
		 * 各テストでベースとする正常系のリクエスト
		 * 
		 * @return 正常系のリクエスト
		 */
		private CreateUserRequest buildValidRequest() {
			CreateUserRequest request = new CreateUserRequest();
			request.setLoginId("user1");
			request.setDisplayName("ユーザー１");
			request.setPassword("Password1!");
			
			return request;
		}
		
		@Test
		void loginIdがNull() {
			CreateUserRequest request = buildValidRequest();
			request.setLoginId(null);
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0006");
		}
		
		@Test
		void loginIdが空() {
			CreateUserRequest request = buildValidRequest();
			request.setLoginId("");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0008");
		}
		
		@Test
		void loginIdの長さが上限を上回る() {
			CreateUserRequest request = buildValidRequest();
			request.setLoginId(StringUtils.repeat("a", 21));
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0007");
		}
		
		@Test
		void loginIdの長さが上限と一致() {
			CreateUserRequest request = buildValidRequest();
			request.setLoginId(StringUtils.repeat("a", 20));
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(0);
		}
		
		@Test 
		void loginIdの長さが上限を下回る() {
			CreateUserRequest request = buildValidRequest();
			request.setLoginId(StringUtils.repeat("a", 19));
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(0);
		}
		
		@Test
		void loginIdが正常() {
			CreateUserRequest request = buildValidRequest();
			request.setLoginId("Aa1");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(0);
		}

		@Test
		void loginIdに記号を含む() {
			CreateUserRequest request = buildValidRequest();
			request.setLoginId("Aa1]");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0008");
		}
		
		@Test
		void loginIdにひらがなを含む() {
			CreateUserRequest request = buildValidRequest();
			request.setLoginId("Aa1あ");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0008");
		}
		
		@Test
		void passwordがNull() {
			CreateUserRequest request = buildValidRequest();
			request.setPassword(null);
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0009");
		}
		
		@Test
		void passwordの長さが上限を上回る() {
			CreateUserRequest request = buildValidRequest();
			request.setPassword("P" + StringUtils.repeat("a", 18) + "1!");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0010");
		}
		
		@Test
		void passwordの長さが上限と一致() {
			CreateUserRequest request = buildValidRequest();
			request.setPassword("P" + StringUtils.repeat("a", 17) + "1!");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(0);
		}
		
		@Test
		void passwordの長さが下限を下回る() {
			CreateUserRequest request = buildValidRequest();
			request.setPassword("P" + StringUtils.repeat("a", 4) + "1!");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0010");
		}
		
		@Test
		void passwordの長さが下限と一致() {
			CreateUserRequest request = buildValidRequest();
			request.setPassword("P" + StringUtils.repeat("a", 5) + "1!");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(0);
		}
		
		@Test
		void passwordに大文字が含まれない() {
			CreateUserRequest request = buildValidRequest();
			request.setPassword("password1!");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0011");
		}
		
		@Test
		void passwordに記号が含まれない() {
			CreateUserRequest request = buildValidRequest();
			request.setPassword("Password11");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0011");
		}
		
		@Test
		void passwordにひらがなが含まれる() {
			CreateUserRequest request = buildValidRequest();
			request.setPassword("Pあssword1!");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0011");
		}
		
		@Test
		void displayNameが空() {
			CreateUserRequest request = buildValidRequest();
			request.setDisplayName("");
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0013");
		}
		
		@Test
		void displayNameの長さが上限を超える() {
			CreateUserRequest request = buildValidRequest();
			request.setDisplayName(StringUtils.repeat("a", 21));
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(1);
			assertThat(violations).extracting("messageTemplate").containsOnly("E0014");
		}
		
		@Test
		void displayNameの長さが上限と一致() {
			CreateUserRequest request = buildValidRequest();
			request.setDisplayName(StringUtils.repeat("a", 20));
			Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);
			assertThat(violations).hasSize(0);
		}
	}
}
