import React from 'react';
import axios from 'axios';
import { Button, Container, Row, Col, Image } from 'react-bootstrap';
//import './Modal.css'

export default class Profile extends React.Component {
    constructor() {
        //super(props);
        //console.log(props)
        super();
        this.state = {
          username: 'user',
          userID: '123',
          password: '123',
          contactEmail: 'user@gmail.com',
          phone: '123-456-7890',
          location: 'Ames',
          bio: 'example user bio',
          

          show: true

        };

        this.open = this.open.bind(this);
    }

    open() {
    
      this.fill();

      this.setState({ show: true })
    }

    fill(){
      axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/profiles/' + this.state.userID).then(r => {
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

    render() {
        return(
            <> {/*
                <Modal size="lg" show={ this.state.show } onHide={ this.close }>
                    <Modal.Header>
                        <Modal.Title>User Profile</Modal.Title>
                    </Modal.Header>

                    //<Modal.Body> */}
                      
                            <h2>{this.state.username} <Image width='100' height='100' src="https://cdn3.iconfinder.com/data/icons/vector-icons-6/96/256-512.png" roundedCircle/>
                             </h2>
                            <h3>{this.state.bio}</h3>
                          
                       { /*    <h2>Instruments:</h2>
                            <h3>{this.state.instrument1}</h3>
                            <h3>{this.state.instrument2}</h3>
                          
                            <h3>Skill Level:</h3>
                            <h4>{this.state.skill1}</h4>
                       <h4>{this.state.skill2}</h4>*/}
                         
                          
                            <h3>Contact Info</h3>
                            <h4>{this.state.contactEmail}</h4>
                            <h4>{this.state.phone}</h4>
                            <h4>{this.state.location}</h4>
                      
                 {/*           <Button variant="danger" onClick={ this.close }>Cancel</Button>
                <Button ref={ (modal) => { this.EditProfile = modal } } openEditProfile={ this.openEditProfile }/>*/ }
                       
                   {/*} </Modal.Body>

                        <Footer>
                        <Button variant="danger" onClick={ this.close }>Cancel</Button>
                      {<Button ref={ (modal) => { this.EditProfile = modal } } openEditProfile={ this.openEditProfile }/> }
                    </Footer>
               // </Modal>*/}
            </>
        )
    }
}