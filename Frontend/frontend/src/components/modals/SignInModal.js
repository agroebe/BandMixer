import React, { Component } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';

export default class SignInModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            passwordConfirmation: '',
            show: false
        };

        this.close = this.close.bind(this);
    }

    open() {
        this.setState({ show: true })
    }

    close() {
        this.setState({ show: false })
    }

    signIn() {
        console.log('Attempted sign in')
    }

    render() {
        return(
            <Modal size="lg" show={ this.state.show }>
                <Modal.Header>
                    <Modal.Title>Sign In</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text"/>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="success" onClick={ this.signIn }>Sign In</Button>
                    <Button variant="danger" onClick={ this.close }>Cancel</Button>
                </Modal.Footer>
            </Modal>
        )
    }
}