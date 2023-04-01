import styled from 'styled-components';

const StyledInput = styled.input`
  background-color: ${props => (props.disabled ? ' #e9e9e9' : '#fffffe')};
  border-radius: 5px;
  margin-top: 10px;
  padding: 5px 10px;
  border: 2px solid #55494c;
  width: 200px;
  &:focus {
    box-shadow: 0 0 10px #ffc6c7;
    outline: none;
    border: 2px solid #ffc6c7;
  }
`;
export default StyledInput;
