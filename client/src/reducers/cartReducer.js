import { INCREASE, DECREASE, SET_NUMBER } from '../actions';

const cartReducer = (state = 0, action) => {
  switch (action.type) {
    case 'INCREASE':
      return state + 1;

    case 'DECREASE':
      return state - 1;

    case 'SET_NUMBER':
      return action.payload;

    default:
      return state;
  }
};

export default cartReducer;
