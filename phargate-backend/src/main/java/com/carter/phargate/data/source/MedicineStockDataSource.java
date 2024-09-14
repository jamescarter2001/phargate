package com.carter.phargate.data.source;

import com.carter.phargate.data.entity.MedicineStockEntity;
import com.carter.phargate.data.repo.MedicineStockRepository;
import com.carter.phargate.model.MedicineStock;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MedicineStockDataSource {

    private final MedicineStockRepository medicineStockRepository;

    public void saveAll(List<MedicineStock> medicineStockList) {
        List<MedicineStockEntity> medicineStockEntities = medicineStockList.stream()
                .map(MedicineStockEntity::fromMedicineStock)
                .toList();
        medicineStockRepository.saveAll(medicineStockEntities);
    }

}
