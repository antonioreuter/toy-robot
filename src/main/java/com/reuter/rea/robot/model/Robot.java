package com.reuter.rea.robot.model;

import com.reuter.rea.robot.exception.InvalidCommandException;
import com.reuter.rea.robot.exception.InvalidDirection;
import com.reuter.rea.robot.exception.RobotNotPlacedException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aandra1 on 26/04/16.
 */
public class Robot {
  private static final Pattern PLACE_PATTERN = Pattern.compile("PLACE (\\d+),(\\d+),(.*)");

  private Direction direction = Direction.NORTH;

  private Position currentPosition;

  /**
   * Instruct the robot to perform a command
   * <p>Valid Commands: MOVE, LEFT, RIGHT, REPORT and PLACE X,Y,F<p/>
   * <p>On PLACE Command: X- coordinate X, Y- coordinate Y adn F- direction(NORTH,EAST,SOUTH,WEST)<p/>
   *
   * @param command
   */
  public void execute(String command) {
    if (StringUtils.isEmpty(command))
      throw new IllegalArgumentException("The command cannot be NULL!");

    command = command.toUpperCase();

    if (command.startsWith("PLACE")) {
      Matcher m = PLACE_PATTERN.matcher(command);
      m.find();
      this.place(new Position(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2))), m.group(3));
    } else if (command.startsWith("MOVE")) {
      this.move();
    } else if (command.startsWith("LEFT")) {
      this.left();
    } else if (command.startsWith("RIGHT")) {
      this.right();
    } else if (command.startsWith("REPORT")) {
      System.out.println(this.report());
    } else {
      throw new InvalidCommandException("You try to perform an invalid command: " + command);
    }
  }

  public Position getCurrentPosition() {
    return this.currentPosition;
  }

  public Direction getDirection() {
    return this.direction;
  }

  private void place(Position position, String direction) {
    try {
      this.direction = Direction.valueOf(direction.toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new InvalidDirection("Invalid direction" + direction, ex);
    }

    if (Table.getInstance().validMovement(position)) {
      this.updatePosition(position);
    }
  }

  private void move() {
    validateRobotOnTable();
    Position nextPosition = forward(1);
    if (Table.getInstance().validMovement(nextPosition)) {
      this.updatePosition(nextPosition);
    }
  }

  private void left() {
    validateRobotOnTable();

    this.direction = this.direction.left();
  }

  private void right() {
    validateRobotOnTable();

    this.direction = this.direction.right();
  }

  private String report() {
    validateRobotOnTable();

    return String.format("%s,%s", this.currentPosition, this.direction);
  }

  public void updatePosition(Position position) {
    this.currentPosition = position;
  }

  private void validateRobotOnTable() {
    if (this.currentPosition == null)
      throw new RobotNotPlacedException("The robot is not placed on table.");
  }

  private Position forward(int step) {
    Position nextPosition = currentPosition.clone();

    switch (this.direction) {
      case NORTH:
        nextPosition.incrementY(step);
        break;
      case SOUTH:
        nextPosition.incrementY(-step);
        break;
      case EAST:
        nextPosition.incrementX(step);
        break;
      case WEST:
        nextPosition.incrementX(-step);
        break;
    }

    return nextPosition;
  }
}
