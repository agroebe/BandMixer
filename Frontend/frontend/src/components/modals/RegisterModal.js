import React from 'react';
import axios from 'axios';
import { Modal, Button, Form } from 'react-bootstrap';
import { ToastContainer, toast, Zoom } from 'react-toastify';
import './Modal.css'

export default class RegisterModal extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            email: '',
            password: '',
            passwordConfirmation: '',
            response: '',
            responseExists: false,
            show: false
        };

        this.close = this.close.bind(this);
        this.register = this.register.bind(this);
    }

    open() {
        this.setState({ show: true })
    }

    close() {
        this.setState({ responseExists: false, response: '', show: false })
    }

    register(e) {
        e.preventDefault()

        if (this.state.password !== this.state.passwordConfirmation) {
            this.setState({ response: 'It looks like your passwords don\'t match, try confirming again!', responseExists: true })
            return;
        }

        this.setState({ responseExists: false })

        axios.post('http://coms-309-cy-01.cs.iastate.edu:8080/users/add?name=' + this.state.username + '&email=' + this.state.email + '&password=' + this.state.password).then(r => {
            if (r.data.includes('Saved')) {
                this.close();

                toast.success('Welcome to BandMixer, ' + this.state.username + '!', {
                    position: "top-center",
                    autoClose: 2500,
                    hideProgressBar: true,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    transition: Zoom
                });

                this.props.setLoggedIn(true)
                this.props.setUserId(this.state.username)
                
            } else {
                this.setState({ response: r.data, responseExists: true })
            }
        })
    }

    render() {
        return(
            <Modal size="lg" show={ this.state.show } onHide={ this.close }>
                <Modal.Header>
                    <Modal.Title>Register An Account</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <Form onSubmit={this.register}>
                        <Form.Group>
                            <Form.Label>Email</Form.Label>
                            <Form.Control required type="email" onChange={ e => this.setState({ email: e.target.value }) }/>
                            <Form.Control.Feedback type="invalid">
                                Please provide a valid state.
                            </Form.Control.Feedback>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Username</Form.Label>
                            <Form.Control required type="text" onChange={ e => this.setState({ username: e.target.value }) }/>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Password</Form.Label>
                            <Form.Control required type="password" onChange={ e => this.setState({ password: e.target.value }) }/>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Confirm Password</Form.Label>
                            <Form.Control required type="password" onChange={ e => this.setState({ passwordConfirmation: e.target.value }) }/>
                        </Form.Group>
                        { this.state.responseExists &&
                            <p className="text-danger">{ this.state.response }</p>
                        }
                        <hr></hr>
                        <p className="redirect mr-auto d-inline" onClick={ this.props.openSignInModal }>Already registered? Sign in here</p>
                        <div className="float-right">
                            <Button variant="success" type="submit" className="mr-2">Register</Button>
                            <Button variant="danger" onClick={ this.close }>Cancel</Button>
                        </div>
                    </Form>
                </Modal.Body>
            </Modal>
        )
    }
}