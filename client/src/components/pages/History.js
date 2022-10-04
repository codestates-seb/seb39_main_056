import { useState, useEffect } from 'react';
import { UserHistory } from '../organism/History/index';

const History = () => {
  const [productHistory, setProductHistory] = useState();
  //const getData = async () => {
  // let url = `http://localhost:3001/history`;
  // const data = await fetch(url) //
  //   .then(res => res.json());
  //setProductHistory(data);
  //};
  useEffect(() => {
    //getData();
    const data = [
      { image: 1, username: '킴코딩', product: '냄비' },
      { image: 2, username: '박해커', product: '라면' },
      { image: 3, username: '초콜릿', product: '칫솔' },
      { image: 4, username: '새우깡', product: '노트북' },
      { image: 5, username: '포도', product: '이쑤시개' },
    ];
    setProductHistory(data);
  }, []);

  return (
    <>
      <UserHistory productHistory={productHistory} />
    </>
  );
};
export default History;
