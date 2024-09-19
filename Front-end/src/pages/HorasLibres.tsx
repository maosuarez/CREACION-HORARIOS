import BtnGroup from "../components/BtnGroup";
import Semanario from "../components/Semanario";
import Title from "../components/Title";

function Principal() {
  return (
    <div>
      <Title />
      <Semanario titulo={"Horas Libres en la semana"} />
      <BtnGroup />
    </div>
  );
}

export default Principal;
