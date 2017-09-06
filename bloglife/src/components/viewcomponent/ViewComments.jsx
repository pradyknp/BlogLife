import React, { Component } from 'react';
import CommentIt from './CommentGroup';
import {stateToHTML} from 'draft-js-export-html';

class Comments extends Component {

    constructor(props){
        super(props)
        this.state = {
            comments: [],
            commentsCount:0
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

        console.log("inside deleteComment");
        // var url = 'http://localhost:7777/BlogLife/Blogit/blog/delete/'+childdata.id;
        //
        // return fetch(url)
        //     .then((response) => response.json())
        //     .then((responseJson) => {
        //         console.log("Inside DeleteBlog");
        //         var pageNo = this.state.pageNo;
        //         var blogCount= this.state.blogcount-1;
        //         var pageSize = ((blogCount) - (this.state.pageSize * (pageNo - 1))) >= this.state.pageSize ? this.state.pageSize : blogCount%this.state.pageSize;
        //
        //         console.log("page No:"+pageNo+"Page Size"+pageSize);
        //
        //         if(pageSize == 0 && pageNo > 1){
        //             pageSize = this.state.pageSize;
        //             --pageNo;
        //         }
        //
        //
        //
        //         if(blogCount>0) {
        //             if (this.props.categoryProps == "getAll") {
        //                 this.getAllBlog(blogCount,pageNo, pageSize, this.state.category);
        //             }
        //             else {
        //                 this.getBlogByCategory(blogCount, pageNo, pageSize, this.state.category);
        //             }
        //         }
        //         else{
        //             if (this.props.categoryProps == "getAll") {
        //                 this.setState({
        //                     totalBlogCount:0,
        //                     blogcount:0,
        //
        //                 })
        //             }
        //             else {
        //                 this.setState({
        //                     blogcount:0,
        //                 })
        //             }
        //         }
        //
        //     })
        //     .catch((error) => {
        //         console.error(error)
        //     });

    }

    addComment(){

        var username = document.getElementById("username").value;
        var commentBody = document.getElementById("commentBody").value;
        var getAllComments=this.getAllComments;

        var data={
            "id":Math.floor((Math.random() * 200000) + 1),
            "body":commentBody,
            "username":username,
            "createdDate":"July 29th 2016",
            "modifiedDate":"July 34th 2016",
            "blogId":this.props.blogIDProps
        };

        var url ='http://localhost:7777/BlogLife/Blogit/blog/postComment';
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
                    <input  id="username" style={{'width': '50%','alignContent':'center', 'marginLeft':'25%'}} type="text" placeholder="User name"></input><br/>
                    <textarea id="commentBody" style={{'width': '50%','alignContent':'center', 'marginLeft':'25%','minHeight':'150px'}} placeholder="Add your comments"></textarea>
                </div>
                <button onClick={this.addComment}>Post Comments</button>
            <div id="displayAllComments" className="getAllComments">
                {this.state.comments.map((dynamicComponent, i) => <CommentIt deleteCommentProp={this.deleteComment.bind(this,dynamicComponent)}
                                                                            key = {i} commentData = {dynamicComponent} />)}
            </div>
            </div>
        );
    }
}

export default Comments;
