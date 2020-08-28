import React from 'react';
import { Jumbotron, Form, Button } from 'react-bootstrap';

export default class Showcase extends React.Component {
    render() {
        return(
            <Jumbotron className="text-center">
                <h1 className="display-2">BandMixer</h1>
                <p className="lead">Let's help you find your jam!</p>
                <p>Start your search below</p>
                <Form inline className="justify-content-center">
                    <Form.Control as="select" className="mr-2">
                        <option>Select what you're looking for</option>
                        <option>Guitar</option>
                        <option>Bass</option>
                        <option>Drums</option>
                        <option>Piano</option>
                        <option>Keyboard</option>
                    </Form.Control>
                    <Form.Control type="text" placeholder="ZIP code or location" className="mr-2"></Form.Control>
                    <Button type="submit">Search</Button>
                </Form>
            </Jumbotron>
        )
    }
}