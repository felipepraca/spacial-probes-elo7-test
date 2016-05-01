package com.test.elo7.spacial.probes.model;

import javax.validation.constraints.NotNull;

public class Coordinate {

	@NotNull(message = "Coordinate X required!")
	private int x;

	@NotNull(message = "Coordinate Y required!")
	private int y;

	protected Coordinate() {
	}

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Coordinate simulate(Coordinate coordinate) {
		int x = getX() + coordinate.getX();
		int y = getY() + coordinate.getY();
		return new Coordinate(x, y);
	}

	public void add(Coordinate coordinate) {
		Coordinate simulate = simulate(coordinate);

		this.x = simulate.getX();
		this.y = simulate.getY();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}

}
