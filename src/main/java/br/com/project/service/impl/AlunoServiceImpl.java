package br.com.project.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.handler.exception.AlunoException;
import br.com.project.handler.exception.ExceptionCodeEnum;
import br.com.project.repository.AlunoRepository;
import br.com.project.repository.data.Aluno;
import br.com.project.service.AlunoService;
import br.com.project.service.dto.AlunoDTO;

@Service
public class AlunoServiceImpl implements AlunoService {

	private AlunoRepository alunoRepository;

	private ModelMapper modelMapper;

	@Autowired
	public AlunoServiceImpl(AlunoRepository alunoRepository, ModelMapper modelMapper) {
		super();
		this.alunoRepository = alunoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void save(AlunoDTO dto) {
		final Aluno aluno = modelMapper.map(dto, Aluno.class);
		alunoRepository.save(aluno);
	}

	@Override
	public void update(long id, AlunoDTO dto) {
		Optional<Aluno> opt = alunoRepository.findById(id);
		if (opt.isPresent()) {
			Aluno aluno = opt.get();
			aluno.setIdade(dto.getIdade());
			aluno.setNome(dto.getNome());
			alunoRepository.save(aluno);
		} else {
			throw new AlunoException(ExceptionCodeEnum.ALUNO_INEXISTENTE);
		}
	}

	@Override
	public List<AlunoDTO> get() {
		return alunoRepository.findAll().stream().map(user -> modelMapper.map(user, AlunoDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public AlunoDTO get(long id) {
		Optional<Aluno> opt = alunoRepository.findById(id);
		if (opt.isPresent()) {
			return modelMapper.map(opt.get(), AlunoDTO.class);
		} else {
			throw new AlunoException(ExceptionCodeEnum.ALUNO_INEXISTENTE);
		}
	}

	@Override
	public void delete(long id) {
		AlunoDTO aluno = get(id);
		if (aluno != null) {
			alunoRepository.deleteById(id);
		}
	}
}
