import { useState, useEffect } from 'react';
import { UserHistory } from '../organism/History/index';

const History = () => {
  const [productHistory, setProductHistory] = useState();
  const token = localStorage.getItem('JWT TOKEN');
  const getData = async () => {
    let url = `${process.env.REACT_APP_API_URL}/order/list`;
    try {
      fetch(url, {
        method: 'GET',
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
        .then(res => {
          if (!res.ok) throw new Error('No Response');
          return res.json();
        })
        .then(data => {
          setProductHistory(data.orders);
        });
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
