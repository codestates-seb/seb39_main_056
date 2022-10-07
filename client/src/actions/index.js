export const SET_LOGIN_CHANGE = 'SET_LOGIN_CHANGE';
export const SET_VEGE_TYPE = 'SET_VEGE_TYPE';
export const INCREASE = 'INCREASE';
export const DECREASE = 'DECREASE';
export const SET_NUMBER = 'SET_NUMBER';

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

export const increase = () => {
  return {
    type: INCREASE,
  };
};

export const decrease = () => {
  return {
    type: DECREASE,
  };
};

export const setNumber = num => {
  return {
    type: SET_NUMBER,
    payload: num,
  };
};
