import React from 'react'
import renderer from 'react-test-renderer'
import Explore from '../routes/Explore'
import { Card } from 'react-bootstrap'

it('renders without crashing', () => {
    const home = renderer.create(
        <Explore/>
    )

    let tree = home.toJSON()
    expect(Object.keys(tree).length).not.toEqual(0)
})

it('properly renders subcomponents', () => {
    const home = renderer.create(
        <Explore/>
    )

    const homeInstance = home.root
    expect(homeInstance.findAllByType(Card)).not.toEqual(null) // Card is a sub-component of explore
})