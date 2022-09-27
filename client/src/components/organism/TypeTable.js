import styled from 'styled-components';

const TypeTable = styled.table`
  margin: 50px;
  outline: 1px solid #594a4e;

  thead > tr {
    background-color: #c3f0ca;
    color: #33272a;
  }

  tr {
    z-index: 999;
    &.selected {
      /* background-color: red; */
      box-shadow: 0 0 10px #c3f0ca;
    }
    td {
      padding: 5px;
      text-align: center;
    }
    &:hover:first-child {
      border: none;
      box-shadow: none;
    }
    &:hover {
      box-shadow: 0 0 10px #ffc6c7;
      cursor: pointer;
      /* border: 2px solid #c3f0ca; */
    }
  }
`;

export default TypeTable;
