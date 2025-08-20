package jp.co.backend.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * {@link LocalDate}のUtilsクラス
 */
public class LocalDateUtils {

	/**
	 * 引数の日時が非Nullなら{@link LocalDateTime}に変換
	 * NullならNullを返す
	 * 
	 * @param targetDateTime {@link LocalDateTime}
	 * @return {@link LocalDate}
	 */
	public static LocalDate convertToLocalDate(LocalDateTime targetDateTime) {
		if (Objects.nonNull(targetDateTime)) {
			return targetDateTime.toLocalDate();
		}
		
		return null;
	}
}
