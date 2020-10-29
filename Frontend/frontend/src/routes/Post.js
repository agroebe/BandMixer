import React from 'react';
import axios from 'axios'

export default class Post extends React.Component {
    state = {
        post: null
    }

    componentDidMount() {
        console.log(this.props.match.params.postId)

        const formData = new FormData()
        formData.append('post', this.props.match.params.postId)
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/posts/fetch', formData).then(r => {
            console.log(r.data)
        })
    }

    render() {
        return(
            <p>Hi</p>            
        )
    }
}