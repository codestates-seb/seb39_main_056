import { useState, useEffect } from 'react';
import { ProductPage } from '../organism/ProductDetail';
import { useParams, useNavigate } from 'react-router-dom';
import { GetData } from '../../hooks/fetchApi';
import { useDispatch } from 'react-redux';
import { setProductListData } from '../../actions/index';

const ProductDetail = () => {
  const [productData, setProductData] = useState();
  const [thumnailImg, setThumnailImg] = useState();
  const { id } = useParams();
  const navigate = useNavigate();
  const dispatch = useDispatch();

  let url = `${process.env.REACT_APP_API_URL}/products/detail/${id}`;
  let thumnailUrl = `${process.env.REACT_APP_API_URL}/products/list`;

  // let url = 'http://localhost:3001/detail';
  // let thumnailUrl = 'http://localhost:3001/products';

  useEffect(() => {
    GetData(url, setProductData).then(result => {
      if (result === 'fail') navigate('/noresponse');
    });
    GetData(thumnailUrl, setThumnailImg).then(result => {
      if (result === 'fail') navigate('/noresponse');
    });
  }, []);

  useEffect(() => {
    // if (thumnailImg !== undefined) {
    //   dispatch(setProductListData(thumnailImg));
    // }
    if (thumnailImg?.products !== undefined) {
      dispatch(setProductListData(thumnailImg.products));
    }
    console.log('useEffect');
  }, [thumnailImg]);

  return <ProductPage productId={id} productData={productData?.product} />;
};
export default ProductDetail;
