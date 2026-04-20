class Calculadora {

    sumar(n1: number, n2: number): number {
        return n1 + n2;
    }

    restar(n1: number, n2: number): number {
        return n1 - n2;
    }

    multiplicacion(n1: number, n2: number): number {
        return n1 * n2;
    }

    divicion(n1: number, n2: number): number {
        return n1 / n2;
    }

    calcular(operacion: string, n1: number, n2: number): number {
        switch (operacion) {
            case "sumar":
                return this.sumar(n1, n2);

            case "restar":
                return this.restar(n1, n2);

            case "multiplicar":
                return this.multiplicacion(n1, n2);

            case "dividir":
                if (n2 === 0) {
                    throw new Error("No se puede dividir entre cero");
                }
                return this.divicion(n1, n2);

            default:
                throw new Error("Operación no válida");
        }
    }
}

const calc = new Calculadora();

console.log(calc.calcular("sumar", 10, 5));
console.log(calc.calcular("restar", 10, 5));
console.log(calc.calcular("multiplicar", 10, 5));
console.log(calc.calcular("dividir", 10, 5));