import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Coletivo } from '../../model/Coletivo';
import { buscarTodosColetivos, excluirColetivo, registraColetivo } from '../../../service';
import Button from '../Button';
import Input from '../Input';
import './style.css';


export default function Registrar() {

    const [placa, setPlaca] = useState('');
    const [prefixo, setPrefixo] = useState('');
    const [modelo, setModelo] = useState('');
    const [cor, setCor] = useState('');
    const [doc, setDoc] = useState('');
    const [status, setStatus] = useState('Operando');
    const [erro, setErro] = useState('');


    const [coletivo, setColetivo] = useState<Coletivo[]>([]);
    console.log(coletivo);

    const navigate = useNavigate();

    const onSubmit = async () => {

        await registraColetivo({
            placa: placa,
            prefixo: prefixo,
            modelo: modelo,
            cor: cor,
            doc: doc,
            status: status,
        }).then(() => {
            setPrefixo('');
            setPlaca('');
            setCor('');
            setDoc('');
            setModelo('');
            navigate(0);
            console.log("fOI");

        })
            .catch((err) => {
                setErro(err.response.data.error)
                console.log(err);
                console.log("Erro ao acessar a api");
                alert(erro);

            });


    }


    useEffect(() => {
        buscarTodosColetivos()
            .then((resp) => {
                setColetivo(resp.data);
            });
    }, []);

    const excluir = (id: number) => {

        excluirColetivo(id)
            .then(() => {
                navigate(0);
                console.log('Sucesso');
            })
            .catch(() => {
                console.log('Erro ao excluir');
                alert('Erro ao excluir');
            });

    }


    return (
        <>
            <div className='paiDeTodos'>
                <div className="cardPrincipal">
                    <h1>Registrar Coletivo</h1>
                    <Input label={'Placa:'} placeholder={'AXA-1A25'} type={'text'} name={'placa'} onChange={(e) => setPlaca(e.target.value)} />
                    <Input label={'Prefixo:'} placeholder={'21.???'} type={'text'} name={'prefixo'} onChange={(e) => setPrefixo(e.target.value)} />
                    <Input label={'Modelo:'} placeholder={'Digite o modelo do coletivo'} type={'text'} name={'modelo'} onChange={(e) => setModelo(e.target.value)} />
                    <Input label={'Cor:'} placeholder={'Digite a cordo coletivo'} type={'text'} name={'cor'} onChange={(e) => setCor(e.target.value)} />
                    <Input label={'Documento:'} placeholder={'Digite o RENAVAM do coletivo'} type={'text'} name={'documento'} onChange={(e) => setDoc(e.target.value)} />

                    <div className='inputStatus'>
                        <label htmlFor='status'>Status: </label>
                        <select name="status" onChange={(e) => setStatus(e.target.value)}>
                            <option value="operando">Operando</option>
                            <option value="Parado">Parado</option>
                            <option value="emmanutencao">Em Manutenção</option>
                        </select>
                    </div>

                    <Button name='Salvar' onClick={onSubmit} onSubmit={onSubmit} />
                </div>

                <div className="divTable">
                    <table className='tabelaColetivo'>
                        <thead>
                            <tr>
                                <td>Id</td>
                                <td>Placa</td>
                                <td>Prefixo</td>
                                <td>Modelo</td>
                                <td>Status</td>
                                <td>Botões</td>
                            </tr>
                        </thead>
                    </table>
                    <tbody>
                        {coletivo.map(coletivo => {
                            return (
                                <tr className="tdId" key={coletivo.id}>
                                    <td >{coletivo.id}</td>
                                    <td >{coletivo.placa}</td>
                                    <td >{coletivo.prefixo}</td>
                                    <td >{coletivo.modelo}</td>
                                    <td >{coletivo.status}</td>
                                    <button type='submit' onClick={() => excluir(coletivo.id)}>Excluir</button>
                                </tr>
                            )
                        })}
                    </tbody>
                </div>
            </div>
        </>
    )
}