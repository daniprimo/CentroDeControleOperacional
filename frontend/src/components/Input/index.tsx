import './style.css';


export interface props {
  label: string;
  placeholder: string;
  type: string;
  name: string;
  onChange?: any;
}

export default function Input(props: props){
  return (
    <>
      <form>
        <div>
          <label>{props.label}</label>
          <input type={props.type} placeholder={props.placeholder} onChange={props.onChange}></input>
        </div>
      </form>
    </>
  )
}