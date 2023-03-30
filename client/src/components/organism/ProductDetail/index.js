import * as Styled from './style';
import "./aiChatCss.css";
import * as AIStyled from './aiChatStyle';
import { ProductInfoContainer } from '../../molecule/ProductDetail/index';
import { setTokenHeader } from '../../../service/setTokenHeader';
import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import $ from 'jquery'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHandPointRight, faMessage} from '@fortawesome/free-regular-svg-icons';
import H1 from '../../atom/H1';
import { faFeatherPointed, faRobot, faShare } from '@fortawesome/free-solid-svg-icons';

export const ProductPage = ({ productId, productData }) => {
  const navigate = useNavigate();
  const [quantity, setQuantity] = useState(1);
  const tokenHeader = setTokenHeader();
  const url = `${process.env.REACT_APP_API_URL}/cart/product/${productId}`;
  //장바구니에 담기 눌렀을때
  const AddToCartProduct = async () => {
    if (productData.stockQuantity < quantity) {
      alert(
        `장바구니에 담을 수 있는 최대 재고량은 ${productData.stockQuantity}입니다.`,
      );
    } else {
      try {
        fetch(url, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            ...tokenHeader,
          },
          body: JSON.stringify({
            purchaseQuantity: quantity,
          }),
        })
          .then(response => {
            if (!response.ok) throw new Error('로그인 후 이용해주세요.');
            console.log(response.json());
            response.json();
          })
          .then(data => {
            alert('장바구니에 추가되었습니다!');
            window.location.reload();
          });
      } catch (e) {
        alert('로그인 후 이용해주세요.');
        console.log(e.message);
      }
    }
  };

  const BuyProduct = () => {
    if (productData.stockQuantity < quantity) {
      alert(
        `현재 구매 가능한 최대 재고량은 ${productData.stockQuantity}입니다.`,
      );
    } else {
      axios({
        method: 'post',
        url: `${process.env.REACT_APP_API_URL}/order/product/${productId}`,
        headers: {
          Accept: 'application/json',
          'Content-type': 'application/json',
          ...tokenHeader,
        },
        data: JSON.stringify({
          orderQuantity: quantity,
        }),
      }).then(res => {
        if (res.status === 200) {
          alert('주문이 완료되었습니다!');
          navigate('/mypage/history');
        } else {
          alert('다시 한 번 시도해주세요!');
        }
      });
    }
  };

  const ReqeustChatGptAPI = (event) => {

    event.preventDefault()
    var userInput = $("#user-input").val();
    $("#user-input").val("");
    $("#chat").append("<div class='user-block'>"
    + "<p class='user-chat'> " + userInput + "</p>"
    + "</div>");

    $('#chat').scrollTop($('#chat')[0].scrollHeight);

    axios({
      method: 'post',
      url: `${process.env.REACT_APP_API_URL}/api/helper`,
      headers: {
        Accept: 'application/json',
        'Content-type': 'application/json',
      },
      data: JSON.stringify({
        text : userInput
      }),
    }).then(res => {
      const answer = res.data.answer;
      console.log(answer);
      $("#chat").append("<div class='admin-block'>"
      +"<i class='fa-solid fa-robot fa-2x' style='margin:10px; color:#C2C2C2; align-self:flex-start;'></i>"
      +"<p class='admin-chat'>" + answer +"</p>"
      +"</div>");
      $('#chat').scrollTop($('#chat')[0].scrollHeight);
    });
  }

  return (
    <>
      <Styled.CategoryPageTitle>Home {'>'} </Styled.CategoryPageTitle>
      <Styled.CategoryPageSubTitle> Food Category</Styled.CategoryPageSubTitle>
      <Styled.CategoryPageBox>
        <ProductInfoContainer
          productData={productData}
          quantity={quantity}
          setQuantity={setQuantity}
        />
        <Styled.BtnBlock>
          {productData !== undefined && productData.stockQuantity === 0 ? (
            <Styled.SoldOutBtn disabled>품절</Styled.SoldOutBtn>
          ) : (
            <Styled.CartBtn onClick={AddToCartProduct}>
              장바구니
            </Styled.CartBtn>
          )}
          {productData !== undefined && productData.stockQuantity === 0 ? (
            <Styled.SoldOutBtn disabled>품절</Styled.SoldOutBtn>
          ) : (
            <Styled.BuyBtn onClick={BuyProduct}>구매하기</Styled.BuyBtn>
          )}
        </Styled.BtnBlock>

        {/* chatGPT-API HELPER */}
        <AIStyled.AIHelperBlock>
          <AIStyled.AIHelperTitle>
            <FontAwesomeIcon
                icon={faMessage}
                color='#fff'
                style={{ widht: '25px', height: '25px', margin: '10px' }}
            />
            <AIStyled.AIHelperH1>채식이 AI </AIStyled.AIHelperH1>
          </AIStyled.AIHelperTitle>

          <AIStyled.AIHelperChat id="chat">
          <AIStyled.AdminBlock>
          <FontAwesomeIcon
                icon={faRobot}
                color='#c2c2c2'
                size='2x'
                style={{ margin: '10px' }}
            />
              <AIStyled.AdminChat>무엇이 궁금하신가요?</AIStyled.AdminChat>
            </AIStyled.AdminBlock>
          </AIStyled.AIHelperChat>

          <AIStyled.AIHelperInputBox>
            <AIStyled.AIHelperForm onSubmit={ReqeustChatGptAPI}>
              <AIStyled.AIHelperInput id="user-input" placeholder='현재 제품이 본인의 채식 유형과 적합한지 물어보세요!'></AIStyled.AIHelperInput>
              <AIStyled.AIHelperButton type="submit">
                <FontAwesomeIcon
                  icon={faShare} color='#fff' size='lg'/>
              </AIStyled.AIHelperButton>
            </AIStyled.AIHelperForm>
          </AIStyled.AIHelperInputBox>
        </AIStyled.AIHelperBlock>
        
        <Styled.Space></Styled.Space>
        
        <Styled.DetailedProductTitle>제품 정보 자세히 보기</Styled.DetailedProductTitle>
        <Styled.DetailedProductDesc src={productData?.detailImage} />
      </Styled.CategoryPageBox>
    </>
  );
};
