import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';

class UserList extends Component {

  constructor(props) {
    super(props);
    this.state = {
      users: []
    };
  }

  componentDidMount() {
    axios.get('/api/users')
      .then(res => {
        this.setState({ users: res.data });
        console.log(this.state.users);
      });
  }

  render() {
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              USERS LIST
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to="/create"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Add User</Link></h4>
            <table class="table table-stripe">
              <thead>
                <tr>
                  <th>UserName</th>
                  <th>Password</th>
                  <th>AccountType</th>
                </tr>
              </thead>
              <tbody>
                {this.state.users.map(c =>
                  <tr>
                    <td><Link to={`/show/${c.id}`}>{c.userName}</Link></td>
                    <td>{c.password}</td>
                    <td>{c.accountType}</td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  }
}

export default UserList;