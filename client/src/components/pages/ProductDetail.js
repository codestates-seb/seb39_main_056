import { useState, useEffect } from 'react';
import { ProductPage } from '../organism/ProductDetail';
import { useParams } from 'react-router-dom';

const ProductDetail = () => {
  const [productData, setProductData] = useState();
  const { id } = useParams();
  const getData = async () => {
    let url = `${process.env.REACT_APP_API_URL}/detail/${id}`;
    //let url = `${process.env.REACT_APP_API_URL}/products`;

    try {
      const data = await fetch(url).then(res => {
        if (!res.ok) throw new Error('No Response');
        return res.json();
      });
      setProductData(data.product);
      //setProductData(data[0]);
    } catch (e) {
      console.log(e.message);
    }
  };
  useEffect(() => {
    getData();
  }, []);

  return <ProductPage productId={id} productData={productData} />;
};
export default ProductDetail;
