interface FiltersProps {
  onFilterChange: (filterType: string) => void;
}

const Filters = ({ onFilterChange }: FiltersProps) => {
  return (
    <div className="btn-group flex justify-center space-x-4 mt-4">
      <button
        onClick={() => onFilterChange("distance")}
        className="btn btn-secondary"
      >
        Sort by Distance
      </button>
      <button
        onClick={() => onFilterChange("stock")}
        className="btn btn-secondary"
      >
        Sort by Stock
      </button>
    </div>
  );
};

export default Filters;
