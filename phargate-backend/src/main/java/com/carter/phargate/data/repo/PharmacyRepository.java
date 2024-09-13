package com.carter.phargate.data.repo;

import com.carter.phargate.data.entity.PharmacyEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<PharmacyEntity, Long> {

    @Transactional
    @NonNull
    @Override
    List<PharmacyEntity> findAll();

    @Transactional
    @NonNull
    @Override
    Page<PharmacyEntity> findAll(@NonNull Pageable pageable);

}
