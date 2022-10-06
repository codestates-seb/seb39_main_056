export const setTokenHeader = () => {
  const ACCESS_TOKEN = localStorage.getItem('JWT TOKEN');
  const aaa = {};

  if (ACCESS_TOKEN !== undefined) {
    aaa.Authorization = `Bearer ${ACCESS_TOKEN}`;
  }

  return aaa;
};
