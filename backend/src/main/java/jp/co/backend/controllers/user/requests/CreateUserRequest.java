package jp.co.backend.controllers.user.requests;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * ユーザー作成リクエスト
 */
@Data
public class CreateUserRequest {

	/**
	 * ログインID最大長
	 */
	private static final int MAX_LOGIN_ID_LENGTH = 20;
	
	/**
	 * パスワード最小長
	 */
	private static final int MIN_PASSWORD_LENGTH = 8;
	
	/**
	 * パスワード最大長
	 */
	private static final int MAX_PASSWORD_LENGTH = 20;
	
	/**
	 * 表示名最大長
	 */
	private static final int MAX_DISPLAY_NAME_LENGTH = 20;
	
	/**
	 * ログインIDパターン
	 */
	private static final String LOGIN_ID_PATTERN = "^[0-9a-zA-Z_-]+$";
	
	/**
	 * パスワードパターン
	 */
	private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[!@./_-])[0-9a-zA-Z!@./_-]+$";
	
	/**
	 * ログインID
	 */
	@NotNull(message = "E0006")
	@Length(message = "E0007", max = MAX_LOGIN_ID_LENGTH)
	@Pattern(message = "E0008", regexp = LOGIN_ID_PATTERN)
	private String loginId;
	
	/**
	 * パスワード
	 */
	@NotNull(message = "E0009")
	@Length(message = "E0010", min = MIN_PASSWORD_LENGTH, max = MAX_PASSWORD_LENGTH)
	@Pattern(message = "E0011", regexp = PASSWORD_PATTERN)
	private String password;
	
	/**
	 * 表示名
	 */
	@NotBlank(message = "E0013")
	@Length(message = "E0014", max = MAX_DISPLAY_NAME_LENGTH)
	private String displayName;
	
	/**
	 * エンコーダー
	 */
	@JsonIgnore
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
}
