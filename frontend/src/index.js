import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import UserList from './components/UserList';
import Show from './components/Show';
import Create from './components/Create';
import Edit from './components/Edit';
import Home from './components/Home';
import Login from './components/Login';
import Logout from './components/Logout';
import Register from './components/Register';
import List from './components/List';
import Footer from './components/Footer';
import history from "./components/history";

ReactDOM.render(
<div class="mainContainer">

      <Router history={history}>
        <Switch>
            <Route path='/' exact={true} component={Login} />
            <Route path='/Register' exact={true} component={Register} />
            <Route path='/home' exact={true} component={Home} />
            <Route path='/create' exact={true} component={Create} />
            <Route path='/Show/:id' exact={true} component={Show} />
            <Route path='/ShowAll' exact={true} component={UserList} />
            <Route path='/edit/:id' exact={true} component={Edit} />
            <Route path='/List' exact={true} component={List} />
         </Switch>
      </Router> 
 
        <footer class="footer">
     <Footer/>
            </footer>
</div>,  
  document.getElementById('root')
);
