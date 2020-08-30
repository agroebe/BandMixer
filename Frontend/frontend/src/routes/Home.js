import React from 'react';
import { CardDeck, Card, Button } from 'react-bootstrap';

export default class Home extends React.Component {
    render() {
        return(
            <CardDeck className="mx-auto w-75">
            <Card>
                <Card.Img variant="top" src="https://www.superprof.co.uk/blog/wp-content/uploads/2017/07/guitar-guitar-player-musician-instrument-guitarist-1060x707.jpg" />
                <Card.Body>
                <Card.Title>Card title</Card.Title>
                <Card.Text>
                    This is a wider card with supporting text below as a natural lead-in to
                    additional content. This content is a little bit longer.
                </Card.Text>
                </Card.Body>
                <Card.Footer>
                <small className="text-muted">Last updated 3 mins ago</small>
                </Card.Footer>
            </Card>
            <Card>
                <Card.Img variant="top" src="https://mentalitch.com/wp-content/uploads/2019/07/Top-British-Bands-of-the-80s.jpeg" />
                <Card.Body>
                <Card.Title>Card title</Card.Title>
                <Card.Text>
                    This card has supporting text below as a natural lead-in to additional
                    content.{' '}
                </Card.Text>
                </Card.Body>
                <Card.Footer>
                <small className="text-muted">Last updated 3 mins ago</small>
                </Card.Footer>
            </Card>
            <Card>
                <Card.Img variant="top" src="https://www.bmi.com/cache/images/news/2019/iStock-1125877063_770_514_70_s.jpg" />
                <Card.Body>
                <Card.Title>Card title</Card.Title>
                <Card.Text>
                    This is a wider card with supporting text below as a natural lead-in to
                    additional content. This card has even longer content than the first to
                    show that equal height action.
                </Card.Text>
                </Card.Body>
                <Card.Footer>
                <small className="text-muted">Last updated 3 mins ago</small>
                </Card.Footer>
            </Card>
            </CardDeck>
        )
    }
}