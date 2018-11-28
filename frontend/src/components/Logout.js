
import React, { Component } from 'react';
import { Button } from 'reactstrap';
import { Link } from 'react-router-dom';

class Logout extends Component {
    
async logout() {
  await fetch('/api/logout', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                        'Accept': 'application/json',
                     }}
   ).then(sessionStorage.clear()) ;            

  }

render() {
    return (
        <Link to='/'>
            <Button color="link" onClick={this.logout} className="logout">Logout</Button>     
            </Link>  
    )
}
}

export default Logout;