import styled from 'styled-components';

const SocialBtnContainer = styled.div`
  height: calc(55vh - 36px);
  /* background-color: red; */
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  /* 현재 소셜 버튼이 1개밖에 없어 아래로 치우쳐져 보여 추후 소셜로그인 버튼 추가 시 유동적으로 수정할 것 */
  padding-bottom: 5rem;
`;

export default SocialBtnContainer;
