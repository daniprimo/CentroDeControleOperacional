import './style.css';


export interface props {
  label: string;
  placeholder: string;
  type: string;
  name: string;
  value?: string;
  onChange?: any;
}

export default function Input(props: props){
  return (
    <>
      <form>
        <div>
          <label>{props.label}</label>
          <input className='corDoInput' type={props.type} placeholder={props.placeholder} onChange={props.onChange} value={props.value}></input>
        </div>
      </form>
    </>
  )
}