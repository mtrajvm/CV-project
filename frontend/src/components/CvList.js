import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Edit from './Edit';
import {
    Container, Col, Form,
    FormGroup, Label, Input,
    Button, FormFeedback, Table,
} from 'reactstrap';
import InformationComponent from './InformationComponent';
import { Document } from 'react-pdf'
import FileSaver from 'file-saver';
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
            toDownload:null,
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
                    this.setState({ toDownload: res.data.fileBinaryData.data})        
                    this.setState({ cv1: res.data });
                    this.setState({ file: toShow });
                }
               
            });


        axios.get('/api/trainee/cvdownload/2/' + sessionStorage.getItem('id'))
            .then(res => {

                if (res.data.fileBinaryData != null) {
                    const toShow = "data:application/pdf;base64," + res.data.fileBinaryData.data;
                    this.setState({ toDownload: res.data.fileBinaryData.data})    
                    this.setState({ cv2: res.data });
                    this.setState({ cv2: res.data });
                }
            });


        axios.get('/api/trainee/cvdownload/3/' + sessionStorage.getItem('id'))
            .then(res => {

                if (res.data.fileBinaryData != null) {
                    const toShow = "data:application/pdf;base64," + res.data.fileBinaryData.data;
                    this.setState({ toDownload: res.data.fileBinaryData.data})                    
                    this.setState({ cv3: res.data });
                    this.setState({ cv3: res.data });
                }
            });

    }

    async onChange(e,id, cv) {
        await this.setState({ file: e.target.files[0] });
        this.fileUpload(id, cv);
    }

    remove = (id, cv) => {
        console.log("delete")
        fetch('/api/trainee/cvdelete/' + id + "/" +sessionStorage.getItem('id'), {
            method: 'DELETE',
        }).then(res=> console.log(res.status))
        
        axios.delete('http://localhost:8081/email', 
                {
                  fileName: "file",
                  email: sessionStorage.getItem('userName')  
                }
        )
        .then(cv.fileName = "file deleted").then(this.forceUpdate());
    }


    fileUpload = (id, cv) => {
        const formData = new FormData();
        formData.append('file', this.state.file)
        fetch('/api/trainee/cvupload/' + id + '/' + sessionStorage.getItem('id') ,  {
            method: 'POST',
            body: formData
        }).then(res => {
            console.log(res)
            if (res.ok) {
                window.location.reload();
             }
            })

        if(cv.fileName == "file not added")
        {
                axios.post('http://localhost:8081/email', 
                {
                  fileName: cv.fileName,
                  email: sessionStorage.getItem('userName')  
                }
                ).then(cv.fileName = "File uploaded").then(this.forceUpdate());
        }
        else
        {
            axios.put('http://localhost:8081/email', 
                {
                  fileName: cv.fileName,
                  email: sessionStorage.getItem('userName')  
                }
                ).then(cv.fileName = "File uploaded").then(this.forceUpdate());
        } ;


    }

    donwloadFile = () => {
        var blob = new Blob([window.atob(this.state.toDownload)], { type: 'application/pdf"'});
        FileSaver.saveAs(blob, "this.pdf");
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
        let pdfData = null;
        if (this.state.cvs != null) {
            let pdfData = this.state.cvs.data;
        }

        console.log(this.state.cv1);
        return (
            <div >
                <div class="cvContainer">
                    <iframe src={this.state.file}/>
                </div>
                <div class="cvlistContainer" background-color="white">
                    <h4>

                      CV List
                    </h4>
                    <Table class="table table-stripe">
                        <thead>
                            <tr>
                                <th>File Name</th>
                                <th>Flag</th>
                            </tr>
                        </thead>
                        <tbody >

                            <tr onClick={()=>this.changePdf(1)}>
                                <td>{this.state.cv1.fileName}</td>
                                 <td><Button color={this.state.cv1.fileFlag}  disabled>Flag</Button></td>
                                <td><button onClick={() => { if (this.state.cv1.fileBinaryData != null) { this.remove(1, this.state.cv1) } }}>delete</button></td>
                                <td> <input id="upload" ref="upload" type="file" onChange={(event) => {this.onChange(event,1, this.state.cv1) }} onClick={(event) => {event.target.value = null}} /></td>
                            </tr>

                            <tr onClick={()=>this.changePdf(2)}>
                                <td>{this.state.cv2.fileName}</td>
                                 <td><Button color={this.state.cv2.fileFlag}  disabled>Flag</Button></td>
                                     <td><button onClick={() => { if (this.state.cv1.fileBinaryData != null) { this.remove(2, this.state.cv2) } }}>delete</button></td>
                                <td> <input id="upload" ref="upload" type="file" onChange={(event) => {this.onChange(event,2, this.state.cv2) }} onClick={(event) => {event.target.value = null}} /></td>
                            </tr>

                            <tr onClick={()=>this.changePdf(3)}>
                                <td>{this.state.cv3.fileName}</td>
                                 <td><Button color={this.state.cv3.fileFlag}  disabled>Flag</Button></td>
                                <td><button onClick={() => { if (this.state.cv1.fileBinaryData != null) { this.remove(3, this.state.cv3) } }}>delete</button></td>
                               <td> <input id="upload" ref="upload" type="file" onChange={(event) => {this.onChange(event,3, this.state.cv3) }} onClick={(event) => {event.target.value = null}} /></td>
                            </tr>
                        </tbody>
                    </Table>
                    
                    <Button onClick={(e)=>this.donwloadFile(e)} >Download Selected</Button>
                               
                </div>
                <div class="rightContainer">
                                    

                </div>
            </div>
        );
    }
}

export default CvList;