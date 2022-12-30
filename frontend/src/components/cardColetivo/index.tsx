import './style.css';

export interface props {
    prefixo: string;
    placa: string;
    documento: string;
    status?: string;
    onChange?: any;
}

export default function CardColetivo(props: props) {
    return (
        <>
            <div className={'CardApresentacao'}>
                <div className='PrefixoEPlaca'>
                    <div className='Prefixo'>
                        <h4>Prefixo:</h4>
                        <h2>{props.prefixo}</h2>
                    </div>
                    <div className='Placa'>
                        <h4>Placa:</h4>
                        <h2>{props.placa}</h2>
                    </div>
                </div>

                <div className='Documento'>
                    <h4>Documento:</h4>
                    <h2>{props.documento}</h2>
                </div>

            </div>

        </>
    )
}   