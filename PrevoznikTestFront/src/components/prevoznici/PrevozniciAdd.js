import React, { useState } from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import PrevozniciAxios from "../../apis/PrevozniciAxios";
import { useNavigate } from "react-router-dom";

const AddPrevoznik = () => {
  var prevoznikInit = {
    name: "",
    adress: "",
    pib: 0,
  };

  const [prevoznik, setPrevoznik] = useState(prevoznikInit);
  var navigate = useNavigate();

  const valueInputChanged = (e) => {
    let input = e.target;

    let name = input.name;
    let value = input.value;

    setPrevoznik({ ...prevoznik, [name]: value });
    console.log(prevoznik)
}

const create = () => {
    var params = {
        'naziv': prevoznik.name,
        'adresa': prevoznik.adress,
        'pib': prevoznik.pib
    };

    PrevozniciAxios.post('/prevoznici', params)
    .then(res => {
        // handle success
        console.log(res);
       
        alert('Prevoznik was added successfully!');
        navigate('/prevoznici'); 
    })
    .catch(error => {
        // handle error
        console.log(error);
        alert('Error occured please try again!');
     });
}

  return (
    <div>
      <Row>
        <Col></Col>
        <Col xs="12" sm="10" md="8">
          <h1>Dodaj novog prevoznika</h1>
          <Form>
            <Form.Label htmlFor="name">Name:</Form.Label>
            <Form.Control
              id="name"
              type="text"
              name="name"
              onChange={(e) => valueInputChanged(e)}
            />
            <Form.Label htmlFor="adress">Adress:</Form.Label>
            <Form.Control
              id="adress"
              type="text"
              name="adress"
              onChange={(e) => valueInputChanged(e)}
            />
            <Form.Label htmlFor="pib">Pib:</Form.Label>
            <Form.Control
              id="pib"
              type="number"
              name="pib"
              onChange={(e) => valueInputChanged(e)}
            />

            <Button style={{ marginTop: "25px" }} onClick={create}  disabled={!prevoznik.name || !prevoznik.adress || !prevoznik.pib}>
              Add
            </Button>
          </Form>
        </Col>
        <Col></Col>
      </Row>
    </div>
  );
};

export default AddPrevoznik;
