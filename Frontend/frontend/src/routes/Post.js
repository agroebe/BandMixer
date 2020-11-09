import React from 'react';
import axios from 'axios'
import { Card, Image, CardDeck } from 'react-bootstrap'
import '../Global.css'

export default class Post extends React.Component {
    state = {
        post: null,
        otherPosts: []
    }

    componentDidMount() {// this.props.match.params.postId
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/posts/fetch/' + this.props.match.params.postId).then(r => {
            this.setState({ post: r.data })
            axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/posts/all').then(r => {
                r.data.forEach(post => {
                    if (post.owner.username == this.state.post.owner.username && this.state.post.id != post.id) {
                        var joined = this.state.otherPosts.concat(post)
                        this.setState( { otherPosts: joined })
                    }
                })
            })
        })
    }

    render() {
        return(
            <>
                { this.state.post ? (
                    <>
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
                        <hr className="w-50" style={{ color: "black"}}></hr>
                        <h6 className="text-center">More from { this.state.post.owner.username }:</h6>
                        <CardDeck className="mx-auto w-50 h-auto">
                            { this.state.otherPosts.slice(0, 5).map(post => (
                                <Card style={{  marginLeft: '5px', marginRight: '5px', marginBottom: '10px', marginTop: '10px' }}>
                                    <Card.Body>
                                    <Card.Title>{ post.title }</Card.Title>
                                    <Card.Subtitle className="mb-2 text-muted">Posted by { post.owner.username }</Card.Subtitle>
                                    <Card.Text>
                                        { post.textContent }
                                    </Card.Text>
                                    <Card.Link href={'/post/' + post.id }>View Post</Card.Link>
                                    </Card.Body>
                                </Card>
                            ))}
                        </CardDeck>
                    </>
                ) : (
                    <p className="text-center">Post with id { this.props.match.params.postId } not found; try searching again!</p>
                )}
            </>
        )
    }
}