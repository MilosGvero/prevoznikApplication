import React, { useCallback, useEffect, useState } from "react";
import PrevozniciAxios from "../../apis/PrevozniciAxios";
import { useParams, useNavigate } from "react-router-dom";
import { Row, Col, Form, Button } from "react-bootstrap";
import moment from "moment";

const EditLinije = () => {
  var navigate = useNavigate();
  const routeParams = useParams();
  var linijaId = routeParams.id;
  const [prevoznici, setPrevoznici] = useState([]);

  const getprevoznici = useCallback(() => {
    PrevozniciAxios.get("/prevoznici")
      .then((res) => {
        console.log(res);
        setPrevoznici(res.data);
      })
      .catch((error) => {
        console.log(error);
        alert("Error occured please try again!");
      });
  }, []);

  var linija = {
    linijaId: linijaId,
    linijaBrojMesta: -1,
    linijaCenaKarte: -1,
    linijaPolazak: new Date(),
    linijaDestinacija: "",
    linijaPrevoznik: "",
    linijaPrevoznikId: -1,
  };

  const [updateLine, setUpdateLine] = useState(linija);

  const valueInputChanged = (e) => {
    let input = e.target;
    let name = input.name;
    let value = input.value;

    let lineFromState = { ...updateLine };
    lineFromState[name] = value;
    setUpdateLine(lineFromState);
  };

  const onPrevChange = (e) => {
    const value = e.target.value;
    const selectedPrev = prevoznici.find((prev) => prev.id === parseInt(value));
    if (selectedPrev) {
      setUpdateLine((prev) => ({
        ...prev,
        linijaPrevoznikId: selectedPrev.id,
        linijaPrevoznik: selectedPrev.naziv,
      }));
    }
  };

  const getLineById = useCallback((linijaId) => {
    PrevozniciAxios.get("/linije/" + linijaId)
      .then((res) => {
        console.log(res);
        setUpdateLine((prevState) => ({
          ...prevState,
          linijaId: linijaId,
          linijaBrojMesta: res.data.brojMesta,
          linijaCenaKarte: res.data.cenaKarte,
          linijaPolazak: moment(res.data.polazak, "YYYY-MM-DD HH:mm").toDate(),
          linijaDestinacija: res.data.destinacija,
          linijaPrevoznik: res.data.prevoznikNaziv,
          linijaPrevoznikId: res.data.prevoznikId,
        }));
        console.log(updateLine);
      })
      .catch((error) => {
        console.log(error);
        alert("Error occured please try again!");
      });
  }, []);

  useEffect(() => {
    getLineById(linijaId);
    getprevoznici();
  }, []);

  const edit = (e) => {
    e.preventDefault();


    const formattedPolazak = moment(updateLine.linijaPolazak).format('YYYY-MM-DD HH:mm');

    var params = {
      brojMesta: updateLine.linijaBrojMesta,
      cenaKarte: updateLine.linijaCenaKarte,
      id: updateLine.linijaId,
      polazak: formattedPolazak,
      destinacija: updateLine.linijaDestinacija,
      prevoznikId: updateLine.linijaPrevoznikId,
      prevoznikNaziv: updateLine.linijaPrevoznik,
    };

    PrevozniciAxios.put("/linije/" + updateLine.linijaId, params)
      .then((res) => {
        console.log(res);
        alert("Line was edited successfully!");
        navigate("/linije");
      })
      .catch((error) => {
        console.log(error);
        alert("Error occured please try again!");
      });
  };

  return (
    <>
      <Row>
        <Col></Col>
        <Col xs="12" sm="10" md="8">
          <h1>Edit Line</h1>
          <Form>
            <Form.Group>
              <Form.Label htmlFor="bMesta">Broj mesta</Form.Label>
              <Form.Control
                id="bMesta"
                value={updateLine.linijaBrojMesta}
                name="linijaBrojMesta"
                type="number"
                onChange={(e) => valueInputChanged(e)}
              />{" "}
              <br />
            </Form.Group>
            <Form.Group>
              <Form.Label htmlFor="cKarte">Cena Karte</Form.Label>
              <Form.Control
                type="number"
                value={updateLine.linijaCenaKarte}
                id="cKarte"
                name="linijaCenaKarte"
                onChange={(e) => valueInputChanged(e)}
              />{" "}
              <br />
            </Form.Group>

            <Form.Group>
              <Form.Label htmlFor="polazak">Polazak</Form.Label>
              <Form.Control
                type="datetime-local"
                value={moment(updateLine.linijaPolazak).format(
                  "YYYY-MM-DDTHH:mm"
                )}
                id="polazak"
                name="linijaPolazak"
                onChange={(e) => valueInputChanged(e)}
              />
            </Form.Group>
            <Form.Group>
              <Form.Label htmlFor="destinacija">Destinacija</Form.Label>
              <Form.Control
                type="text"
                value={updateLine.linijaDestinacija}
                id="destinacija"
                name="linijaDestinacija"
                onChange={(e) => valueInputChanged(e)}
              />{" "}
              <br />
            </Form.Group>
            <Row>
              <Col>
                <Form.Group>
                  <Form.Label>Prevoznici</Form.Label>
                  <Form.Select name="id" onChange={(e) => onPrevChange(e)}>
                    {prevoznici.map((prev) => {
                      return (
                        <option
                          key={prev.id}
                          value={prev.id}
                          selected={prev.id === updateLine.linijaPrevoznikId}
                        >
                          {prev.naziv}
                        </option>
                      );
                    })}
                  </Form.Select>
                </Form.Group>
              </Col>
            </Row>
            <Button type="submit" onClick={edit}>
              Edit
            </Button>
          </Form>
        </Col>
        <Col></Col>
      </Row>
    </>
  );
};

export default EditLinije;
