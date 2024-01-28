# Apis

Este proyecto constará de una serie de APIS que se irán documentando aquí mismo a medida que se vayan generando. Además, el proyecto debe tener disponible el Swagger.

## GET api/koi/orders

Devolverá un listado con todas las ordenes que se encuentran en BD. Al ser 1.000.000 de ordenes aproximadamente, se hará una lista paginada, y cada página se pedirá al server para no cargarlas todas en el navegador del cliente.

[URL de SwaggerUI](http://localhost:8080/swagger-ui/index.html#/)


## GET api/koi/orders/summary

Devolverá un objeto con los totales para cada campo.


## POST api/koi/orders/import-orders

Generará la petición al API que nos conectaremos para importar las ordenes a Base de datos.


## get api/koi/document/download

Devolverá el documento para su descarga.