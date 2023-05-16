import React, { useCallback, useEffect, useState } from "react";
import { Table, Button } from "react-bootstrap";
import PrevozniciAxios from "../../apis/PrevozniciAxios";
import { useNavigate } from "react-router-dom";

const Prevoznici = () => {
  const [prevoznici, setPrevoznici] = useState([]);
  var navigate = useNavigate();

  const getprevoznici = useCallback(() => {
    PrevozniciAxios.get("/prevoznici")
      .then((res) => {
        // handle success
        console.log(res);
        setPrevoznici(res.data);
      })
      .catch((error) => {
        // handle error
        console.log(error);
        alert("Error occured please try again!");
      });
  }, []);

  useEffect(() => {
    getprevoznici();
  }, []);

  const goToAdd = () => {
    navigate("/prevoznici/add");
  };

  const renderPrevoznici = () => {
    return prevoznici.map((prevoznik) => {
      return (
        <tr key={prevoznik.id}>
          <td>{prevoznik.naziv}</td>
          <td>{prevoznik.adresa}</td>
          <td>{prevoznik.pib}</td>
        </tr>
      );
    });
  };

  return (
    <div>
      <h1>Prevoznici</h1>
      {window.localStorage.getItem("role") == "ROLE_ADMIN" ? (
        <Button onClick={() => goToAdd()}>Add</Button>
      ) : null}
      <br />
      <div>
        <Table style={{ marginTop: 5 }}>
          <thead>
            <tr>
              <th>Name</th>
              <th>Adress</th>
              <th>PIB</th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>{renderPrevoznici()}</tbody>
        </Table>
      </div>
    </div>
  );
};

export default Prevoznici;
