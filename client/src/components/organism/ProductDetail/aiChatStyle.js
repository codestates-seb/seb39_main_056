import styled from 'styled-components';

export const AIHelperBlock = styled.div`
width: 60%;
height: 450px;
margin: 100px auto 20px;
min-width: 450px;
background-color : #f4f4f4;
`;

// Header

export const AIHelperTitle = styled.div`
padding : 30px;
background-color: #6ba543;
height : 12%;
display : flex;
align-items: center;
    justify-content: center;
`;

export const AIHelperH1 = styled.h1`
    font-weight : 700;
    font-size : 1.5em;
color: #fff;
`;

// Chat 

export const AIHelperChat = styled.div`
height : 80%;
overflow-y: overlay;
`;

export const AdminBlock = styled.div`
    display : flex;
    align-items : center;
    justify-content : flex-start;
    margin : 10px;
`;

export const AdminChat = styled.p `
    background-color: #c2c2c2;
    border-radius: 20px;
    width : fit-content;
    height : fit-content;
    max-width: 70%;
    padding : 15px;
    line-height: 18px;
`;


// Input
export const AIHelperInputBox = styled.div`
    height: 10%;
    background-color: #6ba543;
`;

export const AIHelperForm = styled.form`
    display: flex;  
    align-items: center;
    justify-content: center;
    width : 100%;
    height : 100%;
`;

export const AIHelperInput = styled.input`
width : 70%;
height: 35px;
margin-left : 30px;
font-size: 1.1em;
padding: 10px;
font-family: "Spoqa Han Sans Neo",sans-serif;
font-weight: 400;
-webkit-underline: none;
-ms-flex-underline: none;
underline: none;
border-radius: 10px;
border: 1px solid #6ba543;
&:focus {
    outline : none;
}
`;

export const AIHelperButton = styled.button`
background: inherit;
border:none;
box-shadow:none;
border-radius:0;
padding:0;
overflow:visible;
cursor:pointer;
width : 45px;
height : 45px;
`;

