import styled from 'styled-components';

const CenterBox = styled.div`
  min-width: 450px;
  width: ${props => props.width || '40vw'};
  height: ${props => props.height || '60vh'};
  background-color: #f6f9fc;
  margin: 0 auto;
  padding: 5vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08);
`;

export default CenterBox;
