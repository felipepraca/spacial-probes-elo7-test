package com.test.elo7.spacial.probes.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.test.elo7.spacial.probes.exception.PlateauLimitException;
import com.test.elo7.spacial.probes.exception.ProbeInvalidePositionException;

public class PlateauTest {

	@Test(expected = PlateauLimitException.class)
	public void validatePlateauLimits() throws PlateauLimitException, ProbeInvalidePositionException {
		// GIVEN
		Coordinate coordinate = new Coordinate(10, 8);
		Plateau plateau = new Plateau(coordinate);

		// WHEN
		plateau.validateCoordinate(new Coordinate(10, 9));

		// THEN
		fail();
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

	@Test(expected = ProbeInvalidePositionException.class)
	public void invalideProbeNewPosition() throws ProbeInvalidePositionException, PlateauLimitException {
		// GIVEN
		Coordinate coordinate = new Coordinate(20, 15);
		Plateau plateau = new Plateau(coordinate);

		Probe probe = new Probe(10, new Coordinate(3, 5), Direction.N);
		plateau.addProbe(probe);

		// WHEN
		plateau.validateCoordinate(new Coordinate(3, 5));

		// THEN
		fail();
	}

	@Test
	public void valideProbeNewPosition() throws ProbeInvalidePositionException, PlateauLimitException {
		// GIVEN
		Coordinate coordinate = new Coordinate(20, 15);
		Plateau plateau = new Plateau(coordinate);

		Probe probe = new Probe(10, new Coordinate(3, 5), Direction.N);
		plateau.addProbe(probe);

		// WHEN
		plateau.validateCoordinate(new Coordinate(4, 3));

		// THEN
		// Não gerou nenhuma exceção, tudo ok
	}

	@Test(expected = ProbeInvalidePositionException.class)
	public void invalideProbePosition() throws ProbeInvalidePositionException, PlateauLimitException {
		// GIVEN
		Coordinate coordinate = new Coordinate(20, 15);
		Plateau plateau = new Plateau(coordinate);

		Probe probe1 = new Probe(1, new Coordinate(3, 5), Direction.N);
		Probe probe2 = new Probe(2, new Coordinate(3, 5), Direction.S);

		plateau.addProbe(probe1);

		// WHEN
		plateau.addProbe(probe2);

		// THEN
		fail();
	}

}
