package com.carter.phargate.data.source;

import com.carter.phargate.data.entity.PharmacyEntity;
import com.carter.phargate.data.repo.PharmacyRepository;
import com.carter.phargate.model.Pharmacy;
import com.carter.phargate.model.PharmacyChainId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public List<Pharmacy> getByPharmacyChainId(final PharmacyChainId pharmacyChainId) {
        return pharmacyRepository.findAllByPharmacyChainId(pharmacyChainId)
                .stream()
                .map(PharmacyEntity::asPharmacy)
                .toList();
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
