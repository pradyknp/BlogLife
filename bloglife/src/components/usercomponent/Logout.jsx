import React, { Component } from 'react';
import Auth from '../../Authentication/Auth';
import Header from '../headercomponent/Header';

class Logout extends Component {

    constructor(props){
        super(props);
        this.state={
            url:window.location.origin+"/BlogLife/Blogit",
            //url:'http://localhost:7777/BlogLife/Blogit'
        }
        this.logged = false;
    }


    componentWillMount() {

        var token = "";
        var user =""
        if (Auth.isUserAuthenticated()) {
            token = Auth.getToken();
            user = Auth.getUser();
        }

         // Auth.deauthenticateUser();

        var url = `${this.state.url}/user/logout`;

        var data = {
            "user":user,
            "token":token
        };



        return fetch(url, {
            method: "POST",
            headers: {
                'Access-Control-Request-Headers': '*',
                'Authorization': token,
            },
            body: JSON.stringify(data)
        }).then(function (response) {
            console.log(response);
            Auth.deauthenticateUser();
            if(response.status === 200)
                console.log("success");
 
             window.location.href="/BlogLife";
        }).catch(function (err) {
            console.log(err);
        });



    }

    render() {



            return (
                <div>
                    You successfully logged out of the website. Please refresh the page.
                </div>
            );
        }
}

export default Logout;
