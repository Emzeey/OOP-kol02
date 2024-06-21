package org.example;

public class Point {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point sum(Point p) {
        return new Point(this.x + p.getX(), this.y + p.getY());
    }

    public Point sum(double val) { return new Point(this.x + val, this.y + val); }

    public Point times(double val) { return new Point(this.x * val, this.y * val); }

    public Point divideBy(double val) { return new Point(this.x / val, this.y / val); }
}
