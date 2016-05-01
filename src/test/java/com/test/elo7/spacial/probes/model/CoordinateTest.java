package com.test.elo7.spacial.probes.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void addOnAxisX() {
		// GIVEN
		Coordinate coordinate = new Coordinate(3, 2);

		// WHEN
		coordinate.add(Direction.E.getCoordinate());

		// THEN
		assertEquals(4, coordinate.getX());
	}

	@Test
	public void addOnAxisY() {
		// GIVEN
		Coordinate coordinate = new Coordinate();

		// WHEN
		coordinate.add(Direction.N.getCoordinate());

		// THEN
		assertEquals(1, coordinate.getY());
	}

	@Test
	public void simulateOnAxisX() {
		// GIVEN
		Coordinate coordinate = new Coordinate();

		// WHEN
		Coordinate simulate = coordinate.simulate(Direction.W.getCoordinate());

		// THEN
		assertEquals(new Coordinate(-1, 0), simulate);
	}

	@Test
	public void simulateOnAxisY() {
		// GIVEN
		Coordinate coordinate = new Coordinate(5, 6);

		// WHEN
		Coordinate simulate = coordinate.simulate(Direction.S.getCoordinate());

		// THEN
		assertEquals(new Coordinate(5, 5), simulate);
	}

}
