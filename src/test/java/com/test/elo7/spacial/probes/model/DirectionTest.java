package com.test.elo7.spacial.probes.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void validateChangeDirectionToRight() {
		// GIVEN
		Direction directionInitial = Direction.N;

		// THEN
		Direction directionE = directionInitial.rotateFor(Action.R);
		Direction directionS = directionE.rotateFor(Action.R);
		Direction directionW = directionS.rotateFor(Action.R);
		Direction directionN = directionW.rotateFor(Action.R);

		// WHEN
		assertEquals(Direction.E, directionE);
		assertEquals(Direction.S, directionS);
		assertEquals(Direction.W, directionW);
		assertEquals(Direction.N, directionN);
	}

	@Test
	public void validateChangeDirectionToLeft() {
		// GIVEN
		Direction directionInitial = Direction.N;

		// THEN
		Direction directionW = directionInitial.rotateFor(Action.L);
		Direction directionS = directionW.rotateFor(Action.L);
		Direction directionE = directionS.rotateFor(Action.L);
		Direction directionN = directionE.rotateFor(Action.L);

		// WHEN
		assertEquals(Direction.W, directionW);
		assertEquals(Direction.S, directionS);
		assertEquals(Direction.E, directionE);
		assertEquals(Direction.N, directionN);
	}

}
