import React from 'react'
import renderer from 'react-test-renderer'
import Results from '../routes/Results'
import { Container } from 'react-bootstrap'

it('renders without crashing', () => {
    const home = renderer.create(
        <Results/>
    )

    let tree = home.toJSON()
    expect(Object.keys(tree).length).not.toEqual(0)
})

it('properly renders subcomponents', () => {
    const home = renderer.create(
        <Results/>
    )

    const homeInstance = home.root
    expect(homeInstance.findAllByType(Container)).not.toEqual(null) // Card is a sub-component of explore
})

it('properly renders sub-sub-components', () => {
    const home = renderer.create(
        <Results/>
    )

    const homeInstance = home.root
    expect(homeInstance.findAllByType(Container)).not.toEqual(null) // Card is a sub-component of explore
})