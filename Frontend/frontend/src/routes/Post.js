import React from 'react';
import axios from 'axios'

export default class Post extends React.Component {
    state = {
        post: null
    }

    componentDidMount() {// this.props.match.params.postId
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/posts/fetch', {
            post: {
                id: 144
            }
        }, {
            headers: {
                "Content-Type": "application/json",
                "Accept": "*/*",
                "Accept-Encoding": "gzip, deflate, br",
                "Connection": "keep-alive"
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