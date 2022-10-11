import React from 'react';
import * as Styled from './style';

const Page = ({ searchParams, setSearchParams, page }) => {
  const handleClick = e => {
    const params = [];
    const paramsObj = {};
    for (let entry of searchParams.entries()) {
      params.push(entry);
    }
    for (let i = 0; i < params.length; i++) {
      paramsObj[`${params[i][0]}`] = params[i][1];
    }
    setSearchParams({
      ...paramsObj,
      page: `${e.target.textContent}`,
    });
  };
  return <Styled.Page onClick={handleClick}>{page}</Styled.Page>;
};

export default Page;
