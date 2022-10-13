export const setTokenHeader = () => {
  const ACCESS_TOKEN = localStorage.getItem('JWT TOKEN');
  const aaa = {};

  if (ACCESS_TOKEN !== null) {
    aaa.Authorization = `Bearer ${ACCESS_TOKEN}`;
  }

  return aaa;
};
