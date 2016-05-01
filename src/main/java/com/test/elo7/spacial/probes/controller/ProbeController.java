package com.test.elo7.spacial.probes.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.elo7.spacial.probes.exception.ProbeInvalidePositionException;
import com.test.elo7.spacial.probes.exception.ProbeNotFoundException;
import com.test.elo7.spacial.probes.model.Action;
import com.test.elo7.spacial.probes.model.Mission;
import com.test.elo7.spacial.probes.model.Probe;
import com.test.elo7.spacial.probes.model.Result;
import com.test.elo7.spacial.probes.service.ProbeService;

@RestController
@RequestMapping(path = "/probe", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProbeController {

	@Autowired
	private ProbeService probeService;

	@RequestMapping(path = "/mission", method = RequestMethod.POST)
	public Result startMission(@Valid @RequestBody Mission mission, BindingResult result) {

		List<String> errors = result.getFieldErrors().stream().map(e -> e.getDefaultMessage())
				.collect(Collectors.toList());

		if (errors.isEmpty()) {
			try {
				List<Probe> probes = probeService.explorer(mission);
				return new Result(probes);
			} catch (ProbeInvalidePositionException e) {
				return new Result(Arrays.asList(e.getMessage()));
			}

		} else {
			return new Result(errors);
		}

	}

	@RequestMapping(path = "/{probeId}", method = RequestMethod.PUT)
	public Probe explorer(@PathVariable("probeId") Integer probeId, @RequestBody List<Action> actions)
			throws ProbeNotFoundException {
		Probe probe = probeService.explorerWith(probeId, actions);
		return probe;
	}

	@RequestMapping(path = "/{probeId}", method = RequestMethod.GET)
	public Probe get(@PathVariable("probeId") Integer probeId) throws ProbeNotFoundException {
		return probeService.getProbeBy(probeId);
	}

	@RequestMapping(path = "/{probeId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("probeId") Integer probeId) {
		probeService.deleteProbeBy(probeId);
	}

}
