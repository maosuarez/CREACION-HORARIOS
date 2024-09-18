import "./App.css";
import Principal from "./pages/Principal";

function App() {
  localStorage.setItem("url", "http://localhost:8080");

  return (
    <>
      <Principal />
    </>
  );
}

export default App;
