import { combineReducers } from 'redux';
import loginReducer from './loginReducer';
import vegeTypeReducer from './vegeTypeReducer';
import cartReducer from './cartReducer';
import productDataReducer from './productDataReducer';

const rootReducer = combineReducers({
  loginReducer,
  vegeTypeReducer,
  cartReducer,
  productDataReducer,
});

export default rootReducer;
