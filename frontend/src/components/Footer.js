import React, { Component } from 'react';
import { Nav, NavItem, Row, Col } from 'reactstrap';
import { Grid } from 'react-bootstrap';
import StickyFooter from 'react-sticky-footer';

export default class Footer extends Component {

render() {
  return (
      <StickyFooter
    bottomThreshold={50}
    normalStyles={{
    backgroundColor: "rgba(255,255,255,.0)",
    padding: "2rem"
    }}
    stickyStyles={{
    backgroundColor: "rgba(255,255,255,.0)",
    padding: "2rem"
    }}
>
      <Grid>
        <Nav justified>
          <NavItem
            eventKey={1}>
            Privacy policy
          </NavItem>
          <NavItem
            eventKey={2}
            title="Item">
            Terms & Conditions
          </NavItem>
          <NavItem
            eventKey={3}>
            Technologies used
          </NavItem>
        </Nav>

        <div className="text-center small copyright">
          © SOMHD 3030
        </div>
      </Grid>


    </StickyFooter>
        
  )
}

}