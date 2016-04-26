package com.reuter.rea.robot.exception;

/**
 * Created by aandra1 on 26/04/16.
 */
public class InvalidDirection extends RuntimeException {

  public InvalidDirection(String message, Throwable th) {
    super(message, th);
  }
}
