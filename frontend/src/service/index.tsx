import { api } from './api';


export interface coletivo {
    placa: string;
    prefixo: string;
    modelo: string;
    cor: string;
    doc: string;
    status: string;

}

export interface blocoColetivo {
    placa: string;
    prefixo: string;
    doc: string;
}

export const  registraColetivo = (data: coletivo) => {
    const url = '/coletivo';
    return api.post(url,data);
}

export const buscarTodosColetivos = () => {
    const url = '/coletivo';
    return api.get(url);
}

export const buscarColetivoPeloPrefixo = (prefixo: string) => {
    const url = `/coletivo/${prefixo}`;
    return api.get(url);
}

export const buscarColetivoPeloPlaca = (placa: string) => {
    const url = `/coletivo/placa=${placa}`;
    return api.get(url);
}

export const buscarColetivoPeloDoc = (documento: string) => {
    const url = `/coletivo/${documento}`;
    return api.get(url);
}

export const excluirColetivo = (id: number) => {
    const url = `/coletivo/${id}`;
    return api.delete(url);
}

export const editarColetivo = (id: number, data: coletivo) => {
    const url = `/coletivo/${id}`;
    return api.put(url, data);
}