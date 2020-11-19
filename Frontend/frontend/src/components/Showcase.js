import React from 'react';
import { withRouter } from 'react-router-dom'
import { Link } from 'react-router-dom';
import { Jumbotron, Form, Button, Modal } from 'react-bootstrap';
import './Showcase.css'
import { MusicNote } from 'react-bootstrap-icons'

export default class Showcase extends React.Component {
    state = {
        instrumentOptions: [
            'Guitar', 'Bass', 'Drums', 'Piano', 'Keyboard'
        ],
        locationOptions: [
            'California', 'Florida', 'Georgia', 'Illinois', 'Iowa', 'Michigan', 'Minnesota', 'New-Jersey', 'New-York', 'Pennsylvania', 'Texas', 'Washington'
        ],
        instrument: '',
        location: '',
        showBeginnerTutorial: false
    }


    search() {
    }

    render() {
        return(
            <>
                <Modal size="xl" show={ this.state.showBeginnerTutorial } onHide={ () => this.setState({ showBeginnerTutorial: false }) }>
                    <Modal.Header>
                        <Modal.Title>BandMixer - Beginner Tutorial</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <h5><strong>Creating an account</strong></h5>
                        <p>To create an account with BandMixer, locate the buttons in the top right of this website's navigation bar. After doing so, you will want to click the "Register"
                            Button. Fill out the required fields, ensuring that you will remember what you enter in as your username and password. <strong>Note: registering an account does
                            NOT create your musician profile. You will do so after registering and signing-in.</strong></p>

                        <br/>

                        <h5><strong>Creating your profile</strong></h5>
                        <p>As mentioned above, your musician profile will not be created upon registration; you will need to follow some more steps to create one. Once signed in, navigate
                            to your "Profile" button in the top right of the navigation bar. This should open a dialogue box that allows you to enter some basic information to your musician profile,
                            such as instruments, skill levels, genre preferences, etc,. From this form, you will also be able to set your privacy options and determine whether or not you want to 
                            advertise your musician profile to the public. If you choose to advertise your profile, it will appear in search terms of other users, and you may be contacted by
                            other musicians seeking bandmates!</p>

                        <br/>

                        <h5><strong>Start your search</strong></h5>
                        <p>To take full advantage of BandMixer, you will need to know how to navigate our search functionality. The basic search seen on the homepage should be extremely
                            self-explanatory; simply enter an instrument and location, and we will do the rest for you! After doing so, you should be able to see any results we could match 
                            with your search terms. From that point, you can feel free to reach out to any musicians you are interested in by looking at their contact information. 
                            An important note is that <strong>you do not need an account to search for musician profiles</strong>.
                        </p>


                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="danger" onClick={ () => this.setState({ showBeginnerTutorial: false }) }>Cancel</Button>
                    </Modal.Footer>
                </Modal>
                <Jumbotron className="text-center jumbo">
                    <h1 className="display-2">BandMixer</h1>
                    <p className="lead"><MusicNote/>Let's help you find your jam - start your search below!</p>
                    <Form inline className="justify-content-center mb-3" onSubmit={ this.search }>
                        <Form.Control as="select" className="mr-2" onChange={ e => this.setState({ instrument: e.target.value }) } >
                            <option value={0}>Select what you're looking for...</option>
                            { this.state.instrumentOptions.map((value, index) => {
                                return <option value={ index + 1} key={ index }>{ value }</option>
                            }) }
                        </Form.Control>
                        <Form.Control as="select" className="mr-2" onChange={ e => this.setState({ location: e.target.value }) } >
                            <option value={0}>Select a location...</option>
                            { this.state.locationOptions.map((value, index) => {
                                return <option value={ index + 1} key={ index }>{ value }</option>
                            }) }
                        </Form.Control>
                        { this.state.location && this.state.instrument ? (
                            <Link to={{
                                pathname: '/results',
                                state: {
                                    location: this.state.location,
                                    instrument: this.state.instrument
                                }
                            }}><Button>Search</Button></Link>
                        ) : (
                            <Button onClick={ () => alert('Please double check your search terms, ensuring both and instrument and location are selected.')}>Search</Button>
                        )}
                    </Form>
                    <h5 className="d-inline">Confused? Try out <a onClick={ () => this.setState({ showBeginnerTutorial: true }) } className="link">our beginner tutorial</a>!</h5>
                </Jumbotron>
            </>
        )
    }
}

withRouter(Showcase)