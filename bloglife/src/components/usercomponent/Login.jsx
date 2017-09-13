// npm install -save react-cookie
import React, { Component } from 'react';
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
            url:window.location.origin+"/BlogLife/Blogit",
            username:"pradyknp20",
            password:"Sandeep",
            token:""
        };

        this.sendCredentials = this.sendCredentials.bind(this);
        this.changeUserData = this.changeUserData.bind(this);
    }

    componentWillMount() {

    }

    changeUserData(e){
      this.setState({
         [e.target.name]:e.target.value
      });
    }

    sendCredentials(e){

        e.preventDefault();
       var url = `${this.state.url}/login`;
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

            if(response.status === 200) {
                document.getElementById("error").style.display="none";
                return response.json();
            }
            else if(response.status === 500) {
               document.getElementById("error").innerHTML="User does not exist. Please sign up";
                document.getElementById("error").style.display="block";
               return;
            }
            else if(response.status === 401){
                document.getElementById("error").innerHTML="Password entered is wrong";
                document.getElementById("error").style.display="block";
                return;
            }
            else {
                document.getElementById("error").innerHTML="Something Wrong";
                document.getElementById("error").style.display="block";
                return;
            }

        }).then(function(data) {
            console.log(data.token);

            Auth.authenticateUser(data.token,username);
            window.location.href="/BlogLife"

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
                     <input type="text" id="username"  name="username" placeholder="Username"  onChange={this.changeUserData}/>
                     <label><b>Password</b></label>
                     <input type="password" id="password" name="password"  onChange={this.changeUserData} placeholder="Password"/>
                     <div id="error" style={{'display':'none','color':'red','text-align':'center'}}></div>
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