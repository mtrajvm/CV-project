import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import UserList from './components/UserList';
import Show from './components/Show';
import Create from './components/Create';
import Edit from './components/Edit';
import Home from './components/Home';
class App extends Component {
  render() {
    return (
     <div> 
      <div>HELLO THERE </div>
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/create' exact={true} component={Create}/>
          <Route path='/Show/:id' exact={true} component={Show}/>
          <Route path='/ShowAll' exact={true} component={UserList}/>
          <Route path='/edit/:id' exact={true} component={Edit}/>
         </Switch>
      </Router> 
      </div>   
    );
  }
}

export default App;