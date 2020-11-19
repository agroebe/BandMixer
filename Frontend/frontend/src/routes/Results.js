import React from 'react';
import { Container, Row, Col, Form, Card, Button } from 'react-bootstrap';

export default class About extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            results: [],
            selectedInstruments: [],
            selectedSkillLevels: [],
            selectedGenres: []
        }

        this.filter = this.filter.bind(this);
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
        fetch("http://coms-309-cy-01.cs.iastate.edu:8080/search/post/", {
            body: JSON.stringify({
                "@type": "post",
                "child": {
                    "@type": "and",
                    "children": [
                        {
                            "@type": "application",
                            "tag": "expert",
                            "operation": "equal",
                        },
                        {
                            "@type": "application",
                            "tag": "guitar",
                            "operation": "equal",
                        }
                    ]
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

  filter() {
        if (this.state.selectedInstruments.length === 0 && this.state.selectedSkillLevels.length === 0 && this.state.selectedGenres.length === 0) {
            alert('Please select at least one filter!')
            return
        }

        if (this.state.selectedInstruments.length + this.state.selectedSkillLevels.length + this.state.selectedGenres.length === 1) {
            if (this.state.selectedInstruments.length === 1) {
                fetch("http://coms-309-cy-01.cs.iastate.edu:8080/search/post/", {
                    body: JSON.stringify({
                        "@type": "post",
                        "child": {
                            "@type": "application",
                            "tag": this.state.selectedInstruments[0].toLowerCase(),
                            "operation": "equal"
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

            } else if (this.state.selectedSkillLevels.length === 1) {
                fetch("http://coms-309-cy-01.cs.iastate.edu:8080/search/post/", {
                    body: JSON.stringify({
                        "@type": "post",
                        "child": {
                            "@type": "application",
                            "tag": this.state.selectedSkillLevels[0].toLowerCase(),
                            "operation": "equal"
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
            } else {
                fetch("http://coms-309-cy-01.cs.iastate.edu:8080/search/post/", {
                    body: JSON.stringify({
                        "@type": "post",
                        "child": {
                            "@type": "application",
                            "tag": this.state.selectedGenres[0].toLowerCase(),
                            "operation": "equal"
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
            return;
        }

        const children = []
        this.state.selectedInstruments.forEach(instrument => {
            children.push({ 
                "@type": "application",
                "tag": instrument.toLowerCase(),
                "operation": "eqaual"
             })
        })

        this.state.selectedSkillLevels.forEach(skillLevel => {
            children.push({ 
                "@type": "application",
                "tag": skillLevel.toLowerCase(),
                "operation": "eqaual"
             })
        })

        this.state.selectedGenres.forEach(genre => {
            children.push({ 
                "@type": "application",
                "tag": genre.toLowerCase(),
                "operation": "eqaual"
             })
        })

        fetch("http://coms-309-cy-01.cs.iastate.edu:8080/search/post/", {
            body: JSON.stringify({
                "@type": "post",
                "child": {
                    "@type": "and",
                    "children": children
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
                        <Button className="text-center m-auto" onClick={ this.filter }>Apply Filter(s)</Button>
                    </Col>
                    <Col xs={9}>
                        <h3>Results</h3>
                        <hr></hr>
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