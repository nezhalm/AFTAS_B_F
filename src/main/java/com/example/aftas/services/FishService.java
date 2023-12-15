package com.example.aftas.services;

import com.example.aftas.DTOs.fish.fishDTO;
import com.example.aftas.DTOs.hunting.huntingResponseDTO;
import com.example.aftas.models.Fish;
import com.example.aftas.repositories.FishRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FishService {
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;

    public FishService(FishRepository fishRepository, ModelMapper modelMapper) {
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
    }
    public List<fishDTO> getAll() {
        return Arrays.asList(modelMapper.map(fishRepository.findAll(), fishDTO[].class));
    }
}
