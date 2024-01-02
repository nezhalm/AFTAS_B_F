package com.quiz.api.services;
import com.quiz.api.DTOs.LevelDTO.LevelDTO;
import com.quiz.api.DTOs.SubjectDTO.SubjectDTO;
import com.quiz.api.DTOs.SubjectDTO.SubjectResponseDTO;
import com.quiz.api.models.Subject;
import org.modelmapper.ModelMapper;
import com.quiz.api.DTOs.LevelDTO.LevelResponseDTO;
import com.quiz.api.models.Level;
import com.quiz.api.repositories.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LevelService {
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LevelService(LevelRepository repository, ModelMapper mapper) {
        levelRepository = repository;
        modelMapper = mapper;
    }

    public List<LevelResponseDTO> getAll() {
        return Arrays.asList(modelMapper.map(levelRepository.findAll(), LevelResponseDTO[].class));
    }


    public LevelResponseDTO save(LevelDTO levelDTO) {
        Level level = modelMapper.map(levelDTO, Level.class);
        return modelMapper.map(levelRepository.save(level), LevelResponseDTO.class);
    }


    public void delete(Integer levelId) {
        Optional<Level> optionalLevel = levelRepository.findById(levelId);
        if (optionalLevel.isPresent()) {
            levelRepository.deleteById(levelId);
        } else {
            throw new IllegalArgumentException("Le niveau avec l'ID " + levelId + " n'a pas été trouvé.");
        }
    }

    public LevelResponseDTO update(LevelDTO levelDTO) {
        Level level = modelMapper.map(levelDTO,Level.class);
        levelRepository.save(level);
        return modelMapper.map(levelRepository.save(level),LevelResponseDTO.class);
    }

    public LevelResponseDTO getLevelByID(Integer id) {
        Level level = modelMapper.map(levelRepository.findById(id).get(), Level.class);
        return modelMapper.map(level, LevelResponseDTO.class);
    }

    public boolean existsById(Integer id){
        return levelRepository.existsById(id);
    }
}