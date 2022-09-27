import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Container = styled.table`
	width: 100%;
	border-top: 1px solid #444444;
	border-bottom: 1px solid #444444;
	border-collapse: collapse;
`;

const Types = [
	'플렉시테리언',
	'폴로-페스코',
	'페스코',
	'폴로',
	'락토-오보',
	'락토',
	'오보',
	'비건',
	'프루테리언',
];

const Mypage = () => {
	const patchInfo = () => {
		alert('정보 수정 버튼');
	};
	const deletInfo = () => {
		alert('삭제 버튼');
	};
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
							{Types.map(type => {
								return <option>{type}</option>;
							})}
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td>
						<button onClick={patchInfo}>정보수정</button>
						<button onClick={deletInfo}>회원탈퇴</button>
						<Link to="/">
							<button>메인으로</button>
						</Link>
					</td>
				</tr>
			</tbody>
		</Container>
	);
};

export default Mypage;
