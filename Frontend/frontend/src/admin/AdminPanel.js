import React from 'react'
import { Admin, Resource } from 'react-admin'
import restProvider from 'ra-data-simple-rest'
import UserList from './UserList'

function AdminPanel() {
    return (
        <Admin dataProvider={ restProvider('http://coms-309-cy-01.cs.iastate.edu:8080')}>
            <Resource name='users' list={ UserList }/>
        </Admin>
    )
}

export default AdminPanel