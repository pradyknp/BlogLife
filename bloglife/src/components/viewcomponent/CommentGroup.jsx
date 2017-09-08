import React, { Component } from 'react';
import Auth from '../../Authentication/Auth';
import downvote from '../../images/downvote.png';
import upvote from '../../images/upvote.png';

class CommentGroup extends Component {

    constructor(props){
        super(props);

        this.bloglaunch=this.bloglaunch.bind(this);
    }

    bloglaunch(){
        console.log("inside bloglaunch");
        /*return (<Router><Route path="/launchBlog" render={routeProps => <launchBlog {...routeProps} blogGetter={this.props.componentData} />}/></Router>);*/
    }

    render() {
        var username ="";
        var style ={
            'display':'none'
        }

        if (Auth.isUserAuthenticated()) {
            username=Auth.getUser();
        }



        if(username === `${this.props.commentData.username}`)
        {
            style ={
                'display':'block'
            }
        }

        return (
            <div>

                <div className="allComments" ref="allComments">
                    <div className="commentTitle">
                        <button id="commentBlog" style={style} className="deleteCommentBut" name="commentBlog" onClick={this.props.deleteCommentProp}>x</button></div>
                    <h6><b>{this.props.commentData.username}</b> commented on {this.props.commentData.modifiedDate} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h6>
                    <br/>
                    <div> {this.props.commentData.body}</div>
                    <div className="likeDislike"><button className="upvoteBut"><img src={upvote} className="upvote"></img></button>&nbsp;3{this.props.commentData.likes} &nbsp; &nbsp; &nbsp; <button className="upvoteBut"><img src={downvote} className="upvote"></img></button>&nbsp;3{this.props.commentData.dislikes}</div>
                </div>
            </div>
        );
    }
}

export default CommentGroup;

