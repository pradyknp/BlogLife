/*npm install --save draft-js react react-dom*/
/*npm install --save draft-js react react-dom
npm install --save draft-js react react-dom babel-polyfill*/
/*npm install --save draft-css*/
/*npm install react-form-data --save
npm install react-form-data --save*/
/*npm install react-select --save*/

import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import {Editor, EditorState, RichUtils} from 'draft-js';
import {stateToHTML} from 'draft-js-export-html';
import { Route, Redirect } from 'react-router';
import Auth from '../../Authentication/Auth';

/*
import Select from 'react-select';
import jquery from "min-jquery";*/
/*import axios from 'axios';
import FormData from 'react-form-data';
import request from 'superagent';*/


class Createpost extends Component {
    constructor(props) {
        super(props);
        this.state = {
            editorState: EditorState.createEmpty(),
            blogCount:1,
            title:"title",
            url:window.location.origin+"/BlogLife/Blogit",
            category:"entertainment",
            options:[{
                value:"Entertainment",
                label:"Entertainment"
            },
                {
                    value:"Politics",
                    label:"Politics"
                },
                {
                    value:"Travel",
                    label:"Travel"
                },
                {
                    value:"History",
                    label:"History"
                },
                {
                    value:"Finance",
                    label:"Finance"
                },
                {
                    value:"HealthCare",
                    label:"Health Care"
                },
                {
                    value:"HumanResources",
                    label:"Human Resources"
                },
                {
                    value:"Business",
                    label:"Business"
                },
                {
                    value:"General",
                    label:"General"
                }
            ]
        };


        this.focus = () => this.refs.editor.focus();
        this.onChange = (editorState) => this.setState({editorState});

        this.handleKeyCommand = (command) => this._handleKeyCommand(command);
        this.onTab = (e) => this._onTab(e);
        this.toggleBlockType = (type) => this._toggleBlockType(type);
        this.toggleInlineStyle = (style) => this._toggleInlineStyle(style);
        this.submitBlog=this.submitBlog.bind(this);
    }

    componentWillMount() {

        var url =`${this.state.url}/blog/blogCount?category=${this.props.categoryProps}`;

        fetch(url)
            .then((response) => response.json())
            .then((responseJson) => {
                console.log("Inside blogCount");
                console.log(responseJson);
                this.setState({
                    blogCount:++responseJson
                });
            })
            .catch((error) => {
                console.error(error);
            });

    }

    componentDidUpdate(){
        var categorySelection = document.getElementById("category");

        this.state.options.map ((category,id) =>
        {
            var newOption = document.createElement("option");
            newOption.label = category.label;
            newOption.value = category.value;
            categorySelection.appendChild(newOption);
        });

    }

    submitBlog(){
        console.log(this.state.editorState.getCurrentContent().getPlainText());
        console.log(this);
        let contentState = this.state.editorState.getCurrentContent();
        let html = stateToHTML(contentState);
        console.log(html);
        var title = document.getElementById("title").value;
        var category = document.getElementById("category").value;

        var date = new Date();
        var dateString = date.toString().split("GMT")[0];
        var token = "";
        var username ="";

        if (Auth.isUserAuthenticated()) {
            token = Auth.getToken();
            username=Auth.getUser();
        }
        else{
            alert("please login");
            return;
        }

        console.log(token);

        var data={
            "id":Math.floor((Math.random() * 100000) + 1),
            "title":title,
            "body":html,
            "username":username,
            "createdDate":dateString,
            "modifiedDate":dateString,
            "category":category,
            "modifiedTime":"12.34pm"
        };

        var url =`${this.state.url}/blog`;
        console.log(data);

        return fetch(url, {
            method: "POST",
            headers: {
                'Access-Control-Request-Headers': '*',
                'Authorization': token,
            },
            body:JSON.stringify(data)
        }).then(function(response) {

            console.log(response);
            return response.json();

        }).then(function(data) {

            console.log("success");
            window.location.href = "/BlogLife";

        }).catch(function(err) {
            console.log(err);
        });
    }

    _handleKeyCommand(command) {
        const {editorState} = this.state;
        const newState = RichUtils.handleKeyCommand(editorState, command);
        if (newState) {
            this.onChange(newState);
            return true;
        }
        return false;
    }

    _onTab(e) {
        const maxDepth = 4;
        this.onChange(RichUtils.onTab(e, this.state.editorState, maxDepth));
    }

    _toggleBlockType(blockType) {
        this.onChange(
            RichUtils.toggleBlockType(
                this.state.editorState,
                blockType
            )
        );
    }

    _toggleInlineStyle(inlineStyle) {
        this.onChange(
            RichUtils.toggleInlineStyle(
                this.state.editorState,
                inlineStyle
            )
        );
    }

    render() {
        const {editorState} = this.state;

        // If the user changes block type before entering any text, we can
        // either style the placeholder or hide it. Let's just hide it now.
        let className = 'RichEditor-editor';
        var contentState = editorState.getCurrentContent();
        if (!contentState.hasText()) {
            if (contentState.getBlockMap().first().getType() !== 'unstyled') {
                className += ' RichEditor-hidePlaceholder';
            }
        }

        return (
            <div>
            <div className="container">
                <label><b>Title</b></label>
                <input type="text" id="title"   placeholder="Title"/>
                <label><b>Category</b></label><br/>
                <select style={{'padding':'10px','width':'100%'}} name="blogCategory"  id="category">
                </select>
            </div>
            <div className="RichEditor-root">
                <BlockStyleControls
                    editorState={editorState}
                    onToggle={this.toggleBlockType}
                />
                <InlineStyleControls
                    editorState={editorState}
                    onToggle={this.toggleInlineStyle}
                />
                <div className={className} onClick={this.focus}>
                    <Editor
                        blockStyleFn={getBlockStyle}
                        customStyleMap={styleMap}
                        editorState={editorState}
                        handleKeyCommand={this.handleKeyCommand}
                        onChange={this.onChange}
                        onTab={this.onTab}
                        placeholder="Tell a story..."
                        ref="editor"
                        spellCheck={true}
                    />
                </div>
            </div>
                <div  className="container">
                    <button type="submit" onClick={this.submitBlog}>Blog It !!!</button>
                </div>
            </div>
        );
    }
}

// Custom overrides for "code" style.
const styleMap = {
    CODE: {
        backgroundColor: 'rgba(0, 0, 0, 0.05)',
        fontFamily: '"Inconsolata", "Menlo", "Consolas", monospace',
        fontSize: 16,
        padding: 2,
    },
};

function getBlockStyle(block) {
    switch (block.getType()) {
        case 'blockquote': return 'RichEditor-blockquote';
        default: return null;
    }
}

class StyleButton extends React.Component {
    constructor() {
        super();
        this.onToggle = (e) => {
            e.preventDefault();
            this.props.onToggle(this.props.style);
        };
    }

    render() {
        let className = 'RichEditor-styleButton';
        if (this.props.active) {
            className += ' RichEditor-activeButton';
        }

        return (
            <span className={className} onMouseDown={this.onToggle}>
              {this.props.label}
            </span>
        );
    }
}

const BLOCK_TYPES = [
    {label: 'H1', style: 'header-one'},
    {label: 'H2', style: 'header-two'},
    {label: 'H3', style: 'header-three'},
    {label: 'H4', style: 'header-four'},
    {label: 'H5', style: 'header-five'},
    {label: 'H6', style: 'header-six'},
    {label: 'Blockquote', style: 'blockquote'},
    {label: 'UL', style: 'unordered-list-item'},
    {label: 'OL', style: 'ordered-list-item'},
    {label: 'Code Block', style: 'code-block'},
];

const BlockStyleControls = (props) => {
    const {editorState} = props;
    const selection = editorState.getSelection();
    const blockType = editorState
        .getCurrentContent()
        .getBlockForKey(selection.getStartKey())
        .getType();

    return (

        <div className="RichEditor-controls">
            {BLOCK_TYPES.map((type) =>
                <StyleButton
                    key={type.label}
                    active={type.style === blockType}
                    label={type.label}
                    onToggle={props.onToggle}
                    style={type.style}
                />
            )}
        </div>
    );
};

var INLINE_STYLES = [
    {label: 'Bold', style: 'BOLD'},
    {label: 'Italic', style: 'ITALIC'},
    {label: 'Underline', style: 'UNDERLINE'},
    {label: 'Monospace', style: 'CODE'},
];

const InlineStyleControls = (props) => {
    var currentStyle = props.editorState.getCurrentInlineStyle();
    return (
        <div className="RichEditor-controls">
            {INLINE_STYLES.map(type =>
                <StyleButton
                    key={type.label}
                    active={currentStyle.has(type.style)}
                    label={type.label}
                    onToggle={props.onToggle}
                    style={type.style}
                />
            )}
        </div>
);
}

export default Createpost;
