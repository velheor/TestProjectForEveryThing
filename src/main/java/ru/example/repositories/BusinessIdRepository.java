package ru.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.example.entities.BusinessId;

public interface BusinessIdRepository extends JpaRepository<BusinessId, String> {

    @Modifying
    @Query("UPDATE BusinessId b SET b.lastId = ?2 WHERE b.id = ?1")
    void updateLastIdWhereId(String name, Long lastId);

    BusinessId findById(String name);
}
