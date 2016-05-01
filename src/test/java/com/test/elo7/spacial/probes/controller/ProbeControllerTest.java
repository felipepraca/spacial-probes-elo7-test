package com.test.elo7.spacial.probes.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.test.elo7.spacial.probes.exception.ProbeInvalidePositionException;
import com.test.elo7.spacial.probes.model.Coordinate;
import com.test.elo7.spacial.probes.model.Direction;
import com.test.elo7.spacial.probes.model.Mission;
import com.test.elo7.spacial.probes.model.Probe;
import com.test.elo7.spacial.probes.service.ProbeService;

@RunWith(MockitoJUnitRunner.class)
public class ProbeControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private ProbeController controller;

	@Mock
	private ProbeService probeService;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void validServiceProbeMission() throws Exception {
		// GIVEN
		Probe probe1 = new Probe(1, new Coordinate(1, 3), Direction.N);
		Probe probe2 = new Probe(2, new Coordinate(5, 1), Direction.E);

		List<Probe> probes = Arrays.asList(probe1, probe2);

		when(probeService.explorer(any(Mission.class))).thenReturn(probes);

		// WHEN
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/probe/mission")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(getRequesBody()));

		// THEN
		resultActions.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$.result[0].id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result[0].coordinate.x").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result[0].coordinate.y").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result[0].direction").value("N"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result[1].id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result[1].coordinate.x").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result[1].coordinate.y").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result[1].direction").value("E"));

		verify(probeService, times(1)).explorer(any(Mission.class));
	}

	@Test
	public void invalidProbesSamePosition() throws Exception {
		// GIVEN
		when(probeService.explorer(any(Mission.class))).thenThrow(new ProbeInvalidePositionException("Position error"));

		// WHEN
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/probe/mission")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(getRequesBody()));

		// THEN
		resultActions.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value("Position error"))
				.andDo(MockMvcResultHandlers.print());

		verify(probeService, times(1)).explorer(any(Mission.class));
	}

	@Test
	public void invalidServiceProbeMission() throws Exception {
		// GIVEN

		// WHEN
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/probe/mission")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("{}"));

		// THEN
		resultActions.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value("Plateau is required!"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors[1]").value("Probes is required!"))
				.andDo(MockMvcResultHandlers.print());

		verify(probeService, never()).explorer(any(Mission.class));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void validateServiceProbeExplorer() throws Exception {
		// GIVEN
		Probe probe = new Probe(1, new Coordinate(2, 4), Direction.W);
		probe.setError("Erro teste");

		when(probeService.explorerWith(anyInt(), anyList())).thenReturn(probe);

		// WHEN
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/probe/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("[]"));

		// THEN
		resultActions.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.coordinate.x").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.coordinate.y").value(4))
				.andExpect(MockMvcResultMatchers.jsonPath("$.direction").value("W"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Erro teste"));

		verify(probeService, times(1)).explorerWith(anyInt(), anyList());
	}

	@Test
	public void validateServiceProbeGet() throws Exception {
		// GIVEN
		Probe probe = new Probe(1, new Coordinate(6, 3), Direction.W);

		when(probeService.getProbeBy(1)).thenReturn(probe);

		// WHEN
		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.get("/probe/1").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

		// THEN
		resultActions.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.coordinate.x").value(6))
				.andExpect(MockMvcResultMatchers.jsonPath("$.coordinate.y").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.direction").value("W"));

		verify(probeService, times(1)).getProbeBy(anyInt());
	}

	@Test
	public void validateServiceProbeDelete() throws Exception {
		// GIVEN

		// WHEN
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/probe/1"));

		// THEN
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());

		verify(probeService, times(1)).deleteProbeBy(anyInt());
	}

	private String getRequesBody() {
		return "{" +
				"	\"plateau\" : {" +
				"		\"area\" : {" +
				"			\"x\" : 5," +
				"			\"y\" : 5" +
				"		}" +
				"	}," +
				"	\"probes\" : [" +
				"		{" +
				"			\"id\" : 1," +
				"			\"direction\" : \"N\"," +
				"			\"coordinate\" : {" +
				"				\"x\" : 1," +
				"				\"y\" : 2" +
				"			}" +
				"		}," +
				"		{" +
				"			\"id\" : 2," +
				"			\"direction\" : \"E\"," +
				"			\"coordinate\" : {" +
				"				\"x\" : 3," +
				"				\"y\" : 3" +
				"			}" +
				"		}" +
				"	]," +
				"	\"actions\" : {" +
				"		\"1\" : [ \"L\", \"M\" ,\"L\", \"M\", \"L\", \"M\", \"L\", \"M\", \"M\" ]," +
				"		\"2\" : [ \"M\", \"M\", \"R\", \"M\", \"M\", \"R\", \"M\", \"R\", \"R\", \"M\" ]" +
				"	}" +
				"}";
	}

}
