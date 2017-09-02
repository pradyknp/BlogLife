import React, { Component } from 'react';
import ShowBlog from "./ShowBlog";
import ReactDOM from 'react-dom';


class Blogrender extends Component {

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
                <ShowBlog  blogDataProp={this.props.blogData}></ShowBlog>
                <hr/>
                <div>
                    Comment Section coming soon
                </div>
            </div>
        );
    }
}

export default Blogrender;
