import React, { Component } from 'react';
import Tableleft from './Tableleft'
import Tableright from './Tableright'

class Homepage extends Component {
    constructor(props){
        super(props)
    }




    render() {
        return (
            <div className="wrap">
                <h1/>
                <h1/>
                <table className="Table-entire">
                    <tbody>
                        <tr>
                            <td id="table-left" className="Table-left" >
                                <Tableleft/></td>

                            <td id="table-middle" className="Table-middle">
                                <Tableright/></td>
                        </tr>

                </tbody>
                </table>

            </div>
        );
    }
}

export default Homepage;


