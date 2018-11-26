import React, { Component } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';

export default class AppNavbar extends Component {
  constructor(props) {
    super(props);
    this.state = {isOpen: false,
                  user: {}
                  };
    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  componentDidMount() {
    fetch('/users/'+this.props.match.params.id, {
          headers: { 'Content-Type': 'application/json',
                        'Accept': 'application/json',
                     }})
      .then(res => {
        this.setState({ user: res.data });
        console.log(this.state.user);
      });
  }
  render() {
    return <Navbar color="dark" dark expand="md">
      <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
      <NavbarToggler onClick={this.toggle}/>
      <Collapse isOpen={this.state.isOpen} navbar>
        <Nav className="ml-auto" navbar>
          <NavItem>
            <NavLink href="https://github.com/oktadeveloper/okta-spring-boot-react-crud-example">
              {this.state.user.userName}
            </NavLink>
          </NavItem>
        </Nav>
      </Collapse>
    </Navbar>;
  }
}