package com.reuter.rea.robot;

import com.reuter.rea.robot.exception.InvalidDirection;
import com.reuter.rea.robot.exception.InvalidMovement;
import com.reuter.rea.robot.exception.RobotNotPlacedException;
import com.reuter.rea.robot.model.Robot;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Created by aandra1 on 07/04/16.
 */
public class Simulator {

  public static void main(String args[]) throws Exception {
    Robot robot = new Robot();

    FileReader reader = new FileReader(args[0]);
    BufferedReader bufferedReader = new BufferedReader(reader);
    String command = bufferedReader.readLine();

    while (command != null) {
      try {
        robot.execute(command);
      } catch (IllegalArgumentException | InvalidDirection | InvalidMovement | RobotNotPlacedException ex) {
        System.out.println(ex.getMessage());
      }

      command = bufferedReader.readLine();
    }
  }
}
