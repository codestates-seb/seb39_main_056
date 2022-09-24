import FoodCatrgoryItems from './FoodCatrgoryItems';
import FoodCategoryBtn from './FoodCategoryBtn';

const FoodCategory = ({ Foodarr, isClick, setIsClick }) => {
	return (
		<>
			<FoodCatrgoryItems
				Foodarr={Foodarr}
				isClick={isClick}
				setIsClick={setIsClick}
			/>
			<FoodCategoryBtn />
		</>
	);
};

export default FoodCategory;
