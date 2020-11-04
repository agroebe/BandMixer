import React from 'react'
import { BrowserRouter } from 'react-router-dom'
import renderer from 'react-test-renderer'
import React from 'react';
import './Modal.css'
import ProfileModal from '../components/modals/ProfileModal';

it('Profile runs properly', () => {
    const profile = renderer.create(
        <BrowserRouter><ProfileModal/></BrowserRouter> // Wrap test in BrowserRouter so that Link and Route properly works
    )

    let pm = profile.toJSON()
    expect(Object.keys(pm).length).not.toEqual(0)
})