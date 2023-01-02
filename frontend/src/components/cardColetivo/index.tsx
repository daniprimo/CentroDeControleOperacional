import { useNavigate } from 'react-router-dom';
import './style.css';

export interface props {
    id: number;
    prefixo: string;
    placa: string;
    documento: string;
    status?: string;
    onChange?: any;
}

export default function CardColetivo(props: props) {

    const navigate = useNavigate();


    return (
        <>
            <div className={'CardApresentacao'} onClick={() => navigate(
                '/Formulario', {
                    state: {
                        userId: props.id,
                        userPlaca: props.placa,
                        userPrefixo: props.prefixo,
                    }
                }
            )}>
                <div className='PrefixoEPlaca'>
                    <div className='Prefixo'>
                        <p className='TituloPrefixo'>Prefixo:</p>
                        <p className='ValorPrefixo'>{props.prefixo}</p>
                    </div>
                    <div className='Placa'>
                        <p className='TituloPlaca'>Placa:</p>
                        <p className='ValorPlaca' style={{ fontSize: '' }}>{props.placa}</p>
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