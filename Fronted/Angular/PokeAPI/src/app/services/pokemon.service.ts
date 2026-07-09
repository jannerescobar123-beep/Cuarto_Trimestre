import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {
  Observable,
  forkJoin,
  throwError,
  of,
  shareReplay
} from 'rxjs';

import {
  switchMap,
  map,
  catchError
} from 'rxjs/operators';

import {
  Pokemon,
  PokemonListResponse
} from '../models/pokemon';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  private readonly API_URL = 'https://pokeapi.co/api/v2/pokemon';

  constructor(private http: HttpClient) { }

  private getPokemonList(offset: number): Observable<PokemonListResponse> {

    return this.http.get<PokemonListResponse>(
      `${this.API_URL}?limit=20&offset=${offset}`
    );

  }

  private getPokemon(nombre: string): Observable<Pokemon> {

    return this.http.get<Pokemon>(
      `${this.API_URL}/${nombre.toLowerCase()}`
    );

  }

  getPokemons(offset: number): Observable<Pokemon[]> {

    return this.getPokemonList(offset).pipe( // trasforma la informacion

      switchMap((response) => { // recibe la respuesta

        const requests = response.results.map((pokemon) => // recorre los pokemones
          this.http.get<Pokemon>(pokemon.url) // y ejecuta cada uno
        );

        return forkJoin(requests);

      }),

      map((pokemons) =>
        pokemons.sort((a, b) => a.name.localeCompare(b.name))
      ),

      catchError((error) => {

        return throwError(() => error);

      }),

      shareReplay(1)

    );

  }

  buscarPokemon(nombre: string): Observable<Pokemon[]> {

    return this.getPokemon(nombre).pipe(

      map((pokemon) => [pokemon]),

      catchError(() => of([]))

    );

  }

}