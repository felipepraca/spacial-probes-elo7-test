package com.test.elo7.spacial.probes.model;

public class Plateau {

	private Coordinate coordinate;

	public Plateau(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public int getX() {
		return coordinate.getX();
	}

	public int getY() {
		return coordinate.getY();
	}

	@Override
	public String toString() {
		return "Plateau [coordinate=" + coordinate + "]";
	}

}
