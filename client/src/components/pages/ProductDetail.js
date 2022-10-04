import { useState, useEffect } from 'react';
import { ProductPage } from '../organism/ProductDetail';
import { useParams } from 'react-router-dom';

const ProductDetail = () => {
  const [productData, setProductData] = useState();
  const { id } = useParams();
  const getData = async () => {
    let url = `http://localhost:3001/products/detail/${id}`;
    try {
      const data = await fetch(url).then(res => {
        if (!res.ok) throw new Error('No Response');
        return res.json();
      });
      setProductData(data.product);
    } catch (e) {
      console.log(e.message);
    }
  };
  useEffect(() => {
    getData();
  }, []);

  return <ProductPage productData={productData} />;
};
export default ProductDetail;
