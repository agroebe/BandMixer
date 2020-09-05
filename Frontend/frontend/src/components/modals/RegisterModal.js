import React from 'react';
import axios from 'axios';
import { Modal, Button, Form } from 'react-bootstrap';
import './Modal.css'

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
        this.close();
    }

    render() {
        return(
            <Modal size="lg" show={ this.state.show } onHide={ this.close }>
                <Modal.Header>
                    <Modal.Title>Register An Account</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <Form>
                        <Form.Group>
                            <Form.Label>Username</Form.Label>
                            <Form.Control type="text" onChange={ e => this.setState({ username: e.target.value }) }/>
                            <Form.Text className="text-muted">A valid username must be at least 6 characters in length</Form.Text>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" onChange={ e => this.setState({ password: e.target.value }) }/>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Confirm Password</Form.Label>
                            <Form.Control type="password" onChange={ e => this.setState({ passwordConfirmation: e.target.value }) }/>
                        </Form.Group>
                    </Form>
                </Modal.Body>

                <Modal.Footer>
                    <p className="redirect mr-auto" onClick={ this.props.openSignInModal }>Already registered? Sign in here</p>
                    <Button variant="success" onClick={ this.register }>Register</Button>
                    <Button variant="danger" onClick={ this.close }>Cancel</Button>
                </Modal.Footer>
            </Modal>
        )
    }
}