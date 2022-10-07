import axios from 'axios';
import TypeTable from '../../organism/TypeTable';
import { useDispatch, useSelector } from 'react-redux';
import { setVege } from '../../../actions';
import { useNavigate, useParams } from 'react-router-dom';
import MainColorBtn from '../../atom/MainColorBtn';
import * as Styled from './style';

const Test = ({ explanation }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { id } = useParams();
  const vegeState = useSelector(state => state.vegeTypeReducer);
  const { name, ment, imgUrl } = explanation;

  const getTargetId = e => {
    if (e.target.parentNode.id !== 'thead') {
      return e.target.parentNode.id;
    }
  };

  const handleTrSelecte = e => {
    const prevVegeType = document.querySelector(`#${vegeState}`);
    if (prevVegeType.classList[0] === 'selected') {
      prevVegeType.classList.remove('selected');
    }

    dispatch(setVege(getTargetId(e)));

    if (e.target.parentNode.id !== 'thead') {
      e.target.parentNode.classList.add('selected');
    }
  };

  const decideType = () => {
    axios({
      method: 'post',
      url: `${process.env.REACT_APP_API_URL}/members/sign-up/type/${id}`,
      headers: {
        Accept: 'application/json',
        'Content-type': 'application/json',
      },
      data: JSON.stringify({
        vegetarianType: vegeState,
      }),
      // withCredentials: true,
    })
      .then(res => {
        if (res.status === 200) {
          navigate('/login');
        }
      })
      .catch(error => alert(error));
  };

  return (
    <div>
      <Styled.Title>채식유형선택</Styled.Title>
      <Styled.ContentContainer>
        <TypeTable>
          <thead className="thead">
            <tr>
              <td>허용식품</td>
              <td>적색육🥩</td>
              <td>백색육🍗</td>
              <td>어패류🦪</td>
              <td>난류🥚</td>
              <td>유지류🥛</td>
              <td>균류, 해조류, 채소류🥒</td>
              <td>과일류🍎</td>
            </tr>
          </thead>
          <tbody>
            <tr
              id="플렉시테리언"
              onClick={e => handleTrSelecte(e)}
              title="플렉시테리언"
              className="selected"
            >
              <td>플렉시테리언</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
            </tr>
            <tr id="폴로-페스코" onClick={e => handleTrSelecte(e)}>
              <td>폴로-페스코</td>
              <td>x</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
            </tr>
            <tr id="페스코" onClick={e => handleTrSelecte(e)}>
              <td>페스코</td>
              <td>x</td>
              <td>x</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
            </tr>
            <tr id="폴로" onClick={e => handleTrSelecte(e)}>
              <td>폴로</td>
              <td>x</td>
              <td>o</td>
              <td>x</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
            </tr>
            <tr id="락토-오보" onClick={e => handleTrSelecte(e)}>
              <td>락토-오보</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
            </tr>
            <tr id="락토" onClick={e => handleTrSelecte(e)}>
              <td>락토</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>o</td>
              <td>o</td>
              <td>o</td>
            </tr>
            <tr id="오보" onClick={e => handleTrSelecte(e)}>
              <td>오보</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>o</td>
              <td>x</td>
              <td>o</td>
              <td>o</td>
            </tr>
            <tr id="비건" onClick={e => handleTrSelecte(e)}>
              <td>비건</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>o</td>
              <td>o</td>
            </tr>
            <tr id="프루테리언" onClick={e => handleTrSelecte(e)}>
              <td>프루테리언</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>x</td>
              <td>o</td>
            </tr>
          </tbody>
        </TypeTable>
        <Styled.Explanation>
          <Styled.VegeType>{vegeState}</Styled.VegeType>
          <Styled.ImgContainer>
            <Styled.Img src={imgUrl} />
          </Styled.ImgContainer>
          <Styled.VegeExplanation>{name}</Styled.VegeExplanation>
          <Styled.VegeExplanation>{ment}</Styled.VegeExplanation>
        </Styled.Explanation>
      </Styled.ContentContainer>
      <MainColorBtn onClick={() => decideType()}>가입하기</MainColorBtn>
    </div>
  );
};

export default Test;
