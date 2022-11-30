import { api } from './api';


export interface coletivo {
    placa: string;
    prefixo: string;
    modelo: string;
    cor: string;
    doc: string;
    status: string;

}

export const  registraColetivo = (data: coletivo) => {
    const url = '/coletivo';
    return api.post(url,data);
}

export const buscarTodosColetivos = () => {
    const url = '/coletivo';
    return api.get(url);
}

export const excluirColetivo = (id: number) => {
    const url = `/coletivo/${id}`;
    return api.delete(url);
}