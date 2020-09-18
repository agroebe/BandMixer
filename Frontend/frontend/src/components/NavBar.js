import React, { useState } from 'react';
import { Navbar, Button, Dropdown, FormControl } from 'react-bootstrap';
import SignInModal from './modals/SignInModal'
import RegisterModal from './modals/RegisterModal'
import ProfileModal from './modals/ProfileModal'

const CustomToggle = React.forwardRef(({ children, onClick }, ref) => (
    <a
      href=""
      ref={ref}
      onClick={(e) => {
        e.preventDefault();
        onClick(e);
      }}
    >
      {children}
      &#x25bc;
    </a>
  ));

  const CustomMenu = React.forwardRef(
    ({ children, style, className, 'aria-labelledby': labeledBy }, ref) => {
      const [value, setValue] = useState('');
  
      return (
        <div
          ref={ref}
          style={style}
          className={className}
          aria-labelledby={labeledBy}
        >
          <FormControl
            autoFocus
            className="mx-3 my-2 w-auto"
            placeholder="Type to filter..."
            onChange={(e) => setValue(e.target.value)}
            value={value}
          />
          <ul className="list-unstyled">
            {React.Children.toArray(children).filter(
              (child) =>
                !value || child.props.children.toLowerCase().startsWith(value),
            )}
          </ul>
        </div>
      );
    },
  );
  

export default class NavBar extends React.Component {
    constructor(props) {
        super(props);

        this.openSignInModal = this.openSignInModal.bind(this);
        this.openRegisterModal = this.openRegisterModal.bind(this);
        this.openProfileModal = this.openProfileModal.bind(this);
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

    openProfileModal() {
        //this.signInModal.close();
        this.profileModal.open()
    }

    render() {
        return(
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand>BandMixer</Navbar.Brand>
                <Navbar.Collapse className="justify-content-end">
                    <Button variant="primary" className="mr-2" onClick={ this.openSignInModal }>Sign In</Button>
                    <Button variant="secondary" onClick={ this.openRegisterModal }>Register</Button>
                    { <Button variant="primary" onClick={ this.openProfileModal }>Profile</Button> }
                    
                </Navbar.Collapse>

                { /* Modals for registering and signing in. */ }
                <RegisterModal ref={ (modal) => { this.registerModal = modal } } openSignInModal={ this.openSignInModal }/>
                <SignInModal ref={ (modal) => { this.signInModal = modal } } openRegisterModal={ this.openRegisterModal }/>
                { <ProfileModal ref={ (modal) => { this.profileModal = modal } } openProfileModal={ this.openProfileModal }/> }

            </Navbar>
        )
    }
}