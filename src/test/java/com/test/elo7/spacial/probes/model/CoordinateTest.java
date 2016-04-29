package com.test.elo7.spacial.probes.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void addOnAxisX() {
		// GIVEN
		int x = 1;

		Coordinate coordinate = new Coordinate(3, 2);

		// THEN
		coordinate.addX(x);

		// WHEN
		assertEquals(4, coordinate.getX());
	}

	@Test
	public void addOnAxisY() {
		// GIVEN
		int y = 1;

		Coordinate coordinate = new Coordinate(5, 7);

		// THEN
		coordinate.addY(y);

		// WHEN
		assertEquals(8, coordinate.getY());
	}

}
