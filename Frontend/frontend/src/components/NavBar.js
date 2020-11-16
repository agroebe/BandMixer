import React, { useState } from 'react';
import { Navbar, Button, Dropdown, DropdownButton, FormControl } from 'react-bootstrap';
import { Plus, Search } from 'react-bootstrap-icons';
import SignInModal from './modals/SignInModal'
import RegisterModal from './modals/RegisterModal'
import ProfileModal from './modals/ProfileModal'
import EditProfileModal from './modals/EditProfileModal'
import NewPostModal from './modals/NewPostModal'
import SearchModal from './modals/SearchModal'
import cookie from 'react-cookies'
import Chat from './Chat'

export default class NavBar extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
          loggedIn: false,
          userId: ''
        }

        this.openSignInModal = this.openSignInModal.bind(this);
        this.openRegisterModal = this.openRegisterModal.bind(this);
        this.openSearchModal = this.openSearchModal.bind(this);
        this.setLoggedIn = this.setLoggedIn.bind(this);
        this.setUserId = this.setUserId.bind(this);
    }

    componentWillMount() {
      if (cookie.load('stayLoggedIn')) {
        this.setState({ loggedIn: true, userId: cookie.load('userId')})
      }
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

    openSearchModal() {
      this.searchModal.open();
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
                <Chat></Chat>
                <Navbar.Brand href="/">BandMixer</Navbar.Brand>
                <Navbar.Collapse className="justify-content-end">
                    { this.state.loggedIn ? (
                      <>
                        <DropdownButton alignRight id="dropdown-basic-button" title={ this.state.userId }>
                          <Dropdown.Item onClick={ () => this.profileModal.open() }>View Profile</Dropdown.Item>
                          <Dropdown.Item onClick={ () => this.editProfileModal.open() }>Edit Profile</Dropdown.Item>
                          <Dropdown.Item onClick={ () => {
                            cookie.remove('stayLoggedIn')
                            cookie.remove('userId')
                          } }>Clear Cookies</Dropdown.Item>
                          <Dropdown.Item onClick={ () => {
                            this.setLoggedIn(false)
                            this.setUserId('')
                            cookie.remove('stayLoggedIn')
                            cookie.remove('userId')
                          } }>Logout</Dropdown.Item>
                        </DropdownButton>
                        <Button variant="secondary" className="ml-2" onClick={ () => this.newPostModal.open() }><Plus></Plus></Button>
                      </>
                    ) : (
                      <div>
                        <Button variant="primary" className="mr-2" onClick={ this.openSignInModal }>Sign In</Button>
                        <Button variant="secondary" onClick={ this.openRegisterModal }>Register</Button>
                      </div>
                    )}
                    { window.location.pathname === '/' ? (
                      <></> // This is the home page, so do not render search modal button.
                    ) : (
                      <Search onClick={ this.openSearchModal } className="d-inline ml-1" style={{ cursor: 'pointer', color: 'white' }} size='28'></Search>
                    )}
                </Navbar.Collapse>

                { /* Modals for registering and signing in. */ }
                <RegisterModal ref={ (modal) => { this.registerModal = modal } } openSignInModal={ this.openSignInModal } setLoggedIn={ this.setLoggedIn } setUserId={ this.setUserId }/>
                <SignInModal ref={ (modal) => { this.signInModal = modal } } openRegisterModal={ this.openRegisterModal } setLoggedIn={ this.setLoggedIn } setUserId={ this.setUserId }/>
                <ProfileModal ref={ (modal) => { this.profileModal = modal } }/>
                <EditProfileModal ref={ (modal) => { this.editProfileModal = modal } }/>
                <NewPostModal ref={ (modal) => {this.newPostModal = modal }} userId={ this.state.userId }/>
                { window.location.pathname === '/' ? (
                      <></> // This is the home page, so do not render search modal.
                    ) : (
                      <SearchModal ref={ (modal) => { this.searchModal = modal }}></SearchModal>
                    )}
            </Navbar>
        )
    }
}
