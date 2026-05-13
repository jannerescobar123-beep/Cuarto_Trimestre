from flask import Flask
app = Flask(__name__)
# Ruta principal
@app.route("/")
def inicio():
    return "¡Bienvenido a mi app Flask!"
# Ruta estática: /acerca
@app.route("/acerca")
def acerca():
    return "Esta es una app de ejemplo con Flask."
# Ruta dinámica: recibe un nombre por la URL
@app.route("/saludo/<nombre>")
def saludo(nombre):
    return f"¡Hola, {nombre}!"
# Ruta con parámetro entero
@app.route("/cuadrado/<int:numero>")
def cuadrado(numero):
    return f"El cuadrado de {numero} es {numero ** 2}"
if __name__ == "__main__":
    app.run(debug=True)