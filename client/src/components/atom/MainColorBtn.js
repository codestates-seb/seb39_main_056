import styled from 'styled-components';

const MainColorBtn = styled.button`
  background-color: #c3f0ca;
  font-size: 1rem;
  color: #33272a;
  border-radius: 0.2rem;
  padding: 0.6rem 0.8rem;
  font-family: 'MICEGothic_Bold';
  text-align: center;
  cursor: pointer;
  outline: none;
  border: none;
  transition: all 0.2s;
  display: block;
  margin: 0 auto;

  &:hover,
  :active {
    background-color: #ffc6c7;
  }
`;

export default MainColorBtn;
