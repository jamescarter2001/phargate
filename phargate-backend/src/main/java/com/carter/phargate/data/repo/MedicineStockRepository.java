package com.carter.phargate.data.repo;

import com.carter.phargate.data.entity.MedicineStockEntity;
import com.carter.phargate.data.entity.id.MedicineSourceIdEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineStockRepository extends JpaRepository<MedicineStockEntity, MedicineSourceIdEntityId> {
}
