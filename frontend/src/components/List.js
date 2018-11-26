import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import UserList from './UserList';
import CvList from './CvList';
import TraineeList from './TraineeList';
import AppNavbar from './AppNavbar';
import {
  Container, Col, Form,
  FormGroup, Label, Input,
  Button, FormFeedback,
} from 'reactstrap';
import InformationComponent from './InformationComponent';
class List extends Component {

  constructor(props) {
      super(props);
    this.state = {
      users: [],
     
      };
    // this is the user details for logged person
  
  
  }



nextPath = (path) => {
  this.props.history.push(path);

}
    render() {   
    
      console.log(this.props.location.details)
        let list;    
        if (this.props.location.details.accountType == "admin") {
            list = <UserList details={this.props.location.details}/>;
        } 
        else if (this.props.location.details.accountType == "trainee") {
            list = <CvList details={this.props.location.details} />;
        }
        else if(this.props.location.details.accountType == "traineemanager") {
          list = <TraineeList details={this.props.location.details} />
        }

    return (
          <div>
            <AppNavbar details={this.props.location.details}/>
            {list} 
            </div>  
    );
  }
}

export default List;