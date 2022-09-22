import axios from "axios";
import React from "react";
import "./App.css";
import GlobalStyles from "./styles/GlobalStyles";
import { useState } from "react";

const App = () => {
  const url = process.env.REACT_APP_API_URL;
  const [state, setState] = useState("ddd");

  fetch(url)
    .then((res) => res.text())
    .then((data) => setState(data));

  return (
    <>
      <GlobalStyles />
      <p>{state}</p>
    </>
  );
};

export default App;
