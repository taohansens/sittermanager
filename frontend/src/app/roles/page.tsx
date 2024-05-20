'use client'

import {useEffect, useState} from "react";
import {requestBackend} from "@/util/requests";
import {AxiosRequestConfig} from "axios";
import '@/app/global.css';
import {Role} from "@/types/role";

export default function Users() {
    const [roles, setRoles] = useState<Role[]>([])
    const [isLoading, setIsLoading] = useState(false);

    const getUsers = () => {
        const params: AxiosRequestConfig = {
            method: 'GET',
            url: 'roles',
            withCredentials: false
        };

        requestBackend(params)
            .then((response) => {
                setRoles(response.data.content);
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
                        <h1>Roles cadastrados</h1>
                        <ul>
                            {roles.map(role => (
                                <li key={role.id}>
                                    <strong>ID:</strong> {role.id}
                                    <br/>
                                    <strong>Authority:</strong> {role.authority}
                                </li>
                            ))}
                        </ul>
                    </div>
                )
            }
        </>
    )
};