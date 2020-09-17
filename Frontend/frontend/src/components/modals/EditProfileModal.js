import React, { Component } from 'react';
import { Modal, Button, ButtonGroup, Container, Row, Col, Image } from 'react-bootstrap';
import './Modal.css'

export default class EditProfileModal extends Component {
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
                        <Modal.Title>Edit Profile</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                    <Container>
                      <Row>
                        <Col> 
                            <Form>
                                <Form.Group controlId="formBasicEmail">
                                    <Form.Label>Edit Username</Form.Label>
                                    <Form.Control type="username" placeholder="enter new username" />
                                    
                                </Form.Group>

                                <Form.Group controlId="formBasicBio">
                                    <Form.Label>Band Bio</Form.Label>
                                    <Form.Control type="bio" placeholder="Bio" />
                                </Form.Group>
                            

                                <Button variant="primary" type="submit">
                                    Submit
                                </Button>
                                </Form>
                              </Col>
                        <Col xs={6} md={4}>
                          <Button variant="primary">Change Picture</Button>
                        </Col>
                      
                      </Row>
                      <Row>
                        <Col>
                        <ButtonGroup className="mr-2" aria-label="Instruments">
                          <Button variant="secondary">Guitar</Button>{' '}
                          <Button variant="secondary">Piano</Button>{' '}
                          <Button variant="secondary">Bass</Button>{' '}
                          <Button variant="secondary">Singer</Button>
                        </ButtonGroup>
                        </Col>
                        <Col>

                        </Col>
                        <Col>
                          <h2>Contact</h2>
                          <h3>sample@gmail.com</h3>
                          <h3>123-456-7890</h3>
                        </Col>
                      </Row>
                      
                    
                      </Container>
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