package com.example.aftas.services;
import org.modelmapper.ModelMapper;

import com.example.aftas.DTOs.level.levelDTO;
import com.example.aftas.models.Level;
import com.example.aftas.repositories.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LevelService {
    private  final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    public LevelService(LevelRepository levelRepository, ModelMapper modelMapper) {
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }


    public levelDTO save(levelDTO bean) throws RuntimeException {
        Level level = modelMapper.map(bean, Level.class);
        Optional<Level> lastInsertedLevelOptional = levelRepository.findLevelByIdDesc();
        if (lastInsertedLevelOptional.isPresent()) {
            Level lastInsertedLevel = lastInsertedLevelOptional.get();
            if (lastInsertedLevel.getPoints() >= level.getPoints())
                throw new RuntimeException("The points of the level you are trying to insert should be higher");
        }
        return modelMapper.map(levelRepository.save(level), levelDTO.class);
    }
}
