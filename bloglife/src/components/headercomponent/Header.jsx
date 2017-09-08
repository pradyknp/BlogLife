import React, { Component } from 'react';
import Auth from '../../Authentication/Auth';

import {
    Link
} from 'react-router-dom';

class Header extends Component {

    render() {
        console.log("inside header:"+this.props.loggedinProp);
        if(this.props.loggedinProp){
            var currentUser= Auth.getUser();
            return (
                        <header>
                            <div>
                                LOGO
                            </div>
                            <div style={{'display':'inline-flex'}}>
                            <span><h1>Welcome to Blogging Website</h1> </span>
                              <span className="input-group">
                               <label htmlFor="general-search-search-input" className="isvishidden">Search the site</label>
                                 <input type="text" className="input-search" name="q" ref="general-search-search-input" placeholder="Search By Title"></input>
                                <button className="btn btn-go">GO</button>

                              </span>

                             <span>
                            <nav>
                            <ul>
                                <li><Link to="/myblogs">Hi &nbsp; {currentUser}!</Link></li>
                                <li><Link to="/BlogLife">Home</Link></li>
                                <li><Link to="/Createpost" id="createPost">Create Post</Link></li>
                                <li><Link to="/Logout">Logout</Link></li>
                            </ul>
                            </nav>
                            </span>
                           </div>
                        </header>
           );
        }
        else{
        return (
            <header>

                <div>
                    LOGO
                </div>
                <div style={{'display':'inline-flex'}}>
                <span><h1>Welcome to Blogging Website</h1></span>
                 <span className="input-group">
                     <label htmlFor="general-search-search-input" className="isvishidden">Search the site</label>
                     <input type="text" style={{'marginLeft':'150% !important'}} className="input-search" name="q" ref="general-search-search-input" placeholder="Search By Title"></input>
                      <button className="btn btn-go">GO</button>
                       </span>
                   <span>
                <nav>
                <ul>
                    <li id="login-list"><Link to="/login">Logged In</Link></li>
                    <li><Link to="/BlogLife">Home</Link></li>
                    <li><Link to="/Login">Login</Link></li>
                    <li><Link to="/Signup">Sign up</Link></li>
                </ul>
                </nav>
                </span>
                </div>
            </header>
        );
       }
    }
}

export default Header;
