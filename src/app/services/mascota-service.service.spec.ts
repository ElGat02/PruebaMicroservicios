import { TestBed } from '@angular/core/testing';

import { MascotaServicioService } from './mascota-service.service';

describe('MascotaServiceService', () => {
  let service: MascotaServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MascotaServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
