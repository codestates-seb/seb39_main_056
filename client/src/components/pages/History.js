import { useState, useEffect } from 'react';
import { UserHistory } from '../organism/History/index';

const History = () => {
  const [productHistory, setProductHistory] = useState();
  const getData = async () => {
    let url = `${process.env.REACT_APP_API_URL}/orders`;
    try {
      const data = await fetch(url) //
        .then(res => {
          if (!res.ok) throw new Error('No Response');
          return res.json();
        });
      setProductHistory(data);
    } catch (e) {
      console.log(e.message);
    }
  };
  useEffect(() => {
    getData();
  }, []);

  return (
    <>
      <UserHistory productHistory={productHistory} />
    </>
  );
};
export default History;
