import React, { Component } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import 'react-toastify/dist/ReactToastify.css';
import './Modal.css'

export default class SearchModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            instrumentOptions: [
                'Guitar', 'Bass', 'Drums', 'Piano', 'Keyboard'
            ],
            
            instrument: '',
            location: ''
        };

        this.close = this.close.bind(this);
    }

    open() {
        this.setState({ show: true })
    }

    close() {
        this.setState({ responseExists: false, response: '', show: false })
    }

    render() {
        return(
            <>
                <Modal size="lg" show={ this.state.show } onHide={ this.close }>
                    <Modal.Header>
                        <Modal.Title>Search</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <p className="lead text-center"><strong>Let's help you find your jam - start your search below!</strong></p>
                        <Form inline className="justify-content-center mb-3" onSubmit={ this.search }>
                            <Form.Control as="select" className="mr-2" onChange={ e => this.setState({ instrument: e.target.value }) } >
                                <option value={0}>Select what you're looking for</option>
                                { this.state.instrumentOptions.map((value, index) => {
                                    return <option value={ index + 1} key={index }>{value}</option>
                                }) }
                            </Form.Control>
                            <Form.Control type="text" placeholder={ toTitleCase(this.props.loc) } readOnly className="mr-2" onChange={ e => this.setState({ location: e.target.value }) }></Form.Control>
                            { this.props.loc && this.state.instrument ? (
                                <Link to={{
                                    pathname: '/results',
                                    state: {
                                        location: this.props.loc,
                                        instrument: this.state.instrument
                                    }
                                }}><Button>Search</Button></Link>
                            ) : (
                                <Button onClick={ () => alert('Please double check your search terms, ensuring both and instrument and location are selected.')}>Search</Button>
                            )}
                        </Form>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="danger" onClick={ this.close }>Cancel</Button>
                    </Modal.Footer>
                </Modal>
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