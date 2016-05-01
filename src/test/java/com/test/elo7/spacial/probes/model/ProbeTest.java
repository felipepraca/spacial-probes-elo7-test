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

		Probe probe = new Probe(1, coordinate, direction);
		probe.setPlateau(plateau);

		List<Action> actions = Arrays.asList(Action.L, Action.L, Action.L, Action.R);

		// WHEN
		probe.execute(actions);

		// THEN
		assertEquals(new Coordinate(0, 0), probe.getCoordinate());
		assertEquals(Direction.S, probe.getDirection());
	}

	@Test
	public void validateMove() throws PlateauLimitException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(5, 8));
		Coordinate coordinate = new Coordinate(1, 5);
		Direction direction = Direction.E;

		Probe probe = new Probe(1, coordinate, direction);
		probe.setPlateau(plateau);

		List<Action> actions = Arrays.asList(Action.M,
											 Action.M,
											 Action.M);

		// WHEN
		probe.execute(actions);

		// THEN
		assertEquals(new Coordinate(4, 5), probe.getCoordinate());
		assertEquals(Direction.E, probe.getDirection());
	}

	@Test
	public void validateRotateAndeMove() throws PlateauLimitException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(5, 5));
		Coordinate coordinate = new Coordinate(1, 2);
		Direction direction = Direction.N;

		Probe probe = new Probe(1, coordinate, direction);
		probe.setPlateau(plateau);

		List<Action> actions = Arrays.asList(Action.L, Action.M,
											 Action.L, Action.M,
											 Action.L, Action.M,
											 Action.L, Action.M,
											 Action.M);

		// WHEN
		probe.execute(actions);

		// THEN
		assertEquals(new Coordinate(1, 3), probe.getCoordinate());
		assertEquals(Direction.N, probe.getDirection());
	}

	@Test
	public void validateRotateAndeMove2() throws PlateauLimitException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(5, 5));
		Coordinate coordinate = new Coordinate(3, 3);
		Direction direction = Direction.E;

		Probe probe = new Probe(1, coordinate, direction);
		probe.setPlateau(plateau);

		List<Action> actions = Arrays.asList(Action.M, Action.M,
											 Action.R, Action.M,
											 Action.M, Action.R,
											 Action.M, Action.R,
											 Action.R, Action.M);

		// WHEN
		probe.execute(actions);

		// THEN
		assertEquals(new Coordinate(5, 1), probe.getCoordinate());
		assertEquals(Direction.E, probe.getDirection());
	}

	@Test(expected = PlateauLimitException.class)
	public void erroToMoveInAxisY() throws PlateauLimitException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(3, 3));
		Coordinate coordinate = new Coordinate(0, 0);
		Direction direction = Direction.N;

		Probe probe = new Probe(1, coordinate, direction);
		probe.setPlateau(plateau);

		List<Action> actions = Arrays.asList(Action.M, Action.M, 
											 Action.M, Action.M);

		// WHEN
		probe.execute(actions);

		// THEN
		fail();
	}

	@Test(expected = PlateauLimitException.class)
	public void erroToMoveInAxisX() throws PlateauLimitException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(3, 3));
		Coordinate coordinate = new Coordinate(0, 0);
		Direction direction = Direction.E;

		Probe probe = new Probe(1, coordinate, direction);
		probe.setPlateau(plateau);

		List<Action> actions = Arrays.asList(Action.M, Action.M, 
											 Action.M, Action.M);

		// WHEN
		probe.execute(actions);

		// THEN
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorToCreateProdeWithoutCoordinate() {
		// GIVEN
		Coordinate coordinate = null;
		Direction direction = Direction.N;

		// WHEN
		new Probe(1, coordinate, direction);

		// THEN
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void errorToCreateProdeWithoutDirection() {
		// GIVEN
		Coordinate coordinate = new Coordinate(0, 0);
		Direction direction = null;

		// WHEN
		new Probe(1, coordinate, direction);

		// THEN
		fail();
	}

}
