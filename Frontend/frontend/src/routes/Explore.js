import React from 'react';
import axios from 'axios'
import { Container, Row, Col, Form, CardDeck, Card, Badge, Button } from 'react-bootstrap';

export default class Explore extends React.Component {
    componentDidMount() {
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/posts/all').then(r => {
            console.log(r.data)
        })
    }

    render() {
        return(
            <div>Hey!</div>
        )
    }
}