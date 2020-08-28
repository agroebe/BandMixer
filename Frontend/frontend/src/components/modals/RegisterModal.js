import React from 'react';
import { Modal, Button, Form } from 'react-bootstrap';

export default class RegisterModal extends React.Component {
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

    register() {
        console.log('Attempted register')
    }

    render() {
        return(
            <Modal size="lg" show={ this.state.show }>
                <Modal.Header>
                    <Modal.Title>Register</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" value={ this.state.value } onChange={ e => this.setState({username: e.target.value}) }/>

                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" value={ this.state.value } onChange={ e => this.setState({password: e.target.value}) }/>

                    <Form.Label>Confirm Password</Form.Label>
                    <Form.Control type="password" value={ this.state.value } onChange={ e => this.setState({passwordConfirmation: e.target.value}) }/>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="success" onClick={ this.register }>Register</Button>
                    <Button variant="danger" onClick={ this.close }>Cancel</Button>
                </Modal.Footer>
            </Modal>
        )
    }
}