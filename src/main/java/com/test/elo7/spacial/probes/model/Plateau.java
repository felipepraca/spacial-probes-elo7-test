package com.test.elo7.spacial.probes.model;

import java.util.HashSet;
import java.util.Set;

public class Plateau {

	private Coordinate area;
	private Set<Probe> probes;

	protected Plateau() {
		this.probes = new HashSet<>();
	}

	public Plateau(Coordinate coordinate) {
		this();
		this.area = coordinate;
	}

	public boolean positionIsEmpty(Coordinate coordinate) {
		long count = probes.stream().filter(probe -> probe.getCoordinate().equals(coordinate)).count();

		return count == 0;
	}

	public Coordinate getArea() {
		return area;
	}

	public void addProbe(Probe probe) {
		probes.add(probe);
	}

	public int getLimitX() {
		return area.getX();
	}

	public int getLimitY() {
		return area.getY();
	}

}
