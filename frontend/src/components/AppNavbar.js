import React, { Component } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link, Redirect } from 'react-router-dom';

export default class AppNavbar extends Component {
  constructor(props) {
    super(props);
    this.state = {isOpen: false,};
    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  logout() {
   fetch('/api/logout', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                        'Accept': 'application/json',
                     }}
        )
  }

  render() {

    
    return (
      <div>
    <Navbar color="dark" dark expand="md">
      <NavbarBrand tag={Link} to="/List">Home</NavbarBrand>
      <NavbarToggler onClick={this.toggle}/>
      <Collapse isOpen={this.state.isOpen} navbar>
        <Nav className="ml-auto" navbar>
          <NavItem>
            <NavLink href="/Edit">
             {this.props.details.userName}
            </NavLink>
          </NavItem>
          <NavItem onClick = {this.logout()}>
              <NavLink href="/">
                LOGOUT
              </NavLink>
            </NavItem>
        </Nav>
      </Collapse>
</Navbar>
</div>
    );
  }
}
