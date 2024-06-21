package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    @ParameterizedTest
    @ValueSource(doubles = {1, 2, 3, 4, 5.8, 6, 7, 8, 9, 10})
    public void timesTest(double val) {
        int x = 4, y = 6;
        Point p = new Point(x, y);
        Point newPoint = p.times(val);
        assertEquals(p.getX() * val, newPoint.getX());
        assertEquals(p.getY() * val, newPoint.getY());
    }

    @ParameterizedTest
    @MethodSource("examplePoints")
    void sumTest(Point point) {
        Point sum = point.sum(new Point(5.1, 5.9));
        assertEquals(point.getX() + 5.1, sum.getX());
        assertEquals(point.getY() + 5.9, sum.getY());
    }

    private static Stream<Point> examplePoints() {
        return Stream.of(
                new Point(3, 4),
                new Point(9, -1),
                new Point(-10, 10),
                new Point(-1./11, 0.09)
        );
    }
}