package com.carter.phargate.data.repo;

import com.carter.phargate.data.entity.MedicineSourceIdEntity;
import com.carter.phargate.model.PharmacyChainId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineSourceIdRepository extends JpaRepository<MedicineSourceIdEntity, Long> {

    List<MedicineSourceIdEntity> findAllByPharmacyChainId(PharmacyChainId pharmacyChainId);

    List<MedicineSourceIdEntity> findAllByMedicineIdInAndPharmacyChainId(List<Long> medicineIds, PharmacyChainId pharmacyChainId);

    Optional<MedicineSourceIdEntity> findByMedicineIdAndPharmacyChainId(long medicineId, PharmacyChainId pharmacyChainId);

}
