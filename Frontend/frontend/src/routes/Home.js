import React from 'react';
import Showcase from '../components/Showcase'
import axios from 'axios'
import './Home.css'
import { CardDeck, Card, Button } from 'react-bootstrap';

export default class Home extends React.Component {
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
        return(
            <>
                <Showcase loc={this.props.loc}/>
                <CardDeck className="mx-auto w-75 pb-10">
                <Card>
                    <Card.Img variant="top" src="https://www.superprof.co.uk/blog/wp-content/uploads/2017/07/guitar-guitar-player-musician-instrument-guitarist-1060x707.jpg" height="275px"/>
                    <Card.Body>
                        <Card.Title>Create your musician profile</Card.Title>
                        <Card.Text>
                            Get started right away with a fully-customizable musician profile, allowing you to showcase
                            your instrument competencies, skill level, and genre and location preferences.
                        </Card.Text>
                        <Card.Text>
                            After doing so, we'll use the information you provide to help you find bandmates through our advanced searching.
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer className="text-center">
                        <Button>Get started</Button>
                    </Card.Footer>
                </Card>
                <Card>
                    <Card.Img variant="top" src="https://www.bmi.com/cache/images/news/2019/iStock-1125877063_770_514_70_s.jpg" height="275px"/>
                    <Card.Body>
                        <Card.Title>Start your search for bandmates</Card.Title>
                        <Card.Text>
                            Finding bandmates won't be so hard anymore! We give users the power to create bands, join existing ones, or even
                            just find some musician buddies to learn from and jam with.
                        </Card.Text>
                        <Card.Text>
                            You'll be able to search for other musicians by instrument, skill levels, genre preferences, and most importantly, location!
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer className="text-center">
                        <Button href="#">Start searching</Button>
                    </Card.Footer>
                </Card>
                <Card>
                    <Card.Img variant="top" src="https://mentalitch.com/wp-content/uploads/2019/07/Top-British-Bands-of-the-80s.jpeg" height="275px"/>
                    <Card.Body>
                        <Card.Title>Start your band</Card.Title>
                        <Card.Text>
                            BandMixer connects musicians with the ultimate goal of enabling the formation of bands and music groups.
                        </Card.Text>
                        <Card.Text>
                            No matter the instrument, skill level, or music taste, we want to allow musicians to live out their dreams of
                            being in a band.
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer className="text-center">
                        <Button>Success stories</Button>
                    </Card.Footer>
                </Card>
                </CardDeck>

                <br></br><hr className="w-75" style={{ color: "black"}}></hr><br></br>

                <h1 className="text-center">Featured posts:</h1>
                <p className="text-center">Click <a href="/explore">here</a> to see more.</p>
                <CardDeck className="mx-auto w-75">
                { this.state.posts.slice(0, 5).map(post => (
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

                <br></br><hr className="w-75"></hr><br></br>
                <br></br><br></br>
            </>

        )
    }
}