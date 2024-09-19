import { useState, useEffect } from "react";
import RowTable from "./RowTable";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSave } from "@fortawesome/free-solid-svg-icons";

interface Materia {
  materia: string;
  profesor: string;
  codigo: string;
}

function Table() {
  //Uso de hooks
  const [query, setQuery] = useState<string>(""); //manejo del texto en el buscador
  const [filteredMaterias, setFilteredMaterias] = useState<string[]>([]); //Materias que aparecen como sugerencia
  const [materiasCache, setMateriasCache] = useState<string[]>([]); //Materias que van siendo buscadas
  const [seleccionadas, setSeleccionadas] = useState<string[]>([]); //MAterias selecionadas y hacen parte de la tabla
  const [preferencias, setPreferencias] = useState<Materia[]>([]);
  const [boolePertenece, setBoolePertenece] = useState<boolean>(true); //Verificar que sea una materia valida
  const url = localStorage.getItem("url"); //url de la api

  //Evento que escucha cuando se debe cerrar las recomendaciones de busqueda
  useEffect(() => {
    const handleClickOutside = (e: MouseEvent) => {
      const target = e.target as HTMLElement;
      if (target.id !== "busqueda") {
        setFilteredMaterias([]);
      }
    };
    window.addEventListener("click", handleClickOutside);

    return () => window.removeEventListener("click", handleClickOutside);
  }, []);

  //Se llama cuando se modifica el input
  function handleInputChange(e: React.ChangeEvent<HTMLInputElement>) {
    const value = e.target.value;
    setQuery(value);
    sugerencias(value);
  }

  //Consultar api
  async function consultarApi(texto: string): Promise<string[]> {
    try {
      const link = `${url}/opciones/materia?name=${encodeURIComponent(texto)}`;
      const respuesta = await fetch(link);
      if (!respuesta.ok) throw new Error("Error en la API");
      const datos = await respuesta.json();
      return datos.sugerencias;
    } catch (error) {
      console.error("Error al consultar la API:", error);
      return [];
    }
  }

  //sugerencias y previamente vistas
  async function sugerencias(texto: string) {
    const listaMaterias = await consultarApi(texto);

    setBoolePertenece(listaMaterias.length > 0);
    setFilteredMaterias(listaMaterias);

    const nuevasMaterias = listaMaterias.filter(
      (mat) => !materiasCache.includes(mat)
    );
    if (nuevasMaterias.length > 0) {
      setMateriasCache([...materiasCache, ...nuevasMaterias]);
    }
  }

  //para agregar a las filas
  function llamadaFilas() {
    if (!boolePertenece || !materiasCache.includes(query)) {
      alert("Lo siento, esta materia no existe.");
    } else if (seleccionadas.includes(query)) {
      alert("Esta materia ya está agregada.");
    } else {
      setSeleccionadas((prev) => [...prev, query]);
      setPreferencias((prev) => [
        ...prev,
        { materia: query, profesor: "", codigo: "" },
      ]);
      setQuery("");
    }
  }

  //agregar materia o return null
  function crearFila(materia: string, index: number) {
    return materia ? (
      <RowTable
        materia={materia}
        setPreferencias={setPreferencias}
        preferencias={preferencias}
        key={index}
        index={index}
        setSeleccionadas={setSeleccionadas}
      />
    ) : null;
  }

  return (
    <div className="w-full flex flex-col p-4 bg-gray-100">
      <section className="mb-4 relative flex gap-5">
        <main className="w-96 mx-5" id="busqueda">
          <input
            type="text"
            placeholder="Ingresa la materia"
            className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            value={query}
            onChange={handleInputChange}
            onKeyDown={(e: React.KeyboardEvent<HTMLInputElement>) => {
              if (e.key === "Enter") {
                llamadaFilas();
              }
            }}
          />
          {query && filteredMaterias.length > 0 && (
            <ul className="absolute w-96 bg-white border border-gray-300 rounded-md mt-1 max-h-40 overflow-y-auto z-10">
              {filteredMaterias.map((materia, index) => (
                <li
                  key={index}
                  className="p-2 hover:bg-blue-100 cursor-pointer"
                  onClick={() => {
                    setQuery(materia);
                    setFilteredMaterias([]);
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
            id="submit"
            onClick={llamadaFilas}
            className="bg-blue-500 text-white p-2 rounded-md hover:bg-blue-600 active:bg-blue-700"
          >
            <FontAwesomeIcon icon={faSave} /> Guardar
          </button>
        </aside>
      </section>
      <table className="w-full bg-white rounded-md shadow-md max-h-28">
        <thead>
          <tr>
            <th className="text-left p-4 border-b">Materia</th>
            <th className="text-left p-4 border-b">Profesor</th>
            <th className="text-left p-4 border-b">Código</th>
            <th className="text-left p-4 border-b">Acciones</th>
          </tr>
        </thead>
        <tbody id="bodyTabla">
          {seleccionadas.map((mate, index) => crearFila(mate, index))}
        </tbody>
      </table>
      <footer className="mt-4">
        <button
          className="w-full p-3 bg-blue-500 text-white font-semibold rounded-md hover:bg-blue-600 active:bg-blue-700 transition-colors"
          onClick={() => {
            console.log(preferencias);
          }}
        >
          Generar
        </button>
      </footer>
    </div>
  );
}

export default Table;
