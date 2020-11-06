import React from 'react';
import axios from 'axios'

export default class Post extends React.Component {
    state = {
        post: null
    }

    componentDidMount() {// this.props.match.params.postId

        axios.get("http://coms-309-cy-01.cs.iastate.edu:8080/posts/fetch", {
            id: 144
        }).then(res => {
          console.log("my call", res)
        });
    }

    render() {
        return(
            <p>Hi</p>            
        )
    }
}