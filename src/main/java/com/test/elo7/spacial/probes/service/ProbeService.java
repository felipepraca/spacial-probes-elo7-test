package com.test.elo7.spacial.probes.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.elo7.spacial.probes.exception.PlateauLimitException;
import com.test.elo7.spacial.probes.exception.ProbeNotFoundException;
import com.test.elo7.spacial.probes.model.Action;
import com.test.elo7.spacial.probes.model.Mission;
import com.test.elo7.spacial.probes.model.Plateau;
import com.test.elo7.spacial.probes.model.Probe;
import com.test.elo7.spacial.probes.repository.ProbeRepository;

@Service
public class ProbeService {

	@Autowired
	private ProbeRepository probeRepository;

	public List<Probe> explorer(Mission mission) {

		Plateau plateau = mission.getPlateau();
		List<Probe> probes = mission.getProbes();

		prepareProbes(plateau, probes);

		Map<Integer, List<Action>> actionsMap = mission.getActions();

		probes.forEach(probe -> {
			explorer(probe, actionsMap.get(probe.getId()));
		});

		return probes;
	}

	public Probe getProbeBy(Integer id) throws ProbeNotFoundException {
		return probeRepository.getProbeBy(id)
				.orElseThrow(() -> new ProbeNotFoundException(String.format("Probe %d not found", id)));

	}

	public void deleteProbeBy(Integer id) {
		probeRepository.deleteProbe(id);
	}

	public Probe explorerWith(Integer probeId, List<Action> actions) throws ProbeNotFoundException {
		Probe probe = getProbeBy(probeId);

		explorer(probe, actions);

		return probe;
	}

	private void explorer(Probe probe, List<Action> actions) {
		try {
			probe.execute(actions);
		} catch (PlateauLimitException e) {
			probe.setError(e.getMessage());
		}
	}

	private void prepareProbes(Plateau plateau, List<Probe> probes) {
		probes.forEach(probe -> {
			probe.setPlateau(plateau);
			probeRepository.addProbe(probe);
		});
	}
}
