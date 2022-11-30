import logo from "../../assets/logotipo_para_empresa_de_tecnologia-removebg-preview 1.svg";
import { Nav, NavBtn, Bars, NavBtnLink, NavLink, NavMenu } from './NavElments';


export default function NavBar() {
    return (
        <>
            <Nav>
                <NavLink to="/">
                    <img src={logo} alt="logo" />
                </NavLink>
                <Bars />
                <NavMenu>
                    <NavLink to="/Registrar" activeStyle>
                        Cadastrar
                    </NavLink>
                    <NavLink to="/Consultar" activeStyle>
                        Consulta
                    </NavLink>
                </NavMenu>

                <NavBtn>
                    <NavBtnLink to="/drawer">Sign in</NavBtnLink>
                </NavBtn>

            </Nav>
        </>

    )
}