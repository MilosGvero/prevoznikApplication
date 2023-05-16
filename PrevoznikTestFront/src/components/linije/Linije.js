import React, { useState, useEffect, useCallback } from "react";
import { Row, Col, Button, Table, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import PrevozniciAxios from "../../apis/PrevozniciAxios";
import LinijaRow from "./LinijaRow";

const Linije = () => {
  const empty_search = {
    minPrice: "",
    maxPrice: "",
    destination: "",
    prevoznik: "",
  };

  const [search, setSearch] = useState(empty_search);
  const [totalPages, setTotalPages] = useState(0);
  const [pageNo, setPageNo] = useState(0);
  const [linije, setLinije] = useState([]);
  const [prevoznici, SetPrevoznici] = useState([]);
  const [isChecked, setIsChecked] = useState(false);

  var navigation = useNavigate();

  const getLinije = (newPageNo) => {
    const conf = {
      params: {
        cenaKarteOd: search.minPrice,
        cenaKarteDo: search.maxPrice,
        destinacija: search.destination,
        prevoznikId: search.prevoznik,
        pageNo: newPageNo,
      },
    };
    PrevozniciAxios.get("/linije", conf)
      .then((result) => {
        console.log(result);
        setPageNo(newPageNo);
        setTotalPages(result.headers["total-pages"]);
        setLinije(result.data);
      })
      .catch((error) => {
        console.log(error);
        alert("Error occured please try again!");
      });
  };

  const getPrevoznici = () => {
    PrevozniciAxios.get("/prevoznici")
      .then((resp) => {
        SetPrevoznici(resp.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const reservationLinija = (linijaId) => {
    var params = {
        'linijaId': linijaId
    };

    PrevozniciAxios.post('/rezervacije', params)
    .then(res => {
        console.log(res);
       
        alert('Rezervacija was added successfully!');
        navigation('/linije'); 
        getLinije(pageNo); 
    })
    .catch(error => {
        console.log(error);
        alert('Nema mesta da se rezervise!');
     });
  };

  useEffect(() => {
    getLinije(0);
    getPrevoznici();
  }, []);

  const onInputChange = (event) => {
    const { name, value } = event.target;

    setSearch((prevSearch) => ({
      ...prevSearch,
      [name]: value,
    }));
  };

  const renderLinije = () => {
    return linije.map((linija, index) => {
      return <LinijaRow key={linija.id} linija={linija} reservationLinija={reservationLinija}></LinijaRow>;
    });
  };

  const handleCheckboxChange = () => {
    setIsChecked(!isChecked);
  };

  const renderSearchForm = () => {
    return (
      <>
        <Form style={{ width: "99%" }}>
          <Row>
            <Col>
              <Form.Group>
                <Form.Label>Cena od</Form.Label>
                <Form.Control
                  name="minPrice"
                  as="input"
                  type="number"
                  onChange={(e) => onInputChange(e)}
                  placeholder="Unesite minimalnu cenu"
                ></Form.Control>
              </Form.Group>
            </Col>

            <Col>
              <Form.Group>
                <Form.Label>Cena do</Form.Label>
                <Form.Control
                  name="maxPrice"
                  as="input"
                  type="number"
                  onChange={(e) => onInputChange(e)}
                  placeholder="Unesite maksimalnu cenu"
                ></Form.Control>
              </Form.Group>
            </Col>

            <Col>
              <Form.Group>
                <Form.Label>Destinacija</Form.Label>
                <Form.Control
                  name="destination"
                  as="input"
                  type="text"
                  onChange={(e) => onInputChange(e)}
                  placeholder="Unesite destinaciju"
                ></Form.Control>
              </Form.Group>
            </Col>
            <Col>
              <Form.Group>
                <Form.Label>Prevoznici</Form.Label>
                <Form.Select name="prevoznik" onChange={onInputChange}>
                  <option value="">Izaberi prevoznika</option>
                  {prevoznici.map((prev) => {
                    return (
                      <option value={prev.id} key={prev.id}>
                        {prev.naziv}
                      </option>
                    );
                  })}
                </Form.Select>
              </Form.Group>
            </Col>
          </Row>
        </Form>
        <Row>
          <Col>
            <Button className="mt-3" onClick={() => getLinije(0)}>
              Search
            </Button>
          </Col>
        </Row>
      </>
    );
  };

  return (
    <Col>
      <Row>
        <h1>Prikaz svih Linija</h1>
      </Row>
      <div>
      <label>
        <input
          type="checkbox"
          checked={isChecked}
          onChange={handleCheckboxChange}
        />
        Pretraga
      </label>
      </div>
      {isChecked && <div>
        <Row>{renderSearchForm()}</Row>
      </div>}
     
      <br />
      <Row>
        <Col>
          <Table id="linije-table">
            <thead>
              <tr>
                <th>Broj mesta</th>
                <th>Cena (rsd)</th>
                <th>Destinacija</th>
                <th>Prevoznik</th>
                <th>Polazak</th>
                <th></th>
              </tr>
            </thead>
            <tbody>{renderLinije()}</tbody>
          </Table>
        </Col>
      </Row>
      <Button
        disabled={pageNo === 0}
        onClick={() => getLinije(pageNo - 1)}
        className="mr-3"
      >
        Prev
      </Button>
      <Button
        disabled={pageNo === totalPages - 1}
        onClick={() => getLinije(pageNo + 1)}
      >
        Next
      </Button>
    </Col>
  );
};

export default Linije;
