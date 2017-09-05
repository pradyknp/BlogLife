import React, { Component } from 'react';
import {
    Link
} from 'react-router-dom';


class BlogIt extends Component {

    constructor(props){
        super(props);

        this.bloglaunch=this.bloglaunch.bind(this);
    }

    rawMarkup()
    {
        var rawMarkup = this.props.componentData.body.substring(0,100);
        return {__html: rawMarkup};
    }

bloglaunch(){
        console.log("inside bloglaunch");
     /*return (<Router><Route path="/launchBlog" render={routeProps => <launchBlog {...routeProps} blogGetter={this.props.componentData} />}/></Router>);*/
    }

    render() {
        var title = `${this.props.componentData.title}`;
        title=title.split(" ").join("_");

        var titleTodispay = `${this.props.componentData.title}`;
        titleTodispay = (titleTodispay.length > 60) ?  titleTodispay.substring(0,60)+"..." : titleTodispay;

         return (
            <div>

                <div className="allBlogs" ref="allBlogs">
                    <div className="blogTitle"><h3 style={{'width':'100%'}}><Link to={`/Blog/`+title}>{titleTodispay}</Link></h3>
                        <button id="deleteBlog" className="deleteBlogBut" name="deleteBlog" onClick={this.props.deleteBlogProp}>x</button></div>
                    <h6> Created By <b>{this.props.componentData.username}</b> on {this.props.componentData.createdDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Comments {this.props.componentData.comments}</a></h6>
                    <br/>
                    <div> <span dangerouslySetInnerHTML={this.rawMarkup()}/></div>
                    <div className="likeDislike">Like&nbsp;{this.props.componentData.likes} &nbsp; &nbsp; &nbsp; Dislike&nbsp;{this.props.componentData.dislikes}</div>
                </div>
            </div>
        );
    }
}

export default BlogIt;

