import React, { Component } from 'react';

class Signup extends Component {
    constructor(props){
        super(props);
        this.state={
            url:window.location.origin+"/BlogLife/Blogit",
            signedUp:false
        }
        this.createUser=this.createUser.bind(this);
        this.goBackToSignUp = this.goBackToSignUp.bind(this);
    }

    goBackToSignUp(){
        this.setState({
            signedUp:false
        });
    }
    createUser (){
        var local = this;
        var username= document.getElementById("username").value;
        var mailid = document.getElementById("mailid").value;
        var pwd = document.getElementById("password").value;
        var confirmpassword =  document.getElementById("confirmpassword").value;

        if(mailid === ""){
            alert("Please enter the mailid");
            return;
        }

        if(username === ""){
            alert("Please enter the username");
            return;
        }

        if(pwd === ""){
            alert("Please enter the username");
            return;
        }

        if(pwd !== confirmpassword){
            alert("passwords did not match. please re-type the password");
            document.getElementById("pwd").value="";
            document.getElementById("confirmpassword").value=""
        }
        // {"pwd":"Sandeep","mailid":"pradyknp19","username":"pradyknp102","tagLine":"ha ha"}

        var data={
            "pwd":pwd,
            "mailid":mailid,
            "username":username,
            "tagLine":""
        };


        var url =`${this.state.url}/createUser`;
        console.log(data);

        return fetch(url, {
            method: "POST",
            header:{
                "Accept":"application/json",
                "Content-Type":"application/json"
            },
            body:JSON.stringify(data)
        }).then(function(response) {
            console.log(response);

            if(response.status == 500)
            {
                alert("The user already exists");
                local.setState({
                    signedUp:false
                });
                return;
            }

            return response.json();

        }).then(function(data) {
            console.log("success");
            local.setState({
                signedUp:true
            });
            // window.location = "/BlogLife/Login";
        }).catch(function(err) {
            console.log(err);
        });
    }

    render() {
        if(!this.state.signedUp) {
            return (
                <div style={{'maxHeight': '900px', 'minHeight': '675px'}}>
                    <h2 style={{'margin-left': '45%', 'width': '10%'}}>Sign Up</h2>

                    <div className="imgcontainer">
                        <img src="img_avatar2.png" alt="Avatar" className="avatar"></img>
                    </div>

                    <div className="container">
                        <label><b>Username</b></label>
                        <input type="text" id="username" placeholder="Username"/>
                        <label><b>Mail ID</b></label>
                        <input type="email" id="mailid" placeholder="Mail ID"/>
                        <label><b>Password</b></label>
                        <input type="password" id="password" placeholder="Password"/>
                        <label><b>Confirm Password</b></label>
                        <input type="password" id="confirmpassword" placeholder="Password"/>
                        <button type="submit" onClick={this.createUser}>Create</button>
                    </div>

                </div>
            );
        }
        else{
            return(
                <div>
                    <div>
                        <p className="succBlogPost">Welcome !!! You have successfully signed up. Hope you enjoy the stay with us, visit the Login page to login </p></div>
                    <button className="goBack" onClick={this.goBackToSignUp}>GO Back</button>
                </div>
            )
        }
    }
}

export default Signup;