// npm install -save react-cookie
import React, { Component } from 'react';
import { instanceOf } from 'prop-types';
import Auth from '../../Authentication/Auth';

class Login extends Component {

    constructor(props,context){
        super(props, context);

        const storedMessage = localStorage.getItem('successMessage');
        let successMessage = '';

        if (storedMessage) {
            successMessage = storedMessage;
            localStorage.removeItem('successMessage');
        }

        this.state={
            errors:{},
            successMessage,
            isLoggedin:false,
            username:"pradyknp20",
            password:"Sandeep",
            token:""
        };

        this.sendCredentials = this.sendCredentials.bind(this);
        this.changeUserData = this.changeUserData.bind(this);
    }

    componentWillMount() {
        const { cookies } = this.props;
    }

    changeUserData(e){
      this.setState({
         [e.target.name]:e.target.value
      });
    }

    sendCredentials(e){
        const { cookies } = this.props;

        e.preventDefault();
       var url = 'http://localhost:7777/BlogLife/Blogit/login'
        var username = this.state.username;
       var password = this.state.password;

       var data ={
           "username":username,
           "pwd":password
       }

        return fetch(url, {
            method: "POST",
            body:JSON.stringify(data)
        }).then(function(response) {
            console.log(response);
            return response.json();
        }).then(function(data) {
            console.log(data.token);

            Auth.authenticateUser(data.token,username);
            window.location.href="/"

        }).catch(function(err) {
            console.log(err);
        });
    }

    render() {
        return (
            <div style={{'maxHeight':'900px','minHeight':'675px'}}>
               <h2 style={{'marginLeft':'45%','width':'10%'}}>Login Form</h2>

                 <div className="imgcontainer">
                   <img src="img_avatar2.png" alt="Avatar" className="avatar"></img>
                 </div>

                 <div className="container">
                 <label><b>Username</b></label>
                       <input type="text" id="username"  name="username" placeholder="Username" value={this.state.username} onChange={this.changeUserData}/>
                  <label><b>Password</b></label>
                       <input type="password" id="password" name="password" value={this.state.password}  onChange={this.changeUserData} placeholder="Password"/>
                                     <button type="submit" onClick={this.sendCredentials}>Submit</button>
                             </div>

                 <div className="container">
                   <span className="psw">Forgot <a href="#">password?</a></span>
                 </div>

            </div>
        );
    }
}


export default Login;