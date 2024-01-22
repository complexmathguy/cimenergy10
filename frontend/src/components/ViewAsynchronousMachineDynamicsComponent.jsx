import React, { Component } from 'react'
import AsynchronousMachineDynamicsService from '../services/AsynchronousMachineDynamicsService'

class ViewAsynchronousMachineDynamicsComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            asynchronousMachineDynamics: {}
        }
    }

    componentDidMount(){
        AsynchronousMachineDynamicsService.getAsynchronousMachineDynamicsById(this.state.id).then( res => {
            this.setState({asynchronousMachineDynamics: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View AsynchronousMachineDynamics Details</h3>
                    <div className = "card-body">
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewAsynchronousMachineDynamicsComponent
