import React, { Component } from 'react';
import CommentGroup from './CommentGroup';
import {stateToHTML} from 'draft-js-export-html';
import Auth from '../../Authentication/Auth';

class Comments extends Component {

    constructor(props){
        super(props)
        this.state = {
            comments: [],
            commentsCount:0,
            user:Auth.getUser()
            }
            this.addComment=this.addComment.bind(this);
            this.getAllComments = this.getAllComments.bind(this);
    }

    getAllComments() {
        var commentCount=0;

        var url =`http://localhost:7777/BlogLife/Blogit/blog/getComments?blogId=${this.props.blogIDProps}`;
        fetch(url)
            .then((response) => response.json())
            .then((responseJson) => {
                console.log("Inside getComments");
                commentCount = responseJson.length;
                this.setState({
                    comments:responseJson,
                    commentsCount:commentCount
                });


            })
            .catch((error) => {
                console.error(error);
            });
    }

    componentWillMount() {
        this.getAllComments();
    }

    deleteComment(childdata,event){

        var token = "";
        var getAllComments=this.getAllComments;

        if (Auth.isUserAuthenticated()) {
            token = Auth.getToken();
            this.setState({
                user:Auth.getUser()
            })
        }
        else{
            alert("please login");
            return;
        }


        console.log("inside deleteComment");
        var url = 'http://localhost:7777/BlogLife/Blogit/comment/'+childdata.id;

        return fetch(url,{
                method:"DELETE",
                headers: {
                    'Access-Control-Request-Headers': '*',
                    'Authorization': token,
                }
            })
            .then((response) => {

                console.log("success");
                document.getElementById("commentBody").value="";
                document.getElementById("username").value="";
                getAllComments();

            })

            .catch((error) => {
                console.error(error)
            });

    }

    addComment(){

        var username = document.getElementById("username").value;
        var commentBody = document.getElementById("commentBody").value;
        var getAllComments=this.getAllComments;

        var token= ""
        if (Auth.isUserAuthenticated()) {
            token = Auth.getToken();
        }else{
            alert("please login to post comments");
            return;
        }

        var date = new Date();
        var dateString = date.toString().split("GMT")[0];

        var data={
            "id":Math.floor((Math.random() * 200000) + 1),
            "body":commentBody,
            "username":username,
            "createdDate":dateString,
            "modifiedDate":dateString,
            "blogId":this.props.blogIDProps
        };

        var url ='http://localhost:7777/BlogLife/Blogit/blog/postComment';
        console.log(data);

        return fetch(url, {
            method: "POST",
            headers: {
                'Access-Control-Request-Headers': '*',
                'Authorization': token,
            },
            body:JSON.stringify(data)
        }).then(function(response) {
            console.log(response);
            return response.json();
        }).then(function(data) {
            console.log("success");
            document.getElementById("commentBody").value="";
            document.getElementById("username").value="";
            getAllComments();
        }).catch(function(err) {
            console.log(err);
        });
    }


    render() {

        return (
            <div>
                <h2 style={{'textAlign': 'center'}}>Comments Sections</h2>
                <div>
                    <input  id="username" disabled style={{'width': '50%','alignContent':'center', 'marginLeft':'25%'}} type="text" value={this.state.user} placeholder="User name"></input><br/>
                    <textarea id="commentBody" style={{'width': '50%','alignContent':'center', 'marginLeft':'25%','minHeight':'150px'}} placeholder="Add your comments"></textarea>
                </div>
                <button onClick={this.addComment}>Post Comments</button>
            <div id="displayAllComments" className="getAllComments">
                {this.state.comments.map((dynamicComponent, i) => <CommentGroup deleteCommentProp={this.deleteComment.bind(this,dynamicComponent)}
                                                                            key = {i} commentData = {dynamicComponent} />)}
            </div>
            </div>
        );
    }
}

export default Comments;
