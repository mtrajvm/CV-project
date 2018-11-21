import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import {onChange} from './helper/Helper';

class Create extends Component {
  emptyValues = {
      userName: '',
      password: '',
      accountType: '',  
  };

  constructor(props) {
    super(props);
    this.state = {
        details: this.emptyValues     
    }

  }



  onSubmit = (e) => {
    e.preventDefault();
    const { details} = this.state;
    console.log(details);
    fetch('/api/user', {   
      method : 'POST',
      headers: {
        'Content-Type' : 'application/json',
        'Accept' : 'application/json'
      },
      body: JSON.stringify(details)
    });

    this.props.history.push("/")
     
  }

  render() {
    const { details} = this.state;
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
                <input type="text" class="form-control" name="userName" onChange={event => onChange(event,this.state.details)} placeholder="Name" />
              </div>
              <div class="form-group">
                <label for="title">Password:</label>
                <input type="text" class="form-control" name="password"   onChange={event => onChange(event,this.state.details)} placeholder="Password" />
              </div>
              <div class="form-group">
                <label for="author">AccountType:</label>
                <input type="text" class="form-control" name="accountType"  onChange={event => onChange(event,this.state.details)} placeholder="AccountType" />
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