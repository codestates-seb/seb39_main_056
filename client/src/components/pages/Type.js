import React, { useState } from 'react';
import axios from 'axios';
import TypeTable from '../organism/TypeTable';
import { useDispatch, useSelector } from 'react-redux';
import { setVege } from '../../actions';
import { useNavigate } from 'react-router-dom';

const Type = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const vegeState = useSelector(state => state.vegeTypeReducer);
  const [selected, setSelected] = useState();

  const trSelcte = e => {
    // e.target.classList.add('selcted');
    console.log(e.target.parentNode);
    if (e.target.parentNode.className !== 'thead') {
      e.target.parentNode.classList.add('selected');
    }
  };

  const decideType = () => {
    console.log(vegeState);
    axios
      .patch(
        `${process.env_REACT_APP_API_URL}backendURL`,
        {
          vegeType: vegeState,
        },
        { withCredentials: true },
      )
      .then(res => {
        if (res.ok) {
          navigate('/');
        }
      });
  };

  return (
    <div>
      <h1>채식유형 선택</h1>
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
          <tr className="flexi" onClick={e => trSelcte(e)}>
            <td>플렉시테리언</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
          </tr>
          <tr className="polllo-pesco" onClick={e => trSelcte(e)}>
            <td>폴로-페스코</td>
            <td>x</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
          </tr>
          <tr className="pesco" onClick={e => trSelcte(e)}>
            <td>페스코</td>
            <td>x</td>
            <td>x</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
          </tr>
          <tr className="polo" onClick={e => trSelcte(e)}>
            <td>폴로</td>
            <td>x</td>
            <td>o</td>
            <td>x</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
          </tr>
          <tr className="lacto-ovo" onClick={e => trSelcte(e)}>
            <td>락토-오보</td>
            <td>x</td>
            <td>x</td>
            <td>x</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
          </tr>
          <tr className="lacto" onClick={e => trSelcte(e)}>
            <td>락토</td>
            <td>x</td>
            <td>x</td>
            <td>x</td>
            <td>x</td>
            <td>o</td>
            <td>o</td>
            <td>o</td>
          </tr>
          <tr className="ovo" onClick={e => trSelcte(e)}>
            <td>오보</td>
            <td>x</td>
            <td>x</td>
            <td>x</td>
            <td>o</td>
            <td>x</td>
            <td>o</td>
            <td>o</td>
          </tr>
          <tr className="vegan" onClick={e => trSelcte(e)}>
            <td>비건</td>
            <td>x</td>
            <td>x</td>
            <td>x</td>
            <td>x</td>
            <td>x</td>
            <td>o</td>
            <td>o</td>
          </tr>
          <tr className="fruit" onClick={e => trSelcte(e)}>
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
      <h2>{vegeState}</h2>
      <button onClick={() => decideType()}>가입 완료</button>
    </div>
  );
};

export default Type;
