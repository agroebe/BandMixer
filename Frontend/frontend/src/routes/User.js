import React from 'react';
import axios from 'axios'
import { Container, Row, Col, Card, Tab, Tabs, Table } from 'react-bootstrap'
import '../Global.css'

export default class User extends React.Component {
    state = {
        user: null,
        profile: null,
        locations: [
            'N/A', 'California', 'Florida', 'Georgia', 'Illinois', 'Iowa', 'Michigan', 'Minnesota', 'New-Jersey', 'New-York', 'Pennsylvania', 'Texas', 'Washington'
        ]
    }

    componentDidMount() {// this.props.match.params.postId
        const userRequest = axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/users/' + this.props.match.params.userId)

        const profileRequest = axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/profiles/' + this.props.match.params.userId)

        axios.all([ userRequest, profileRequest ]).then(axios.spread((...responses) => {
            this.setState({ user: responses[0].data, profile: responses[1].data })
            console.log(responses[0].data)
            console.log(responses[1].data)
        })).catch(errors => {

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
                                            { this.state.profile.profilePicture !== "unset" ? ( 
                                                <Card.Img style={{ width: "100%", height: 'auto', display: "inline-block" }} variant="top" src={ 'http://coms-309-cy-01.cs.iastate.edu:8080/files/' + this.state.profile.profilePicture }></Card.Img>                                        
                                            ) : (
                                                <></>
                                            )}
                                            <Card.Body>
                                                <Card.Title>{ toTitleCase(this.state.profile.username) }</Card.Title>
                                                <Card.Text>User-ID: { this.props.match.params.userId }</Card.Text>
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
                                                                <td>{ toTitleCase(this.state.profile.username) }</td>
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
                                                                <td>Location</td>
                                                                <td>{ this.state.locations[this.state.profile.location] }</td>
                                                            </tr>
                                                        </tbody>
                                                    </Table>
                                                </Tab>
                                                <Tab eventKey="posts" title={ "Posts (" + (Object.keys(this.state.user.posts).length) + ")" }>
                                                    <br></br>
                                                    { Object.entries(this.state.user.posts).map(([key, value]) => (
                                                        <Card style={{  marginLeft: '5px', marginRight: '5px', marginBottom: '20px', marginTop: '10px' }}>
                                                            <Card.Body>
                                                            <Card.Title>{ value.title }</Card.Title>
                                                            <Card.Subtitle className="mb-2 text-muted">Posted by <a href={ '/user/' + this.props.match.params.userId }>{ toTitleCase(this.state.user.username) }</a></Card.Subtitle>
                                                            <Card.Text>
                                                                { value.textContent }
                                                            </Card.Text>
                                                            </Card.Body>
                                                            <Card.Footer>
                                                                <Card.Link href={'/post/' + value.id }>View Post</Card.Link>
                                                            </Card.Footer>
                                                        </Card>
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
