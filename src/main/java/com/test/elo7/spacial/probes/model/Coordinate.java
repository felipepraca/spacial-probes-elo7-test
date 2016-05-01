package com.test.elo7.spacial.probes.model;

public class Coordinate {

	private int x;
	private int y;

	protected Coordinate() {
	}

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int simulateAddX(int x) {
		return getX() + x;
	}

	public void addX(int x) {
		this.x = simulateAddX(x);
	}

	public int simulateAddY(int y) {
		return getY() + y;
	}

	public void addY(int y) {
		this.y = simulateAddY(y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
