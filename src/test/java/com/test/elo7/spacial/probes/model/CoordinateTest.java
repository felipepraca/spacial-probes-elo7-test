package com.test.elo7.spacial.probes.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void addOnAxisX() {
		// GIVEN
		int x = 1;

		Coordinate coordinate = new Coordinate(3, 2);

		// WHEN
		coordinate.addX(x);

		// THEN
		assertEquals(4, coordinate.getX());
	}

	@Test
	public void addOnAxisY() {
		// GIVEN
		int y = 1;

		Coordinate coordinate = new Coordinate();

		// WHEN
		coordinate.addY(y);

		// THEN
		assertEquals(1, coordinate.getY());
	}

	@Test
	public void simulateOnAxisX() {
		// GIVEN
		int x = 1;
		Coordinate coordinate = new Coordinate();

		// WHEN
		int simulateAddX = coordinate.simulateAddX(x);

		// THEN
		assertEquals(1, simulateAddX);
	}

	@Test
	public void simulateOnAxisY() {
		// GIVEN
		int y = 1;
		Coordinate coordinate = new Coordinate(5, 6);

		// WHEN
		int simulateAddY = coordinate.simulateAddY(y);

		// THEN
		assertEquals(7, simulateAddY);
	}

}
