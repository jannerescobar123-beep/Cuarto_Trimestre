export interface PokemonListResponse{

  count:number;

  results:PokemonResult[];

}

export interface PokemonResult{

  name:string;

  url:string;

}

export interface Pokemon{

  id:number;

  name:string;

  height:number;

  weight:number;

  base_experience:number;

  sprites:PokemonSprites;

  types:PokemonType[];

}

export interface PokemonSprites{

  front_default:string;

}

export interface PokemonType{

  slot:number;

  type:{

      name:string;

  };

}