import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { registraColetivo } from '../../service';
import Button from '../Button';
import Input from '../Input';
import './style.css';

export default function FormularioRegistrar() {

    const [image, setImage] = useState(null);
    const [id, setId] = useState('');

    const [placa, setPlaca] = useState('');
    const [prefixo, setPrefixo] = useState('');
    const [modelo, setModelo] = useState('');
    const [cor, setCor] = useState('');
    const [doc, setDoc] = useState('');
    const [status, setStatus] = useState('Operando');
    const [erro, setErro] = useState('');

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

     


    }, []);





    return (
        <>


            <div className='CardFormulario'>

                <div className='imagem'>
                    <input type="file" />
                </div>

                <Input label={'Placa:'} placeholder={'AXA-1A25'} type={'text'} name={'placa'} onChange={(e) => setPlaca(e.target.value)} />
                <Input label={'Prefixo:'} placeholder={'21.???'} type={'text'} name={'prefixo'} onChange={(e) => setPrefixo(e.target.value)} />
                <Input label={'Modelo:'} placeholder={'Digite o modelo do coletivo'} type={'text'} name={'modelo'} onChange={(e) => setModelo(e.target.value)} />
                <Input label={'Cor:'} placeholder={'Digite a cordo coletivo'} type={'text'} name={'cor'} onChange={(e) => setCor(e.target.value)} />
                <Input label={'Documento:'} placeholder={'Digite o RENAVAM do coletivo'} type={'text'} name={'documento'} onChange={(e) => setDoc(e.target.value)} />

                <div className='selectOperando'>
                    <label htmlFor='status'>Status: </label>
                    <select name="status"  onChange={(e) => setStatus(e.target.value)}>
                        <option value="operando">Operando</option>
                        <option value="Parado">Parado</option>
                        <option value="emmanutencao">Em Manutenção</option>
                    </select>
                </div>

                    <Button name='Adicionar' onClick={onSubmit} onSubmit={onSubmit} />


            </div>
        </>
    )
}