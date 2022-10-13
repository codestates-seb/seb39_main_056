import H1 from '../atom/H1';
import React from 'react';
import styled from 'styled-components';

const Wrap = styled.div`
  margin-top: 20px;
`;

const PageName = () => {
  return (
    <Wrap>
      <H1>All Proucts</H1>
    </Wrap>
  );
};

export default PageName;
