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

    render() {
        return(
            <Modal size="lg" show={this.state.show} onHide={this.close}>
                <Modal.Header>
                    <Modal.Title>Sign In</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text"/>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="success">Sign In</Button>
                    <Button variant="danger" onClick={this.props.onHide}>Cancel</Button>
                </Modal.Footer>
            </Modal>
        )
    }
}