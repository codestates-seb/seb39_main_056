import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import Test from '../templates/Type/Type';
import paul from '../../assets/paul.jpg';
import ariana from '../../assets/ariana.jpg';
import brian from '../../assets/brian.png';
import chris from '../../assets/chris.png';
import franz from '../../assets/franz.jpg';
import steven from '../../assets/steven.png';
import toby from '../../assets/toby.jpg';
import tolstoy from '../../assets/tolstoy.jpg';
import tyson from '../../assets/tyson.jpg';

const Type = () => {
  const vegeState = useSelector(state => state.vegeTypeReducer);
  const [explanation, setExplanation] = useState({});

  const printSuitableExplanation = vegeType => {
    switch (vegeType) {
      case '플렉시테리언':
        return {
          name: '폴 매카트니',
          ment: '“만약 도살장 벽이 유리로 되어 있다면 사람들은 모두 채식주의자가 될 것입니다.“',
          imgUrl: paul,
        };
      case '폴로-페스코':
        return {
          name: '아리아나 그란데',
          ment: '“농담이 아니라, 저는 대부분의 사람들을 좋아하는 것보다 동물들을 더 좋아해요.”',
          imgUrl: ariana,
        };
      case '페스코':
        return {
          name: '크리스 스몰링',
          ment: '“공장식 축산은, 동물에게는 불행을 인류에게는 불필요함을, 환경에는 큰피해를 준다는 것을 깨달았습니다.”',
          imgUrl: chris,
        };
      case '폴로':
        return {
          name: '스티븐 스필버그',
          ment: '“인간은 배고프지 않을 때에도 사냥하는 유일한 동물입니다.”',
          imgUrl: steven,
        };
      case '락토-오보':
        return {
          name: '마이크 타이슨',
          ment: '“코카인, 고혈압으로 인해 힘들었떤 삶에서 벗어날 수 있었던 것은 채식 덕분입니다.”',
          imgUrl: tyson,
        };
      case '락토':
        return {
          name: '토비 맥과이어',
          ment: '“어릴 적 동물의 삶에 대해 상상하며 채식을 시작했어요.“',
          imgUrl: toby,
        };
      case '오보':
        return {
          name: '톨스토이',
          ment: '“어떤 말로 채식을 반대하든, 우리 인간은 양과 닭을 죽이는 것을 불쌍히 여깁니다. 제 손으로 죽일 바에는 차라리 고기를 먹지 않겠다는 사람이 대부분일 것입니다.”',
          imgUrl: tolstoy,
        };
      case '비건':
        return {
          name: '프란츠 카프카',
          ment: '“수족관 물고기의 평화로움을 바라보다 결국 채식주의를 결심했습니다.”',
          imgUrl: franz,
        };
      case '프루테리언':
        return {
          name: '브라이언 메이',
          ment: '“지구의 모든 생명은 좋은 삶을 영위해야 한다고 생각해요. 사람은 다른 생명체의 사용자나 학대자가 되어서는 안 되죠.”',
          imgUrl: brian,
        };
      default:
        return;
    }
  };

  useEffect(() => {
    setExplanation(printSuitableExplanation(vegeState));
  }, [vegeState]);

  return <Test explanation={explanation} />;
};

export default Type;
