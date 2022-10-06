import { combineReducers } from 'redux';
import loginReducer from './loginReducer';
import vegeTypeReducer from './vegeTypeReducer';
import cartReducer from './cartReducer';

const rootReducer = combineReducers({
  loginReducer,
  vegeTypeReducer,
  cartReducer,
});

export default rootReducer;
