import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    Button, FormFeedback, Table
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
        })
    }

    componentDidMount() {
        axios.get('/api/trainee/cvdownload/1/' + sessionStorage.getItem('id'))
            .then(res => {

                if (res.data.fileBinaryData != null) {
                    const toShow = "data:application/pdf;base64," + res.data.fileBinaryData.data;
                    this.setState({ cv1: res.data });
                    this.setState({ file: toShow });
                }
               
            });


        axios.get('/api/trainee/cvdownload/2/' + sessionStorage.getItem('id'))
            .then(res => {

                if (res.data.fileBinaryData != null) {
                    this.setState({ cv2: res.data });
                    this.setState({ cv2: res.data });
                }
            });


        axios.get('/api/trainee/cvdownload/3/' + sessionStorage.getItem('id'))
            .then(res => {

                if (res.data.fileBinaryData != null) {
                    this.setState({ cv3: res.data });
                    this.setState({ cv3: res.data });
                }
            });

    }

    async onChange(e,id) {
        await this.setState({ file: e.target.files[0] });
        this.fileUpload(id);
    }

    remove = (id) => {
        fetch('/api/trainee/cvdelete/' + id + "/" +sessionStorage.getItem('id'), {
            method: 'DELETE',
        }).then(res=> console.log(res.status))
    }


    fileUpload = (id) => {
        const formData = new FormData();
        formData.append('file', this.state.file)
        fetch('/api/trainee/cvupload/' + id + '/' + sessionStorage.getItem('id') ,  {
            method: 'POST',
            body: formData
        })


    }

    changePdf = (id) =>{
        axios.get('/api/trainee/cvdownload/'+id+ '/' +sessionStorage.getItem('id'))
            .then(res => {
                if (res.data.fileBinaryData != null) {
                    const toShow = "data:application/pdf;base64," + res.data.fileBinaryData.data;
                    this.setState({ file: toShow });
                }     
            });
    }


    nextPath = (path) => {
        this.props.history.push(path);
    }


    render() {
        return (
            <div >
                <div class="infoContainer">
                    <div> {sessionStorage.getItem('userName')}</div>
                     <div> {sessionStorage.getItem('firstName')}</div>
                      <div> {sessionStorage.getItem('surName')}</div>
                </div>

                <div class="listContainer">
                    <h4>

                      
                    </h4>
                    <Table  hover>
                        <thead>
                            <tr>
                                <th>File Name</th>
                                <th>Flag</th>
                            </tr>
                        </thead>
                        <tbody >

                            <tr class="hoverable" onClick={() => this.changePdf(1)} >
                                <td>{this.state.cv1.fileName}</td>
                                 <td><Button color={this.state.cv1.fileFlag}  disabled>Flag</Button></td>
                                <td><button onClick={() => { if (this.state.cv1.fileBinaryData != null) { this.remove(1) } }}>delete</button></td>
                                <td> <input id="upload" ref="upload" type="file" onChange={(event) => {this.onChange(event,1) }} onClick={(event) => {event.target.value = null}} /></td>
                            </tr>

                            <tr class="hoverable" onClick={()=>this.changePdf(2)}>
                                <td>{this.state.cv2.fileName}</td>
                                 <td><Button color={this.state.cv2.fileFlag}  disabled>Flag</Button></td>
                                     <td><button onClick={() => { if (this.state.cv1.fileBinaryData != null) { this.remove(2) } }}>delete</button></td>
                                <td> <input id="upload" ref="upload" type="file" onChange={(event) => {this.onChange(event,2) }} onClick={(event) => {event.target.value = null}} /></td>
                            </tr>

                            <tr class="hoverable" onClick={()=>this.changePdf(3)}>
                                <td>{this.state.cv3.fileName}</td>
                                 <td><Button color={this.state.cv3.fileFlag}  disabled>Flag</Button></td>
                                <td><button onClick={() => { if (this.state.cv1.fileBinaryData != null) { this.remove(3) } }}>delete</button></td>
                               <td> <input id="upload" ref="upload" type="file" onChange={(event) => {this.onChange(event,3) }} onClick={(event) => {event.target.value = null}} /></td>
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

export default CvList;