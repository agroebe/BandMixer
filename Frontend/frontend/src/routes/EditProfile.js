import React from 'react';
import axios from 'axios';
import { toast, Zoom } from 'react-toastify';
import { Button, Container, Row, Col, Image, Form, FormControl, InputGroup } from 'react-bootstrap';
//import './Modal.css'

export default class EditProfile extends React.Component {
    constructor() {
        //super(props);
        //console.log(props)
        super();
        this.state = {
          username: 'user',
          userID: '12',
          password: '123',
          email: 'user@gmail.com',
          phone: '123-456-7890',
          location: 'Ames',
          bio: 'example user bio',
          

          show: true

        };

        this.open = this.open.bind(this);
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
          axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/profiles/' + this.state.userID + '/').then(r => {
  
              this.setState({ username: r.data.username, userID: r.data.userID, location: r.data.location, phone: r.data.phoneNumber })
             
          })
          
      }
  
      close() {
          this.setState({ show: false })
      }
  
      signIn() {
          this.close();
      }
  
      save(){
        /*  axios.post('http://coms-309-cy-01.cs.iastate.edu:8080/profiles/' + this.state.userID + '&=username' + this.state.username + '&location=' + this.state.location, '&phone=' + this.state.phone).then(r => {
              if (r.data.includes('Saved')) {
  
                  toast.success(this.state.username + ' your profile was updated!', {
                      position: "top-center",
                      autoClose: 2500,
                      hideProgressBar: true,
                      closeOnClick: true,
                      pauseOnHover: true,
                      draggable: true,
                      progress: undefined,
                      transition: Zoom
                  });
              }
          })*/
          toast.success(this.state.username + ' your profile was updated!', {
            position: "top-center",
            autoClose: 2500,
            hideProgressBar: true,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            transition: Zoom
        });
      }
    render() {
        return(
            <> {/*
                <Modal size="lg" show={ this.state.show } onHide={ this.close }>
                    <Modal.Header>
                        <Modal.Title>User Profile</Modal.Title>
                    </Modal.Header>

                    //<Modal.Body> */}
                      <h2>Edit Profile <Image width='100' height='100' src="https://cdn3.iconfinder.com/data/icons/vector-icons-6/96/256-512.png" roundedCircle/></h2>
                      <h3><Form>
                                <Form.Group controlId="formBasicEmail">
                                    <Form.Label>{"Username:"}</Form.Label>
                                    <Form.Control type="username" placeholder={this.state.username} />
                                    
                                </Form.Group>

                                <Form.Group controlId="formBasicBio">
                                    <Form.Label>{"Bio:"}</Form.Label>
                                    <Form.Control type="bio" placeholder={this.state.bio} />
                                </Form.Group>


                               
                                </Form>
                                <Button variant="primary">Change Picture</Button>
                        </h3>
                        <h3>Edit Contact Info</h3>
                        <h4>
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
                                </h4>
                                <h3><Button onClick={()=>this.save()}variant="primary">Save Changes</Button></h3>
                          
                       { /*    <h2>Instruments:</h2>
                            <h3>{this.state.instrument1}</h3>
                            <h3>{this.state.instrument2}</h3>
                          
                            <h3>Skill Level:</h3>
                            <h4>{this.state.skill1}</h4>
                       <h4>{this.state.skill2}</h4>*/}
                         
                          
                            
                      
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