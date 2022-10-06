import React from 'react';
import H2 from '../atom/H2';
import styled from 'styled-components';

const Container = styled.div`
  text-align: center;
  margin: 20px;
`;

const PageInfo = () => {
  return (
    <Container>
      <H2> 전체 상품을 보여줍니다. </H2>
    </Container>
  );
};

export default PageInfo;
