import React, { Component } from 'react';
import axios from 'axios';
import { Modal, Button, Form, Image } from 'react-bootstrap';
import { toast, Zoom } from 'react-toastify';
import './Modal.css'

export default class EditProfileModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location : '',
            profilePicture: '',
            title: '',
            username: '',
            phoneNumber: '',
            show: false,
            locationOptions: [
                'California', 'Florida', 'Georgia', 'Illinois', 'Iowa', 'Michigan', 'Minnesota', 'New-Jersey', 'New-York', 'Pennsylvania', 'Texas', 'Washington'
            ]
        };

        this.close = this.close.bind(this);
        this.save = this.save.bind(this);
        this.uploadFile = this.uploadFile.bind(this);
    }

    open() {
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/profiles/' + this.props.userId).then(r => {
            console.log(r.data)
            this.setState({ location: r.data.location, username: r.data.username, profilePicture: r.data.profilePicture, phoneNumber: r.data.phoneNumber, show: true })
        })

    }

    uploadFile(file) {
        console.log(file)
        const formData = new FormData()
        formData.append('file', file)

        axios.post("http://coms-309-cy-01.cs.iastate.edu:8080/files", formData).then(r => {
            this.setState({ profilePicture: r.data.id })
            this.forceUpdate()
            console.log(r.data.id)
        })
    }

    close() {
        this.setState({ show: false })
    }

    save() {
        const profile = {
            userId: this.props.userId,
            username: this.state.username,
            location: this.state.location,
            phoneNumber: this.state.phoneNumber,
            profilePicture: this.state.profilePicture,
            title: 'unset',
            textContent: 'unset',
            contentPath: 'unset',
            contentType: 'Profile',
            isSearch: 1
        }

        fetch("http://coms-309-cy-01.cs.iastate.edu:8080/profiles/" + this.props.userId + "/update/", {
                body: JSON.stringify(profile),
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "accept": "*/*"
                },
            }).then(response => {
                console.log(response)
            }
        )
    }

    render() {
        return(
            <>
                <Modal size="lg" show={ this.state.show } onHide={ this.close }>
                    <Modal.Header>
                        <Modal.Title>Edit Profile</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <Form>
                            <Form.Group>
                                <Form.Label>Username</Form.Label>
                                <Form.Control disabled placeholder={this.state.username}/>
                            </Form.Group>

                            <Form.Group>
                                <Form.Label>Profile Picture</Form.Label>
                                <Image src={'http://coms-309-cy-01.cs.iastate.edu:8080/files/' + this.state.profilePicture}></Image>
                                <Form.File onChange={ e => { this.uploadFile(e.target.files[0])} }></Form.File>
                            </Form.Group>

                            <Form.Group>
                                <Form.Label>Phone Number</Form.Label>
                                <Form.Control placeholder={ this.state.phoneNumber } onChange={ e => this.setState({ phoneNumber: e.target.value }) }/>
                            </Form.Group>

                            <Form.Group>
                                <Form.Label>Location</Form.Label>
                                <Form.Control as="select" className="mr-2" onChange={ e => this.setState({ location: e.target.value }) } >
                                    <option value={0}>Select a location...</option>
                                    { this.state.locationOptions.map((value, index) => {
                                        return <option value={ index + 1} key={ index }>{ value }</option>
                                    }) }
                                </Form.Control>
                            </Form.Group>
                        </Form>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="success" onClick={ this.save }>Save Changes</Button>
                        <Button variant="danger" onClick={ this.close }>Cancel</Button>
                    </Modal.Footer>
                </Modal>
            </>
        )
    }
}
