package com.test.elo7.spacial.probes.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.test.elo7.spacial.probes.exception.PlateauLimitException;
import com.test.elo7.spacial.probes.exception.ProbeInvalidePositionException;

public class Plateau {

	@NotNull(message = "Plateau area is required!")
	private Coordinate area;
	private Set<Probe> probes;

	protected Plateau() {
		this.probes = new HashSet<>();
	}

	public Plateau(Coordinate coordinate) {
		this();
		this.area = coordinate;
	}

	private void positionEmpty(Coordinate coordinate) throws ProbeInvalidePositionException {
		long count = probes.stream().filter(probe -> probe.getCoordinate().equals(coordinate)).count();

		if (count != 0) {
			throw new ProbeInvalidePositionException(String
					.format("Exists a probe in this coordinate : [%s]. Recalculate mission.", coordinate));
		}
	}

	public Coordinate getArea() {
		return area;
	}

	public void addProbe(Probe probe) throws ProbeInvalidePositionException {
		positionEmpty(probe.getCoordinate());
		probes.add(probe);
	}

	private void inLimits(Coordinate coordinate) throws PlateauLimitException {
		boolean xInLimit = coordinate.getX() <= area.getX();
		boolean yInLimit = coordinate.getY() <= area.getY();

		if (!xInLimit || !yInLimit) {
			throw new PlateauLimitException(String.format(
					"The probe tried to exceed the plateau limit: probe position [%s], plateau limit [%s].", coordinate,
					area));
		}
	}

	public void validateCoordinate(Coordinate coordinate) throws PlateauLimitException, ProbeInvalidePositionException {
		inLimits(coordinate);
		positionEmpty(coordinate);
	}

}
