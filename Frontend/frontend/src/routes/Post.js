import React from 'react';
import axios from 'axios'

export default class Post extends React.Component {
    state = {
        post: null
    }

    componentDidMount() {// this.props.match.params.postId
        axios({
            method: 'get',
            url: 'http://coms-309-cy-01.cs.iastate.edu:8080/posts/fetch',
            data: {
                post: {
                    id: 144
                }
            }
        })
    }

    render() {
        return(
            <p>Hi</p>            
        )
    }
}