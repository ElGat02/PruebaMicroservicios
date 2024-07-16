import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Mascota } from 'src/app/menu/Mascotas';
import { MascotaServicioService } from 'src/app/services/mascota-service.service';


@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})
export class PrincipalComponent implements OnInit {
  mascotas: Mascota[] = [];
  mascotaForm: FormGroup;
  id?: number; // ID de la mascota para editar

  constructor(
    private mascotaService: MascotaServicioService, 
    private fb: FormBuilder,
    private route: ActivatedRoute, 
    private router: Router
  ) {
    this.mascotaForm = this.fb.group({
      id: [{value: '', disabled: false}], // Añade id como un campo deshabilitado si solo quieres mostrarlo
      nombre: ['', Validators.required],
      especie: ['', Validators.required],
      raza: ['', Validators.required],
      dueno: ['', Validators.required]
    });
    
  }

  ngOnInit(): void {
    this.loadMascotas();
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        this.id = +idParam;
        this.loadMascotaDetails(this.id);
      }
    });
  }

  loadMascotas(): void {
    this.mascotaService.getMascotas().subscribe((data: Mascota[]) => {
      this.mascotas = data;
    });
  }

  loadMascotaDetails(id: number): void {
    this.mascotaService.getMascota(id).subscribe({
      next: (data: Mascota) => {
        this.mascotaForm.patchValue(data);
      },
      error: (error) => {
        console.error('Error fetching mascota details', error);
      }
    });
  }

  add(): void {
    if (this.mascotaForm.valid) {
      this.mascotaService.addMascota(this.mascotaForm.value).subscribe({
        next: (data) => {
          console.log('Mascota agregada', data);
          this.loadMascotas();
        },
        error: (error) => console.error('Error adding mascota', error)
      });
    }
  }

  update(): void {
    console.log('ID:', this.id);  // Verifica si el ID es correcto
    console.log('Form Valid:', this.mascotaForm.valid);  // Verifica si el formulario es válido
    console.log('Form Value:', this.mascotaForm.value);  // Verifica los valores del formulario
  
    if (this.mascotaForm.valid && this.id !== undefined) {
      const updatedMascota = { ...this.mascotaForm.value, id: this.id };
      this.mascotaService.updateMascota(updatedMascota).subscribe({
        next: (data) => {
          console.log('Mascota actualizada', data);
          this.loadMascotas();  // Asegúrate de que este método está correctamente implementado
        },
        error: (error) => console.error('Error updating mascota', error)
      });
    } else {
      console.log('Form is invalid or ID is undefined');
    }
  }
  

  deleteMascota(id: number): void {
    this.mascotaService.deleteMascota(id).subscribe({
      next: () => {
        this.loadMascotas();
      },
      error: (error) => console.error('Error deleting mascota', error)
    });
  }

  selected(id: number): void {
    if (id !== undefined) {
      this.mascotaService.getMascota(id).subscribe(data => {
        this.mascotaForm.patchValue(data);
        this.id = data.id;  // Asegúrate de que el id se establece correctamente
      });
    }
  }
}
