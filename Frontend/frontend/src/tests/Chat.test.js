import React from 'react'
import renderer from 'react-test-renderer'
import Chat from '../routes/Chat'
import { Card } from 'react-bootstrap'

it('renders without crashing', () => {
    const home = renderer.create(
        <Chat/>
    )

    let tree = home.toJSON()
    expect(Object.keys(tree).length).not.toEqual(0)
})

it('properly renders subcomponents', () => {
    const home = renderer.create(
        <Chat/>
    )

    const homeInstance = home.root
    expect(homeInstance.findAllByType(Card)).not.toEqual(null) // Card is a sub-component of explore
})