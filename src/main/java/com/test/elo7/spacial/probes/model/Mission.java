package com.test.elo7.spacial.probes.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

public class Mission {

	@NotNull(message = "Plateau is required!")
	private Plateau plateau;

	@NotNull(message = "Probes is required!")
	private List<Probe> probes;

	private Map<Integer, List<Action>> actions = new HashMap<>();

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public List<Probe> getProbes() {
		return probes;
	}

	public void setProbes(List<Probe> probes) {
		this.probes = probes;
	}

	public Map<Integer, List<Action>> getActions() {
		return actions;
	}

	public void setActions(Map<Integer, List<Action>> actions) {
		this.actions = actions;
	}

}
