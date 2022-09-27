import { combineReducers } from 'redux';
import loginReducer from './loginReducer';
import vegetTypeReducer from './vegeTypeReducer';

const rootReducer = combineReducers({
  loginReducer,
  vegetTypeReducer,
});

export default rootReducer;
