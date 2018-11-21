import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
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
    
remove = (id) =>{
  console.log(id);
  fetch('/api/user/'+id,{
    method : 'delete',
         headers: {
        'Content-Type' : 'application/json',
        'Accept' : 'application/json'
      }
  }).then(()=>{
    let updateUsers = [...this.state.users].filter(i => i.id !==id);
    this.setState({users:updateUsers});
  });
}

nextPath = (path) => {
  this.props.history.push(path);
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
                {this.state.users.map(user =>
                  <tr>
                    <td><Link to={`/Show/${user.id}`}>{user.userName}</Link></td>
                    <td>{user.password}</td>
                    <td>{user.accountType}</td>
                    <td><button onClick={()=> this.remove(user.id)}>delete</button></td>
                    <td><button onClick={()=> this.nextPath('/edit/'+ user.id)}>update</button></td>
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