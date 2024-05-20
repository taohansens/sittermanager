'use client'

import {useEffect, useState} from "react";
import {requestBackend} from "@/util/requests";
import {AxiosRequestConfig} from "axios";
import {User} from "@/types/user";
import './../global.css';

export default function Users() {
    const [users, setUsers] = useState<User[]>([])
    const [isLoading, setIsLoading] = useState(false);

    const getUsers = () => {
        const params: AxiosRequestConfig = {
            method: 'GET',
            url: 'users',
            withCredentials: false
        };

        requestBackend(params)
            .then((response) => {
                setUsers(response.data.content);
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
                        <h1>Usuários cadastrados</h1>
                        <ul>
                            {users.map(user => (
                                <li key={user.id}>
                                    <strong>ID:</strong> {user.id}
                                    <br/>
                                    <strong>USERNAME:</strong> {user.username}
                                    <br/>
                                    <strong>EMAIL:</strong> {user.email}
                                </li>
                            ))}
                        </ul>
                    </div>
                )
            }
        </>
    )
};