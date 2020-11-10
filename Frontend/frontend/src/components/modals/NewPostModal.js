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

            const tagApplications = []
            this.state.tags.forEach(tagName => {
                console.log('adding tag name ' + String(tagName))
                tagApplications.push({ tag: tagName })
            })

            const newPost = {
                ownerId: userId,
                title: this.state.title,
                contentType: 'hi',
                textContent: this.state.text,
                isSearch: false,
                applications:  tagApplications
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

        console.log('in add method, tag = ' + tag)
        
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
                        <Badge pill variant="primary" onClick={ () => this.addTag('guitar') } style={{ cursor: 'pointer' }}>Guitar</Badge>{' '}
                        <Badge pill variant="primary" onClick={ () => this.addTag('bass') } style={{ cursor: 'pointer' }}>Bass</Badge>{' '}
                        <Badge pill variant="primary" onClick={ () => this.addTag('drums') }  style={{ cursor: 'pointer' }}>Drums</Badge>{' '}
                        <Badge pill variant="primary" onClick={ () => this.addTag('piano') }  style={{ cursor: 'pointer' }}>Piano</Badge>{' '}

                        <p className="mb-0">Skill Levels:</p>
                        <Badge pill variant="warning" onClick={ () => this.addTag('novice') }  style={{ cursor: 'pointer' }}>Novice</Badge>{' '}
                        <Badge pill variant="warning" onClick={ () => this.addTag('beginner') }  style={{ cursor: 'pointer' }}>Beginner</Badge>{' '}
                        <Badge pill variant="warning" onClick={ () => this.addTag('intermediate') }  style={{ cursor: 'pointer' }}>Intermediate</Badge>{' '}
                        <Badge pill variant="warning" onClick={ () => this.addTag('expert') }  style={{ cursor: 'pointer' }}>Expert</Badge>{' '}
                        <Badge pill variant="warning" onClick={ () => this.addTag('master') }  style={{ cursor: 'pointer' }}>Master</Badge>{' '}
                        <Badge pill variant="warning" onClick={ () => this.addTag('professional') }  style={{ cursor: 'pointer' }}>Professional</Badge>{' '}

                        <p className="mb-0">Genre Preferences:</p>
                        <Badge pill variant="info" onClick={ () => this.addTag('jazz') }  style={{ cursor: 'pointer' }}>Jazz</Badge>{' '}
                        <Badge pill variant="info" onClick={ () => this.addTag('blues') }  style={{ cursor: 'pointer' }}>Blues</Badge>{' '}
                        <Badge pill variant="info" onClick={ () => this.addTag('rock') }  style={{ cursor: 'pointer' }}>Rock</Badge>{' '}
                        <Badge pill variant="info" onClick={ () => this.addTag('hardrock') }  style={{ cursor: 'pointer' }}>Hard Rock</Badge>{' '}
                        <Badge pill variant="info" onClick={ () => this.addTag('metal') }  style={{ cursor: 'pointer' }}>Metal</Badge>{' '}
                        <Badge pill variant="info" onClick={ () => this.addTag('heavymetal') }  style={{ cursor: 'pointer' }}>Heavy Metal</Badge>{' '}
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