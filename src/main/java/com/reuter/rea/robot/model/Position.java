package com.reuter.rea.robot.model;

/**
 * Created by aandra1 on 26/04/16.
 */
public class Position {

  private int xAxis;
  private int yAxis;

  public Position(int xAxis, int yAxis) {
    this.xAxis = xAxis;
    this.yAxis = yAxis;
  }

  public void incrementX(int step) {
    this.xAxis += step;
  }

  public void incrementY(int step) {
    this.yAxis += step;
  }

  public int getXAxis() {
    return this.xAxis;
  }

  public int getYAxis() {
    return this.yAxis;
  }

  public Position clone() {
    return new Position(this.xAxis, this.yAxis);
  }

  public String toString() {
    return String.format("%d,%d", this.xAxis, this.yAxis);
  }


  public boolean equals(Object obj) {
    if (!(obj instanceof Position))
      throw new IllegalArgumentException("The object must be a Position.");

    Position target = (Position) obj;
    return (this.getXAxis() == target.getXAxis()) && (this.getYAxis() == target.getYAxis());
  }


  public int hashCode() {
    int hashCode = 17;
    hashCode *= this.getXAxis();
    hashCode *= this.getYAxis();
    return hashCode;
  }
}
