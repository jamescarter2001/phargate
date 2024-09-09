import { useState, useEffect } from "react";
import Search from "./components/Search";
import PharmacyList from "./components/PharmacyList";
import Filters from "./components/Filters";
import { usePharmacyData } from "./services/PharmacyService.ts";

const App = () => {
  const [postcode, setPostcode] = useState("");
  const [medication, setMedication] = useState("");
  const [filteredPharmacies, setFilteredPharmacies] = useState<any[]>([]);
  const [sortType, setSortType] = useState<"distance" | "stock" | null>(null); // Added a simple state for sorting

  const { data: pharmacies, loading } = usePharmacyData(postcode, medication);

  const handleSearch = (postcode: string, medication: string) => {
    setPostcode(postcode);
    setMedication(medication);
  };

  useEffect(() => {
    if (sortType === "distance") {
      setFilteredPharmacies(
        [...pharmacies].sort((a, b) => a.distance - b.distance),
      );
    } else if (sortType === "stock") {
      setFilteredPharmacies(
        [...pharmacies].sort((a, b) =>
          a.stockStatus.localeCompare(b.stockStatus),
        ),
      );
    } else {
      setFilteredPharmacies(pharmacies); // Default, no sorting
    }
  }, [pharmacies, sortType]);

  const handleFilterChange = (filterType: string) => {
    if (filterType === "distance") {
      setSortType("distance");
    } else if (filterType === "stock") {
      setSortType("stock");
    }
  };

  return (
    <div className="container mx-auto p-6">
      <div className="text-center">
        <h1 className="text-3xl font-bold mb-4">Pharmacy Search</h1>
        <Search onSearch={handleSearch} />
        <Filters onFilterChange={handleFilterChange} />
      </div>
      {loading && <p className="text-center mt-4">Loading...</p>}
      {!loading && filteredPharmacies && (
        <PharmacyList pharmacies={filteredPharmacies} />
      )}
    </div>
  );
};

export default App;
