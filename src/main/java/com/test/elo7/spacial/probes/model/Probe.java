package com.test.elo7.spacial.probes.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.test.elo7.spacial.probes.exception.PlateauLimitException;

public class Probe {

	private int id;

	@JsonIgnore
	private Plateau plateau;
	private Coordinate coordinate;
	private Direction direction;

	@JsonInclude(Include.NON_NULL)
	private String error;

	protected Probe() {
	}

	public Probe(int id, Coordinate coordinate, Direction direction) {

		if (Objects.isNull(coordinate) || Objects.isNull(direction)) {
			throw new IllegalArgumentException(
					String.format("Invalid parameters coordinate : [%s], direction [%s]", coordinate, direction));
		}

		this.id = id;
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

	private void move() throws PlateauLimitException {

		if (coordinate.simulateAddX(direction.getCoordinate().getX()) <= plateau.getLimitX()) {
			coordinate.addX(direction.getCoordinate().getX());
		} else {
			throw getPlateauLimitException();
		}

		if (coordinate.simulateAddY(direction.getCoordinate().getY()) <= plateau.getLimitY()) {
			coordinate.addY(direction.getCoordinate().getY());
		} else {
			throw getPlateauLimitException();
		}

	}

	private PlateauLimitException getPlateauLimitException() throws PlateauLimitException {
		return new PlateauLimitException(
				String.format("The probe tried to exceed the plateau limit: probe position [%s], plateau limit [%s].",
						this.coordinate, plateau.getArea()));
	}

	private void changeDirection(Action action) {
		direction = direction.rotateFor(action);
	}

	public int getId() {
		return id;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
