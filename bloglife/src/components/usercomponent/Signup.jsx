import React, { Component } from 'react';

class Signup extends Component {
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
                              <button type="submit">Submit</button>
                            </div>

         </div>
        );
    }
}

export default Signup;