import React from 'react';
import axios from 'axios'
import { Card } from 'react-bootstrap';

export default class Users extends React.Component {
    state = {
        users: []
    }

    componentDidMount() {
        axios.get('http://coms-309-cy-01.cs.iastate.edu:8080/users').then(r => {
            r.data.forEach(user => {
                var joined = this.state.users.concat(user)
                this.setState( { users: joined })
            })
        })
    }

    render() {
        if (this.state.users.length === 0) {
            return <div className="text-center">No users found...</div>
        }

        var users = this.state.users

        return(
            <>
                <h1 className="text-center">{ users.length } total users found...</h1>
                { users.map(user => (
                    <>
                    { console.log(user) }
                    <Card style={{ width: '60rem', marginLeft: 'auto', marginRight: 'auto', marginBottom: '10px', marginTop: '10px' }}>
                        <Card.Body>
                        <Card.Title><a href={ '/user/' + user.id }>{ toTitleCase(user.username) }</a></Card.Title>
                        <Card.Subtitle>{ user.email }</Card.Subtitle>
                        <Card.Text>
                            { user.textContent }
                        </Card.Text>
                        <Card.Link href={ '/user/' + user.id }>View User</Card.Link>
                        </Card.Body>
                </Card>
                </>
                ))}
            </>
            
        )
    }
}

function toTitleCase(str) {
    if (str == null) {
        return "Select a location..."
    }
    return str.replace(
      /\w\S*/g,
      function(txt) {
        return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
      }
    );
}