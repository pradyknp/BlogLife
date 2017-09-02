import React, { Component } from 'react';

class Tableleft extends Component {
    constructor(props){
        super(props)

    }


    render() {
        return (
            <div>
                    <div>

                        <b>Filter By Category</b><br/><br/>
                        <a href="#" id="viewAll"> View All</a> <br/>
                        <a href="#" id="enterLink2"  value="Entertainment">Entertainment</a> <br/>
                        <a href="#" id="politicsLink">Politics</a> <br/>
                        <a href="#"  id="travelLink">Travel</a> <br/>
                        <a href="#" id="historyLink">History</a><br/>
                        <a href="#" id="general">Finance</a> <br/>
                        <a href="#" id="healthCare"> HealthCare</a> <br/>
                        <a href="#" id="humanresources"> Human Resources</a> <br/>
                        <a href="#" id="humanresources">Business</a> <br/>
                        <a href="#" id="general">General</a> <br/>
                        <a href="#" id="popular">Popular</a> <br/>
                        <a href="#" id="recent">Recent</a> <br/>

                    </div>
                    <br/>
                    <br/>
                    <div className="input-group">
                    <label htmlFor="general-search-search-input" className="isvishidden">Search the site</label>

                        <input type="text" name="q" ref="general-search-search-input" className="general-search-input" placeholder="Search By Title"></input>

                        <button className="btn btn-go">GO</button>

                </div>
            </div>
        );
    }
}

export default Tableleft;