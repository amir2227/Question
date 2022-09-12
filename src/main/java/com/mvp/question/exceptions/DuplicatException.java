package com.mvp.question.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DuplicatException(String message) {
    super(String.format(message));
  }
}
