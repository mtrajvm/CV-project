import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import UserList from './UserList';
import CvList from './CvList';
import SalesUserList from './SalesUserList';
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    Button, FormFeedback,
} from 'reactstrap';
import Logout from './Logout';
import InformationComponent from './InformationComponent';
class List extends Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [],

        }; 
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
        }   else if (sessionStorage.getItem('accounType') == "traineemanager") {
            this.props.history.push('/TraineeManList')
        }
        else if (sessionStorage.getItem('accounType') == "sales") {
           this.props.history.push('/SalesList')
        }

        return (       
            
           <div> 
            <div class="logout">
                    <Logout/>
                </div>
            <div class="changingContainer">

                {list}
            </div>
            </div>
        );
    }
}

export default List;