package jp.co.backend.exceptions.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * リソース重複例外
 */
@Getter
@AllArgsConstructor
public class DuplicatedResourceFoundException extends Exception {
	private final String errorCode;
	private final String target;
}
