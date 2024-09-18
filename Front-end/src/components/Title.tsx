import { FaEllipsisV } from "react-icons/fa"; // Asegúrate de tener instalada esta librería

function Title() {
  return (
    <div className="w-full h-24 bg-gray-100 flex items-center px-4">
      <div className="text-gray-500 w-1/5">
        <FaEllipsisV className="text-2xl" />
      </div>
      <h1 className="w-4/5 text-3xl font-semibold text-gray-800">
        CREACION HORARIOS
      </h1>
    </div>
  );
}

export default Title;
