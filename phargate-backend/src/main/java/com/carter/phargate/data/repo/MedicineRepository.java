package com.carter.phargate.data.repo;

import com.carter.phargate.data.entity.MedicineEntity;
import com.carter.phargate.model.PharmacyChainId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Long> {

    @Query("select e from MedicineEntity e left join MedicineSourceIdEntity mside on e.medicineId = mside.medicineId where mside.pharmacyChainId = :pharmacyChainId and mside.sourceId = :sourceId")
    Optional<MedicineEntity> findBySourceIdAndPharmacyChainId(String sourceId, PharmacyChainId pharmacyChainId);

}
