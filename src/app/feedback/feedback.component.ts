import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  model:FeedbackViewModel = {
    name:'',
    email:'',
    feedback:''
  };

  constructor() { }

  ngOnInit(): void {
  }
}

export interface FeedbackViewModel {
  name:string;
  email:string;
  feedback:string;
}
