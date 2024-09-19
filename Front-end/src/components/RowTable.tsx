import DropDown from "./DropDown";
import { FaTrash } from "react-icons/fa";

interface Materia {
  materia: string;
  profesor: string;
  codigo: string;
}

function Row(props: {
  index: number;
  materia: string;
  setSeleccionadas: React.Dispatch<React.SetStateAction<string[]>>;
  setPreferencias: React.Dispatch<React.SetStateAction<Materia[]>>;
  preferencias: Materia[];
}) {
  const eliminarMateria = (materia: string) => {
    props.setSeleccionadas((prevSeleccionadas) =>
      prevSeleccionadas.filter((item) => item !== materia)
    );
  };

  const eliminarObjeto = () => {
    const nuevaLista = props.preferencias.filter((_, i) => i !== props.index);
    props.setPreferencias(nuevaLista);
  };

  return (
    <tr className="py-1 px-2">
      <td>{props.materia}</td>
      <td>
        <DropDown
          setPreferencias={props.setPreferencias}
          preferencias={props.preferencias}
          key={props.index}
          index={props.index}
          para="profesor"
          materia={props.materia}
        ></DropDown>
      </td>
      <td>
        <DropDown
          setPreferencias={props.setPreferencias}
          preferencias={props.preferencias}
          key={props.index}
          index={props.index}
          para="codigo"
          materia={props.materia}
        ></DropDown>
      </td>
      <td>
        <button
          className="flex justify-center items-center"
          onClick={() => {
            eliminarMateria(props.materia);
            eliminarObjeto();
          }}
        >
          Eliminar <FaTrash className="mx-4" />
        </button>
      </td>
    </tr>
  );
}

export default Row;
