package com.quiz.api.services;

import com.quiz.api.DTOs.LevelDTO.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LevelServiceTest {

    @Mock
    static LevelService levelService;
    static LevelDTO levelDTO;
    static LevelResponseDTO levelResponseDTO;

    @BeforeAll
    public static void init() {

        levelDTO = new LevelDTO();
        levelDTO.setId(1);
        levelDTO.setMaxPoints(5);
        levelDTO.setMinPoints(2);
        levelDTO.setDescription("test");
        levelResponseDTO = new LevelResponseDTO();
        levelResponseDTO.setId(levelDTO.getId());
        levelResponseDTO.setMaxPoints(levelDTO.getMaxPoints());
        levelResponseDTO.setMinPoints(levelDTO.getMinPoints());
        levelResponseDTO.setDescription(levelDTO.getDescription());
        levelResponseDTO.setQuestions(new ArrayList<>());

    }

    @Test
    public void testSave() {
        when(levelService.save(levelDTO)).thenReturn(levelResponseDTO);
        LevelResponseDTO returnedLevel = levelService.save(levelDTO);
        assertEquals(returnedLevel, levelResponseDTO);
    }


    @Test
    public void getLevelByID() {
        when(levelService.getLevelByID(1)).thenReturn(levelResponseDTO);
        LevelResponseDTO returnedLevel = levelService.getLevelByID(1);
        assertEquals(returnedLevel, levelResponseDTO);
    }

    @Test
    public void testUpdate() {
        when(levelService.update(levelDTO)).thenReturn(levelResponseDTO);
        LevelResponseDTO returnedLevel = levelService.update(levelDTO);
        assertEquals(returnedLevel, levelResponseDTO);
    }


    @Test
    public void testGetAll() {

        List<LevelResponseDTO> levels = new ArrayList<>();
        levels.add(levelResponseDTO);
        when(levelService.getAll()).thenReturn(levels);
        List<LevelResponseDTO> returnedLevels = levelService.getAll();
        assertEquals(returnedLevels, levels);
    }


    @Test
    public void destroy() {
    }



}
