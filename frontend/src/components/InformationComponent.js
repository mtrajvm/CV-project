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
    return (
      <div class="container">
        <div class="panel panel-default">
            <h3 class="panel-title">
             {this.props.userName}
            </h3>
        </div>
      </div>
    );
  }
}

export default InformationComponent;