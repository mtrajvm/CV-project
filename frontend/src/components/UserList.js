import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import {
    Container, Col, Form,Table,
    FormGroup, Label, Input,
    Button, FormFeedback, Dropdown, DropdownToggle, DropdownMenu, DropdownItem
} from 'reactstrap';
import InformationComponent from './InformationComponent';
import Logout from './Logout';
class UserList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [],
            searchString: '',
        };
    }

    componentDidMount() {
        axios.get('/api/admin/users')
            .then(res => {
                this.setState({ users: res.data });               
                this.state.users.forEach(users =>  {users.dropdownOpen = false; });
            });
    }

    remove = (user) => {
        fetch('/api/admin/user/' + user.userName, {
            method: 'delete',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(() => {
            let updateUsers = [...this.state.users].filter(i => i.id !== user.id);
            this.setState({ users: updateUsers });
        });
    }

    updatePrivilage = (user) => {

        const details = {
            userName:user.userName,
            accountType:user.accountType,
        }
        fetch('/api/admin/user/'+user.userName,{
            method:'PUT',
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(details)
        })
    }

    nextPath = (path) => {
        this.props.history.push(path);
    }

    filterList = (event) => {
        this.setState({ searchString: event.target.value })
    }

    toggle = (curUser) => {
          var userList = this.state.users;
          var userIndex = userList.findIndex(user => { return user.id == curUser.id})
          this.state.users[userIndex].dropdownOpen = !this.state.users[userIndex].dropdownOpen
          this.forceUpdate()
      
    }

    changeAccountType=(curUser,accType) =>{
         var userList = this.state.users;
          var userIndex = userList.findIndex(user => { return user.id == curUser.id})
          this.state.users[userIndex].accountType = accType;
          this.forceUpdate()
    }
    render() {
        let filteredNames = this.state.users.filter(user => user.userName.toLowerCase().search(this.state.searchString.toLowerCase()) !== -1)

        return (
            <div>
                <Logout/>
                <div class="infoContainer">
                
                </div>
                <div class="listContainer" background-color="white">
                    <input type="text" value={this.state.searchString} onChange={this.filterList.bind(this)} placeholder="Search..." />
                    <div class="table-wrapper-scroll-y">
                    <Table  hover bordered>
                        <thead>
                            <tr>
                                <th>UserName</th>
                                <th colspan="3"><Button  style={{width: 400, height: 60}} ><Link to="/create" style={{color:"white"}}><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Add User</Link></Button></th>
         
                            </tr>
                        </thead>
                        <tbody >
                            {filteredNames.map(user =>
                                <tr onMouseEnter={() => {   
                                    const de = Object.create(null,{
                                        userName:{value :user.userName},
                                        firstName:{value:user.firstName},
                                        surName:{value:user.surName},
                                    })
                                    this.setState({ hoveredUser: de})
                                    }}>
                                    <td><Link to={`/edit/${user.id}`} >{user.userName}</Link></td>
                                    <td>  <Dropdown   isOpen={user.dropdownOpen} toggle={()=>this.toggle(user)} >
                                            <DropdownToggle style={{width: 150}} caret>
                                                 {user.accountType}
                                            </DropdownToggle>
                                            <DropdownMenu>
                                            <DropdownItem header>Change privilage</DropdownItem>
                                            <DropdownItem onClick={()=>this.changeAccountType(user,'trainee')}>trainee</DropdownItem>
                                            <DropdownItem divider />
                                            <DropdownItem onClick={()=>this.changeAccountType(user,'traineemanager')}>trainee manager</DropdownItem>
                                            <DropdownItem divider />
                                            <DropdownItem onClick={()=>this.changeAccountType(user,'sales')}>sales team</DropdownItem>
                                            </DropdownMenu>
                                        </Dropdown></td>
                                    <td><button onClick={() => this.remove(user)}>delete</button></td>
                                    <td><button onClick={() => this.updatePrivilage(user)}>update</button></td>
                                </tr>
                            )}
                        </tbody>
                    </Table>
                    </div>
                </div>
                <div class="rightContainer">
                                    

                </div>
            </div>
        );
    }
}

export default UserList;