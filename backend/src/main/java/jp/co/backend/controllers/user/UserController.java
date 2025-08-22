package jp.co.backend.controllers.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.backend.controllers.user.requests.CreateUserRequest;
import jp.co.backend.controllers.user.responses.GetUserResponse;
import jp.co.backend.dto.user.CreateUser;
import jp.co.backend.dto.user.FoundUser;
import jp.co.backend.exceptions.resource.user.DuplicatedLoginIdException;
import jp.co.backend.exceptions.resource.user.UserNotFoundException;
import jp.co.backend.services.user.CreateUserService;
import jp.co.backend.services.user.SelectUserService;
import lombok.RequiredArgsConstructor;

/**
 * ユーザーに関するコントローラー
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	@NonNull
	private final SelectUserService selectUserService;
	
	@NonNull
	private final CreateUserService createUserService;
	
	/**
	 * ユーザー取得API
	 * 
	 * @param userId ユーザーID
	 * @return ユーザー
	 * @throws UserNotFoundException 指定したIDのユーザーなし例外
	 */
	@GetMapping("/{userId}")
	ResponseEntity<GetUserResponse> getUser(@PathVariable("userId") int userId) throws UserNotFoundException {
		FoundUser foundUser = selectUserService.findByUserId(userId);
		
		return ResponseEntity.ok(new GetUserResponse(foundUser));
	}
	
	/**
	 * ユーザー作成API
	 * 
	 * @param request リクエストボディ
	 * @return ボディなしレスポンス
	 * @throws DuplicatedLoginIdException ログインID重複例外
	 */
	@PostMapping
	ResponseEntity<Void> createUser(@RequestBody @Validated CreateUserRequest request) throws DuplicatedLoginIdException {
		createUserService.createUser(new CreateUser(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
