package jp.co.backend.exceptions.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * リソースなし例外
 */
@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends Exception{
	private final String errorCode;
	private final String target;
}
