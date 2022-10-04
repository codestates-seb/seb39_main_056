import styled from 'styled-components';

const TypeTable = styled.table`
  margin: 1rem;
  outline: 1px solid #594a4e;

  td {
    padding: 5px;
    text-align: center;
  }

  thead > tr {
    background-color: #c3f0ca;
    color: #33272a;
    border: none;
    box-shadow: none;
  }

  tbody > tr {
    &.selected {
      /* background-color: red; */
      box-shadow: 0 0 10px #c3f0ca;
    }
    &:hover {
      box-shadow: 0 0 10px #ffc6c7;
      cursor: pointer;
      /* border: 2px solid #c3f0ca; */
    }
  }
`;

export default TypeTable;
