import React from 'react';
import { Navbar, Button } from 'react-bootstrap';
import SignInModal from './modals/SignInModal'
import RegisterModal from './modals/RegisterModal'

export default class NavBar extends React.Component {
    constructor(props) {
        super(props);

        this.openSignInModal = this.openSignInModal.bind(this);
        this.openRegisterModal = this.openRegisterModal.bind(this);
    }

    /* This function will be passed in as a prop so it can be opened from the RegisterModal. */
    openSignInModal() {
        this.registerModal.close();
        this.signInModal.open();
    }

    /* This function will be passed in as a prop so it can be opened from the SignInModal. */
    openRegisterModal() {
        this.signInModal.close();
        this.registerModal.open()
    }

    render() {
        return(
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand>BandMixer</Navbar.Brand>
                <Navbar.Collapse className="justify-content-end">
                    <Button variant="primary" className="mr-2" onClick={ this.openSignInModal }>Sign In</Button>
                    <Button variant="secondary" onClick={ this.openRegisterModal }>Register</Button>
                </Navbar.Collapse>

                { /* Modals for registering and signing in. */ }
                <RegisterModal ref={ (modal) => { this.registerModal = modal } } openSignInModal={ this.openSignInModal }/>
                <SignInModal ref={ (modal) => { this.signInModal = modal } } openRegisterModal={ this.openRegisterModal }/>

            </Navbar>
        )
    }
}