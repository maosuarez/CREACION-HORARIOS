import React, { useState } from "react";
import { FaPlus } from "react-icons/fa"; // Ícono de agregar

const Dropdown = (props: { para: string; materia: string }) => {
  // Estado para la opción seleccionada
  const [opcionSeleccionada, setOpcionSeleccionada] = useState("");
  const [listaOpciones, setOpciones] = React.useState([""]);

  async function consultLista(mate: string, goal: string) {
    const url = localStorage.getItem("url");
    const link = url + "/opciones/" + goal + "?name=" + mate;
    const result = await fetch(link);
    const data = await result.json();

    setOpciones(data.sugerencias);
  }

  // Manejar el cambio de selección
  const manejarSeleccion = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setOpcionSeleccionada(event.target.value);
  };

  consultLista(props.materia, props.para);

  return (
    <select
      value={opcionSeleccionada}
      onChange={manejarSeleccion}
      className="p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
    >
      <option value="" disabled>
        Selecciona
      </option>
      {listaOpciones.map((opcion, index) => (
        <option key={index} value={opcion}>
          {opcion}
        </option>
      ))}
    </select>
  );
};

export default Dropdown;
