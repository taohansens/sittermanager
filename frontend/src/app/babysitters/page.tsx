'use client'

import {useEffect, useState} from "react";
import {requestBackend} from "@/util/requests";
import {AxiosRequestConfig} from "axios";
import '@/app/global.css';
import {Babysitter} from "@/types/babysitter";

export default function Babysitters() {
    const [babysitters, setBabysitters] = useState<Babysitter[]>([])
    const [isLoading, setIsLoading] = useState(false);

    const getUsers = () => {
        const params: AxiosRequestConfig = {
            method: 'GET',
            url: 'babysitters',
            withCredentials: false
        };

        requestBackend(params)
            .then((response) => {
                setBabysitters(response.data.content);
            })
            .catch(error => {
                console.error('Erro ao fazer requisição:', error);
            });
    }
    useEffect(() => {
        getUsers();
    }, []);

    return (
        <>
            {
                isLoading ? (
                    <div className="alert alert-danger d-flex align-items-center" role="alert">
                  <span className="spinner-border spinner-border-sm me-2"
                        role="status"></span> Aguarde, estamos carregando os usuários !
                    </div>
                ) : (
                    <div className='profile-container'>
                        <h1>Babás cadastradas</h1>
                        <ul>
                            {babysitters.map(babysitter => (
                                <li key={babysitter.id}>
                                    <strong>ID:</strong> {babysitter.id}
                                    <br/>
                                    <strong>Nome:</strong> {babysitter.name}
                                    <br/>
                                    <strong>CPF:</strong> {babysitter.cpf}
                                    <br/>
                                    <strong>Nascimento:</strong> {babysitter.dateOfBirth}
                                    <br/>
                                    <strong>Endereço:</strong> {babysitter.address}
                                    <br/>
                                    <strong>Telefone:</strong> {babysitter.phoneNumber}
                                    <br/>
                                    <strong>Email:</strong> {babysitter.email}
                                    <br/>
                                    <strong>Horas Semanais:</strong> {babysitter.weeklyHours}
                                    <br/>
                                    <strong>Salário:</strong> {babysitter.monthlySalary.toFixed(2)}
                                    <br/>
                                    <strong>Bonus:</strong> {babysitter.bonus.toFixed(2)}
                                    <br/>
                                    <strong>Anos de Exp.:</strong> {babysitter.yearsOfExperience}
                                    <br/>
                                    <strong>Distância Máxima:</strong> {babysitter.maxTravelDistance} km
                                </li>
                            ))}
                        </ul>
                    </div>
                )
            }
        </>
    )
};