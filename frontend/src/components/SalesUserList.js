import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    Button, FormFeedback, Table
} from 'reactstrap';
import InformationComponent from './InformationComponent';
import { Document } from 'react-pdf'

class SalesUserList extends Component {

    constructor(props) {
        super(props);
        const traineeName = null;
        this.state = {
            users: [],
            searchString: '',
        };
    }

    componentDidMount() {
        axios.get('/api/sales/trainees')
            .then(res => {
                this.setState({ users: res.data });
            });
    }

    nextPath = (path) => {
        this.props.history.push(path);
    }

    filterList = (event) => {
        this.setState({ searchString: event.target.value })
    }

    pushToUser = (user) => {
        this.props.history.push('/SalesShow',
        {
            userData: user
        })
    }

    render() {
        let filteredNames = this.state.users.filter(user => user.userName.toLowerCase().search(this.state.searchString.toLowerCase()) !== -1)

        return (
            <div >
                <div class="infoContainer">
                    <InformationComponent userName={this.state.hoveredUser} />
                </div>
                <div class="smallList">
                    <input type="text" value={this.state.searchString} onChange={this.filterList.bind(this)} placeholder="Search..." />

                    <Table hover class="table table-stripe">
                        <thead>
                            <tr>
                                <th>UserName</th>
                                <th>First Name</th>
                                <th>Second Name</th>
                            </tr>
                        </thead>
                        <tbody >
                            {filteredNames.map(user =>
                                <tr onMouseEnter={() => this.setState({ hoveredUser: user.userName })}>
                                    <td><Button color="link" onClick={() => this.pushToUser(user)} >{user.userName}
                                        </Button></td>
                                    <td>{user.firstName}</td>
                                    <td>{user.secondName}</td>
                                </tr>
                            )}
                        </tbody>
                    </Table >
                </div>
                <div class="rightContainer">
                    
                    </div>
            </div>
        );
    }
}

export default SalesUserList;