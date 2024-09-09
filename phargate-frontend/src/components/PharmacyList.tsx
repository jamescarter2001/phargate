interface Pharmacy {
  name: string;
  address: string;
  distance: number;
  stockStatus: string;
}

interface PharmacyListProps {
  pharmacies: Pharmacy[];
}

const PharmacyList = ({ pharmacies }: PharmacyListProps) => {
  return (
    <div className="mt-6 grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
      {pharmacies.length === 0 ? (
        <p>No pharmacies found</p>
      ) : (
        pharmacies.map((pharmacy, index) => (
          <div key={index} className="card w-full bg-base-100 shadow-xl">
            <div className="card-body">
              <h2 className="card-title">{pharmacy.name}</h2>
              <p>{pharmacy.address}</p>
              <p>Distance: {pharmacy.distance} km</p>
              <p>Status: {pharmacy.stockStatus}</p>
            </div>
          </div>
        ))
      )}
    </div>
  );
};

export default PharmacyList;
