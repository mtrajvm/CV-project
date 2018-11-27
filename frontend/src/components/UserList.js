import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    Button, FormFeedback,
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
        axios.get('/api/users')
            .then(res => {

                this.setState({ users: res.data });
                // just for checking
                console.log(this.state.users);
            });
    }

    remove = (id) => {
        fetch('/api/user/' + id, {
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

    nextPath = (path) => {
        this.props.history.push(path);
    }

    filterList = (event) => {
        this.setState({ searchString: event.target.value })
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
                                    <td>{user.password}</td>
                                    <td>{user.accountType}</td>
                                    <td>{user.firstName}</td>
                                    <td>{user.secondName}</td>
                                    <td><button onClick={() => this.remove(user.id)}>delete</button></td>
                                    <td><button onClick={() => this.nextPath('/edit/' + user.id)}>update</button></td>
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