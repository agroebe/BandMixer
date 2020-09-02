import React, { Component } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import './Modal.css'

export default class ProfileModal extends Component {
    constructor(props) {
        super(props);
        console.log(props)
        this.state = {
            username: '',
            password: '',
            show: false
        };

        this.close = this.close.bind(this);
        this.signIn = this.signIn.bind(this);
    }

    open() {
        this.setState({ show: true })
    }

    close() {
        this.setState({ show: false })
    }

    signIn() {
        this.close();
    }

    render() {
        return(
            <>
                <Modal size="lg" show={ this.state.show } onHide={ this.close }>
                    <Modal.Header>
                        <Modal.Title>Profile</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <Form.Label>Username</Form.Label>
                        <Form.Control type="text" onChange={ e => this.setState({ username: e.target.value }) }/>

                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" onChange={ e => this.setState({ password: e.target.value }) }/>
                    </Modal.Body>

                    <Modal.Footer>
                        <p className="redirect mr-auto" onClick={ this.props.openRegisterModal }>Don't have an account? Register here</p>
                        <Button variant="success" onClick={ this.signIn }>Sign In</Button>
                        <Button variant="danger" onClick={ this.close }>Cancel</Button>
                    </Modal.Footer>
                </Modal>
            </>
        )
    }
}