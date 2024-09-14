package com.carter.phargate.data.source;

import com.carter.phargate.data.entity.MedicineStockEntity;
import com.carter.phargate.data.repo.MedicineStockRepository;
import com.carter.phargate.model.MedicineStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MedicineStockDataSource {

    private final MedicineStockRepository medicineStockRepository;
    private final Clock clock;

    public void saveAll(List<MedicineStock> medicineStockList) {
        Instant now = clock.instant();

        List<MedicineStockEntity> medicineStockEntities = medicineStockList.stream()
                .filter(ms -> ms.pharmacyId() != null && ms.medicineId() != null)
                .map(ms -> MedicineStockEntity.fromMedicineStock(ms, now))
                .toList();
        medicineStockRepository.saveAll(medicineStockEntities);
    }

}
