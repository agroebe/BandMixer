import React from 'react';
import { CardDeck, Card, Button } from 'react-bootstrap';

export default class Home extends React.Component {
    render() {
        return(
            <CardDeck className="mx-auto w-75">
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
        )
    }
}