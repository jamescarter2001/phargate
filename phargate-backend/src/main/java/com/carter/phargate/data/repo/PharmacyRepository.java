package com.carter.phargate.data.repo;

import com.carter.phargate.data.entity.PharmacyEntity;
import com.carter.phargate.model.PharmacyChainId;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PharmacyRepository extends JpaRepository<PharmacyEntity, Long> {

    List<PharmacyEntity> findAllByPharmacyChainId(PharmacyChainId pharmacyChainId);
    Page<PharmacyEntity> findAllByTownAndCountyIsNotNull(String town, Pageable pageable);

}
