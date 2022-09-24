import FoodCatrgoryItems from './FoodCatrgoryItems';

const FoodCategory = ({ Foodarr, isClick, setIsClick }) => {
	return (
		<>
			<FoodCatrgoryItems
				Foodarr={Foodarr}
				isClick={isClick}
				setIsClick={setIsClick}
			/>
		</>
	);
};

export default FoodCategory;
