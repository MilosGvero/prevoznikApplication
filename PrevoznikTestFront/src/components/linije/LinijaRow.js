import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import PrevozniciAxios from "../../apis/PrevozniciAxios";

const LinijaRow = (props) => {

    var navigate = useNavigate()


    const goToEdit = (linijeId) => {
        navigate('/linije/edit/' + linijeId); 
    }


    const deleteLinije = (linijeId) => {
        PrevozniciAxios.delete('/linije/' + linijeId)
        .then(res => {
            // handle success
            console.log(res);
            alert('Line was deleted successfully!');
            window.location.reload();
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }


    return (
        <tr key={props.linija.id}>
            <td>{props.linija.brojMesta}</td>
            <td>{props.linija.cenaKarte} rsd</td>
            <td>{props.linija.destinacija}</td>
            <td>{props.linija.prevoznikNaziv}</td>
            <td>{props.linija.polazak}</td>
            
            {window.localStorage.getItem("role")=="ROLE_ADMIN"?
            [<td><Button className="button button-navy" onClick={() => deleteLinije(props.linija.id)}>Delete</Button></td>,
            <td><Button className="button button-navy" onClick={() => goToEdit(props.linija.id)}>Edit</Button></td>]:null}
            <td><Button className="button button-navy" onClick={() => props.reservationLinija(props.linija.id)}>Reservation</Button></td>
        </tr>
    )
}

export default LinijaRow;