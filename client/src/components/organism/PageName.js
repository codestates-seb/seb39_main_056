import H1 from '../atom/H1';
import React from 'react';
import styled from 'styled-components';

const Wrap = styled.div`
  margin-top: 20px;
`;

const PageName = () => {
  return (
    <Wrap>
      <H1>현재 품목</H1>
    </Wrap>
  );
};

export default PageName;
