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

    render() {

        return (
            <div>
                <table>
                    <tbody>
                    <tr>
                        <td><div className="titlefontstyle" id="titleread" disabled>{this.props.blogDataProp.title}</div>
                        </td>
                    </tr>
                    <tr>
                        <td><div className="datefontstyle" id="createread" disabled>{this.props.blogDataProp.username}</div>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr></tr>
                    <tr>
                                <td><div className="bodyfontstyle" id="bodyread" disabled>{this.props.blogDataProp.body}</div>
                                </td>
                            </tr>
                    </tbody>
                </table>

            </div>
        );
    }
}

export default ShowBlog;

