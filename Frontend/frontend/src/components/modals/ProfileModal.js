import React, { Component } from 'react';
import { Modal, Button, Container, Row, Col, Image } from 'react-bootstrap';
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
                        <Modal.Title>User Profile</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                      <Container>
                        <Row>
                          <Col>
                            <h1>Username</h1>
                            <h2>Singer in random band</h2>
                          </Col>
                          <Col xs={1} md={4}><Image src="holder.js/171x180" roundedCircle/></Col>
                        </Row>
                        <Row>
                          <Col>
                            <h2>Instruments:</h2>
                            <h3>Guitar</h3>
                            <h3>Bass</h3>
                          </Col>
                          <Col>
                            <h3>Skill Level:</h3>
                            <h4>3</h4>
                            <h4>5</h4>
                          </Col>
                          <Col>
                            <h2>Contact:</h2>
                            <h3>sample@gmail.com</h3>
                            <h3>123-456-7890</h3>
                          </Col>
                        </Row>
                      </Container>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="danger" onClick={ this.close }>Cancel</Button>
                      {<ProfileModal ref={ (modal) => { this.EditProfileModal = modal } } openEditProfileModal={ this.openEditProfileModal }/> }
                    </Modal.Footer>
                </Modal>
            </>
        )
    }
}