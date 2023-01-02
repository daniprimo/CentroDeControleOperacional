import { useEffect, useState } from 'react';
import { Coletivo } from '../../../model/Coletivo';
import { buscarTodosColetivos } from '../../../service';
import CardColetivo from '../../cardColetivo';
import Input from '../../Input';
import './style.css';

export default function Consultar() {

    const [coletivo, setColetivo] = useState<Coletivo[]>([]);
    const [filter, setFilter] = useState('');
    const [filterStatus, setFilterStatus] = useState('');

    useEffect(() => {
        buscarTodosColetivos()
            .then((resp) => {
                setColetivo(resp.data);
            });
    }, []);

    return (
        <>
            <div className='CardPrincipal'>


                <div className='filtros'>
                    <Input label='Prefixo:' placeholder='Digite' type='text' onChange={(e: any) => setFilter(e.target.value)} name={''} />

                    <div className='selectStatus'>
                        <label htmlFor='status'>Status: </label>
                        <select name="status" onChange={(e) => setFilterStatus(e.target.value)}>
                            <option value=''>Todos</option>
                            <option value="Operando">Operando</option>
                            <option value="Parado">Parado</option>
                            <option value="emmanutencao">Em Manutenção</option>
                        </select>
                    </div>
                </div>

                {coletivo.filter(result => result.prefixo.includes(filter) && result.status.includes(filterStatus)).map(coletivo => {
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
                })

                }


            </div>
        </>
    )
}