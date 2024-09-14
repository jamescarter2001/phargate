package com.carter.phargate.data.repo;

import com.carter.phargate.data.entity.PharmacyEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PharmacyRepository extends JpaRepository<PharmacyEntity, Long> {
}
