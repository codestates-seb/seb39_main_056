import { combineReducers } from 'redux';
import loginReducer from './loginReducer';
import vegeTypeReducer from './vegeTypeReducer';

const rootReducer = combineReducers({
  loginReducer,
  vegeTypeReducer,
});

export default rootReducer;
