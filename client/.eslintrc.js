// 출처 : https://any-ting.tistory.com/94

module.exports = {
  env: {
    browser: true,
    es2020: true,
  },
  parser: "babel-eslint",
  // parserOptions: {
  //   sourceType: "module",
  // },
  extends: [
    "airbnb",
    "airbnb/hooks",
    "plugin:import/errors",
    "plugin:import/warnings",
    "plugin:prettier/recommended",
    "plugin:eslint/recommended",
    "prettier",
    // "@salesforce/eslint-config-lwc/recommended",
  ],
  rules: {
    "react/prop-types": 0,
    "no-extra-semi": "error", // 확장자로 js와 jsx 둘다 허용하도록 수정
    "react/jsx-filename-extension": [1, { extensions: ["js", "jsx"] }], // 화살표 함수의 파라미터가 하나일때 괄호 생략
    "arrow-parens": ["warn", "as-needed"], // 사용하지 않는 변수가 있을때 빌드에러가 나던 규칙 해제
    "no-unused-vars": ["off"], // 콘솔을 쓰면 에러가 나던 규칙 해제
    "no-console": ["off"], // export const 문을 쓸때 에러를 내는 규칙 해제
    "import/prefer-default-export": ["off"], // hooks의 의존성배열이 충분하지 않을때 강제로 의존성을 추가하는 규칙을 완화
    "react-hooks/exhaustive-deps": ["warn"], // props spreading을 허용하지 않는 규칙 해제
    "react/jsx-props-no-spreading": [1, { custom: "ignore" }],
  },
};
