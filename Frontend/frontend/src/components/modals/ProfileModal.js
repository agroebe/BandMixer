import React, { Component } from 'react';
import { Modal, Button, Accordion, Card } from 'react-bootstrap';
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

    
    
      
      Instruments() {
        return (
          <Accordion defaultActiveKey="0">
            <Card>
              <Card.Header>
              <button
                    type="button"
                    eventKey="0"
                    style={{ backgroundColor: 'pink' }}
                   // onClick={decoratedOnClick}
                    >
                    Click me!
                    </button>
              </Card.Header>
              <Accordion.Collapse eventKey="0">
                <Card.Body onClick>Hello! I'm the body</Card.Body>
              </Accordion.Collapse>
            </Card>
            <Card>
              <Card.Header>
                      
                    <button
                    eventKey="1"
                    type="button"
                    style={{ backgroundColor: 'pink' }}
                   // onClick={decoratedOnClick}
                    >
                    Click me!
                    </button>
                
              </Card.Header>
              <Accordion.Collapse eventKey="1">
                <Card.Body>Hello! I'm another body</Card.Body>
              </Accordion.Collapse>
            </Card>
          </Accordion>
        );
      }
    render() {
        return(
            <>
                <Modal size="lg" show={ this.state.show } onHide={ this.close }>
                    <Modal.Header>
                        <Modal.Title>Profile</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                       
                    <this.Instruments />
                       
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