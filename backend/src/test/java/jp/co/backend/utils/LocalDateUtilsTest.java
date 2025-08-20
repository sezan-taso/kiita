package jp.co.backend.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jp.co.backend.utils.LocalDateUtils.convertToLocalDate;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link LocalDateUtils}テスト
 */
@SpringBootTest
class LocalDateUtilsTest {

	/**
	 * {@link LocalDateUtils#convertToLocalDate(java.time.LocalDateTime)}テスト
	 */
	@Nested
	class ConvertToLocalDateTest {
		
		@Test
		void 引数がNull() {
			assertThat(convertToLocalDate(null)).isNull();
		}
		
		@Test
		void 引数がLocalDateTime() {
			assertThat(convertToLocalDate(LocalDateTime.of(2020, 1, 2, 3, 4)))
				.isEqualTo(LocalDate.of(2020, 1, 2));
		}
	}
}
