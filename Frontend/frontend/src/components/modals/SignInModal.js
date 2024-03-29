import React, { Component } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import axios from 'axios';
import { ToastContainer, toast, Zoom } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './Modal.css'
import cookie from 'react-cookies'

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
        this.setState({ responseExists: false, response: '', show: false })
    }

    signIn() {
        axios.post('http://coms-309-cy-01.cs.iastate.edu:8080/users/login?loginID=' + this.state.loginId + '&password=' + this.state.password + '&stayLoggedIn=false').then(r => {
            if (r.data.status.includes('success')) {
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
                this.props.setActualUserId(r.data.userId)

                cookie.save('stayLoggedIn', true, { path: '/'})
                cookie.save('userId', this.state.loginId, { path: '/'})
                cookie.save('actualUserId', r.data.userId, { path: '/'})
    
                this.close();
            } else {
                this.setState({ response: r.data.reason, responseExists: true })
            }
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

                    <hr/>
                    <p className="redirect mr-auto d-inline" onClick={ this.props.openRegisterModal }>Don't have an account? Register here</p>
                    <div className="float-right">
                        <Button variant="success" className="mr-2" onClick={ this.signIn }>Sign In</Button>
                        <Button variant="danger" onClick={ this.close }>Cancel</Button>
                    </div>
                    </Modal.Body>
                </Modal>
            </>
        )
    }
}