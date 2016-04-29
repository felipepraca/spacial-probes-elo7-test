package com.test.elo7.spacial.probes.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.test.elo7.spacial.probes.exception.PlateauLimitException;

public class ProbeTest {

	@Test
	public void valodateRotate() throws PlateauLimitException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(5, 5));
		Coordinate coordinate = new Coordinate(0, 0);
		Direction direction = Direction.N;

		Probe probe = new Probe(plateau, coordinate, direction);

		List<Action> actions = Arrays.asList(Action.L, Action.L, Action.L, Action.R);

		// THEN
		probe.execute(actions);

		// WHEN
		assertEquals(new Coordinate(0, 0), probe.getCoordinate());
		assertEquals(Direction.S, probe.getDirection());
	}

	@Test
	public void validateMove() throws PlateauLimitException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(5, 8));
		Coordinate coordinate = new Coordinate(1, 5);
		Direction direction = Direction.E;

		Probe probe = new Probe(plateau, coordinate, direction);

		List<Action> actions = Arrays.asList(Action.M,
											 Action.M,
											 Action.M);

		// THEN
		probe.execute(actions);

		// WHEN
		assertEquals(new Coordinate(4, 5), probe.getCoordinate());
		assertEquals(Direction.E, probe.getDirection());
	}

	@Test
	public void validateRotateAndeMove() throws PlateauLimitException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(5, 8));
		Coordinate coordinate = new Coordinate(1, 2);
		Direction direction = Direction.N;

		Probe probe = new Probe(plateau, coordinate, direction);

		List<Action> actions = Arrays.asList(Action.L, Action.M,
											 Action.L, Action.M,
											 Action.L, Action.M,
											 Action.L, Action.M,
											 Action.M);

		// THEN
		probe.execute(actions);

		// WHEN
		assertEquals(new Coordinate(1, 3), probe.getCoordinate());
		assertEquals(Direction.N, probe.getDirection());
	}

	@Test(expected = PlateauLimitException.class)
	public void erroToMoveInAxisY() throws PlateauLimitException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(3, 3));
		Coordinate coordinate = new Coordinate(0, 0);
		Direction direction = Direction.N;

		Probe probe = new Probe(plateau, coordinate, direction);

		List<Action> actions = Arrays.asList(Action.M, Action.M, 
											 Action.M, Action.M);

		// THEN
		probe.execute(actions);

		// WHEN
		fail();
	}

	@Test(expected = PlateauLimitException.class)
	public void erroToMoveInAxisX() throws PlateauLimitException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(3, 3));
		Coordinate coordinate = new Coordinate(0, 0);
		Direction direction = Direction.E;

		Probe probe = new Probe(plateau, coordinate, direction);

		List<Action> actions = Arrays.asList(Action.M, Action.M, 
											 Action.M, Action.M);

		// THEN
		probe.execute(actions);

		// WHEN
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorToCreateProdeWithoutPlateau() {
		// GIVEN
		Plateau plateau = null;
		Coordinate coordinate = new Coordinate(0, 0);
		Direction direction = Direction.N;

		// THEN
		new Probe(plateau, coordinate, direction);

		// WHEN
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorToCreateProdeWithoutCoordinate() {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(3, 3));
		Coordinate coordinate = null;
		Direction direction = Direction.N;

		// THEN
		new Probe(plateau, coordinate, direction);

		// WHEN
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorToCreateProdeWithoutDirection() {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(3, 3));
		Coordinate coordinate = new Coordinate(0, 0);
		Direction direction = null;

		// THEN
		new Probe(plateau, coordinate, direction);

		// WHEN
		fail();
	}

}
