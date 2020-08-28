import React from 'react';
import { Navbar, Button } from 'react-bootstrap';
import SignInModal from './modals/SignInModal'
import RegisterModal from './modals/RegisterModal'

export default class NavBar extends React.Component {
    render() {
        return(
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand>BandMixer</Navbar.Brand>f
                <Navbar.Collapse className="justify-content-end">
                    <Button variant="primary" className="mr-2" onClick={ () => this.signInModal.open() }>Sign In</Button>
                    <Button variant="secondary" onClick={ () => this.registerModal.open() }>Register</Button>
                </Navbar.Collapse>
                <SignInModal ref={ (modal) => { this.signInModal = modal } }/>
                <RegisterModal ref={ (modal) => { this.registerModal = modal } }/>
            </Navbar>
        )
    }
}