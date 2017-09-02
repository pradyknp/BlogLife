import React, { Component } from 'react';
import {
    Link
} from 'react-router-dom';


class BlogIt extends Component {

    constructor(props){
        super(props);

        this.bloglaunch=this.bloglaunch.bind(this);
    }

    bloglaunch(){
        console.log("inside bloglaunch");
     /*return (<Router><Route path="/launchBlog" render={routeProps => <launchBlog {...routeProps} blogGetter={this.props.componentData} />}/></Router>);*/
    }

    render() {
        var title = `${this.props.componentData.title}`;
        title=title.split(" ").join("_");
        return (
            <div>

                <div className="allBlogs" ref="allBlogs">
                    <div className="blogTitle"><h2 style={{'width':'100%'}}><Link to={`/Blog/`+title}>{this.props.componentData.title}</Link></h2>
                        <button id="deleteBlog" className="deleteBlogBut" name="deleteBlog" onClick={this.props.deleteBlogProp}>x</button></div>
                    <h6> Created By <b>{this.props.componentData.username}</b> on April 25 2017 10:30 pm &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Comments {this.props.componentData.comments}</a></h6>
                    <br/>
                    <div> {this.props.componentData.body.substring(0,100)}.....</div>
                    <div className="likeDislike">Like&nbsp;{this.props.componentData.likes} &nbsp; &nbsp; &nbsp; Dislike&nbsp;{this.props.componentData.dislikes}</div>
                </div>
            </div>
        );
    }
}

export default BlogIt;

