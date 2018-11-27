import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import 'bootstrap/dist/css/bootstrap.css';
import UserList from './components/UserList';
import Show from './components/Show';
import Create from './components/Create';
import Edit from './components/Edit';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import List from './components/List';
import Footer from './components/Footer';

ReactDOM.render(
<div>
      <Router>
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

   <footer>
     <Footer/>
  </footer>
</div>,  
  document.getElementById('root')
);
