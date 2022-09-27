import React from 'react';
import styled from 'styled-components';
// import H2 from '../atom/H2';

const Container = styled.table`
	width: 100%;
	border-top: 1px solid #444444;
	border-bottom: 1px solid #444444;
	border-collapse: collapse;
`;

const LineTh = styled.th`
	border-bottom: 1px solid #444444;
	padding: 10px;
`;

const Mypage = () => {
	return (
		<Container>
			<thead>
				<tr>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>회원정보</td>
					<td>이름</td>
					<td>
						<input></input>
					</td>
				</tr>
				<tr>
					<td>배송조회</td>
					<td>휴대전화</td>
					<td>
						<input></input>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>주소</td>
					<td>
						<div>
							<input></input>
							<button>우편번호찾기</button>
						</div>
						<div>
							<input></input>
							<input></input>
						</div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>이메일</td>
					<td>
						<input></input>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>채식유형</td>
					<td>
						<select>
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td>
						<button>정보수정</button>
						<button>회원탈퇴</button>
						<button>메인으로</button>
					</td>
				</tr>
			</tbody>
		</Container>
	);
};

export default Mypage;
