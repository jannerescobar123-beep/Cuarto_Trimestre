package com.demo_vr2.Janner;


import com.demo_vr2.Janner.dto.PersonaDto;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class ClienteRest {
    String URL = "http://localhost:8080/servicio";

    public static void main(String[] args) {

        ClienteRest miClienteRest =
                new ClienteRest();

        miClienteRest.llamarServicioPersona();
    }

    void llamarServicioPersona() {
        guardarPersona(
                new PersonaDto(
                        "555",
                        "Juan Duque",
                        "423423",
                        26,
                        "Abogado",
                        "",
                        2));

        consultarPersonaPorPath(555);

        PersonaDto persona =
                new PersonaDto();

        persona.setDocumento("555");
        persona.setNombre("Juan Duque Zapata");
        persona.setEdad(27);
        persona.setTipo(3);

        actualizarPersona(persona);

        consultarPersonaPorPath(555);

        eliminarPersona(555);

        consultarPersonaPorPath(555);
    }
    public void consultarPersona(int id) {
        System.out.println("Iniciando consulta de persona...");
        //System.setProperty("jakarta.ws.rs.client.ClientBuilder","org.glassfish.jersey.client.JerseyClientBuilder");
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("/personas");
            Response response = webTarget.queryParam("id", id).
                    request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                PersonaDto persona = response.readEntity(PersonaDto.class);
                System.out.println("Persona encontrada: " + persona);
            } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                System.out.println("Persona no encontrada");
                String mensaje = response.readEntity(String.class);
                System.out.println("Mensaje del servidor: " + mensaje);
            } else {
                System.out.println("Error en la consulta. Código HTTP: " + response.getStatus());
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al consultar la persona, " +
                    "Verifique que el servicio esté disponible: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cliente.close();
        }

    }
    public void consultarProfesion(int id,String profesion) {
        System.out.println("Iniciando consulta de profesión...");
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("/profesion");
            Response response = webTarget.
                    queryParam("id", id)
                    .queryParam("profesion", profesion)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                PersonaDto profesionDto = response.readEntity(PersonaDto.class);
                System.out.println("Profesión encontrada: " + profesionDto);
            } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode())
            {
                System.out.println("No se encontró información para los parámetros propor cionados.");
                        String mensaje = response.readEntity(String.class);
                System.out.println("Mensaje del servidor: " + mensaje);
            } else {
                System.out.println("Error en la consulta. Código HTTP: " + response.getStatus());
            }
        }catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cliente.close();
        }
    }
    public void consultarPersonaPorPath(int id) {
        System.out.println("Iniciando consulta de profesión...");
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("/personas/" + id);
            Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                PersonaDto persona = response.readEntity(PersonaDto.class);
                System.out.println("Persona encontrada: " + persona);
            }
            else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                String mensaje = response.readEntity(String.class);
                System.out.println("Persona no encontrada: " + mensaje);
            } else {
                String mensaje = response.readEntity(String.class);
                System.out.println("Error inesperado. Código HTTP: " + response.getStatus() +
                        ". Mensaje: " + mensaje);
            }
        }catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cliente.close();
        }
    }
    public void consultarListaPersonas() {
        System.out.println("Iniciando consulta de lista de personas...");
        Client cliente = ClientBuilder.newClient();
        try {
            WebTarget webTarget = cliente.target(URL).path("/personas-list");
            Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                List<PersonaDto> personas = response.readEntity(new GenericType<List<PersonaDto>>() {});
                if (!personas.isEmpty()) {
                    System.out.println("Lista de personas encontradas:");
                    for (PersonaDto persona : personas) {
                        System.out.println(persona);
                    }
                } else {
                    System.out.println("La lista está vacía, aunque se devolvió un código 20 0.");
                }
            } else if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
                System.out.println("No hay contenido: la lista de personas está vacía.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cliente.close();
        }
    }
    public void guardarPersona(
            PersonaDto persona) {

        System.out.println(
                "Iniciando proceso para guardar persona...");

        Client cliente =
                ClientBuilder.newClient();

        try {

            WebTarget webTarget =
                    cliente.target(URL)
                            .path("/guardar");

            Response response =
                    webTarget
                            .request(MediaType.APPLICATION_JSON)
                            .post(
                                    Entity.entity(
                                            persona,
                                            MediaType.APPLICATION_JSON));

            if (response.getStatus()
                    ==
                    Response.Status.OK.getStatusCode()) {

                PersonaDto personaGuardada =
                        response.readEntity(
                                PersonaDto.class);

                System.out.println(
                        "Persona guardada con éxito: "
                                + personaGuardada);

            } else if (response.getStatus()
                    ==
                    Response.Status.BAD_REQUEST.getStatusCode()) {

                System.out.println(
                        "No se pudo guardar la persona.");

            }

        } catch (Exception e) {

            System.out.println(
                    "Ocurrió un error inesperado: "
                            + e.getMessage());

            e.printStackTrace();

        } finally {

            cliente.close();
        }
    }
    public void actualizarPersona(
            PersonaDto persona) {

        System.out.println(
                "Iniciando proceso para actualizar persona...");

        Client cliente =
                ClientBuilder.newClient();

        try {

            WebTarget webTarget =
                    cliente.target(URL)
                            .path("/actualizar");

            Response response =
                    webTarget
                            .request(MediaType.APPLICATION_JSON)
                            .put(
                                    Entity.entity(
                                            persona,
                                            MediaType.APPLICATION_JSON));

            if (response.getStatus()
                    ==
                    Response.Status.OK.getStatusCode()) {

                PersonaDto personaActualizada =
                        response.readEntity(
                                PersonaDto.class);

                System.out.println(
                        "Persona actualizada con éxito: "
                                + personaActualizada);

            } else if (response.getStatus()
                    ==
                    Response.Status.NOT_FOUND.getStatusCode()) {

                String mensaje =
                        response.readEntity(
                                String.class);

                System.out.println(
                        "Error: "
                                + mensaje);
            }

        } catch (Exception e) {

            System.out.println(
                    "Ocurrió un error inesperado: "
                            + e.getMessage());

            e.printStackTrace();

        } finally {

            cliente.close();
        }
    }
    public void eliminarPersona(int id) {

        System.out.println(
                "Iniciando proceso para eliminar persona con ID: "
                        + id);

        Client cliente =
                ClientBuilder.newClient();

        try {

            WebTarget webTarget =
                    cliente.target(URL)
                            .path("/eliminar/" + id);

            Response response =
                    webTarget
                            .request()
                            .delete();

            if (response.getStatus()
                    ==
                    Response.Status.OK.getStatusCode()) {

                String mensaje =
                        response.readEntity(
                                String.class);

                System.out.println(
                        "Respuesta del servidor: "
                                + mensaje);

            } else if (response.getStatus()
                    ==
                    Response.Status.NOT_FOUND.getStatusCode()) {

                String mensaje =
                        response.readEntity(
                                String.class);

                System.out.println(
                        "Error: "
                                + mensaje);
            }

        } catch (Exception e) {

            System.err.println(
                    "Ocurrió un error inesperado: "
                            + e.getMessage());

            e.printStackTrace();

        } finally {

            cliente.close();
        }
    }

}
