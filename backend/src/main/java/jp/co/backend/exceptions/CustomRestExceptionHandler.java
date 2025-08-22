package jp.co.backend.exceptions;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.constraints.NotNull;
import jp.co.backend.exceptions.resource.DuplicatedResourceFoundException;
import jp.co.backend.exceptions.resource.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 例外ハンドラー
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@NonNull
	private final MessageSource messageSource;
	
	/**
	 * {@link MethodArgumentNotValidException}のハンドリング
	 * 
	 * @param exception the exception
	 * @param headers	the headers to be written to the response
	 * @param status	the selected response status
	 * @param request	the current request
	 * @return レスポンス
	 */
	@NotNull
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception,
			@NotNull HttpHeaders headers,
			@NotNull HttpStatusCode status,
			@NotNull WebRequest request
			
	) {
		List<String> errorMessages = exception
				.getFieldErrors()
				.stream()
				.map(fieldError -> {
					String code = fieldError.getDefaultMessage();
					if (StringUtils.isBlank(code)) {
						throw new IllegalArgumentException("エラーコードが空のエラーが検出されました。");
					}
					Object[] arguments = fieldError.getArguments();
					
					return messageSource.getMessage(code, arguments, Locale.JAPAN);
				})
				.toList();
		
		log.warn(errorMessages.toString(), exception);
		
		return new ResponseEntity<>(errorMessages, headers, status);
	}
	
	/**
	 * {@link ResourceNotFoundException}のハンドリング
	 * 
	 * @param exception {@link ResourceNotFoundException}
	 * @return レスポンス
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ResourceNotFoundException.class})
	@ResponseBody
	String handleResourceNotFound(ResourceNotFoundException exception) {
		String code = exception.getErrorCode();
		Object[] arguments = {exception.getTarget()};
		String message = messageSource.getMessage(code, arguments, Locale.JAPAN);
		log.warn(message, exception);
		
		return message;
	}
	
	/**
	 * {@link DuplicatedResourceFoundException}のハンドリング
	 * 
	 * @param exception {@link DuplicatedResourceFoundException}
	 * @return レスポンス
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({DuplicatedResourceFoundException.class})
	@ResponseBody
	String handleDuplicatedLoginId(DuplicatedResourceFoundException exception) {
		String code = exception.getErrorCode();
		Object[] arguments = {exception.getTarget()};
		String message = messageSource.getMessage(code, arguments, Locale.JAPAN);
		log.warn(message, exception);
		
		return message;
	}
}
