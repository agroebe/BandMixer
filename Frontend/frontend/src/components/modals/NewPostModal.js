import axios from 'axios';
import React from 'react';
import { Modal, Form, Badge, Button } from 'react-bootstrap';

export default class NewPostModal extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            title: '',
            text: '',
            show: false,
        };

        this.close = this.close.bind(this);
    }

    open() {
        this.setState({ show: true })
    }

    close() {
        this.setState({ responseExists: false, response: '', show: false })
    }

    submit() {
        const post = {
            ownerId: 72,
            title: 'hey',
            contentType: 'hi',
            textContent: 'testing',
            isSearch: true,
            applications: ['Rock', 'Metal', 'Vocalist', 'Expert']
        }

        const formData = new FormData()
        formData.append('post', JSON.stringify(post))

        axios.post('http://coms-309-cy-01.cs.iastate.edu:8080/users/addPost', formData)
    }

    render() {
        return(
            <Modal size="lg" show={ this.state.show } onHide={ this.close }>
                <Modal.Header>
                    <Modal.Title>Create New Post</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <Form.Group>
                        <Form.Label>Title</Form.Label>
                        <Form.Control type="text" onChange={ e => this.setState({ loginId: e.target.value }) }/>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Text</Form.Label>
                        <Form.Control as="textarea" rows={5} onChange={ e => this.setState({ password: e.target.value }) }/>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Tag(s)</Form.Label>
                        <p className="mb-0">Instruments:</p>
                        <Badge pill variant="primary">Guitar</Badge>{' '}
                        <Badge pill variant="primary">Bass</Badge>{' '}
                        <Badge pill variant="primary">Drums</Badge>{' '}
                        <Badge pill variant="primary">Piano</Badge>{' '}

                        <p className="mb-0">Skill Levels:</p>
                        <Badge pill variant="warning">Novice</Badge>{' '}
                        <Badge pill variant="warning">Beginner</Badge>{' '}
                        <Badge pill variant="warning">Intermediate</Badge>{' '}
                        <Badge pill variant="warning">Expert</Badge>{' '}
                        <Badge pill variant="warning">Master</Badge>{' '}
                        <Badge pill variant="warning">Professional</Badge>{' '}

                        <p className="mb-0">Genre Preferences:</p>
                        <Badge pill variant="info">Jazz</Badge>{' '}
                        <Badge pill variant="info">Blues</Badge>{' '}
                        <Badge pill variant="info">Rock</Badge>{' '}
                        <Badge pill variant="info">Hard Rock</Badge>{' '}
                        <Badge pill variant="info">Metal</Badge>{' '}
                        <Badge pill variant="info">Heavy Metal</Badge>{' '}
                    </Form.Group>
                </Modal.Body>

                <Modal.Footer>
                    <Button variant="success" onClick={ this.submit }>Submit</Button>
                    <Button variant="danger" onClick={ this.close }>Cancel</Button>
                </Modal.Footer>
            </Modal>
        )
    }
}