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
    this.state = {
      cvs: '',
      file:null,
    };
    this.onFormSubmit = this.onFormSubmit.bind(this)
    this.onChange = this.onChange.bind(this)
    this.fileUpload = this.fileUpload.bind(this)
    console.log(this.props.details)
  }


    onFormSubmit(e){
      e.preventDefault() 
      this.fileUpload(this.state.file).then((response)=>{
        console.log(response.data);
        })
    }

  componentDidMount() {
    axios.get('/api/cvdownload/' + this.props.details.id)
      .then(res => {
        this.setState({ cvs: res.data });
      });
     
  }

    onChange(e) {
    this.setState({file:e.target.files[0]})
  }
    
remove = (id) =>{
  fetch('/api/cvdelete/'+ this.props.userid + "/" + id,{
    method : 'delete',
         headers: {
       "Content-Type": "multipart/form-data"
      }
  }).then(()=>{
    let updateCVs = [...this.state.cvs].filter(i => i.id !== id);
    this.setState({cvs:updateCVs});
  });
}


    fileUpload= () => {
      console.log(this.props.details)
    const formData = new FormData();
    formData.append('file',this.state.file)
      fetch('/api/cvupload/' + this.props.details.id, {
          method : 'POST',

          body: formData
      }).then(response => console.log(response)).then(data => console.log(data))
       
   
  }

nextPath = (path) => {
  this.props.history.push(path);
}


  render() {     
    let pdfData = null;
    if(this.state.cvs!=null){
      let pdfData = this.state.cvs.data;
      console.log(pdfData)
    }
    return (
      <div class="container">
        <div class="one">      
                <InformationComponent userName={this.state.hoveredCV} />
                <Document
                    file={{
                        pdfData
                    }}
                />
        </div>
          <div class="two">
            <h4>      

              <input id="upload" ref="upload" type="file" 
                onChange={(event)=> { 
                  this.onChange(event) 
              }}
                onClick={(event)=> { 
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
                                <td>{this.state.cvs.flag}</td>
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