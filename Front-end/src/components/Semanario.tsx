const horas = Array.from({ length: 12 }, (_, i) => `${i + 7}:00`);
const dias = ["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"];

const Semanario = (props: { titulo: string }) => {
  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">{props.titulo}</h1>
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white border border-gray-200">
          <thead>
            <tr className="bg-gray-100 border-b">
              <th className="px-4 py-2 text-left border-r">Hora</th>
              {dias.map((dia) => (
                <th key={dia} className="px-4 py-2 border-r">
                  {dia}
                </th>
              ))}
            </tr>
          </thead>
          <tbody>
            {horas.map((hora) => (
              <tr key={hora}>
                <td className="px-4 py-2 border-b border-r">{hora}</td>
                {dias.map((dia) => (
                  <td
                    key={dia + hora}
                    className="px-4 py-2 border-b border-r hover:bg-slate-400 cursor-pointer active:bg-green-300"
                  >
                    {/* Aquí puedes añadir contenido o funcionalidades */}
                  </td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Semanario;
