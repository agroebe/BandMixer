import React from 'react';
import axios from 'axios'
import { Card, Image } from 'react-bootstrap'

export default class Post extends React.Component {
    state = {
        post: null
    }

    componentDidMount() {// this.props.match.params.postId
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/posts/fetch/' + this.props.match.params.postId).then(r => {
            this.setState({ post: r.data })
        })
    }

    render() {
        return(
            <>
                { this.state.post ? (
                    <Card style={{ width: '60rem', marginLeft: 'auto', marginRight: 'auto', marginBottom: '10px', marginTop: '10px' }}>
                        <Card.Body>
                        <Card.Title>{ this.state.post.title }</Card.Title>
                        <Image style={{width: 100, height: 'auto', display: "inline-block"}} src="https://support.hubstaff.com/wp-content/uploads/2019/08/good-pic.png" roundedCircle />
                        <Card.Subtitle className="mb-2 text-muted">Posted by { this.state.post.owner.username }</Card.Subtitle>
                        <Card.Text>
                            { this.state.post.textContent }
                        </Card.Text>
                        </Card.Body>
                    </Card>
                ) : (
                    <p className="text-center">Post with id { this.props.match.params.postId } not found; try searching again!</p>
                )}
            </>
        )
    }
}