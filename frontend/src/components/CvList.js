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
import withSelectFiles from 'react-select-files'
class CvList extends Component {

  constructor(props) {
    super(props);
    this.state = {
      cvs: [],
      file:null
    };
    this.onFormSubmit = this.onFormSubmit.bind(this)
    this.onChange = this.onChange.bind(this)
    this.fileUpload = this.fileUpload.bind(this)
  }


    onFormSubmit(e){
      e.preventDefault() 
      this.fileUpload(this.state.file).then((response)=>{
        console.log(response.data);
        })
    }

  componentDidMount() {
    axios.get('/api/cvdownload/' + this.props.userid)
      .then(res => {
        this.setState({ cvs: res.data });
        console.log(this.state.cvs);
      });
  }

    onChange(e) {
    this.setState({file:e.target.files[0]})
  }
    
remove = (id) =>{
  fetch('/api/cvdelete/'+ this.props.userid +"/" + id,{
    method : 'delete',
         headers: {
        'Content-Type' : 'application/json',
        'Accept' : 'application/json'
      }
  }).then(()=>{
    let updateCVs = [...this.state.cvs].filter(i => i.id !== id);
    this.setState({cvs:updateCVs});
  });
}


  fileUpload(file){
    const url = 'http://example.com/file-upload';
    const formData = new FormData();
    formData.append('file',file)
    formData.append('userid',this.props.userid);
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }
   
  }

nextPath = (path) => {
  this.props.history.push(path);
}


  render() {     

    return (
      <div class="container">
        <div class="one">      
                <InformationComponent  userName={this.state.hoveredCV} />
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

            </h4>
            <table class="table table-stripe">
              <thead>
                <tr>
                  <th>File Name</th>
                  <th>Flag</th>
                </tr>
              </thead>
              <tbody >
                {this.state.cvs.map(cv =>
                  <tr onMouseEnter={() => this.setState({hoveredCV :  cv.fileName})}>
                    <td>{cv.flag}</td>
                    <td><button onClick={()=> this.remove(cv.id)}>delete</button></td>
                      {/* <td><button onClick={()=> this.nextPath('/editCv/'+ cv.id)}>update</button></td>  */}
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
    );
  }
}

export default CvList;