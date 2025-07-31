package com.vitae_api.repositories;

import com.vitae_api.models.Cv;
import com.vitae_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CvRepository extends JpaRepository<Cv, UUID> {

    @Query(value = """
    SELECT DISTINCT ON (c.user_id) *
    FROM Cv c
    ORDER BY c.user_id, c.grade DESC
""", nativeQuery = true)
    List<Cv> findCvWithHighestGrade();

    List<Cv> findByUser(User user);

}
