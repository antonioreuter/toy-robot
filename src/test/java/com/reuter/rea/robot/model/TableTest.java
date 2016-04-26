package com.reuter.rea.robot.model;

import com.reuter.rea.robot.exception.InvalidMovement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 * Created by aandra1 on 26/04/16.
 */
@RunWith(JUnit4.class)
public class TableTest {

  @Test(expected = InvalidMovement.class)
  public void testInvalidMovement() {
    Position position = new Position(6, 2);
    Table.getInstance().validMovement(position);
  }

  @Test
  public void testValidMovement() {
    Position position = new Position(3, 2);
    boolean validMovement = Table.getInstance().validMovement(position);
    assertTrue(validMovement);
  }
}
