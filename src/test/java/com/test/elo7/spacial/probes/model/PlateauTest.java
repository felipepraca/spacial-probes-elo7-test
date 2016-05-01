package com.test.elo7.spacial.probes.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlateauTest {

	@Test
	public void validatePlateauLimits() {
		// GIVEN
		Coordinate coordinate = new Coordinate(10, 8);

		// WHEN
		Plateau plateau = new Plateau(coordinate);

		// THEN
		assertEquals(10, plateau.getLimitX());
		assertEquals(8, plateau.getLimitY());
	}
	
	@Test
	public void validateAreaLimits() {
		// GIVEN
		Coordinate coordinate = new Coordinate(7, 5);

		// WHEN
		Plateau plateau = new Plateau(coordinate);

		// THEN
		assertEquals(7, plateau.getArea().getX());
		assertEquals(5, plateau.getArea().getY());
	}

	@Test
	public void validatePosition() {
		// GIVEN
		Coordinate coordinate = new Coordinate(20, 15);
		Plateau plateau = new Plateau(coordinate);

		Probe probe = new Probe(10, new Coordinate(3, 5), Direction.N);
		plateau.addProbe(probe);

		// WHEN
		boolean notEmpty = plateau.positionIsEmpty(new Coordinate(3, 5));
		boolean empty = plateau.positionIsEmpty(new Coordinate(9, 3));

		// THEN
		assertFalse(notEmpty);
		assertTrue(empty);

	}
}
