import React from 'react';
import { createRoot } from 'react-dom/client';
import { Route, Link, BrowserRouter as Router, Routes, Navigate } from 'react-router-dom';
import Home from './components/Home';
import NotFound from './components/NotFound';
import { Button, Container, Nav, Navbar } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import Linije from './components/linije/Linije';
import EditLinije from './components/linije/editLinija';
import Prevoznici from './components/prevoznici/Prevoznici';
import AddPrevoznik from './components/prevoznici/PrevozniciAdd';
import Login from './authorization/Auth';
import { logout } from './services/auth';

const App = () => {


    if(window.localStorage.getItem("jwt")){
    return (
        <>
            <Router>
                <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        TEST
                    </Navbar.Brand>
                    <Nav>
                    <Nav.Link as={Link} to="/linije">
                        Linije
                    </Nav.Link>
                    <Nav.Link as={Link} to="/prevoznici">
                        Prevoznici
                    </Nav.Link>
                    <Button onClick = {logout}>Log out</Button>
                    </Nav>
            </Navbar>
            <Container style={{paddingTop:"10px"}}>
            <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="*" element={<NotFound />} />
                    <Route path="/linije" element={<Linije />} />
                    <Route path="/linije/edit/:id" element={<EditLinije />} />
                    <Route path="/prevoznici" element={<Prevoznici />} />
                    <Route path="/prevoznici/add" element={<AddPrevoznik />} />
                    <Route path="/login" element={<Login/>} />
                    <Route path="*" element={<Navigate replace to="/login" />} />
                </Routes>
            </Container>
            </Router>
        </>
    )}else{
        return (
            <>
            <Router>
                <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        TEST
                    </Navbar.Brand>
                    <Nav>
                    <Nav.Link as={Link} to="/linije">
                        Linije
                    </Nav.Link>
                    <Nav.Link as={Link} to="/login">
                        Login
                    </Nav.Link>
                    </Nav>
            </Navbar>
            <Container style={{paddingTop:"10px"}}>
            <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="*" element={<NotFound />} />
                    <Route path="/linije" element={<Linije />} />
                    <Route path="/login" element={<Login/>} />
                    <Route path="*" element={<Navigate replace to="/login" />} />
                </Routes>
            </Container>
            </Router>
        </>
        )
   
        }
};


const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
    <App />,
);
