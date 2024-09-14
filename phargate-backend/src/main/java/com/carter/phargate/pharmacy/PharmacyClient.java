package com.carter.phargate.pharmacy;

import com.carter.phargate.model.MedicineId;
import com.carter.phargate.model.MedicineStock;
import com.carter.phargate.model.Pharmacy;
import com.carter.phargate.model.PharmacyId;

import java.util.List;
import java.util.Map;

public interface PharmacyClient {

    List<Pharmacy> getPharmacies();

    Map<MedicineId, List<MedicineStock>> getStockByPharmacyIdsAndMedicineIds(List<Long> pharmacyIds, List<Long> medicineIds);

}
