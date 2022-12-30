import './style.css';

export interface props {
    prefixo: string;
    placa: string;
    documento: string;
    status?: string;
    onChange?: any;
}


function res() {
    alert("aaaaaaa");
}

export default function CardColetivo(props: props) {
    return (
        <>
                <div className={'CardApresentacao'} onClick={() => null}>
                    <div className='PrefixoEPlaca'>
                        <div className='Prefixo'>
                            <p className='TituloPrefixo'>Prefixo:</p>
                            <p className='ValorPrefixo'>{props.prefixo}</p>
                        </div>
                        <div className='Placa'>
                            <p className='TituloPlaca'>Placa:</p>
                            <p className='ValorPlaca' style={{fontSize: ''}}>{props.placa}</p>
                        </div>
                    </div>

                    <div className='Documento'>
                        <p className='TituloDocumento'>Documento:</p>
                        <p className='ValorDocumento'>{props.documento}</p>
                    </div>

                </div>
        </>
    )
}   