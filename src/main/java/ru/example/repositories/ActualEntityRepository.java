package ru.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.example.entities.ActualEntity;

public interface ActualEntityRepository extends JpaRepository<ActualEntity, Long> {
}
