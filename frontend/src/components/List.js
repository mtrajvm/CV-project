import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import UserList from './UserList';
import CvList from './CvList';
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
      hoveredUser : '',
      accountType : 2,
      firstName : this.props.firstName ? this.props.firstName : '',
      secondName : this.props.secondName ? this.props.secondName : '',
      email : this.props.email ? this.props.email : '',
    };
  }



nextPath = (path) => {
  this.props.history.push(path);
}

  render() {     
        let list;    
        if (this.state.accountType == 1) {
            list  = <UserList /> ;
              console.log("adming");
          } else{
              list = <CvList />
              console.log("wrong");
          }

    return (
      <div>
        {list}      
      </div>
    );
  }
}

export default List;