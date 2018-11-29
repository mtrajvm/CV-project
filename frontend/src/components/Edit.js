import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import Logout from './Logout';
class Edit extends Component {

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
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

 onChange = (e) => {
    const target = e.target;
    const value = target.value;
    const name = target.name;
    let details = {...this.state.details};
    details[name] = value;
    this.setState({details});
  }


  onSubmit = (id) => {
    const { details} = this.state;
    console.log(details);
    fetch('/api/user/' + id, {   
      method : 'PUT',
      headers: {
        'Content-Type' : 'application/json',
        'Accept' : 'application/json'
      },
      body: JSON.stringify(details)
    }).then(
      window.setTimeout(this.props.history.push("/ShowAll"),1000)
      );
     
  }

  render() {
    const { details} = this.state;
    return (
      <div >
        <Logout/>
      <div class="infoContainer">
                </div>
        <div class="edit">
          <div class="panel-heading">
            <h3 class="panel-title">
              EDIT User
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to="/ShowAll"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Users List</Link></h4>
            <form onSubmit={()=> this.onSubmit(this.props.match.params.id)}>
              <div class="form-group">
                <label for="isbn">User Name:</label>
                <input type="text" class="form-control" name="userName"  value={details.userName} onChange={this.onChange} placeholder="Name" />
              </div>
              <div class="form-group">
                <label for="title">Password:</label>
                <input type="text" class="form-control" name="password"  value={details.password}  onChange={this.onChange} placeholder="Password" />
              </div>
              <div class="form-group">
                <label for="author">AccountType:</label>
                <input type="text" class="form-control" name="accountType" value={details.accountType}  onChange={this.onChange} placeholder="AccountType" />
              </div>
              <button type="submit" class="btn btn-default">Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Edit;