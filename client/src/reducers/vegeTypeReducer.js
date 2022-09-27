import { SET_VEGE_TYPE } from '../actions';

const vegeTypeReducer = (state = '플렉시테리언', action) => {
  switch (action.type) {
    case SET_VEGE_TYPE:
      return action.payload;
    default:
      return state;
  }
};

export default vegeTypeReducer;
