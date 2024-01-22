import React, { Component } from 'react'
import PssELIN2Service from '../services/PssELIN2Service'

class ListPssELIN2Component extends Component {
    constructor(props) {
        super(props)

        this.state = {
                pssELIN2s: []
        }
        this.addPssELIN2 = this.addPssELIN2.bind(this);
        this.editPssELIN2 = this.editPssELIN2.bind(this);
        this.deletePssELIN2 = this.deletePssELIN2.bind(this);
    }

    deletePssELIN2(id){
        PssELIN2Service.deletePssELIN2(id).then( res => {
            this.setState({pssELIN2s: this.state.pssELIN2s.filter(pssELIN2 => pssELIN2.pssELIN2Id !== id)});
        });
    }
    viewPssELIN2(id){
        this.props.history.push(`/view-pssELIN2/${id}`);
    }
    editPssELIN2(id){
        this.props.history.push(`/add-pssELIN2/${id}`);
    }

    componentDidMount(){
        PssELIN2Service.getPssELIN2s().then((res) => {
            this.setState({ pssELIN2s: res.data});
        });
    }

    addPssELIN2(){
        this.props.history.push('/add-pssELIN2/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">PssELIN2 List</h2>
                 <div className = "row">
                    <button className="btn btn-primary btn-sm" onClick={this.addPssELIN2}> Add PssELIN2</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.pssELIN2s.map(
                                        pssELIN2 => 
                                        <tr key = {pssELIN2.pssELIN2Id}>
                                             <td>
                                                 <button onClick={ () => this.editPssELIN2(pssELIN2.pssELIN2Id)} className="btn btn-info btn-sm">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deletePssELIN2(pssELIN2.pssELIN2Id)} className="btn btn-danger btn-sm">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewPssELIN2(pssELIN2.pssELIN2Id)} className="btn btn-info btn-sm">View </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default ListPssELIN2Component
