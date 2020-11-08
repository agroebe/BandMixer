import React from 'react';
import { Navbar, Button, Dropdown, DropdownButton, Container, Row, Col } from 'react-bootstrap';
import { Map, Plus } from 'react-bootstrap-icons';
import SignInModal from './modals/SignInModal'
import RegisterModal from './modals/RegisterModal'
import ProfileModal from './modals/ProfileModal'
import EditProfileModal from './modals/EditProfileModal'
import NewPostModal from './modals/NewPostModal'

const CustomMenu = React.forwardRef(
  ({ children, style, className, 'aria-labelledby': labeledBy }, ref) => {
    return (
      <div
        ref={ref}
        style={style}
        className={className}
        aria-labelledby={labeledBy}
      >
        <Container>
          <Row>
          <Col onClick={ () => console.log('hi') }><a href="/california">California</a></Col>
            <Col onClick={ () => console.log('hi') }><a href="/florida">Florida</a></Col>
          </Row>
          <Row>
            <Col onClick={ () => console.log('hi') }><a href="/georgia">Georgia</a></Col>
            <Col onClick={ () => console.log('hi') }><a href="/illinois">Illinois</a></Col>
          </Row>
          <Row>
            <Col onClick={ () => console.log('hi') }><a href="/iowa">Iowa</a></Col>
            <Col onClick={ () => console.log('hi') }><a href="/michigan">Michigan</a></Col>
          </Row>
          <Row>
            <Col onClick={ () => console.log('hi') }><a href="/minnesota">Minnesota</a></Col>
            <Col onClick={ () => console.log('hi') }><a href="/new-jersey">New Jersey</a></Col>
          </Row>
          <Row>
            <Col onClick={ () => console.log('hi') }><a href="/new-york">New York</a></Col>
            <Col onClick={ () => console.log('hi') }><a href="/pennsylvania">Pennsylvania</a></Col>
          </Row>
          <Row>
            <Col onClick={ () => console.log('hi') }><a href="/texas">Texas</a></Col>
            <Col onClick={ () => console.log('hi') }><a href="/washington">Washington</a></Col>
          </Row>
        </Container>
      </div>
    );
  },
);

export default class NavBar extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
          loggedIn: false,
          userId: ''
        }

        this.openSignInModal = this.openSignInModal.bind(this);
        this.openRegisterModal = this.openRegisterModal.bind(this);
        this.setLoggedIn = this.setLoggedIn.bind(this);
        this.setUserId = this.setUserId.bind(this);
    }

    /* This function will be passed in as a prop so it can be opened from the RegisterModal. */
    openSignInModal() {
        this.registerModal.close();
        this.signInModal.open();
    }

    /* This function will be passed in as a prop so it can be opened from the SignInModal. */
    openRegisterModal() {
        this.signInModal.close();
        this.registerModal.open()
    }

    setLoggedIn(val) {
      this.setState({
        loggedIn: val
      })
    }

    setUserId(val) {
      this.setState({
        userId: val
      })
    }

    render() {
        return(
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="/">BandMixer</Navbar.Brand>
                <Dropdown>
                  <Dropdown.Toggle variant="link">
                    <Map></Map>
                  </Dropdown.Toggle>

                  <Dropdown.Menu as={CustomMenu}></Dropdown.Menu>
                </Dropdown>
                <Navbar.Collapse className="justify-content-end">
                    { this.state.loggedIn ? (
                      <>
                        <DropdownButton alignRight id="dropdown-basic-button" title={ this.state.userId }>
                          <Dropdown.Item onClick={ () => this.profileModal.open() }>View Profile</Dropdown.Item>
                          <Dropdown.Item onClick={ () => this.editProfileModal.open() }>Edit Profile</Dropdown.Item>
                          <Dropdown.Item onClick={ () => {
                            this.setLoggedIn(false)
                            this.setUserId('')
                          } }>Logout</Dropdown.Item>
                        </DropdownButton>
                        <Button variant="secondary" className="ml-2" onClick={ () => this.newPostModal.open() }><Plus></Plus></Button>
                      </>
                    ) : (
                      <div>
                        <Button variant="primary" className="mr-2" onClick={ this.openSignInModal }>Sign In</Button>
                        <Button variant="secondary" onClick={ this.openRegisterModal }>Register</Button>
                      </div>
                    ) }
                    
                </Navbar.Collapse>

                { /* Modals for registering and signing in. */ }
                <RegisterModal ref={ (modal) => { this.registerModal = modal } } openSignInModal={ this.openSignInModal } setLoggedIn={ this.setLoggedIn } setUserId={ this.setUserId }/>
                <SignInModal ref={ (modal) => { this.signInModal = modal } } openRegisterModal={ this.openRegisterModal } setLoggedIn={ this.setLoggedIn } setUserId={ this.setUserId }/>
                <ProfileModal ref={ (modal) => { this.profileModal = modal } }/>
                <EditProfileModal ref={ (modal) => { this.editProfileModal = modal } }/>
                <NewPostModal ref={ (modal) => {this.newPostModal = modal }} userId={ this.state.userId }/>
            </Navbar>
        )
    }
}