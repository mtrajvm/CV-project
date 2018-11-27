import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import UserList from './UserList';
import CvList from './CvList';
import Logout from './Logout';
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

        let list;
        if ( sessionStorage.getItem('accounType') == "admin") {
            list = <UserList />;
        } else if (sessionStorage.getItem('accounType') == "trainee") {
            list = <CvList details={this.props.location.details} />;
        }

        return (
            <div>
            <Logout/>
                {list}
            </div>
        );
    }
}

export default List;