package com.test.elo7.spacial.probes.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.test.elo7.spacial.probes.exception.PlateauLimitException;
import com.test.elo7.spacial.probes.exception.ProbeInvalidePositionException;

public class Probe {

	@NotNull(message = "Probe id is required!")
	private Integer id;

	@JsonIgnore
	private Plateau plateau;

	@NotNull(message = "Probe coordinate is required!")
	private Coordinate coordinate;

	@NotNull(message = "Probe direction is required!")
	private Direction direction;
	private Map<String, String> links;

	@JsonInclude(Include.NON_NULL)
	private String error;

	protected Probe() {
		this.links = new HashMap<>();
	}

	public Probe(Integer id, Coordinate coordinate, Direction direction) {
		this();

		if (Objects.isNull(id) || Objects.isNull(coordinate) || Objects.isNull(direction)) {
			throw new IllegalArgumentException(String.format(
					"Invalid parameters id : [%d] coordinate : [%s], direction [%s]", id, coordinate, direction));
		}

		this.id = id;
		this.coordinate = coordinate;
		this.direction = direction;
	}

	public void execute(List<Action> actions) throws PlateauLimitException, ProbeInvalidePositionException {

		for (Action action : actions) {
			if (Action.M.equals(action)) {
				move();
			} else if (Action.R.equals(action) || Action.L.equals(action)) {
				changeDirection(action);
			}
		}
	}

	private void move() throws PlateauLimitException, ProbeInvalidePositionException {

		Coordinate simulatedCoordinate = coordinate.simulate(direction.getCoordinate());

		plateau.validateCoordinate(simulatedCoordinate);

		coordinate.add(direction.getCoordinate());

	}

	private void changeDirection(Action action) {
		direction = direction.rotateFor(action);
	}

	public Map<String, String> getLinks() {
		links.put("get", "/probe/" + id);
		links.put("delete", "/probe/" + id);
		links.put("put", "/probe/" + id);
		return links;
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
