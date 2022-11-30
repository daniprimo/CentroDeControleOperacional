import './sttyle.css';

interface props {
    name: string;
    onClick?: any;
    onSubmit?:any;

}

export default function Button(props: props) {
    return (
        <>
            <div className='button'>
                <button type='submit' onClick={props.onClick} onSubmit={props.onSubmit} >{props.name}</button>
            </div>
        </>
    )
}