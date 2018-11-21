import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';





    export function onChange(e,d){
        const target = e.target;
        const value = target.value;
        const name = target.name;
        let details = {d};
        details[name] = value;
        return {details};
    }

