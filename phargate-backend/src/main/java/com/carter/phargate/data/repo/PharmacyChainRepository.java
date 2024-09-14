package com.carter.phargate.data.repo;

import com.carter.phargate.data.entity.PharmacyChainEntity;
import com.carter.phargate.model.PharmacyChainId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyChainRepository extends JpaRepository<PharmacyChainEntity, PharmacyChainId> {
}
