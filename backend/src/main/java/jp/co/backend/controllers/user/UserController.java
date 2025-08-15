package jp.co.backend.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.backend.controllers.user.responses.GetUserResponse;
import jp.co.backend.dto.user.FoundUser;
import jp.co.backend.exceptions.resource.user.UserNotFoundException;
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
}
