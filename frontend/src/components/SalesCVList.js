import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import {
    Container, Col, Form, Row,
    FormGroup, Label, Input,
    Button, FormFeedback, ButtonGroup, Table
} from 'reactstrap';
import InformationComponent from './InformationComponent';
import { Document } from 'react-pdf'
import Logout from './Logout'
import FileSaver from 'file-saver';

class TraineeManCvList extends Component {

    constructor(props) {
        super(props);
        const data = {
            fileName: "file not added",
            fileFlag:"black"
        }
        const name = ""
        this.state = {
            cv1: data,
            cv2: data,
            cv3: data,
            file: null,
            cv1Selected: [],
        };
        this.onChange = this.onChange.bind(this)
        this.onRadioBtnClick = this.onRadioBtnClick.bind(this);
    }

    componentDidMount() {

        const traineeData = this.props.location.state.userData

        axios.get('/api/sales/cvdownload/1/' + traineeData.id)
        .then(res => {

            if (res.data.fileBinaryData != null) {
                const toShow = "data:application/pdf;base64," + res.data.fileBinaryData.data;
                this.setState({ toDownload: res.data.fileBinaryData.data })
                this.setState({ cv1: res.data });
                this.setState({ file: toShow });
            }
            
        });


        axios.get('/api/sales/cvdownload/2/' + traineeData.id)
            .then(res => {

                if (res.data.fileBinaryData != null) {
                    const toShow = "data:application/pdf;base64," + res.data.fileBinaryData.data;
                    this.setState({ toDownload: res.data.fileBinaryData.data })
                    this.setState({ cv1: res.data });
                    this.setState({ file: toShow });
                }
            });


        axios.get('/api/sales/cvdownload/3/' + traineeData.id)
            .then(res => {

                if (res.data.fileBinaryData != null) {
                    const toShow = "data:application/pdf;base64," + res.data.fileBinaryData.data;
                    this.setState({ toDownload: res.data.fileBinaryData.data })
                    this.setState({ cv1: res.data });
                    this.setState({ file: toShow });
                }
            });
    }


    donwloadFile = () => {
        var blob = new Blob([window.atob(this.state.toDownload)], { type: 'application/pdf"' });
        FileSaver.saveAs(blob, "this.pdf");
    }

    async onChange(e,id) {
        await this.setState({ file: e.target.files[0] });
        this.fileUpload(id);
    }


    nextPath = (path) => {
        this.props.history.push(path);
    }

    onRadioBtnClick(rSelected) {
    this.setState({ rSelected });
  }


    render() {
        return (
            <div >
                <Logout/>
                <div class="infoContainer">
                </div>
                <div class="smallList">
                    <h4>
                        {this.props.location.state.userData.userName}
                    </h4>
                    <Table hover >
                        <thead>
                            <tr>
                                <th>File Name</th>
                                <th>Flag</th>
                                <td>
                                    <Button onClick={(e) => this.donwloadFile(e)} >Download Selected</Button>
                                </td>
                            </tr>
                        </thead>
                        <tbody >

                            <tr >
                                <td>{this.state.cv1.fileName}</td>
                                 <td>
                                    <Button color={this.state.cv1.fileFlag} disabled>Current Flag</Button>
                                </td>
         
                            </tr>

                            <tr >
                                <td>{this.state.cv2.fileName}</td>
                                 <td>
                                    <Button color={this.state.cv2.fileFlag} disabled>Current Flag</Button>
                                 </td>

                            </tr>

                            <tr >
                                <td>{this.state.cv3.fileName}</td>
                                 <td>
                                    <Button color={this.state.cv3.fileFlag} disabled>Current Flag</Button>
                                 </td>
              
                            </tr>
                        </tbody>
                    </Table>
                </div>
                <div class="rightContainer">
                    <iframe src={this.state.file} />
                </div>
            </div>
        );
    }
}

export default TraineeManCvList;