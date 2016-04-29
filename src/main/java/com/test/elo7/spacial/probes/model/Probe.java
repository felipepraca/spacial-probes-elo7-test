package com.test.elo7.spacial.probes.model;

import java.util.List;
import java.util.Objects;

import com.test.elo7.spacial.probes.exception.PlateauLimitException;

public class Probe {

	private Plateau plateau;
	private Coordinate coordinate;
	private Direction direction;

	public Probe(Plateau plateau, Coordinate coordinate, Direction direction) {

		if (Objects.isNull(plateau) || Objects.isNull(coordinate) || Objects.isNull(direction)) {
			throw new IllegalArgumentException(
					String.format("Invalid parameters plateau: [%s], coordinate : [%s], direction [%s]", plateau, coordinate, direction));
		}

		this.plateau = plateau;
		this.coordinate = coordinate;
		this.direction = direction;
	}

	public void execute(List<Action> actions) throws PlateauLimitException {

		for (Action action : actions) {
			if (Action.M.equals(action)) {
				move();
			} else if (Action.R.equals(action) || Action.L.equals(action)) {
				changeDirection(action);
			}
		}
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Direction getDirection() {
		return direction;
	}

	private void move() throws PlateauLimitException {

		if (coordinate.getX() < plateau.getX()) {
			coordinate.addX(direction.getCoordinate().getX());
		} else {
			getPlateauLimitException();
		}

		if (coordinate.getY() < plateau.getY()) {
			coordinate.addY(direction.getCoordinate().getY());
		} else {
			getPlateauLimitException();
		}

	}

	private void getPlateauLimitException() throws PlateauLimitException {
		throw new PlateauLimitException(String.format("The probe tried to exceed the plateau limit: probe position [%s], plateau limit [%s].", this.coordinate, plateau));
	}

	private void changeDirection(Action action) {
		direction = direction.rotateFor(action);
	}

}
