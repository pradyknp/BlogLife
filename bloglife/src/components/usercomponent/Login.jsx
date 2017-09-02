import React, { Component } from 'react';

class Login extends Component {

    render() {
        return (
            <div style={{'maxHeight':'900px','minHeight':'675px'}}>
               <h2 style={{'margin-left':'45%','width':'10%'}}>Login Form</h2>

                 <div className="imgcontainer">
                   <img src="img_avatar2.png" alt="Avatar" className="avatar"></img>
                 </div>

                 <div className="container">
                 <label><b>Username</b></label>
                       <input type="text" id="username" placeholder="Username"/>
                  <label><b>Password</b></label>
                       <input type="password" id="password" placeholder="Password"/>
                                     <button type="submit">Submit</button>
                             </div>

                 <div className="container">
                   <span className="psw">Forgot <a href="#">password?</a></span>
                 </div>

            </div>
        );
    }
}

export default Login;