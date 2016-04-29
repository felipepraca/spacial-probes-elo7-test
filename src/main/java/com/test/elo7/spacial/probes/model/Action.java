package com.test.elo7.spacial.probes.model;

public enum Action {

	R(+1),
	L(-1),
	M(0);

	private int directionFactor;

	private Action(int directionFactor) {
		this.directionFactor = directionFactor;
	}

	public int getDirectionFactor() {
		return directionFactor;
	}

}
