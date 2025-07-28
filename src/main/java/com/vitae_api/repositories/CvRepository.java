package com.vitae_api.repositories;

import com.vitae_api.models.Cv;
import com.vitae_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CvRepository extends JpaRepository<Cv, UUID> {

    @Query("""
    SELECT c FROM Cv c
    WHERE c.grade = (
        SELECT MAX(c2.grade) FROM Cv c2
    )
""")
    List<Cv> findCvWithHighestGrade();

    List<Cv> findByUser(User user);

}
