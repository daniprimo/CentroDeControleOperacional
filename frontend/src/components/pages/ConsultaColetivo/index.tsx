import { useEffect, useState } from 'react';
import { Coletivo } from '../../../model/Coletivo';
import { buscarTodosColetivos } from '../../../service';
import CardColetivo from '../../cardColetivo';
import './style.css';

export default function Consultar() {

    const [coletivo, setColetivo] = useState<Coletivo[]>([]);

    useEffect(() => {
        buscarTodosColetivos()
        .then((resp) => {
                setColetivo(resp.data);
            });
    }, []);

    return (
        <>
            <div className='CardPrincipal'>
                {coletivo.map(coletivo => {
                    return (
                        <>
                            <CardColetivo
                                id={coletivo.id}
                                prefixo={coletivo.prefixo}
                                placa={coletivo.placa}
                                documento={coletivo.doc}
                            ></CardColetivo>
                        </>
                    )
                })}
            </div>
        </>
    )
}