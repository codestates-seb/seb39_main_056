import { SET_LOGIN_CHANGE } from '../actions';

const loginReducer = (state = false, action) => {
  switch (action.type) {
    case SET_LOGIN_CHANGE:
      return action.payload;
    default:
      return state;
  }
};

export default loginReducer;
