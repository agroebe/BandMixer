import React from 'react';
import axios from 'axios'
import { Container, Row, Col, Card, Tab, Tabs, Table } from 'react-bootstrap'
import '../Global.css'

export default class User extends React.Component {
    state = {
        user: null,
        profile: null,
        done: 'f',
        imageSet: 'f',
        imageSrc: "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        locationOptions: [
            'Select-Location', 'California', 'Florida', 'Georgia', 'Illinois', 'Iowa', 'Michigan', 'Minnesota', 'New-Jersey', 'New-York', 'Pennsylvania', 'Texas', 'Washington'
        ]
    }
    componentDidMount() {// this.props.match.params.postId
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/users/' + this.props.match.params.userId).then(r => {
            this.setState({ user: r.data })
        })
    }

    render() {
        return(
            <>
                { this.state.user ? (
                        <>
                            <Container className="text-center mt-4">
                                <Row>
                                    <Col xs={3}>
                                        <Card className="text-center">
                                            {
                                                this.state.imageSet === 't'?
                                                    <Card.Img style={{ width: "100%", height: 'auto', display: "inline-block" }} variant="top" src={'http://coms-309-cy-01.cs.iastate.edu:8080/files/' + this.state.imageSrc}></Card.Img>                                        
                                                :''
                                            }
                                            
                                            <Card.Body>
                                                <Card.Title>{ toTitleCase(this.state.user.username) }</Card.Title>
                                                <Card.Text>User-ID: { this.state.user.id }</Card.Text>
                                            </Card.Body>
                                        </Card>
                                    </Col>
                                    <Col xs={9}>
                                        <Card>
                                        <Card.Header>
                                            <Tabs defaultActiveKey="info" transition={false}>
                                                <Tab eventKey="info" title="Info">
                                                    <Table className="mt-4" striped bordered hover>
                                                        <tbody>
                                                            <tr>
                                                                <td>Username</td>
                                                                <td>{ toTitleCase(this.state.user.username) }</td>
                                                            </tr>
                                                            <tr>
                                                                <td>User-ID</td>
                                                                <td>{ this.state.user.id }</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Email</td>
                                                                <td>{ this.state.user.email }</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Phone Number</td>
                                                                <td>{ 
                                                                    this.state.done === 't'?
                                                                        this.state.profile.phoneNumber 
                                                                    :'Not Set'
                                                                }</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Location</td>
                                                                <td>{ 
                                                                    this.state.done === 't'?
                                                                        this.state.locationOptions[this.state.profile.location]
                                                                    :'Not Set'
                                                                }</td>
                                                            </tr>
                                                        </tbody>
                                                    </Table>
                                                </Tab>
                                                <Tab eventKey="posts" title={ "Posts (" + (Object.keys(this.state.user.posts).length - 1) + ")" }>
                                                    <br></br>
                                                    { Object.entries(this.state.user.posts).map(([key, value]) => (
                                                       value.title != 'unset'?
                                                        <Card style={{  marginLeft: '5px', marginRight: '5px', marginBottom: '20px', marginTop: '10px' }}>
                                                            <Card.Body> 
                                                            <Card.Title>{ value.title }</Card.Title>
                                                            <Card.Subtitle className="mb-2 text-muted">Posted by <a href={ '/user/' + this.props.match.params.userId }>{ toTitleCase(this.state.user.username) }</a></Card.Subtitle>
                                                            <Card.Text>
                                                                { value.textContent }
                                                            </Card.Text>
                                                            </Card.Body>
                                                            {
                                                               value.contentType === "profilePicture"?
                                                                    this.state.imageSet === 'f'?
                                                                        this.setState({ imageSrc: value.contentPath , imageSet: 't' })
                                                                    :''
                                                               :''
                                                           }
                                                            <Card.Footer>
                                                                <Card.Link href={'/post/' + value.id }>View Post</Card.Link>
                                                            </Card.Footer>
                                                           
                                                        </Card>
                                                        
                                                        
                                                        :value.contentType === "Profile"?
                                                                 this.state.done === 'f'?
                                                                     this.setState({ profile: value , done: 't' })
                                                                 :''
                                                            :''
                                                        
                                                    ))}
                                                </Tab>
                                            </Tabs>
                                        </Card.Header>
                                        </Card>
                                    </Col>
                                </Row>
                            </Container>
                        </>
                    ) : (
                        <p>Loading...</p>
                    )}
            </>
        )
    }
}
/*
function getProf(info){
    if(info.contentType === "Profile"){
        return true;
    }
    return false;
}*/

function toTitleCase(str) {
    if (str == null) {
        return "Select a location..."
    }
    return str.replace(
      /\w\S*/g,
      function(txt) {
        return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
      }
    );
}