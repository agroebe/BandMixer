import React from 'react'
import { Button, Card, Form } from 'react-bootstrap'
import { send, startWebsocketConnection, registerOnMessageCallback } from '../websockets'

export default class Chat extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      text: '',
      posts: []
    }
  }
  
  componentDidMount() {
    startWebsocketConnection('andrew')
    registerOnMessageCallback(this.onMessageReceived.bind(this))
  }

  onMessageReceived(msg) {
    this.setState({
      posts: this.state.posts.concat(JSON.parse(msg))
    })
    this.forceUpdate()
    console.log(msg)
  }

  sendMessage (text) {
    const message = {
      userName: 'Andrew',
      content: text
    }
    send(JSON.stringify(message))
  }

  render() {
    return (
      <>
        <Card style={{ width: '60rem', marginLeft: 'auto', marginRight: 'auto', marginBottom: '10px', marginTop: '10px' }}>
          <Card.Body>
          <Card.Title>BandMixer Chat</Card.Title>
          <hr></hr>
          { this.state.posts.map((post, index) => (
            'content' in post && 'userName' in post ? ( 
              <p><strong>{ post.userName }</strong>: { post.content}</p>
            ) : (
              // <p>{ post['message:'] }</p>
              <></>
            )
          )) }
          </Card.Body>
          <Card.Footer>
            <Form inline>
              <Form.Group>
                <Form.Control required type="text" onChange={ e => this.setState({ text: e.target.value }) }/>
                <Button className="ml-2" onClick={ () => this.sendMessage(this.state.text)}>Send</Button>
              </Form.Group>
            </Form>
          </Card.Footer>
        </Card>
      </>
    )
  }
}