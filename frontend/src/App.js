import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import UserList from './components/UserList';
import Show from './components/Show';
import Create from './components/Create';
import Edit from './components/Edit';
import Home from './components/Home';
import AppNavbar from './components/AppNavbar';
import Login from './components/Login';
import Register from './components/Register';
import List from './components/List';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Login}/>
          <Route path='/Register' exact={true} component={Register}/>
          <Route path='/home' exact={true} component={Home}/>
          <Route path='/create' exact={true} component={Create}/>
          <Route path='/Show/:id' exact={true} component={Show}/>
          <Route path='/ShowAll' exact={true} component={UserList}/>
          <Route path='/edit/:id' exact={true} component={Edit}/>
          <Route path='/list' exact={true} component={List}/>
         </Switch>
      </Router>  
    );
  }
}

export default App;