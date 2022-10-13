import { SET_PRODUCT_LIST_DATA } from '../actions/index';

const productDataReducer = (state = [], action) => {
  switch (action.type) {
    case SET_PRODUCT_LIST_DATA:
      return action.payload;
    default:
      return state;
  }
};

export default productDataReducer;
