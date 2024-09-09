import React, { useState } from "react";

interface SearchProps {
  onSearch: (postcode: string, medication: string) => void;
}

const Search: React.FC<SearchProps> = ({ onSearch }) => {
  const [postcode, setPostcode] = useState("");
  const [medication, setMedication] = useState("");

  const handleSearch = () => {
    if (postcode && medication) {
      onSearch(postcode, medication);
    }
  };

  return (
    <div className="flex flex-col items-center space-y-4">
      <input
        type="text"
        placeholder="Enter postcode"
        value={postcode}
        onChange={(e) => setPostcode(e.target.value)}
        className="input input-bordered w-full max-w-xs"
      />
      <input
        type="text"
        placeholder="Enter medication"
        value={medication}
        onChange={(e) => setMedication(e.target.value)}
        className="input input-bordered w-full max-w-xs"
      />
      <button
        onClick={handleSearch}
        className="btn btn-primary w-full max-w-xs"
      >
        Search
      </button>
    </div>
  );
};

export default Search;
