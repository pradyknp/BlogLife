import React, { Component } from 'react';
import {Editor, EditorState, RichUtils} from 'draft-js';
import {stateToHTML} from 'draft-js-export-html';



class ShowBlog extends Component {

    constructor(props){
        super(props);

        /*this.bloglaunch=this.bloglaunch.bind(this);*/
    }

   /* bloglaunch(){
        console.log("inside bloglaunch");
        /!*return (<Router><Route path="/launchBlog" render={routeProps => <launchBlog {...routeProps} blogGetter={this.props.componentData} />}/></Router>);*!/
    }*/

    rawMarkup()
    {
        var rawMarkup = this.props.blogDataProp.body;
        return {__html: rawMarkup};
    }
   /* render(){
        return(
            <div className="modal-body">
                <span dangerouslySetInnerHTML={this.rawMarkup()} />

            </div>
        )
    }
``}*/



render() {

        return (
            <div>
                <table>
                    <tbody>
                    <tr>
                        <td><h1 className="titlefontstyle" id="titleread" disabled>{this.props.blogDataProp.title}</h1>
                        </td>
                    </tr>
                    <tr>
                        <td><div className="datefontstyle" id="createread" disabled>created By <b>{this.props.blogDataProp.username}</b> on {this.props.blogDataProp.createdDate}</div>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr></tr>
                    <tr></tr>
                    <tr>
                        <td><div className="bodyfontstyle" id="bodyread" disabled><span dangerouslySetInnerHTML={this.rawMarkup()} /></div>
                                </td>
                            </tr>
                    </tbody>
                </table>

            </div>
        );
    }
}

export default ShowBlog;

