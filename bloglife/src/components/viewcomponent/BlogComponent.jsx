import React, { Component } from 'react';
import ViewBlogContent from "./ViewBlogContent";
import ReactDOM from 'react-dom';
import Comments from './ViewComments';
import downvote from '../../images/downvote.png';
import upvote from '../../images/upvote.png';
import Auth from '../../Authentication/Auth';


class BlogComponent extends Component {
    constructor(props){
        super(props);

        this.state={
            likes:this.props.blogData.likes,
            dislikes:this.props.blogData.dislikes,
            likestate:true,
            dislikestate:true,
            isLoggedin:Auth.isUserAuthenticated(),
            user:Auth.getUser()
        }
        this.likeBlog=this.likeBlog.bind(this);
        this.dislikeBlog=this.dislikeBlog.bind(this);
        this.callAPI = this.callAPI.bind(this);

    }

    likeBlog(){
        if(this.state.isLoggedin){
            if(this.state.likestate){
                this.callAPI(this.props.blogData.id,"like",this.state.dislikestate,!this.state.likestate)
            }
            else {
                this.callAPI(this.props.blogData.id,"unlike",this.state.dislikestate,!this.state.likestate)
            }
        }
        else{
            alert("please login to like/dislike the blog");
            return;
        }

    }

    dislikeBlog(){
        if(this.state.isLoggedin){
            if(this.state.dislikestate){
                this.callAPI(this.props.blogData.id,"dislike",!this.state.dislikestate,this.state.likestate);
            }else{
                this.callAPI(this.props.blogData.id,"undislike",!this.state.dislikestate,this.state.likestate);
            }
        }
        else{
            alert("please login to like/dislike the blog");
            return;
        }
    }

    callAPI(blogID,command,dislikestate,likestate){

        var token = Auth.getToken();
        var url = 'http://localhost:7777/BlogLife/Blogit/blog/'+blogID+'/'+command;

        return fetch(url, {
                method: "Post",
                headers: {
                    'Access-Control-Request-Headers': '*',
                    'Authorization': token,
                }
            }
        ).then((response) => response.json())
            .then((responseJson) => {
                console.log("Inside like/dislike/unlike/undislike");

                this.setState({
                    likes:responseJson.likes,
                    dislikes:responseJson.dislikes,
                    likestate:likestate,
                    dislikestate:dislikestate,
                }, function () {

                });
            })
            .catch((error) => {
                console.error(error);
            });
    }

    componentWillMount(){
     var blogAll = document.getElementById("displayBlogPagination");
     var blogindividual =  document.getElementById("individualBlogData");

     if(blogAll != null)
        blogAll.style.display="none";

     if(blogindividual != null)
        blogindividual.style.display="block";
    }

    render() {

        return (
            <div id ="showBlog"   className="showBlog" >
                <ViewBlogContent  blogDataProp={this.props.blogData}></ViewBlogContent>
                <div className="likeDislike"><button onClick={this.likeBlog} className="upvoteBut"><img src={upvote} className="upvote">
                </img></button>&nbsp;{this.state.likes} &nbsp; &nbsp; <button onClick={this.dislikeBlog} className="upvoteBut"><img src={downvote} className="upvote"></img></button>&nbsp;{this.state.dislikes}</div>
                <hr/>
                <div>
                    <Comments blogIDProps={this.props.blogData.id}/>
                </div>
            </div>
        );
    }
}

export default BlogComponent;
