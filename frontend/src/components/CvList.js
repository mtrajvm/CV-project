import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    Button, FormFeedback,
} from 'reactstrap';
import InformationComponent from './InformationComponent';
import { Document } from 'react-pdf'
class CvList extends Component {

    constructor(props) {
        super(props);
        const data = {
            fileName: "file not added",
            fileFlag:"black"
        }
        this.state = {
            cv1: data,
            cv2: data,
            cv3:data,
            file: null,
        };
        this.onFormSubmit = this.onFormSubmit.bind(this)
        this.onChange = this.onChange.bind(this)
        this.fileUpload = this.fileUpload.bind(this)
    }


    onFormSubmit(e) {
        e.preventDefault()
        this.fileUpload(this.state.file).then((response) => {
            console.log(response.data);
        })
    }

    componentDidMount() {
        axios.get('/api/trainee/cvdownload/1/' + this.props.details.id)
            .then(res => {
                const toShow = "data:application/pdf;base64," + res.data.fileBinaryData.data;
                if (res.data.fileBinaryData != null) {
                    this.setState({ cv1: res.data });
                }
                this.setState({ file: toShow });
            });


        axios.get('/api/trainee/cvdownload/2/' + this.props.details.id)
            .then(res => {

                if (res.data.fileBinaryData != null) {
                    this.setState({ cv2: res.data });
                }
            });


        axios.get('/api/trainee/cvdownload/3/' + this.props.details.id)
            .then(res => {

                if (res.data.fileBinaryData != null) {
                    this.setState({ cv3: res.data });
                }
            });

    }

    onChange(e) {
        this.setState({ file: e.target.files[0] })
    }

    remove = (id) => {
        fetch('/api/trainee/cvdelete/' + this.props.userid + "/" + id, {
            method: 'delete',
            headers: {
                "Content-Type": "multipart/form-data"
            }
        }).then(() => {
            let updateCVs = [...this.state.cvs].filter(i => i.id !== id);
            this.setState({ cvs: updateCVs });
        });
    }


    fileUpload = () => {
        console.log(this.props.details)
        const formData = new FormData();
        formData.append('file', this.state.file)
        fetch('/api/trainee/cvupload/1/' + this.props.details.id, {
            method: 'POST',

            body: formData
        }).then(response => console.log(response)).then(data => console.log(data))


    }

    nextPath = (path) => {
        this.props.history.push(path);
    }


    render() {
        let pdfData = null;
        if (this.state.cvs != null) {
            let pdfData = this.state.cvs.data;
            console.log(pdfData)
        }
        return (
            <div class="container">
                <div class="one">
                    <InformationComponent userName={this.state.hoveredCV} />
                    <iframe src={this.state.file}/>
                </div>
                <div class="two">
                    <h4>

                        <input id="upload" ref="upload" type="file"
                            onChange={(event) => {
                                this.onChange(event)
                            }}
                            onClick={(event) => {
                                event.target.value = null
                            }} />
                        <button onClick={this.fileUpload}>Upload</button>
                    </h4>
                    <table class="table table-stripe">
                        <thead>
                            <tr>
                                <th>File Name</th>
                                <th>Flag</th>

                            </tr>
                        </thead>
                        <tbody >

                            <tr>
                                <td>{this.state.cv1.fileName}</td>
                                <td>{this.state.cv1.fileFlag}</td>
                                <td><button onClick={() => this.remove(this.state.cvs.id)}>delete</button></td>
                                {/* <td><button onClick={()=> this.nextPath('/editCv/'+ cv.id)}>update</button></td>  */}
                            </tr>

                            <tr>
                                <td>{this.state.cv2.fileName}</td>
                                <td>{this.state.cv2.fileFlag}</td>
                                <td><button onClick={() => this.remove(this.state.cvs.id)}>delete</button></td>
                                {/* <td><button onClick={()=> this.nextPath('/editCv/'+ cv.id)}>update</button></td>  */}
                            </tr>

                            <tr>
                                <td>{this.state.cv3.fileName}</td>
                                <td>{this.state.cv3.fileFlag}</td>
                                <td><button onClick={() => this.remove(this.state.cvs.id)}>delete</button></td>
                                {/* <td><button onClick={()=> this.nextPath('/editCv/'+ cv.id)}>update</button></td>  */}
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default CvList;