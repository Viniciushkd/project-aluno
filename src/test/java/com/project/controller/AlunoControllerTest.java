package com.project.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.project.Application;
import br.com.project.repository.AlunoRepository;
import br.com.project.repository.data.Aluno;
import br.com.project.service.dto.AlunoDTO;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class AlunoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private AlunoRepository repository;


	@Test
	public void saveTest() throws Exception {
		BDDMockito.given(repository.save(entityAluno())).willReturn(new Aluno());

		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.post("/aluno").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dtoAluno())))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
		System.out.println(result);
	}

	@Test
	public void saveTestConflict() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.post("/aluno").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dtoAlunoConclict())))
				.andExpect(MockMvcResultMatchers.status().isConflict()).andReturn();
		System.out.println(result);
	}

	@Test
	public void updateTest() throws Exception {
		Optional<Aluno> aluno = Optional.of(new Aluno());

		BDDMockito.given(repository.findById(ArgumentMatchers.anyLong())).willReturn(aluno);

		BDDMockito.given(repository.save(entityAluno())).willReturn(new Aluno());

		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.put("/aluno/{id}", 1).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dtoAluno())))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		System.out.println(result);
	}
	
	@Test
	public void updateTestConflict() throws Exception {
		BDDMockito.given(repository.save(entityAluno())).willReturn(new Aluno());

		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.put("/aluno/{id}", 1).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dtoAluno())))
				.andExpect(MockMvcResultMatchers.status().isConflict()).andReturn();
		System.out.println(result);
	}

	@Test
	public void getListTest() throws Exception {
		BDDMockito.given(repository.findAll()).willReturn(new ArrayList<Aluno>());

		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/aluno").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		System.out.println(result);
	}

	@Test
	public void getTest() throws Exception {
		Optional<Aluno> aluno = Optional.of(new Aluno());

		BDDMockito.given(repository.findById(ArgumentMatchers.anyLong())).willReturn(aluno);

		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/aluno/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		System.out.println(result);
	}

	@Test
	public void getTestConflict() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/aluno/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isConflict()).andReturn();
		System.out.println(result);
	}

	@Test
	public void deleteTest() throws Exception {
		Optional<Aluno> aluno = Optional.of(new Aluno());

		BDDMockito.given(repository.findById(ArgumentMatchers.anyLong())).willReturn(aluno);

		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/aluno/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		System.out.println(result);
	}
	
	@Test
	public void deleteTestConflict() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/aluno/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isConflict()).andReturn();
		System.out.println(result);
	}

	private Aluno entityAluno() {
		return new Aluno("teste", 1);
	}

	private AlunoDTO dtoAluno() {
		AlunoDTO dto = new AlunoDTO();
		dto.setIdade(1);
		dto.setNome("teste");
		return dto;
	}

	private AlunoDTO dtoAlunoConclict() {
		AlunoDTO dto = new AlunoDTO();
		dto.setIdade(0);
		dto.setNome("");
		return dto;
	}
}
