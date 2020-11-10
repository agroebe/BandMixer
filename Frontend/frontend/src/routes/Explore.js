import React from 'react';
import axios from 'axios'
import { Card } from 'react-bootstrap';

export default class Explore extends React.Component {
    state = {
        posts: []
    }

    componentDidMount() {
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/posts/all').then(r => {
            r.data.forEach(post => {
                var joined = this.state.posts.concat(post)
                this.setState( { posts: joined })
            })
        })
    }

    render() {
        if (this.state.posts.length === 0) {
            return <div className="text-center">No posts found...</div>
        }

        var posts = this.state.posts

        return(
            <>
            <h1 className="text-center">{ posts.length } total posts found...</h1>
            { posts.map(post => (
                <>
                <Card style={{ width: '60rem', marginLeft: 'auto', marginRight: 'auto', marginBottom: '10px', marginTop: '10px' }}>
                    <Card.Body>
                    <Card.Title>{ post.title }</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">Posted by <a href={ '/user/' + post.owner.id }>{ post.owner.username }</a></Card.Subtitle>
                    <Card.Text>
                        { post.textContent }
                    </Card.Text>
                    <Card.Link href={'/post/' + post.id }>View Post</Card.Link>
                    </Card.Body>
              </Card>
              </>
            ))}
            </>
            
        )
    }
}