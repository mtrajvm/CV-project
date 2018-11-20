import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Edit extends Component {

  constructor(props) {
    super(props);
    this.state = {
      user: {}
    };
  }

  componentDidMount() {
    axios.get('/users/'+this.props.match.params.id)
      .then(res => {
        this.setState({ contact: res.data });
        console.log(this.state.user);
      });
  }

  onChange = (e) => {
    const state = this.state.user
    state[e.target.name] = e.target.value;
    this.setState({user:state});
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { userName, password, accountType} = this.state.user;

    axios.put('/users/'+this.props.match.params.id, { userName, password, accountType})
      .then((result) => {
        this.props.history.push("/show/"+this.props.match.params.id)
      });
  }

  render() {
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              EDIT User
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to={`/show/${this.state.user.id}`}><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> User List</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div class="form-group">
                <label for="userName">UserName:</label>
                <input type="text" class="form-control" name="userName" value={this.state.user.name} onChange={this.onChange} placeholder="UserName" />
              </div>
              <div class="form-group">
                <label for="password">Password:</label>
                <input type="text" class="form-control" name="password" value={this.state.user.address} onChange={this.onChange} placeholder="Password" />
              </div>
              <div class="form-group">
                <label for="accountType">AccountType:</label>
                <input type="text" class="form-control" name="accountType" value={this.state.user.city} onChange={this.onChange} placeholder="AccountType" />
              </div>
              <button type="submit" class="btn btn-default">Update</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Edit;