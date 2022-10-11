import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

const GlobalStyles = createGlobalStyle`
    ${reset};


    *{
        box-sizing: border-box;
        font-family : "MICEGothic";
    }
`;

export default GlobalStyles;
