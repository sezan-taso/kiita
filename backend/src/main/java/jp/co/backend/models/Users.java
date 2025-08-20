package jp.co.backend.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jp.co.backend.models.generated.User;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * {@link User}のリストクラス
 */
@Data
@AllArgsConstructor
public class Users {
	
	/**
	 * ユーザー
	 */
	private List<User> value;
	
	/**
	 * 表示名マップを取得
	 * 
	 * @return 表示名マップ
	 */
	public Map<Integer, String> getDisplayNameMap() {
		return this.value
				.stream()
				.collect(Collectors.toMap(User::getId, User::getDisplayName));
	}
	
	/**
	 * ユーザーマップを取得
	 * 
	 * @return ユーザーマップ
	 */
	public Map<Integer, User> getUserMap() {
		return this.value
				.stream()
				.collect(Collectors.toMap(User::getId, user -> user));
	}
}
