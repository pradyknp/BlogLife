/*npm install --save draft-js react react-dom*/
/*npm install --save react-autobind*/
/*npm install --save react-richtext*/
/*npm install properties-reader*/

import React, { Component } from 'react';


import {
    BrowserRouter as Router,
    Route
} from 'react-router-dom';

import Header from './components/headercomponent/Header.jsx';
/*import Footer from './components/footercomponent/Footer.jsx';*/
import Createpost from './components/viewcomponent/Createpost.jsx';
import Login from './components/usercomponent/Login.jsx';
import Logout from './components/usercomponent/Logout.jsx';
import Signup from './components/usercomponent/Signup.jsx';
import ViewContent from './components/viewcomponent/ViewContent';
import LaunchBlog from './components/viewcomponent/LaunchBlog.jsx';
import ViewFrame from './components/viewcomponent/ViewFrame.jsx';
import Auth from './Authentication/Auth';
/*import Blogrender from './components/viewcomponent/BlogComponent';*/

// import logo from './logo.svg';
import './css/App.css';
/*import PropertyReader from  'properties-reader';*/



class App extends Component {

    constructor(props) {
        super(props)

        this.state={
            isloggedin:false
        }
    }


    componentWillMount(){

        if (Auth.isUserAuthenticated()) {
            this.setState({
                isloggedin:true
            });
        } else {
             this.setState({
                 isloggedin:false
            });
        }

    }

  render() {
    return (
        <Router>
            <div >
                <Header loggedinProp={this.state.isloggedin} />
                <Route exact path='/BlogLife' component={ViewFrame} />
                <Route exact path='/BlogLife/myblogs' component={ViewFrame} />
                <Route exact path='/category/*' component={ViewFrame} />
                <Route exact path='/Createpost' component={Createpost} />
                <Route exact path='/Login' component={Login} />
                <Route exact path='/Logout' component={Logout} />
                <Route exact path='/Signup' component={Signup} />
                <Route exact path='/Blog/*' component={ViewContent} />
                <Route exact path="/LaunchBlog" render={routeProps => <LaunchBlog {...routeProps} blogGetter={this.props.componentData} />}/>
               {/* <Footer/>*/}
            </div>
        </Router>
    );
  }
}

export default App;
