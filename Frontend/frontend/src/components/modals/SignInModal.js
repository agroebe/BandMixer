import React, { Component } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import axios from 'axios';
import { ToastContainer, toast, Zoom } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './Modal.css'

export default class SignInModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            loginId: '',
            password: '',
            responseExists: false,
            response: '',
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
        axios.post('http://coms-309-cy-01.cs.iastate.edu:8080/users/login?loginID=' + this.state.loginId + '&password=' + this.state.password).then(r => {
            if (r.data.includes('successful')) {
                toast.success('Welcome back to BandMixer, ' + this.state.loginId + '!', {
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
                this.props.setUserId(this.state.loginId)
    
                this.close();
            }

            this.setState({ response: r.data, responseExists: true })
        })
    }

    render() {
        return(
            <>
                <ToastContainer/>
                <Modal size="lg" show={ this.state.show } onHide={ this.close }>
                    <Modal.Header>
                        <Modal.Title>Sign In</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <Form.Group>
                            <Form.Label>Username or Email</Form.Label>
                            <Form.Control type="text" onChange={ e => this.setState({ loginId: e.target.value }) }/>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" onChange={ e => this.setState({ password: e.target.value }) }/>
                        </Form.Group>

                        { this.state.responseExists &&
                            <p className="text-danger">{ this.state.response }</p>
                        }
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