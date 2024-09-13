package com.carter.phargate.data.source;

import com.carter.phargate.data.entity.PharmacyEntity;
import com.carter.phargate.data.repo.PharmacyRepository;
import com.carter.phargate.model.Pharmacy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PharmacyDataSource {

    private final PharmacyRepository pharmacyRepository;

    public List<Pharmacy> getPharmacies(final int first) {
        return toPharmacies(
                pharmacyRepository.findAll(PageRequest.of(0, first)).getContent()
        );
    }

    public List<Pharmacy> getPharmacies() {
        return toPharmacies(pharmacyRepository.findAll());
    }

    public void saveAll(List<Pharmacy> pharmacies) {
        List<PharmacyEntity> entities = pharmacies.stream()
                .map(PharmacyEntity::fromPharmacy)
                .toList();
        pharmacyRepository.saveAll(entities);
    }

    private static List<Pharmacy> toPharmacies(List<PharmacyEntity> entities) {
        return entities.stream()
                .map(PharmacyEntity::asPharmacy)
                .toList();
    }

}
