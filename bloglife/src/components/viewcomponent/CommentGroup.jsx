import React, { Component } from 'react';

class CommentIt extends Component {

    constructor(props){
        super(props);

        this.bloglaunch=this.bloglaunch.bind(this);
    }

    bloglaunch(){
        console.log("inside bloglaunch");
        /*return (<Router><Route path="/launchBlog" render={routeProps => <launchBlog {...routeProps} blogGetter={this.props.componentData} />}/></Router>);*/
    }

    render() {

        return (
            <div>

                <div className="allComments" ref="allComments">
                    <div className="commentTitle">
                        <button id="commentBlog" className="deleteCommentBut" name="commentBlog" onClick={this.props.deleteCommentProp}>x</button></div>
                    <h6><b>{this.props.commentData.username}</b> commented on April 25 2017 10:30 pm &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h6>
                    <br/>
                    <div> {this.props.commentData.body}</div>
                    <div className="likeDislike">Like&nbsp;{this.props.commentData.likes} &nbsp; &nbsp; &nbsp; Dislike&nbsp;{this.props.commentData.dislikes}</div>
                </div>
            </div>
        );
    }
}

export default CommentIt;

