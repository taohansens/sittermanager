import {Babysitter} from "@/types/babysitter";
import '@/app/global.css';

const BabysitterCardItem = ({babysitter}: { babysitter: Babysitter }) => {
    return (
        <div className="profile-container">
            <strong>ID:</strong> {babysitter.id}
            <br/>
            <strong>Nome:</strong> {babysitter.name}
            <br/>
            <strong>CPF:</strong> {babysitter.cpf}
            <br/>
            <strong>Email:</strong> {babysitter.email}
            <br/>
            <strong>Telefone:</strong> {babysitter.phoneNumber}
            <br/>
            <strong>Idade:</strong> {babysitter.dateOfBirth}
            <br/>
            <strong>Endereço:</strong> {babysitter.address}
            <br/>
            <strong>Salário mensal:</strong> {babysitter.monthlySalary}
            <br/>
            <strong>Bônus:</strong> {babysitter.bonus}
            <br/>
            <strong>Anos de experiência:</strong> {babysitter.yearsOfExperience}
            <br/>
            <strong>Distância máxima de deslocamento:</strong> {babysitter.maxTravelDistance}
            <br/>
            <strong>Horas semanais:</strong> {babysitter.weeklyHours}
            <br/>
        </div>
    );

}
export default BabysitterCardItem;