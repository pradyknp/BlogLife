/*npm install --save draft-js react react-dom*/
/*npm install --save react-autobind*/
/*npm install --save react-richtext*/

import React, { Component } from 'react';


import {
    BrowserRouter as Router,
    Route,
    Link
} from 'react-router-dom';

import Header from './components/headercomponent/Header.jsx';
import Footer from './components/footercomponent/Footer.jsx';
import Createpost from './components/viewcomponent/Createpost.jsx';
import Login from './components/usercomponent/Login.jsx';
import Signup from './components/usercomponent/Signup.jsx';
import Tableright from './components/viewcomponent/Tableright'
import LaunchBlog from './components/viewcomponent/LaunchBlog.jsx'
import HomepagewithSideBar from './components/viewcomponent/HomepagewithSideBar.jsx'
import Blogrender from './components/viewcomponent/Blogrender';

// import logo from './logo.svg';
import './css/App.css';
import 'babel-polyfill';


class App extends Component {

  render() {
    return (
        <Router>
            <div >
                <Header/>
                <Route exact path='/' component={HomepagewithSideBar} />
                <Route exact path='/category/*' component={HomepagewithSideBar} />
                <Route exact path='/Createpost' component={Createpost} />
                <Route exact path='/Login' component={Login} />
                <Route exact path='/Signup' component={Signup} />
                <Route exact path='/Blog/*' component={Tableright} />
                <Route exact path="/LaunchBlog" render={routeProps => <LaunchBlog {...routeProps} blogGetter={this.props.componentData} />}/>
               {/* <Footer/>*/}
            </div>
        </Router>
    );
  }
}

export default App;
