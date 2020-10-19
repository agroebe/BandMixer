import React, { Component } from 'react';
import axios from 'axios';
import { Modal, Button, ButtonGroup, Container, Row, Col, Form, InputGroup, FormControl } from 'react-bootstrap';
import './Modal.css'

export default class EditProfileModal extends Component {
    constructor(props) {
        super(props);
        console.log(props)
        this.state = {
            username: '',
            userID: '',
            password: '',
            contactEmail: '',
            number: '',
            location: '',
            bio: '',
            guitar: true,
            bass: false,
            piano: false,
            singer: false,
            skillG: '4',
            

            show: false
        };

        this.close = this.close.bind(this);
        this.signIn = this.signIn.bind(this);
    }

    open() {
      /*  axios.post('http://coms-309-cy-01.cs.iastate.edu:8080/users/userID?userID=' + this.state.userID + '&password=' + this.state.password).then(r => {
            if (r.data.includes('successful')) {


               //set var function call                        
                this.fill();

                this.setState({ show: true })
            } else {
                this.close();
            }
        })*/
       this.fill();

        this.setState({ show: true })
    }

   fill(){
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/profiles/14').then(r => {
            //console.log(r.data)
            this.setState({ username: r.data.username, location: r.data.location, phone: r.data.phoneNumber })
        }
        )   
    }

    close() {
        this.setState({ show: false })
    }

    signIn() {
        this.close();
    }

    save(){
        this.close();
    }

    guitarVisible() {
        this.setState({
            guitar: !this.state.guitar
        })
    }

    bassVisible() {
        this.setState({
            bass: !this.state.bass
        })
    }

    singerVisible() {
        this.setState({
            singer: !this.state.singer
        })
    }

    pianoVisible() {
        this.setState({
            piano: !this.state.piano
        })
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
                                    <Form.Label>{this.state.username}</Form.Label>
                                    <Form.Control type="username" placeholder="enter new username" />
                                    
                                </Form.Group>

                                <Form.Group controlId="formBasicBio">
                                    <Form.Label>{this.state.bio}</Form.Label>
                                    <Form.Control type="bio" placeholder="Bio" />
                                </Form.Group>
                            

                               
                                </Form>
                              </Col>
                        <Col xs={6} md={4}>
                          <Button variant="primary">Change Picture</Button>
                        </Col>
                      
                      </Row>
                      <Row>
                        <Col>
                        <ButtonGroup className="mr-2" aria-label="Instruments">
                          <Button variant="secondary" onClick={()=>this.guitarVisible()}>Guitar</Button>{' '}
                          <Button variant="secondary" onClick={()=>this.pianoVisible()}>Piano</Button>{' '}
                          <Button variant="secondary" onClick={()=>this.bassVisible()}>Bass</Button>{' '}
                          <Button variant="secondary" onClick={()=>this.singerVisible()}>Singer</Button>
                        </ButtonGroup>
                        </Col>
                        <Col>
                            
                        </Col>
                        <Col>
                            <h3>Edit Contact Info</h3>
                            <InputGroup className="mb-3">
                                <InputGroup.Prepend>
                                <InputGroup.Text id="basic-addon1">Email</InputGroup.Text>
                                </InputGroup.Prepend>
                                <FormControl
                                placeholder={this.state.email}
                                aria-label={this.state.email}
                                aria-describedby="basic-addon1"
                                />
                            </InputGroup>
                            <InputGroup className="mb-4">
                                <InputGroup.Prepend>
                                <InputGroup.Text id="basic-addon1">Phone #</InputGroup.Text>
                                </InputGroup.Prepend>
                                <FormControl
                                placeholder={this.state.phone}
                                aria-label={this.state.phone}
                                aria-describedby="basic-addon1"
                                />
                            </InputGroup>
                            <InputGroup className="mb-5">
                                <InputGroup.Prepend>
                                <InputGroup.Text id="basic-addon1">Location</InputGroup.Text>
                                </InputGroup.Prepend>
                                <FormControl
                                placeholder={this.state.location}
                                aria-label={this.state.location}
                                aria-describedby="basic-addon1"
                                />
                            </InputGroup>
                        </Col>
                      </Row>
                      <Row>

                            
                                {
                                    this.state.guitar?
                                    <div>
                                        Guitar:
                                        <InputGroup className="mb-5">
                                            <InputGroup.Prepend>
                                            <InputGroup.Text id="basic-addon1"> Skill Level</InputGroup.Text>
                                            </InputGroup.Prepend>
                                            <FormControl
                                            placeholder={this.state.skillG}
                                            aria-label={this.state.skillG}
                                            aria-describedby="basic-addon1"
                                            />
                                        </InputGroup>
                                    </div>
                                    :null
                                }
                                {
                                    this.state.bass?
                                    <div>
                                        Bass:
                                        <InputGroup className="mb-5">
                                            <InputGroup.Prepend>
                                            <InputGroup.Text id="basic-addon1"> Skill Level</InputGroup.Text>
                                            </InputGroup.Prepend>
                                            <FormControl
                                            placeholder={this.state.skillB}
                                            aria-label={this.state.skillB}
                                            aria-describedby="basic-addon1"
                                            />
                                        </InputGroup>
                                    </div>
                                    :null
                                }
                                {
                                    this.state.singer?
                                    <div>
                                        Singer:
                                        <InputGroup className="mb-5">
                                            <InputGroup.Prepend>
                                            <InputGroup.Text id="basic-addon1"> Skill Level</InputGroup.Text>
                                            </InputGroup.Prepend>
                                            <FormControl
                                            placeholder={this.state.skillS}
                                            aria-label={this.state.skillS}
                                            aria-describedby="basic-addon1"
                                            />
                                        </InputGroup>
                                    </div>
                                    :null
                                }
                                {
                                    this.state.piano?
                                    <div>
                                        Piano:
                                        <InputGroup className="mb-5">
                                            <InputGroup.Prepend>
                                            <InputGroup.Text id="basic-addon1"> Skill Level</InputGroup.Text>
                                            </InputGroup.Prepend>
                                            <FormControl
                                            placeholder={this.state.skillP}
                                            aria-label={this.state.skillP}
                                            aria-describedby="basic-addon1"
                                            />
                                        </InputGroup>
                                    </div>
                                    :null
                                }
                            

                      </Row>
                      
            
                      </Container>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="success" onClick={this.save}>Save Changes</Button>
                        <Button variant="danger" onClick={ this.close }>Cancel</Button>
                    </Modal.Footer>
                </Modal>
            </>
        )
    }
}