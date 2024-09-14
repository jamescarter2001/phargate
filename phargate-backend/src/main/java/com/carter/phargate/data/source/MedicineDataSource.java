package com.carter.phargate.data.source;

import com.carter.phargate.data.entity.MedicineEntity;
import com.carter.phargate.data.entity.MedicineSourceIdEntity;
import com.carter.phargate.data.repo.MedicineRepository;
import com.carter.phargate.data.repo.MedicineSourceIdRepository;
import com.carter.phargate.model.Medicine;
import com.carter.phargate.model.MedicineId;
import com.carter.phargate.model.MedicineSourceId;
import com.carter.phargate.model.PharmacyChainId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MedicineDataSource {

    private final MedicineRepository medicineRepository;
    private final MedicineSourceIdRepository medicineSourceIdRepository;

    public Optional<Medicine> getByMedicineId(MedicineId medicineId) {
        return medicineRepository.findById(medicineId.id())
                .map(MedicineEntity::toMedicine);
    }

    public List<MedicineSourceId> getSourceIdsByPharmacyChainId(PharmacyChainId pharmacyChainId) {
        return medicineSourceIdRepository.findAllByPharmacyChainId(pharmacyChainId).stream()
                .map(MedicineSourceIdEntity::toMedicineSourceId)
                .toList();
    }

    public Optional<Medicine> getBySourceIdAndPharmacyChainId(String sourceId, PharmacyChainId pharmacyChainId) {
        return medicineRepository.findBySourceIdAndPharmacyChainId(
                sourceId,
                pharmacyChainId
                ).map(MedicineEntity::toMedicine);
    }

}
