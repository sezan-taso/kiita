package jp.co.backend.controllers.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * ユーザーに関するコントローラー
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	/**
	 * テストAPI
	 * 
	 * @return ボディなしレスポンス
	 */
	@GetMapping("/test")
	public String test() {
		return "hello test";
	}
}
