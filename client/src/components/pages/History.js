import { useState, useEffect } from 'react';
import { UserHistory } from '../organism/History/index';
import { useNavigate } from 'react-router-dom';

const History = () => {
  const [productHistory, setProductHistory] = useState();
  const token = localStorage.getItem('JWT TOKEN');
  const navigate = useNavigate();

  const getData = async () => {
    let url = `${process.env.REACT_APP_API_URL}/orders`;
    try {
      const data = await fetch(url, {
        method: 'GET',
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }).then(res => {
        if (!res.ok) throw new Error('No Response');
        navigate('/noresponse');
        return res.json();
      });
      setProductHistory(data);
    } catch (e) {
      navigate('/noresponse');
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
