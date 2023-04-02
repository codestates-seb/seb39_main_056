import dayjs from "dayjs";

export const setTokenHeader = () => {
  
  // Sun Apr 02 17:44:35 KST 2023 -> dayjs.toDate()
  // type오브
  const JWT_EXPIRATION = dayjs(localStorage.getItem('JWT EXPIRATION'),"YYYY-MM-DD HH:MM::SS").format();
  const currentDate = dayjs(Date.now(),"YYYY-MM-DD HH:MM:SS").format();

  console.log(JWT_EXPIRATION);
  console.log(currentDate);
  // Sun Apr 02 2023 18:23:15 GMT+0900 (한국 표준시)

  if (JWT_EXPIRATION !== null && JWT_EXPIRATION < currentDate) {
    localStorage.removeItem('JWT TOKEN');
    localStorage.removeItem('JWT EXPIRATION');
    alert('회원이 만료되었습니다.');
    window.location.reload();
    return false;
  }
  
  const ACCESS_TOKEN = localStorage.getItem('JWT TOKEN');
  const aaa = {};

  if (ACCESS_TOKEN !== null) {
      aaa.Authorization = `Bearer ${ACCESS_TOKEN}`;
  }

  return aaa;
};
