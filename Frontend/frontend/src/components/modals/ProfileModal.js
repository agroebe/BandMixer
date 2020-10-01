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
            contactEmail: 'email@email.com',
            contactNumber: '1234567890',
            contactLocation: 'Ames, IA',
            bio: 'random bio',
            skill1: '1',
            skill2: '4',
            instrument1: 'Guitar',
            instrument2: 'Bass',

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
                            <h2>{this.state.username}</h2>
                            <h3>{this.state.bio}</h3>
                          </Col>
                          <Col xs={1} md={4}><Image src="holder.js/171x180" roundedCircle/></Col>
                        </Row>
                        <Row>
                          <Col>
                            <h2>Instruments:</h2>
                            <h3>{this.state.instrument1}</h3>
                            <h3>{this.state.instrument2}</h3>
                          </Col>
                          <Col>
                            <h3>Skill Level:</h3>
                            <h4>{this.state.skill1}</h4>
                            <h4>{this.state.skill2}</h4>
                          </Col>
                          <Col>
                          
                            <h2>Contact Info</h2>
                            <h3>{this.state.contactEmail}</h3>
                            <h3>{this.state.contactNumber}</h3>
                            <h3>{this.state.contactLocation}</h3>
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