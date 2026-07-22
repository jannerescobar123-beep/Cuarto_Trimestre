
import { Component, OnInit } from '@angular/core';
import { passwordMatchValidator } from '../../validators/password-match.validator';
import { CommonModule } from '@angular/common';

import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule, Validators
} from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  showPassword = false;
  showConfirmPassword = false;



  constructor(private fb: FormBuilder) { }
  ngOnInit(): void {

    this.registerForm = this.fb.group({

      nombre: ['', [Validators.required, Validators.minLength(3)]],

      correo: ['', [
        Validators.required,
        Validators.email
      ]],

      usuario: ['', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9_]+$/)
      ]],

      password: ['', [
        Validators.required,
        Validators.minLength(8)
      ]],

      confirmarPassword: [
        '',
        Validators.required
      ],

      edad: ['', [
        Validators.required,
        Validators.min(15),
        Validators.max(90)
      ]],

      terminos: [
        false,
        Validators.requiredTrue
      ]

    },
      {
        validators: passwordMatchValidator()
      });
  }

  onSubmit(): void {

    if (this.registerForm.valid) {

      console.log(this.registerForm.value);

      alert('Registro exitoso');

    }

  }
  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  toggleConfirmPassword(): void {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

}
