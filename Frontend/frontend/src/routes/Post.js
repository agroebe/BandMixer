import React from 'react';
import axios from 'axios'
import { FormDataConsumer } from 'react-admin';

export default class Post extends React.Component {
    state = {
        post: null
    }

    componentDidMount() {
        console.log(this.props.match.params.postId)

        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/posts/fetch', {
            post: {
                id: 144
            }
        }).then(r => {
            console.log(r.data)
        })
    }

    render() {
        return(
            <p>Hi</p>            
        )
    }
}