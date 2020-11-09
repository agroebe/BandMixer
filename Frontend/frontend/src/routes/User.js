import React from 'react';
import axios from 'axios'
import { Card, Image, CardDeck, Badge } from 'react-bootstrap'
import '../Global.css'

export default class User extends React.Component {
    state = {
        user: null,
        otherPosts: []
    }

    componentDidMount() {// this.props.match.params.postId
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/profiles/' + this.props.match.params.userId).then(r => {
            this.setState({ user: r.data })
        })
    }

    render() {
        return(
            <>
            </>
        )
    }
}