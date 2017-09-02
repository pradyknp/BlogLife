import React, { Component } from 'react';
import {
    Link
} from 'react-router-dom';

class Header extends Component {
    render() {
        return (
            <header>

                <div>
                    LOGO
                </div>

                <h1>Welcome to Blogging Website</h1>
                <nav>
                <ul>
                    <li id="login-list"><Link to="/login">Logged In</Link></li>
                    <li><Link to="/">Home</Link></li>
                    <li><Link to="/Createpost" id="createPost">Create Post</Link></li>
                    <li><Link to="/Login">Login</Link></li>
                    <li><Link to="/Signup">Sign up</Link></li>
                </ul>
                </nav>
            </header>
        );
    }
}

export default Header;
