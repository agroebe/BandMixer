import React from 'react';
import axios from 'axios'
import { Container, Row, Col, Card, CardDeck, Image } from 'react-bootstrap'
import '../Global.css'

export default class User extends React.Component {
    state = {
        user: null
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
                                            <Card.Img style={{ width: "100%", height: 'auto', display: "inline-block" }} variant="top" src="https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"></Card.Img>                                        
                                            <Card.Body>
                                                <Card.Title>{ this.state.user.username }</Card.Title>
                                                <Card.Text>User-ID: { this.state.user.id }</Card.Text>
                                            </Card.Body>
                                        </Card>
                                    </Col>
                                    <Col xs={9}>
                                        <Card>
                                            <Card.Text>
                                                <Card.Title>Posts from { this.state.user.username } ({ Object.keys(this.state.user.posts).length })</Card.Title>
                                                    { Object.entries(this.state.user.posts).map(([key, value]) => (
                                                        <Card style={{  marginLeft: '5px', marginRight: '5px', marginBottom: '10px', marginTop: '10px' }}>
                                                            <Card.Body>
                                                            <Card.Title>{ value.title }</Card.Title>
                                                            <Card.Subtitle className="mb-2 text-muted">Posted by { this.state.user.username }</Card.Subtitle>
                                                            <Card.Text>
                                                                { value.textContent }
                                                            </Card.Text>
                                                            </Card.Body>
                                                            <Card.Footer>
                                                                <Card.Link href={'/post/' + value.id }>View Post</Card.Link>
                                                            </Card.Footer>
                                                        </Card>
                                                    ))}
                                            </Card.Text>
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