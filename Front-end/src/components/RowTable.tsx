import DropDown from "./DropDown";

function Row(props: { materia: string }) {
  return (
    <tr className="py-1 px-2">
      <td>{props.materia}</td>
      <td>
        <DropDown para="profesor" materia={props.materia}></DropDown>
      </td>
      <td>
        <DropDown para="codigo" materia={props.materia}></DropDown>
      </td>
    </tr>
  );
}

export default Row;
