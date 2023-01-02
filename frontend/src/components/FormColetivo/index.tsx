import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Coletivo } from '../../model/Coletivo';
import { buscarColetivoPeloPlaca, buscarColetivoPeloPrefixo, buscarTodosColetivos, editarColetivo, excluirColetivo, registraColetivo } from '../../service';
import Button from '../Button';
import Input from '../Input';
import './style.css';

export default function Formulario() {

    const [image, setImage] = useState(null);
    const [id, setId] = useState('');

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

    const location = useLocation();

    let userPrefixo = location.state.userPlaca;
    let userId = location.state.userId;                       
    

    console.log(location.state.userId);
    
    const onSubmit = async () => {

        await editarColetivo(userId, {
            placa: placa,
            prefixo: prefixo,
            modelo: modelo,
            cor: cor,
            doc: doc,
            status: status,
        }).then(() => {
            navigate('/consultar');
        })
            .catch((err) => {
                setErro(err.response.data.error)
                console.log(err);
                console.log("Erro ao acessar a api");
                alert(erro);

            });


    }

    const excluir = async () => {
        excluirColetivo(userId).then( () => {
            alert('Coletivo excluido com sucesso')
            navigate('/consultar')
        }).catch(() => {
            alert('Erro ao acessar a API')
            navigate(0)
        })
    } 


    useEffect(() => {

        buscarColetivoPeloPlaca(userPrefixo).then((resp) => {
            const dado = resp.data;
            setColetivo(resp.data);
            setPrefixo(dado.prefixo);
            setPlaca(dado.placa);
            setModelo(dado.modelo);
            setCor(dado.cor);
            setDoc(dado.doc)
            setStatus(dado.status);
        });


    }, []);


    

    
    return (
        <>


            <div className='CardFormulario'>

                <div className='imagem'>
                    <input type="file" />
                </div>

                <Input label={'Placa:'} placeholder={placa} type={'text'} name={'placa'} value={placa} onChange={(e) => setPlaca(e.target.value)} />
                <Input label={'Prefixo:'} placeholder={prefixo} type={'text'} name={'prefixo'} value={prefixo} onChange={(e) => setPrefixo(e.target.value)} />
                <Input label={'Modelo:'} placeholder={modelo} type={'text'} name={'modelo'} value={modelo} onChange={(e) => setModelo(e.target.value)} />
                <Input label={'Cor:'} placeholder={cor} type={'text'} name={'cor'} value={cor} onChange={(e) => setCor(e.target.value)} />
                <Input label={'Documento:'} placeholder={doc} type={'text'} name={'documento'} value={doc} onChange={(e) => setDoc(e.target.value)} />


                <div className='selectOperando'>
                    <label htmlFor='status'>Status: </label>
                    <select name="status"value={status} onChange={(e) => setStatus(e.target.value)}>
                        <option value="operando">Operando</option>
                        <option value="Parado">Parado</option>
                        <option value="emmanutencao">Em Manutenção</option>
                    </select>
                </div>

                <div className='botaoSalvar'>
                    <Button name='Salvar' onClick={onSubmit} onSubmit={onSubmit}/>
                </div>
                <div className='botaoExcluir'>
                    <Button name='Excluir' onClick={excluir} onSubmit={excluir}/>
                </div>


            </div>
        </>
    )
}