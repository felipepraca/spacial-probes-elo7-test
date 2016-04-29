package com.test.elo7.spacial.probes.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlateauTest {

	@Test
	public void validatePlateauLimits() {
		// GIVEN
		Coordinate coordinate = new Coordinate(10, 8);

		// THEN
		Plateau plateau = new Plateau(coordinate);

		// WHEN
		assertEquals(10, plateau.getX());
		assertEquals(8, plateau.getY());
	}

}
