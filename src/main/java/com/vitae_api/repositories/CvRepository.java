package com.vitae_api.repositories;

import com.vitae_api.models.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CvRepository extends JpaRepository<Cv, UUID> {
}
