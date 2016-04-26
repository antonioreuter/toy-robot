package com.reuter.rea.robot.model;

/**
 * Created by aandra1 on 25/04/16.
 */
public enum Direction {
  NORTH("WEST", "EAST"),
  SOUTH("EAST", "WEST"),
  WEST("SOUTH", "NORTH"),
  EAST("NORTH", "SOUTH");

  private String _left;
  private String _right;

  private Direction(String left, String right) {
    this._left = left;
    this._right = right;
  }

  public Direction left() {
    return Direction.valueOf(this._left);
  }

  public Direction right() {
    return Direction.valueOf(this._right);
  }
}
