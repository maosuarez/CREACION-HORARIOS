import { useState } from "react";
import RowTable from "./RowTable";

function Table() {
  const [query, setQuery] = useState("");
  const [filteredMaterias, setFilteredMaterias] = useState([""]);
  const [seleccionadas, setSeleccionada] = useState([""]);
  const url = localStorage.getItem("url");

  function handleInputChange(e) {
    const value = e.target.value;
    setQuery(value);

    sugerencias(value);
  }

  async function sugerencias(texto: string) {
    const link = url + "/opciones/materia?name=" + texto;

    const respuesta = await fetch(link);

    const datos = await respuesta.json();

    const listaMaterias = datos.sugerencias;

    setFilteredMaterias(listaMaterias);
  }

  function crearFila(materia: string, index: number) {
    if (materia !== "") {
      return <RowTable materia={materia} key={index} />;
    }
    return;
  }

  return (
    <div className="w-full flex flex-col p-4 bg-gray-100">
      <section className="mb-4 relative flex gap-5">
        <main className="w-96 mx-5">
          <input
            type="text"
            placeholder="Ingresa la materia"
            className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            value={query}
            onChange={handleInputChange}
          />
          {query && filteredMaterias.length > 0 && (
            <ul className="absolute w-96 bg-white border border-gray-300 rounded-md mt-1 max-h-40 overflow-y-auto z-10">
              {filteredMaterias.map((materia, index) => (
                <li
                  key={index}
                  className="p-2 hover:bg-blue-100 cursor-pointer"
                  onClick={() => {
                    setQuery(materia);
                    setFilteredMaterias([]); // Ocultar lista al seleccionar
                  }}
                >
                  {materia}
                </li>
              ))}
            </ul>
          )}
        </main>
        <aside>
          <button
            onClick={() => {
              if (seleccionadas.length === 1 && seleccionadas[0] === "") {
                setSeleccionada([query]);
              } else {
                if (!seleccionadas.includes(query)) {
                  setSeleccionada([...seleccionadas, query]);
                }
              }
            }}
          >
            Guardar
          </button>
        </aside>
      </section>
      <table className="w-full bg-white rounded-md shadow-md max-h-28">
        {/* La tabla puede ser dinámica, aquí está el esqueleto básico */}
        <thead>
          <tr>
            <th className="text-left p-4 border-b">Materia</th>
            <th className="text-left p-4 border-b">Profesor</th>
            <th className="text-left p-4 border-b">Codigo</th>
          </tr>
        </thead>
        <tbody id="bodyTabla">
          {seleccionadas.map((mate, index) => crearFila(mate, index))}
        </tbody>
      </table>
      <footer className="mt-4">
        <button className="w-full p-3 bg-blue-500 text-white font-semibold rounded-md hover:bg-blue-600 active:bg-blue-700 transition-colors">
          Generar
        </button>
      </footer>
    </div>
  );
}

export default Table;
