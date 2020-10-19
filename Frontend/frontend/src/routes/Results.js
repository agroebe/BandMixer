import React from 'react';
import { Container, Row, Col, Form, CardDeck, Card, Badge, Button } from 'react-bootstrap';

export default class About extends React.Component {

    toInstrumentName(id) {
        switch (id) {
            case "1":
                return "Guitarists"
            case "2":
                return "Bassists"
            case "3":
                return "Drummers"
            case "4":
                return "Pianists"
            case "5":
                 return "Keyboardists"
            default:
                return "Undefined"
        }
    }

    render() {
        return(
            <Container className="mt-4">
                <Row>
                    <Col>
                        <h3>Filter Tags</h3>
                        <hr></hr>
                        <Form.Group controlId="exampleForm.ControlSelect2">
                            <Form.Label>Instrument</Form.Label>
                            <Form.Control as="select" multiple>
                                <option>Guitar</option>
                                <option>Bass</option>
                                <option>Drums</option>
                                <option>Piano</option>
                                <option>Keyboard</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect2">
                            <Form.Label>Skill Level</Form.Label>
                            <Form.Control as="select" multiple>
                                <option>Novice</option>
                                <option>Beginner</option>
                                <option>Intermediate</option>
                                <option>Skilled</option>
                                <option>Expert</option>
                                <option>Proffesional</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect2">
                            <Form.Label>Music Genre</Form.Label>
                            <Form.Control as="select" multiple>
                                <option>Rock</option>
                                <option>Hard Rock</option>
                                <option>Heavy Metal</option>
                                <option>Grunge</option>
                                <option>Blues</option>
                                <option>Jazz</option>
                                <option>Hip-hop</option>
                            </Form.Control>
                        </Form.Group>
                        <Button className="text-center m-auto">Apply Filter(s)</Button>
                    </Col>
                    <Col xs={9}>
                        <h3>Results - { this.toInstrumentName(this.props.location.state.instrument) } in { this.props.location.state.location }</h3>
                        <hr></hr>
                        <p>Results: 6 musicians found...</p>
                        <CardDeck>
                            <Card className="h-100" style={{ maxWidth: '18rem' }}>
                                <Card.Img variant="top" src="data:image/jpeg;base64,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" />
                                <Card.Body>
                                    <Card.Title>Miles Davis</Card.Title>
                                    <Card.Text>Here's my bio!</Card.Text>
                                    <p className="d-inline">Tags: </p>
                                    <Badge variant="secondary">Trumpet</Badge>
                                    <Badge variant="secondary">Jazz</Badge>
                                    <Badge variant="secondary">Blues</Badge>
                                    <Badge variant="secondary">Professional</Badge>
                                </Card.Body>
                            </Card>
                            <Card className="h-100" style={{ maxWidth: '18rem' }}>
                                <Card.Img variant="top" src="https://www.rollingstone.com/wp-content/uploads/2015/02/jimmy-page-led-zeppelin.jpg" />
                                <Card.Body>
                                    <Card.Title>Jimmy Page</Card.Title>
                                    <Card.Text>Here's my bio!</Card.Text>
                                    <p className="d-inline">Tags: </p>
                                    <Badge variant="secondary">Guitar</Badge>
                                    <Badge variant="secondary">Rock</Badge>
                                    <Badge variant="secondary">Classic Rock</Badge>
                                    <Badge variant="secondary">Metal</Badge>
                                    <Badge variant="secondary">Professional</Badge>
                                </Card.Body>
                            </Card>
                            <Card className="h-100" style={{ maxWidth: '18rem' }}>
                                <Card.Img variant="top" src="https://cdn.mos.cms.futurecdn.net/e2cb095df3a181a63f2c9cdc03d0e981-1200-80.jpg" />
                                <Card.Body>
                                    <Card.Title>Tom Scholz</Card.Title>
                                    <Card.Text>Here's my bio!</Card.Text>
                                    <p className="d-inline">Tags: </p>
                                    <Badge variant="secondary">Guitar</Badge>
                                    <Badge variant="secondary">Expert</Badge>
                                    <Badge variant="secondary">Rock</Badge>
                                </Card.Body>
                            </Card>
                        </CardDeck>
                        <CardDeck className="mt-5">
                            <Card className="h-100" style={{ maxWidth: '18rem' }}>
                                <Card.Img variant="top" src="https://www.rollingstone.com/wp-content/uploads/2019/10/kurt-cobain-unplugged-sweater-sale-again.jpg"/>
                                <Card.Body>
                                    <Card.Title>Kurt Cobain</Card.Title>
                                    <Card.Text>Here's my bio!</Card.Text>
                                    <p className="d-inline">Tags: </p>
                                    <Badge variant="secondary">Vocalist</Badge>
                                    <Badge variant="secondary">Guitar</Badge>
                                    <Badge variant="secondary">Rock</Badge>
                                    <Badge variant="secondary">Grunge</Badge>
                                    <Badge variant="secondary">Professional</Badge>
                                </Card.Body>
                            </Card>
                            <Card className="h-100" style={{ maxWidth: '18rem' }}>
                                <Card.Img variant="top" src="https://cdn.mos.cms.futurecdn.net/xCeEbUUQmtA2F4Nvc6vr2T.jpg" />
                                <Card.Body>
                                    <Card.Title>Post Malone</Card.Title>
                                    <Card.Text>Here's my bio!</Card.Text>
                                    <p className="d-inline">Tags: </p>
                                    <Badge variant="secondary">Guitar</Badge>
                                    <Badge variant="secondary">Pop</Badge>
                                    <Badge variant="secondary">Rock</Badge>
                                    <Badge variant="secondary">Vocalist</Badge>
                                    <Badge variant="secondary">Skilled</Badge>
                                </Card.Body>
                            </Card>
                            <Card className="h-100" style={{ maxWidth: '18rem' }}>
                                <Card.Img variant="top" src="https://assets.teenvogue.com/photos/5dea6976fc67030008bbdb25/16:9/w_2560%2Cc_limit/GettyImages-85830346.jpg" />
                                <Card.Body>
                                    <Card.Title>Taylor Swift</Card.Title>
                                    <Card.Text>Here's my bio!</Card.Text>
                                    <p className="d-inline">Tags: </p>
                                    <Badge variant="secondary">Guitar</Badge>
                                    <Badge variant="secondary">Professional</Badge>
                                    <Badge variant="secondary">Country</Badge>
                                    <Badge variant="secondary">Folk</Badge>
                                </Card.Body>
                            </Card>
                        </CardDeck>
                    </Col>
                </Row>
            </Container>
        )
    }
}