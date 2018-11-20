import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Create extends Component {

  constructor() {
    super();
    this.state = {
      userName: '',
      password: '',
      accountType: '',
    };
  }
  onChange = (e) => {
    const state = this.state;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();
    const { userName, password, accountType} = this.state;
      console.log(userName);
      console.log(password);
    axios.post('/api/user', { 
      method : 'POST',
      headers: {
        'Content-Type' : 'application/json'
      },
      body: {userName, password, accountType}
    })
      .then((result) => {
        this.props.history.push("/")
      });
  }

  render() {
    const { userName, password, accountType} = this.state;
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              AD
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to="/ShowAll"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Users List</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div class="form-group">
                <label for="isbn">User Name:</label>
                <input type="text" class="form-control" name="userName"  onChange={this.onChange} placeholder="Name" />
              </div>
              <div class="form-group">
                <label for="title">Password:</label>
                <input type="text" class="form-control" name="password"  onChange={this.onChange} placeholder="Password" />
              </div>
              <div class="form-group">
                <label for="author">AccountType:</label>
                <input type="text" class="form-control" name="accountType"  onChange={this.onChange} placeholder="AccountType" />
              </div>
              <button type="submit" class="btn btn-default">Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Create;