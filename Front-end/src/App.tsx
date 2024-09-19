import { Route, Routes } from "react-router-dom";
import "./App.css";
import Principal from "./pages/Principal";
import HorasLibres from "./pages/HorasLibres";

function App() {
  localStorage.setItem("url", "http://localhost:8080");

  return (
    <>
      <Principal />
    </>
  );
}

export default App;
