package com.test.elo7.spacial.probes.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.test.elo7.spacial.probes.exception.ProbeNotFoundException;
import com.test.elo7.spacial.probes.model.Action;
import com.test.elo7.spacial.probes.model.Coordinate;
import com.test.elo7.spacial.probes.model.Direction;
import com.test.elo7.spacial.probes.model.Mission;
import com.test.elo7.spacial.probes.model.Plateau;
import com.test.elo7.spacial.probes.model.Probe;
import com.test.elo7.spacial.probes.repository.ProbeRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProbeServiceTest {

	@InjectMocks
	private ProbeService service;

	@Mock
	private ProbeRepository probeRepository;

	@Test
	public void executeExploration() {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(5, 5));
		Probe probe1 = new Probe(1, new Coordinate(2, 3), Direction.S);
		probe1.setPlateau(plateau);

		Probe probe2 = new Probe(2, new Coordinate(4, 4), Direction.E);
		probe2.setPlateau(plateau);

		Map<Integer, List<Action>> actions = new HashMap<>();
		actions.put(1, Arrays.asList(Action.M, Action.M, Action.L));
		actions.put(2, Arrays.asList(Action.M, Action.M, Action.M));

		Mission mission = new Mission();
		mission.setPlateau(plateau);
		mission.setProbes(Arrays.asList(probe1, probe2));
		mission.setActions(actions);

		// WHEN
		List<Probe> probes = service.explorer(mission);

		// THEN
		verify(probeRepository, times(2)).addProbe(any(Probe.class));
		verify(probeRepository, times(1)).addProbe(probe1);
		verify(probeRepository, times(1)).addProbe(probe2);

		assertEquals(new Coordinate(2, 1), probes.get(0).getCoordinate());
		assertEquals(Direction.E, probes.get(0).getDirection());
		assertNull(probes.get(0).getError());

		assertEquals(new Coordinate(5, 4), probes.get(1).getCoordinate());
		assertEquals(Direction.E, probes.get(1).getDirection());
		assertNotNull(probes.get(1).getError());

	}

	@Test
	public void getProbe() throws ProbeNotFoundException {
		// GIVEN
		Probe probe = new Probe(1, new Coordinate(2, 3), Direction.S);
		Optional<Probe> probeOp = Optional.of(probe);

		when(probeRepository.getProbeBy(1)).thenReturn(probeOp);

		// WHEN
		Probe probeGet = service.getProbeBy(1);

		// THEN
		assertEquals(probe, probeGet);
	}

	@Test(expected = ProbeNotFoundException.class)
	public void probeNotFound() throws ProbeNotFoundException {
		// GIVEN
		Optional<Probe> probeOp = Optional.empty();

		when(probeRepository.getProbeBy(1)).thenReturn(probeOp);

		// WHEN
		service.getProbeBy(1);

		// THEN
		fail();
	}

	@Test
	public void deleteProbe() throws ProbeNotFoundException {
		// GIVEN

		// WHEN
		service.deleteProbeBy(1);

		// THEN
		verify(probeRepository, times(1)).deleteProbe(1);
	}

	@Test
	public void executeExplorationOnlyAProbe() throws ProbeNotFoundException {
		// GIVEN
		Plateau plateau = new Plateau(new Coordinate(5, 5));
		Probe probe1 = new Probe(1, new Coordinate(2, 3), Direction.S);
		probe1.setPlateau(plateau);

		List<Action> actions = Arrays.asList(Action.M, Action.M, Action.L);

		Optional<Probe> probeOp = Optional.of(probe1);

		when(probeRepository.getProbeBy(1)).thenReturn(probeOp);

		// WHEN
		Probe probe = service.explorerWith(1, actions);

		// THEN
		verify(probeRepository, times(1)).getProbeBy(1);

		assertEquals(new Coordinate(2, 1), probe.getCoordinate());
		assertEquals(Direction.E, probe.getDirection());
		assertNull(probe.getError());

	}

}
