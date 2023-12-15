package com.example.aftas.repositories;
import com.example.aftas.models.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Integer> {
    @Query("SELECT l FROM Level l ORDER BY l.code DESC LIMIT 1")
    Optional<Level> findLevelByIdDesc();
}
