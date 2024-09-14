package com.carter.phargate.data.repo;

import com.carter.phargate.data.entity.MedicineStockEntity;
import com.carter.phargate.data.entity.id.MedicinePharmacyChainId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineStockRepository extends JpaRepository<MedicineStockEntity, MedicinePharmacyChainId> {
}
