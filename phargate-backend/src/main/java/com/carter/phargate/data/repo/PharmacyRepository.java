package com.carter.phargate.data.repo;

import com.carter.phargate.data.entity.PharmacyEntity;
import com.carter.phargate.data.entity.id.PharmacyPharmacyChainId;
import com.carter.phargate.model.PharmacyChainId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PharmacyRepository extends JpaRepository<PharmacyEntity, PharmacyPharmacyChainId> {

    List<PharmacyEntity> findAllByPharmacyChainId(PharmacyChainId pharmacyChainId);

}
