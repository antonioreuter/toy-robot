package com.reuter.rea.robot.model;

import com.reuter.rea.robot.exception.InvalidMovement;

/**
 * Created by aandra1 on 26/04/16.
 */
public class Table {

  private static final Integer MAX_X = 5;
  private static final Integer MAX_Y = 5;

  private static Table instance;

  private Table() {
  }

  public static Table getInstance() {
    if (instance == null) {
      instance = new Table();
    }

    return instance;
  }

  /**
   * Evaluates if the movement goes to a valid position on table.
   *
   * @param position
   * @return
   */
  public boolean validMovement(Position position) {
    if ((position.getXAxis() >= 0 && position.getXAxis() < MAX_X)
        && (position.getYAxis() >= 0 && position.getYAxis() < MAX_Y)) {
      return true;
    } else {
      throw new InvalidMovement(String.format("It's not possible to move to (%d,%d).",
          position.getXAxis(), position.getYAxis()));
    }
  }
}
