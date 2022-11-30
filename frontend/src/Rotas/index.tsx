import { BrowserRouter as Router, Route, Routes, } from 'react-router-dom';
import NavBar from '../components/Navbar';
import Consultar from '../components/pages/ConsultaColetivo';
import Home from '../components/pages/Home';
import Registrar from '../components/pages/RegistrarColetivo';

export default function Rotas() {
    return (
        <>
            <Router>
                <NavBar />
                <Routes>
                    <Route path='/' element={<Home />} />
                    <Route path='/Registrar' element={<Registrar />} />
                    <Route path='/Consultar' element={<Consultar />} />
                </Routes>
            </Router>
        </>
    )
}