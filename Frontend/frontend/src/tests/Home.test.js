import React from 'react'
import { BrowserRouter } from 'react-router-dom'
import renderer from 'react-test-renderer'
import Home from '../routes/Home'
import Showcase from '../components/Showcase'
import { CardDeck } from 'react-bootstrap';

it('renders without crashing', () => {
    const home = renderer.create(
        <BrowserRouter><Home/></BrowserRouter> // Wrap test in BrowserRouter so that Link and Route properly works
    )

    let tree = home.toJSON()
    expect(Object.keys(tree).length).not.toEqual(0)
})

it('properly renders subcomponents', () => {
    const home = renderer.create(
        <BrowserRouter><Home/></BrowserRouter> // Wrap test in BrowserRouter so that Link and Route properly works
    )

    const homeInstance = home.root

    expect(homeInstance.findByType(Showcase)).not.toEqual(null) // Showcase is a sub-component of home
    expect(homeInstance.findAllByType(CardDeck)).not.toEqual(null)
})