import FoodCatrgoryItems from './FoodCatrgoryItems';
import FoodCategorySort from './FoodCategorySort';

const FoodCategory = ({ Foodarr, AssortArr }) => {
	return (
		<>
			<FoodCatrgoryItems Foodarr={Foodarr} />
			<FoodCategorySort AssortArr={AssortArr} />
		</>
	);
};

export default FoodCategory;
