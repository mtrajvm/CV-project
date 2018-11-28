import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import {
  Container, Col, Form,
  FormGroup, Label, Input,
  Button, FormFeedback
} from 'reactstrap';
import { Link } from 'react-router-dom';
import AppNavbar from './AppNavbar';

class Login extends Component {

  constructor(props) {
    super(props);
      this.state = {
      'email': '',
      'password': '',
      'passwordConfirm': '',
      'firstName':'',
      'surName' : '',
      validate: {
        passwordState: '',
        passwordConfirmState: '',
        emailState: '',
        registration: '',
      },
    }
    this.handleChange = this.handleChange.bind(this);
  }

  validateEmail(e) {
    const emailRex =  /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    const { validate } = this.state
      if (emailRex.test(e.target.value)) {
        validate.emailState = 'has-success'
      } else {
        validate.emailState = 'has-danger'
      }
      this.setState({ validate })
    }

    validatePassword(e)
    {
        const passwordRex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$/;
        const { validate } = this.state
            if (passwordRex.test(e.target.value)) {
            validate.passwordState = 'has-success'
            } else {
            validate.passwordState = 'has-danger'
            }
            this.setState({ validate })
    }

       validatePasswordConfirm(e)
    {
        const { validate } = this.state
            if (this.state.password === e.target.value && validate.passwordState == 'has-success') {
            validate.passwordConfirmState = 'has-success'
            } else {
            validate.passwordConfirmState = 'has-danger'
            }
            this.setState({ validate })
    }

    handleChange = async (event) => {
        const { target } = event;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const { name } = target;
        await this.setState({
        [ name ]: value,
        });
    }

  onSubmit = (e) => {
    e.preventDefault();
    const details = Object.create(null, {
            userName: {value:this.state.email, enumerable: true},
            password: {value:this.state.password, enumerable: true},
            firstName: {value:this.state.firstName},
            surName: {value:this.state.surName}
        })
    //return
    fetch('/api/createaccount/user', {   
      method : 'POST',
      headers: {
        'Content-Type' : 'application/json',
        'Accept' : 'application/json'
      },
      body: JSON.stringify(details)
    }).then(res=>{console.log(res.json())
    return true;
    })
    return false;
    
  }


  render() {
    const { firstName, surName, email, password, passwordConfirm } = this.state;
    return (
        <Container> 
            <h3 class="panel-title">
              REGISTRATION PAGE
            </h3>

        
        <Form className="form" onSubmit={(event) => this.onSubmit(event)}>
              <FormGroup row>
                <Label for="firstName" sm={2} md={{ size: 6, offset: 3 }}>First Name</Label>
                <Col sm={12} md={{ size: 6, offset: 3 }}>
                <Input 
                    type="text"
                    name="firstName"
                    id="firstName"
                    placeholder="first name"
                    value = { firstName }
                    onChange={ (event) => {
                            this.validateEmail(event)
                            this.handleChange(event) } }
                />
                </Col>
            </FormGroup>

              <FormGroup row>
                <Label for="surName" sm={2} md={{ size: 6, offset: 3 }}>Surname</Label>
                <Col sm={12} md={{ size: 6, offset: 3 }}>
                <Input 
                    type="text"
                    name="surName"
                    id="surName"
                    placeholder="second name"
                    value = { surName }
                    onChange={ (event) => {
                            this.validateEmail(event)
                            this.handleChange(event) } }
                />
                </Col>
            </FormGroup>

            <FormGroup row>
                <Label for="email" sm={2} md={{ size: 6, offset: 3 }}>Email</Label>
                <Col sm={12} md={{ size: 6, offset: 3 }}>
                <Input 
                    type="email"
                    name="email"
                    id="email"
                    placeholder="trainee@qa.com"
                    value = { email }
                    valid={ this.state.validate.emailState === 'has-success' }
                    invalid={ this.state.validate.emailState === 'has-danger' }
                    onChange={ (event) => {
                            this.validateEmail(event)
                            this.handleChange(event) } }
                />
                </Col>
                <FormFeedback invalid>
                    You must use your QA email
                </FormFeedback>
            </FormGroup>
            

            <FormGroup row>
              <Label for="password" sm={2} md={{ size: 6, offset: 3 }}>Password</Label>
              <Col sm={12} md={{ size: 6, offset: 3 }}>
              <Input
                type="password"
                name="password"
                id="password"
                placeholder="********"
                value={ password }
                valid={ this.state.validate.passwordState === 'has-success' }
                invalid={ this.state.validate.passwordState === 'has-danger' }
                onChange={ (event) => {
                    this.validatePassword(event)
                    this.handleChange(event) } }
              />
              <FormFeedback invalid>
                Password requires 1 uppercase, 1 lowercase, and at least 5 characters
            </FormFeedback>
              </Col>
            </FormGroup>



            <FormGroup row>
              <Label for="passwordConfirm" sm={2} md={{ size: 6, offset: 3 }}>Confirm Password</Label>
              <Col sm={12} md={{ size: 6, offset: 3 }}>
              <Input
                type="password"
                name="passwordConfirm"
                id="passwordConfirm"
                placeholder="********"
                value={ passwordConfirm }
                valid={ this.state.validate.passwordConfirmState === 'has-success' }
                invalid={ this.state.validate.passwordConfirmState === 'has-danger' }
                onChange={ (event) => {
                    this.validatePasswordConfirm(event)
                    this.handleChange(event) } }
              />
               <FormFeedback invalid>
                Password's do not match
            </FormFeedback>
              </Col>
            </FormGroup>
          
            <center>
          <Button type="submit" outline color="primary" size ="lg">Create account</Button>
          </center>
        </Form>
        <center>
            <br></br>
            <Link to="/"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Go back</Link>
        </center>
        </Container>
    );
  }
}

export default Login;