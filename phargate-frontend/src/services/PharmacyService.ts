import { useEffect, useState } from "react";

const fakePharmacyData = [
  {
    name: "Unit 12 St Marks Shopping Centre",
    address: "Lincoln",
    distance: 53,
    stockStatus: "In stock",
  },
  {
    name: "48-58 Prospect Ctr",
    address: "Hull",
    distance: 570,
    stockStatus: "In stock",
  },
  {
    name: "104-106 Rushey Green",
    address: "London",
    distance: 824,
    stockStatus: "Out of stock",
  },
  {
    name: "119-120 LONDON ROAD",
    address: "Brighton",
    distance: 969,
    stockStatus: "In stock",
  },
];

export const usePharmacyData = (postcode: string, medication: string) => {
  const [data, setData] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    setTimeout(() => {
      setData(fakePharmacyData);
      setLoading(false);
    }, 1000);
  }, [postcode, medication]);

  return { data, loading }; // + error
};

// const { loading, error, data, refetch } = useQuery(
//     PHARMACY_QUERY,
//     {
//         variables: { postcode, medication },
//         skip: !postcode || !medication,
//     },
// );
