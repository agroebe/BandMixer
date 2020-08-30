import React from 'react';
import { Jumbotron, Form, Button } from 'react-bootstrap';

export default class Showcase extends React.Component {
    state = {
        instrumentOptions: [
            'Guitar', 'Bass', 'Drums', 'Piano', 'Keyboard'
        ],
        instrument: '',
        location: ''
    }

    search = () => {
        alert(this.state.instrument + ' ' + this.state.location)
    }

    render() {
        return(
            <Jumbotron className="text-center">
                <h1 className="display-2">BandMixer</h1>
                <p className="lead">Let's help you find your jam - start your search below!</p>
                <Form inline className="justify-content-center" onSubmit={ this.search }>
                    <Form.Control as="select" className="mr-2" onChange={ e => this.setState({ instrument: e.target.value }) } >
                        <option value={0} selected>Select what you're looking for</option>
                        { this.state.instrumentOptions.map((value, index) => {
                            return <option value={ index + 1}>{value}</option>
                        }) }
                    </Form.Control>
                    <Form.Control type="text" placeholder="ZIP code or location" className="mr-2" onChange={ e => this.setState({ location: e.target.value }) }></Form.Control>
                    <Button type="submit">Search</Button>
                </Form>
            </Jumbotron>
        )
    }
}