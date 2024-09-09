package com.carter.phargate.data.source;

import com.carter.phargate.data.entity.PharmacyEntity;
import com.carter.phargate.data.repo.PharmacyRepository;
import com.carter.phargate.pharmacy.model.Pharmacy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PharmacyDataSource {

    private final PharmacyRepository pharmacyRepository;

    public List<Pharmacy> getPharmacies(final int first) {
        return pharmacyRepository.findAll(PageRequest.of(0, first))
                .map(PharmacyEntity::asPharmacy)
                .toList();
    }

}
