import FoodCatrgoryItems from './FoodCatrgoryItems';
import FoodCategorySort from './FoodCategorySort';

const FoodCategory = ({
  Foodarr,
  AssortArr,
  searchParams,
  setSearchParams,
  assortArr,
  setAssortArr,
  changeAssortArr,
}) => {
  return (
    <>
      <FoodCatrgoryItems Foodarr={Foodarr} />
      <FoodCategorySort
        AssortArr={AssortArr}
        searchParams={searchParams}
        setSearchParams={setSearchParams}
        assortArr={assortArr}
        setAssortArr={setAssortArr}
        changeAssortArr={changeAssortArr}
      />
    </>
  );
};

export default FoodCategory;
