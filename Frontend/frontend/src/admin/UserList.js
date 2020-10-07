import React from 'react'
import { List, Datagrid, TextField } from 'react-admin'

const UserList = (props) => {
    return <List {...props}>
        <Datagrid>
            <TextField source='id'></TextField>
        </Datagrid>
    </List> 
}

export default UserList