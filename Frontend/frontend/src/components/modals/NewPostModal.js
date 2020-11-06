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
            tags: []
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

    addTag(tag) {
        const index = this.state.tags.indexOf(tag)
        if (index !== -1) {
            const newArr = this.state.tags.filter(item => item !== tag)
            this.setState({ tags: newArr })
            return
        }
        
        var joined = this.state.tags.concat(tag)
        this.setState({ tags: joined })
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
                        <Badge pill variant="primary" onClick={ () => this.addTag('Guitar') } style={{ cursor: 'pointer' }}>Guitar</Badge>{' '}
                        <Badge pill variant="primary" onClick={ () => this.addTag('Bass') } style={{ cursor: 'pointer' }}>Bass</Badge>{' '}
                        <Badge pill variant="primary" onClick={ () => this.addTag('Drums') }  style={{ cursor: 'pointer' }}>Drums</Badge>{' '}
                        <Badge pill variant="primary" onClick={ () => this.addTag('Piano') }  style={{ cursor: 'pointer' }}>Piano</Badge>{' '}

                        <p className="mb-0">Skill Levels:</p>
                        <Badge pill variant="warning" onClick={ () => this.addTag('Novice') }  style={{ cursor: 'pointer' }}>Novice</Badge>{' '}
                        <Badge pill variant="warning" onClick={ () => this.addTag('Beginner') }  style={{ cursor: 'pointer' }}>Beginner</Badge>{' '}
                        <Badge pill variant="warning" onClick={ () => this.addTag('Intermediate') }  style={{ cursor: 'pointer' }}>Intermediate</Badge>{' '}
                        <Badge pill variant="warning" onClick={ () => this.addTag('Expert') }  style={{ cursor: 'pointer' }}>Expert</Badge>{' '}
                        <Badge pill variant="warning" onClick={ () => this.addTag('Master') }  style={{ cursor: 'pointer' }}>Master</Badge>{' '}
                        <Badge pill variant="warning" onClick={ () => this.addTag('Professional') }  style={{ cursor: 'pointer' }}>Professional</Badge>{' '}

                        <p className="mb-0">Genre Preferences:</p>
                        <Badge pill variant="info" onClick={ () => this.addTag('Jazz') }  style={{ cursor: 'pointer' }}>Jazz</Badge>{' '}
                        <Badge pill variant="info" onClick={ () => this.addTag('Blues') }  style={{ cursor: 'pointer' }}>Blues</Badge>{' '}
                        <Badge pill variant="info" onClick={ () => this.addTag('Rock') }  style={{ cursor: 'pointer' }}>Rock</Badge>{' '}
                        <Badge pill variant="info" onClick={ () => this.addTag('Hard Rock') }  style={{ cursor: 'pointer' }}>Hard Rock</Badge>{' '}
                        <Badge pill variant="info" onClick={ () => this.addTag('Metal') }  style={{ cursor: 'pointer' }}>Metal</Badge>{' '}
                        <Badge pill variant="info" onClick={ () => this.addTag('Heavy Metal') }  style={{ cursor: 'pointer' }}>Heavy Metal</Badge>{' '}
                    </Form.Group>

                    <h5>Applied tags: { this.state.tags.length <= 0 ? "N/A" : this.state.tags.toString() }</h5>
                </Modal.Body>

                <Modal.Footer>
                    <Button variant="success" onClick={ this.submit }>Submit</Button>
                    <Button variant="danger" onClick={ this.close }>Cancel</Button>
                </Modal.Footer>
            </Modal>
        )
    }
}