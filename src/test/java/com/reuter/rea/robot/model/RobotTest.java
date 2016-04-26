package com.reuter.rea.robot.model;

import com.reuter.rea.robot.exception.InvalidCommandException;
import com.reuter.rea.robot.exception.InvalidDirection;
import com.reuter.rea.robot.exception.InvalidMovement;
import com.reuter.rea.robot.exception.RobotNotPlacedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by aandra1 on 26/04/16.
 */
@RunWith(JUnit4.class)
public class RobotTest {

  @Test(expected = IllegalArgumentException.class)
  public void testExecuteEmptyCommand() {
    Robot robot = new Robot();
    robot.execute("");
  }

  @Test(expected = InvalidCommandException.class)
  public void testExecuteInvalidCommand() {
    Robot robot = new Robot();
    robot.execute("XXX");
  }

  @Test(expected = RobotNotPlacedException.class)
  public void testMoveWhenRobotIsNotOnTable() {
    Robot robot = new Robot();
    robot.execute("MOVE");
  }

  @Test(expected = InvalidDirection.class)
  public void testPlaceWithInvalidDirection() {
    Robot robot = new Robot();
    robot.execute("PLACE 3,2,XXX");
  }

  @Test(expected = InvalidMovement.class)
  public void testPlaceTheRobotOnAnInvalidSpot() {
    Robot robot = new Robot();
    robot.execute("PLACE 6,4,NORTH");
  }

  @Test
  public void testMoveRobotToEAST() {
    Robot robot = new Robot();
    String[] commands = new String[]{"PLACE 1,2,NORTH", "MOVE", "RIGHT", "MOVE", "REPORT"};

    for (String command : commands) {
      try {
        robot.execute(command);
      } catch (RobotNotPlacedException ex) {

      }
    }

    assertEquals(new Position(2, 3), robot.getCurrentPosition());
    assertEquals(Direction.EAST, robot.getDirection());
  }

  @Test
  public void testIgnoreMovementsBeforeFirstPlace() {
    Robot robot = new Robot();
    String[] commands = new String[]{"MOVE", "MOVE", "LEFT", "MOVE", "PLACE 1,2,NORTH", "MOVE", "RIGHT", "MOVE", "REPORT"};

    for (String command : commands) {
      try {
        robot.execute(command);
      } catch (RobotNotPlacedException ex) {

      }
    }

    assertEquals(new Position(2, 3), robot.getCurrentPosition());
    assertEquals(Direction.EAST, robot.getDirection());
  }
}
