import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    Button, FormFeedback, Dropdown, DropdownToggle, DropdownMenu, DropdownItem
} from 'reactstrap';
import InformationComponent from './InformationComponent';
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
                // just for checking
                console.log(this.state.users);
            });
    }

    remove = (id) => {
        fetch('/api/admin/user/' + id, {
            method: 'delete',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(() => {
            let updateUsers = [...this.state.users].filter(i => i.id !== id);
            this.setState({ users: updateUsers });
        });
    }

    updatePrivilage = (user) => {
        console.log(user)
        const details = Object.create(null,{
            userName:{value:user.userName},
            accountType:{value:user.accountType},
        })
        console.log(details)
        fetch('/api/admin/user/'+user.userName,{
            method:'PUT',
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(details)
        }).then(res=>console.log(res))
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
            <div class="container">
                <div class="one">
                    <InformationComponent userName={this.state.hoveredUser} />
                </div>
                <div class="two">
                    <h4><Link to="/create"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Add User</Link></h4>
                    <input type="text" value={this.state.searchString} onChange={this.filterList.bind(this)} placeholder="Search..." />

                    <table class="table table-stripe">
                        <thead>
                            <tr>
                                <th>UserName</th>
                                <th>Password</th>
                                <th>AccountType</th>
                                <th>First Name</th>
                                <th>Second Name</th>
                            </tr>
                        </thead>
                        <tbody >
                            {filteredNames.map(user =>
                                <tr onMouseEnter={() => this.setState({ hoveredUser: user.userName })}>
                                    <td><Link to={`/Show/${user.id}`}>{user.userName}</Link></td>
                                    <td>{user.accountType}</td>
                                    <td>  <Dropdown isOpen={user.dropdownOpen} toggle={()=>this.toggle(user)}>
                                            <DropdownToggle caret>
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
                                    <td>{user.firstName}</td>
                                    <td>{user.surName}</td>
                                    <td><button onClick={() => this.remove(user.id)}>delete</button></td>
                                    <td><button onClick={() => this.updatePrivilage(user)}>update</button></td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default UserList;