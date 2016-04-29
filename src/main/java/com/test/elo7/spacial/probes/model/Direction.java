package com.test.elo7.spacial.probes.model;

public enum Direction {

	N(new Coordinate(0, 1)),
	E(new Coordinate(1, 0)),
	S(new Coordinate(0, -1)),
	W(new Coordinate(-1, 0));

	private Coordinate coordinate;

	private Direction(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Direction rotateFor(Action action) {
		return getByOrdinal(this.ordinal() + action.getDirectionFactor());
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	private Direction getByOrdinal(int directionOrdinal) {

		if (directionOrdinal > 3) {
			directionOrdinal = 0;
		} else if (directionOrdinal < 0) {
			directionOrdinal = 3;
		}

		return Direction.values()[directionOrdinal];
	}

}
