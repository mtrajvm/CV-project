import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    Button, FormFeedback,
} from 'reactstrap';
import { Link } from 'react-router-dom';
import base64 from 'base-64';
import axios from 'axios';
class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            'email': '',
            'password': '',
            validate: {
                passwordState: '',
                emailState: '',
            },
        }
        this.handleChange = this.handleChange.bind(this);
    }

    validateEmail(e) {
        const emailRex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        const { validate } = this.state
        if (emailRex.test(e.target.value)) {
            validate.emailState = 'has-success'
        } else {
            validate.emailState = 'has-danger'
        }
        this.setState({ validate })
    }

    validatePassword(e) {
        const passwordRex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$/;
        const { validate } = this.state
        if (passwordRex.test(e.target.value)) {
            validate.passwordState = 'has-success'
        } else {
            validate.passwordState = 'has-danger'
        }
        this.setState({ validate })
    }

    handleChange = async (event) => {
        const { target } = event;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const { name } = target;
        await this.setState({
            [name]: value,
        });
    }

    handleLoginSubmit = (event) => {
        event.preventDefault();
        const details = Object.create(null, {
            userName: { value: this.state.email, enumerable: true },
            password: { value: this.state.password, enumerable: true },
        })
        fetch('/api/login/' + this.state.email, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + base64.encode(this.state.email + ":" + this.state.password)
            },
            body: JSON.stringify(details),
        }).then(response => {
            if(response.status==200){
            var toRet = response.json();
            return toRet;
            }else{
                return false;
            }
        }).then(data=>{            
            if(data.accountType!=null){
                sessionStorage.setItem('accounType', data.accountType);
                sessionStorage.setItem('id', data.id);
                sessionStorage.setItem('userName', data.userName);
                sessionStorage.setItem('firstName', data.firstName);
                sessionStorage.setItem('surName', data.surName);
                
                this.props.history.push({
                    pathname: '/List',
                    details: data

                })
        }})
            
    }

    submitForm = (e) => {
        e.preventDefault();
        if (this.state.validate.emailState === 'has-success' && this.state.validate.passwordState === 'has-success') {
            this.handleLoginSubmit(e)
        }

    }

    logout() {
        localStorage.removeItem('user');
    }

    render() {
        const { email, password } = this.state;
        return (
            <div class="login">

         


                <Form className="form" onSubmit={(event) => this.submitForm(event)}>

                    <FormGroup row>
                        <Label for="email" sm={2} md={{ size: 6, offset: 3 }}>Email</Label>
                        <Col sm={12} md={{ size: 6, offset: 3 }}>
                            <Input
                                type="email"
                                name="email"
                                id="email"
                                placeholder="trainee@qa.com"
                                value={email}
                                valid={this.state.validate.emailState === 'has-success'}
                                invalid={this.state.validate.emailState === 'has-danger'}
                                onChange={(event) => {
                                    this.validateEmail(event)
                                    this.handleChange(event)
                                }}
                            />
                            <FormFeedback invalid>
                                You must use your QA email
                </FormFeedback>

                        </Col>
                    </FormGroup>


                    <FormGroup row>
                        <Label for="password" sm={2} md={{ size: 6, offset: 3 }}>Password</Label>
                        <Col sm={12} md={{ size: 6, offset: 3 }}>
                            <Input invalid
                                type="password"
                                name="password"
                                id="password"
                                placeholder="********"
                                value={password}
                                valid={this.state.validate.passwordState === 'has-success'}
                                invalid={this.state.validate.passwordState === 'has-danger'}
                                onChange={(event) => {
                                    this.validatePassword(event)
                                    this.handleChange(event)
                                }}
                            />

                            <FormFeedback invalid>
                                Password requires 1 uppercase, 1 lowercase, and at least 5 characters
            </FormFeedback>
                        </Col>
                    </FormGroup>


                    <center>
                        <Button type="submit" outline color="primary" size="lg">Log on</Button>
                    </center>
                </Form>
                <center>
                    <br></br>
                    <Link to="/Register"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Create an account</Link>
                </center>
            </div>
        );
    }
}

export default Login;