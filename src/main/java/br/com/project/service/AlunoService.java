package br.com.project.service;

import java.util.List;

import javax.validation.Valid;

import br.com.project.service.dto.AlunoDTO;

public interface AlunoService {
	
	public void save(@Valid AlunoDTO dto);
	
	public void update(long id, @Valid AlunoDTO dto);
	
	public List<AlunoDTO> get();
	
	public AlunoDTO get(long id);

	public void delete(long id);
}
