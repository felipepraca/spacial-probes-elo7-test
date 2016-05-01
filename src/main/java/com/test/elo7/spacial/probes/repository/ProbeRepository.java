package com.test.elo7.spacial.probes.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.test.elo7.spacial.probes.model.Probe;

@Repository
public class ProbeRepository {

	private Map<Integer, Probe> probes;

	public ProbeRepository() {
		probes = new HashMap<>();
	}

	public Map<Integer, Probe> getProbes() {
		return probes;
	}

	public void addProbe(Probe probe) {
		this.probes.put(probe.getId(), probe);
	}

	public Optional<Probe> getProbeBy(int id) {
		return Optional.ofNullable(this.probes.get(id));
	}

	public void deleteProbe(Integer id) {
		probes.remove(id);
	}

}
