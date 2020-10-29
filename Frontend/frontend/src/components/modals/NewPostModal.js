import axios from 'axios';
import React from 'react';
import { Modal, Form, Badge, Button } from 'react-bootstrap';
import { toast, Zoom } from 'react-toastify';

export default class NewPostModal extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            title: '',
            text: '',
            show: false,
        };

        this.close = this.close.bind(this);
        this.submit = this.submit.bind(this);
    }

    open() {
        this.setState({ show: true })
    }

    close() {
        this.setState({ show: false })
    }

    submit() {

        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/users/username/' + this.props.userId).then(r => {
            const userId = r.data.id
            const newPost = {
                ownerId: userId,
                title: this.state.title,
                contentType: 'hi',
                textContent: this.state.text,
                isSearch: false,
                applications:  [ { tag: "guitar" } ]
            }

            const formData = new FormData()
            formData.append('post', JSON.stringify(newPost))

            axios.post('http://coms-309-cy-01.cs.iastate.edu:8080/users/addPost', formData).then(r => {
                if (r.data.message.includes('saved')) {
                    toast.success('Successfuly submitted post!', {
                        position: "top-center",
                        autoClose: 2500,
                        hideProgressBar: true,
                        closeOnClick: true,
                        pauseOnHover: true,
                        draggable: true,
                        progress: undefined,
                        transition: Zoom
                    });
                    this.close()
                }
            }).catch (function(response) {
                toast.error('Error submitting post due to one or more violations; please try again.', {
                    position: "top-center",
                    autoClose: 2500,
                    hideProgressBar: true,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    transition: Zoom
                });
            })
        })
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
                        <Form.Control type="text" onChange={ e => this.setState({ title: e.target.value }) }/>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Text</Form.Label>
                        <Form.Control as="textarea" rows={5} onChange={ e => this.setState({ text: e.target.value }) }/>
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