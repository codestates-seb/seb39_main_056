export const SET_LOGIN_CHANGE = 'SET_LOGIN_CHANGE';
export const SET_VEGE_TYPE = 'SET_VEGE_TYPE';

export const setLoginChange = result => {
  return {
    type: SET_LOGIN_CHANGE,
    payload: result,
  };
};

export const setVege = vege => {
  return {
    type: SET_VEGE_TYPE,
    payload: vege,
  };
};
