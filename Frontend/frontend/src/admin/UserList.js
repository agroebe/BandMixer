import React from 'react'
import { List, Datagrid, TextField, EditButton, DeleteWithConfirmButton, CreateButton, TopToolbar } from 'react-admin'

const UserList = (props) => {
    return (
        <>
            <TopToolbar>Hi</TopToolbar>
            <List {...props}>
                <Datagrid>
                    <TextField source='id'/>
                    <TextField source='name'/>
                    <TextField source='email'/>
                    <TextField source='password'/>
                    <EditButton/>
                    <DeleteWithConfirmButton basePath="users/remove"/>
                </Datagrid>
            </List> 
            <CreateButton/>
        </>
    )
}

export default UserList