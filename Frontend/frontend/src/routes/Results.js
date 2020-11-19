import axios from 'axios';
import React from 'react';
import { Container, Row, Col, Form, CardDeck, Card, Badge, Button } from 'react-bootstrap';

export default class About extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            results: [],
            selectedInstruments: [],
            selectedSkillLevels: [],
            selectedGenres: []
        }
    }

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

    componentWillMount() {
        // const query = {
        //     "@type": "post",
        //     "child": {
        //         "@type": "application",
        //         "tag": "expert",
        //         "operation": "equal",
        //         "skill": "unset"
        //     }
        // }

        fetch("http://coms-309-cy-01.cs.iastate.edu:8080/search/post/", {
            body: JSON.stringify({
                "@type": "post",
                "child": {
                    "@type": "application",
                    "tag": "expert",
                    "operation": "equal",
                    "skill": "unset"
                }
            }),
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
        }).then(response => {
            return response.json()
        }).then(posts => {
            console.log(posts)
            this.setState({ results: posts })
        })
    }

    instrumentChange(selectedOptions) {
        const selectedInstruments = []
        Object.keys(selectedOptions).forEach(instrument => (
            selectedInstruments.push(selectedOptions[instrument].label)
        ))
        this.setState({ selectedInstruments : selectedInstruments})
    }

    skillLevelChange(selectedOptions) {
        const selectedSkillLevels = []
        Object.keys(selectedOptions).forEach(skillLevel => (
            selectedSkillLevels.push(selectedOptions[skillLevel].label)
        ))
        this.setState({ selectedSkillLevels : selectedSkillLevels})
    }

    genreChange(selectedOptions) {
        const selectedGenres = []
        Object.keys(selectedOptions).forEach(genre => (
            selectedGenres.push(selectedOptions[genre].label)
        ))
        this.setState({ selectedGenres : selectedGenres})
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
                            <Form.Control as="select" multiple onChange={ e => this.instrumentChange(e.target.selectedOptions)}>
                                <option>Guitar</option>
                                <option>Bass</option>
                                <option>Drums</option>
                                <option>Piano</option>
                                <option>Keyboard</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect2">
                            <Form.Label>Skill Level</Form.Label>
                            <Form.Control as="select" multiple onChange={ e => this.skillLevelChange(e.target.selectedOptions)}>
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
                            <Form.Control as="select" multiple onChange={ e => this.genreChange(e.target.selectedOptions)}>
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
                        { this.state.results.length }
                        { this.state.results.length <= 0 ? (
                            <p>No results found.</p>
                        ) : (
                            <>
                                <p>{ this.state.results.length } results found for search.</p>
                                { this.state.results.map(post => (
                                    <>
                                    <Card style={{ width: '50rem', marginLeft: 'auto', marginRight: 'auto', marginBottom: '10px', marginTop: '10px' }}>
                                        <Card.Body>
                                        <Card.Title>{ post.title }</Card.Title>
                                        <Card.Subtitle className="mb-2 text-muted">Posted by <a href={ '/user/' + post.owner.id }>{ toTitleCase(post.owner.username) }</a></Card.Subtitle>
                                        <Card.Text>
                                            { post.textContent }
                                        </Card.Text>
                                        <Card.Link href={'/post/' + post.id }>View Post</Card.Link>
                                        </Card.Body>
                                    </Card>
                                </>
                                ))}
                            </>
                        )}
                    </Col>
                </Row>
            </Container>
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