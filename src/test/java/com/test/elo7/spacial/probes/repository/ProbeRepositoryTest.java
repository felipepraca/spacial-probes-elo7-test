package com.test.elo7.spacial.probes.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import com.test.elo7.spacial.probes.model.Coordinate;
import com.test.elo7.spacial.probes.model.Direction;
import com.test.elo7.spacial.probes.model.Probe;

public class ProbeRepositoryTest {

	private ProbeRepository repostory = new ProbeRepository();

	@Test
	public void persistsProbesInMap() {
		// GIVEN
		Probe probe1 = new Probe(1, new Coordinate(1, 1), Direction.S);
		Probe probe2 = new Probe(2, new Coordinate(1, 3), Direction.S);

		// WHEN
		repostory.addProbe(probe1);
		repostory.addProbe(probe2);

		// THEN
		Map<Integer, Probe> probes = repostory.getProbes();

		assertEquals(probe1, probes.get(1));
		assertEquals(probe2, probes.get(2));
	}

	@Test
	public void removeProbesInMap() {
		// GIVEN
		Probe probe1 = new Probe(1, new Coordinate(1, 1), Direction.S);
		repostory.addProbe(probe1);

		// WHEN
		repostory.deleteProbe(1);

		// THEN
		Map<Integer, Probe> probes = repostory.getProbes();

		assertTrue(probes.isEmpty());
	}

	@Test
	public void getProbeInMap() {
		// GIVEN
		Probe probe1 = new Probe(1, new Coordinate(1, 1), Direction.S);
		repostory.addProbe(probe1);

		// WHEN
		Optional<Probe> probeOp1 = repostory.getProbeBy(1);
		Optional<Probe> probeOp2 = repostory.getProbeBy(2);

		// THEN
		assertTrue(probeOp1.isPresent());
		assertFalse(probeOp2.isPresent());
	}

}
