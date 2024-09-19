import React, { useEffect, useState } from "react";
//import { FaPlus } from "react-icons/fa"; // Ícono de agregar

interface Materia {
  materia: string;
  profesor: string;
  codigo: string;
}

const Dropdown = (props: {
  index: number;
  para: string;
  materia: string;
  setPreferencias: React.Dispatch<React.SetStateAction<Materia[]>>;
  preferencias: Materia[];
}) => {
  // Estado para la opción seleccionada
  const [opcionSeleccionada, setOpcionSeleccionada] = useState("");
  const [listaOpciones, setOpciones] = React.useState([""]);

  const modificarProfesor = (nuevoProfesor: string) => {
    const nuevaLista = props.preferencias.map((item, i) =>
      i === props.index ? { ...item, profesor: nuevoProfesor } : item
    );
    props.setPreferencias(nuevaLista);
  };

  const modificarCodigo = (nuevoCodigo: string) => {
    const nuevaLista = props.preferencias.map((item, i) =>
      i === props.index ? { ...item, codigo: nuevoCodigo } : item
    );
    props.setPreferencias(nuevaLista);
  };

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
    if (props.para == "codigo") {
      modificarCodigo(event.target.value);
    } else if (props.para == "profesor") {
      modificarProfesor(event.target.value);
    }
  };

  useEffect(() => {
    consultLista(props.materia, props.para);
  }, [props.materia, props.para]);

  return (
    <select
      value={opcionSeleccionada}
      onChange={manejarSeleccion}
      className="p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
    >
      <option value="">Selecciona</option>
      {listaOpciones.map((opcion, index) => (
        <option key={index} value={opcion}>
          {opcion}
        </option>
      ))}
    </select>
  );
};

export default Dropdown;
