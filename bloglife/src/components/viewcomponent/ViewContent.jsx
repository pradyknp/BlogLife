// npm install -save react-loading

import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import ReactLoading from 'react-loading';
import Auth from '../../Authentication/Auth';
import loading from '../../images/loading.gif'

import {
    BrowserRouter as Router,
    Route,
    Link
} from 'react-router-dom';

import BlogGroups from "./BlogGroups";
import BlogComponent from "./BlogComponent"


//npm install react-js-pagination

class ViewContent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            isLoadingAll:true,
            url:window.location.origin+"/BlogLife/Blogit",
            dataSource:[],
            category:"getAll",
            totalBlogCount:0,
            blogcount:0,
            pageSize:5,
            pageNo:1,
            username:Auth.getUser(),
            blogRoute:[],
            blogData:{
            }
        }

        this.paginationDec = this.paginationDec.bind(this);
        this.paginationInc= this.paginationInc.bind(this);
    }

    paginationInc(){

        var pageNo = ++this.state.pageNo;
        console.log(pageNo+"  "+this.state.pageNo);
        var pageSize = (this.state.blogcount - (this.state.pageSize * (pageNo - 1))) >= this.state.pageSize ? this.state.pageSize : this.state.blogcount%this.state.pageSize;

        console.log(pageSize);
        if(this.state.blogcount > 0) {
            if (this.props.categoryProps == "getAll") {
                this.getAllBlog(this.state.blogcount,pageNo,pageSize, this.state.category);
            }
            else {
                this.getBlogByCategory(this.state.blogcount, pageNo, pageSize,  this.state.category);
            }
        }

    }

    paginationDec(){

        var pageNo = --this.state.pageNo;

        var pageSize = (this.state.blogcount - (this.state.pageSize * (pageNo - 1))) >= this.state.pageSize ? this.state.pageSize : this.state.blogcount%this.state.pageSize;
        console.log(pageSize);
        if(this.state.blogcount > 0) {
            if (this.props.categoryProps == "getAll") {
                this.getAllBlog(this.state.blogcount,pageNo, pageSize, this.props.categoryProps);
            }
            else {
                this.getBlogByCategory(this.state.blogcount, pageNo, pageSize, this.props.categoryProps);
            }
        }


    }

    launchBlog(childdata,event){
        console.log(" inside launch Blog")

        var routes =[
            {
                path: '/Blog/'+childdata.title.split(" ").join("_"),
                exact: true,
                main: () => <BlogComponent blogData={this.state.blogData}/>
            }];

        this.setState({
            isLoading:false,
            isLoadingAll:false,
            blogData:{
                title:childdata.title,
                body:childdata.body,
                username:childdata.username
            },
            blogRoute:routes
        });
    }

    deleteBlog(childdata,event){

        var token = "";
        if (Auth.isUserAuthenticated()) {
            token = Auth.getToken();
        }
        else{
            alert("You need to login to delete the blogs");
            return;
        }

      var url = `${this.state.url}/blog/`+childdata.id;

        return fetch(url,{
            method:"DELETE",
            headers: {
                'Access-Control-Request-Headers': '*',
                'Authorization': token,
            }
        }).then((response) => response.json())
            .then((responseJson) => {
                console.log("Inside DeleteBlog");
                var pageNo = this.state.pageNo;
                var blogCount= this.state.blogcount-1;
                var pageSize = ((blogCount) - (this.state.pageSize * (pageNo - 1))) >= this.state.pageSize ? this.state.pageSize : blogCount%this.state.pageSize;

                console.log("page No:"+pageNo+"Page Size"+pageSize);

                if(pageSize == 0 && pageNo > 1){
                    pageSize = this.state.pageSize;
                    --pageNo;
                }



                if(blogCount>0) {
                    if (this.props.categoryProps == "getAll") {
                        this.getAllBlog(blogCount,pageNo, pageSize, this.state.category);
                    }
                    else if(this.props.categoryProps == "getByUser"){
                        this.getBlogsByUser(blogCount, pageNo, pageSize, this.state.username);
                    }
                    else{
                        this.getBlogByCategory(blogCount, pageNo, pageSize, this.state.category);
                    }
                }
                else{
                    if (this.props.categoryProps == "getAll") {
                       this.setState({
                           totalBlogCount:0,
                           blogcount:0,

                       })
                    }
                    else {
                        this.setState({
                            blogcount:0,
                        })
                    }
                }

            })
            .catch((error) => {
                console.error(error)
            });

    }

    getAllBlog(totalCount,pageno,pagesize){
        var url = `${this.state.url}/blog/getAll?pageno=`+pageno+'&pagesize='+pagesize;

        return fetch(url)
            .then((response) => response.json())
            .then((responseJson) => {
                console.log("Inside responseJSON");

                var routes =[];
                responseJson.map((route, index) => (
                    // Render more <Route>s with the same paths as
                    // above, but different components this time.
                    routes[index]={
                       path:`/Blog/${route.title}`.split(" ").join("_"),
                       exact:true,
                       main: () => <BlogComponent blogData={route}/>
                    }
                ));

                this.setState({
                    isLoading: false,
                    isLoadingAll:true,
                    category:this.props.categoryProps,
                    dataSource: responseJson,
                    totalBlogCount:totalCount,
                    blogcount:totalCount,
                    pageNo:pageno,
                    blogRoute:routes,
                }, function () {

                });
                console.log(this.state.totalBlogCount);
                console.log(this.state.blogRoute);
            })
            .catch((error) => {
                console.error(error);
            });
    }

    getBlogByCategory(totalCount,pageno,pagesize,category){
        var url = `${this.state.url}/blog/category?pageno=`+pageno+`&pagesize=`+pagesize+`&search=`+category;

        console.log(url);

        return fetch(url)
            .then((response) => response.json())
            .then((responseJson) => {
                console.log("Inside responseJSON");
                console.log(responseJson[0])

                var routes =[];
                responseJson.map((route, index) => (
                    // Render more <Route>s with the same paths as
                    // above, but different components this time.
                    routes[index]={
                        path:`/Blog/${route.title}`.split(" ").join("_"),
                        exact:true,
                        main: () => <BlogComponent blogData={route}/>
                    }
                ));


                this.setState({
                    isLoading: false,
                    isLoadingAll:true,
                    category:this.props.categoryProps,
                    dataSource: responseJson,
                    blogcount:totalCount,
                    pageNo:pageno,
                    blogRoute:routes

                }, function () {
                    // do something with new state
                });
                console.log(this.state.blogcount);
            })
            .catch((error) => {
                console.error(error);
            });
    }

    getBlogsByUser(totalCount,pageno,pagesize,username){
        var url = `${this.state.url}/blog/user/`+username+`?pageno=`+pageno+`&pagesize=`+pagesize;

        console.log(url);

        return fetch(url)
            .then((response) => response.json())
            .then((responseJson) => {
                console.log("Inside responseJSON");
                console.log(responseJson[0])

                var routes =[];
                responseJson.map((route, index) => (
                    // Render more <Route>s with the same paths as
                    // above, but different components this time.
                    routes[index]={
                        path:`/Blog/${route.title}`.split(" ").join("_"),
                        exact:true,
                        main: () => <BlogComponent blogData={route}/>
                    }
                ));


                this.setState({
                    isLoading: false,
                    isLoadingAll:true,
                    category:this.props.categoryProps,
                    dataSource: responseJson,
                    blogcount:totalCount,
                    pageNo:pageno,
                    blogRoute:routes

                }, function () {
                    // do something with new state
                });
                console.log(this.state.blogcount);
            })
            .catch((error) => {
                console.error(error);
            });
    }


    componentWillMount() {
        var totalCount=0;
        console.log("Inside Mount");
        if(this.props.categoryProps != "getByUser") {
            var url = `${this.state.url}/blog/blogCount?category=${this.props.categoryProps}`;
            fetch(url)
                .then((response) => response.json())
                .then((responseJson) => {
                    console.log("Inside blogCount");
                    totalCount = responseJson;
                    console.log(totalCount);
                    var pageNo = 1;
                    var pageSize = (totalCount - (this.state.pageSize * (pageNo - 1))) >= this.state.pageSize ? this.state.pageSize : totalCount % this.state.pageSize;
                    if (totalCount > 0) {
                        if (this.props.categoryProps == "getAll") {
                            this.getAllBlog(totalCount, pageNo, pageSize, this.props.categoryProps);
                        }
                        else {
                            console.log(pageSize)
                            this.getBlogByCategory(totalCount, pageNo, pageSize, this.props.categoryProps);
                        }
                    }
                    else {
                        this.setState({
                            isLoading: false,
                        })
                    }
                })
                .catch((error) => {
                    console.error(error);
                });
        }
        else{
            console.log("filter by user");
            var username = this.state.username;
            var url = `${this.state.url}/blog/blogCount?username=`+username;
            fetch(url)
                .then((response) => response.json())
                .then((responseJson) => {
                    console.log("Inside blogCount");
                    totalCount = responseJson;
                    console.log(totalCount);
                    var pageNo = 1;
                    var pageSize = (totalCount - (this.state.pageSize * (pageNo - 1))) >= this.state.pageSize ? this.state.pageSize : totalCount % this.state.pageSize;
                    if (totalCount > 0) {
                       this.getBlogsByUser(totalCount, pageNo, pageSize,username)
                    }
                    else {
                        this.setState({
                            isLoading: false,
                        })
                    }
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    }

    componentDidUpdate() {
        var blogAll = document.getElementById("displayBlogPagination");
        var blogindividual =  document.getElementById("individualBlogData");
        console.log(blogAll);

        if(blogAll != null)
            blogAll.style.display="block";

        if(blogindividual != null)
        blogindividual.style.display="none";
    }



    render() {
        if(this.state.isLoading){
        // if(true){
                return (

                        <img src={loading} className="loadingBlog" alt="Smiley face"></img>

                )
        }
            else if(this.state.isLoadingAll){
                console.log("inside showBlog of table right getALL");
                console.log(this.state.isLoadingAll);
                console.log(this.state.category);
                if(this.state.blogcount == 0){
                    return (
                        <div>
                            <b>There are no Blog posts to be displayed</b>
                        </div>
                    )
                }
                else {
                    return (
                        <div>
                            <Router>
                                <div>
                                    <div id="displayBlogPagination">
                                        <div id="displayAllBlog" className="getAllBlog">
                                            {this.state.dataSource.map((dynamicComponent, i) => <BlogGroups deleteBlogProp={this.deleteBlog.bind(this,dynamicComponent)} launchBlogProp ={this.launchBlog.bind(this,dynamicComponent)}
                                                                                                        key = {i} componentData = {dynamicComponent} />)}
                                        </div>
                                        <br/>
                                        <br/>
                                        <div className="paginationContainer">
                                            <button type="submit"  id="pageDec" style={{'width':'45px'}} onClick={this.paginationDec}> &laquo;</button>
                                            <button type="submit"  id="pageInc" style={{'marginLeft':'2px','width':'45px'}} onClick={this.paginationInc}>&raquo;</button>
                                        </div>
                                    </div>
                                    <div id="individualBlogData">
                                        {this.state.blogRoute.map((route, index) => (
                                            // Render more <Route>s with the same paths as
                                            // above, but different components this time.
                                            <Route
                                                key={index}
                                                path={route.path}
                                                exact={route.exact}
                                                component={route.main}
                                            />
                                        ))}
                                    </div>
                                </div>
                            </Router>
                        </div>


                    );
                }
            }
            else{
                console.log("inside showBlog of table right individual blog data");
                console.log(this.state.isLoadingAll);
                console.log(this.state.blogData);
                console.log(this.state.category);
                return (
                    <Router>
                        <div>
                            {this.state.blogRoute.map((route, index) => (
                                // Render more <Route>s with the same paths as
                                // above, but different components this time.
                                <Route
                                    key={index}
                                    path={route.path}
                                    exact={route.exact}
                                    component={route.main}
                                />
                            ))}

                        </div>
                    </Router>

                );
            }
        }

}

export default ViewContent;