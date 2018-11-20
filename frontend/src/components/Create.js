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
    const state = this.state
    state[e.target.userName] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { userName, password, accountType} = this.state;

    axios.post('/users', { userName, password, accountType})
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
              ADD USER
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to="/"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Users List</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div class="form-group">
                <label for="isbn">User Name:</label>
                <input type="text" class="form-control" name="userName" value={userName} onChange={this.onChange} placeholder="Name" />
              </div>
              <div class="form-group">
                <label for="title">Password:</label>
                <input type="text" class="form-control" name="password" value={password} onChange={this.onChange} placeholder="Password" />
              </div>
              <div class="form-group">
                <label for="author">AccountType:</label>
                <input type="text" class="form-control" name="accountType" value={accountType} onChange={this.onChange} placeholder="AccountType" />
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