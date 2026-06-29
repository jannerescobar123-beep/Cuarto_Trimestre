import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../../models/pokemon';
import { PokemonService } from '../../services/pokemon.service';

import { Subject } from 'rxjs';

import {
  debounceTime,
  distinctUntilChanged,
  switchMap
} from 'rxjs/operators';

@Component({
  selector: 'app-pokemon-list',
  templateUrl: './pokemon-list.component.html',
  styleUrls: ['./pokemon-list.component.scss']
})
export class PokemonListComponent implements OnInit {

  pokemons: Pokemon[] = [];

  loading = false;

  error = '';

  offset = 0;

  textoBusqueda = '';

  private buscarSubject = new Subject<string>();

  constructor(private pokemonService: PokemonService) { }

  ngOnInit(): void {

    this.cargarPokemons();

    this.buscarSubject.pipe(

      debounceTime(500),

      distinctUntilChanged(),

      switchMap((nombre) => {

        if (nombre.trim() === '') {

          return this.pokemonService.getPokemons(this.offset);

        }

        return this.pokemonService.buscarPokemon(nombre);

      })

    ).subscribe((pokemons) => {

      this.pokemons = pokemons;

    });

  }

  cargarPokemons(): void {

    this.loading = true;

    this.error = '';

    this.pokemonService.getPokemons(this.offset).subscribe({

      next: (data) => {

        this.pokemons = data;

        this.loading = false;

      },

      error: () => {

        this.error = 'No fue posible cargar los Pokémon';

        this.loading = false;

      }

    });

  }

  buscar(event: Event): void {

    const input = event.target as HTMLInputElement;

    this.textoBusqueda = input.value;

    this.buscarSubject.next(input.value);

  }

  siguientePagina(): void {

    if (this.textoBusqueda.trim() !== '') {

      return;

    }

    this.offset += 20;

    this.cargarPokemons();

  }

  anteriorPagina(): void {

    if (this.textoBusqueda.trim() !== '') {

      return;

    }

    if (this.offset >= 20) {

      this.offset -= 20;

      this.cargarPokemons();

    }

  }

}