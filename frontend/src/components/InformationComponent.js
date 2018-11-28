import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
class InformationComponent extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    if(this.props.details !=null){
    var userName = this.props.details.userName;
    var firstName = this.props.details.firstName;
    var surName = this.props.details.surName;
    }

    return (
      <div >
            {userName}<br/>
            {firstName}    {surName}
      </div>
    );
  }
}

export default InformationComponent;