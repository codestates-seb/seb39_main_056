import FoodCatrgoryItems from './FoodCatrgoryItems';
import FoodCategorySort from './FoodCategorySort';

const FoodCategory = ({
  Foodarr,
  AssortArr,
  searchParams,
  setSearchParams,
}) => {
  return (
    <>
      <FoodCatrgoryItems Foodarr={Foodarr} />
      <FoodCategorySort
        AssortArr={AssortArr}
        searchParams={searchParams}
        setSearchParams={setSearchParams}
      />
    </>
  );
};

export default FoodCategory;
