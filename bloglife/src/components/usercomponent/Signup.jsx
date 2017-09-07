import React, { Component } from 'react';

class Signup extends Component {
    constructor(props){
        super(props);

        this.createUser=this.createUser.bind(this);
    }

    createUser (){
        var username= document.getElementById("username").value;
        var mailid = document.getElementById("mailid").value;
        var pwd = document.getElementById("password").value;
        var confirmpassword =  document.getElementById("confirmpassword").value;

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


        var url ='http://localhost:7777/BlogLife/Blogit/createUser';
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
            return response.json();
        }).then(function(data) {
            console.log("success");
            window.location = "/Login";
        }).catch(function(err) {
            console.log(err);
        });
    }

    render() {
        return (
             <div style={{'maxHeight':'900px','minHeight':'675px'}}>
                           <h2 style={{'margin-left':'45%','width':'10%'}}>Sign Up</h2>

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
}

export default Signup;