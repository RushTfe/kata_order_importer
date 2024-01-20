# Kata order importer

## Introducción

Para esta app será necesario importar desde un GET una serie de ordenes hacia nuestra Base de datos. 

Este GET va a devolver un listado de órdenes con el siguiente formato.

``` json
{
    "page": 1,
    "content": [
        {
            "uuid": "1858f59d-8884-41d7-b4fc-88cfbbf00c53",
            "id": "443368995",
            "region": "Sub-Saharan Africa",
            "country": "South Africa",
            "item_type": "Fruits",
            "sales_channel": "Offline",
            "priority": "M",
            "date": "7/27/2012",
            "ship_date": "7/28/2012",
            "units_sold": 1593,
            "unit_price": 9.33,
            "unit_cost": 6.92,
            "total_revenue": 14862.69,
            "total_cost": 11023.56,
            "total_profit": 3839.13,
            "links": {
                "self": "https://kata-espublicotech.g3stiona.com:443/v1/orders/1858f59d-8884-41d7-b4fc-88cfbbf00c53"
            }
        },
        {
            ....
        }
    ],
    "links": {
        "next": "https://kata-espublicotech.g3stiona.com:443/v1/orders?page=2&max-per-page=100",
        "self": "https://kata-espublicotech.g3stiona.com:443/v1/orders?page=1&max-per-page=100"
    }
}
```

## El api

Tras revisar un poco el **GET** proporcionado, podemos observar lo siguiente:

### Parámetros
El api cuenta con 2 parámetros para consultar:

- page: La página que se quiere consultar. 
  - Mínimo 0.
  - Máximo 1000.
- max-per-page: La cantidad de artículos que se quieren consultar para cada página.
  - Mínimo 1 (0 devuelve 204 No content)
  - Máximo 1000.

### Datos

Los datos no parecen tener ningún orden a la hora de consultarlos, asumimos que, no se repetirán datos en siguientes páginas, si bien al insertarlos en BD tendrán su propio ID que no permitirá que se repitan pedidos.

Parece haber un total de 1.001.000 de órdenes en esta api, por lo que no se importaran todas de golpe (tampoco 1 a 1).

## Estrategias a seguir (Backend)

Para llevar a cabo esta tarea, será necesario importar de manera organizada el conjunto de datos. Para ello, habrá que tener en cuenta algunas cosas, que se testearán para intentar maximizar el rendimiento.

### Consultas al API

Teniendo en cuenta que el API devuelve un máximo de 1000 artículos por página, usaré este valor para hacer la menor cantidad de consultas posibles a dicho endpoint. Es decir, se harán 1001 consultas para obtener 1000 artículos por cada una de ellas.

### Estrategia en código

Al activar la importación, en principio, se hará una importación completa, es decir, se hará un bucle que vaya desde 0 (porque hay página 0) hasta que el API devuelva un 204 no content, de esta manera nos aseguramos de que se van a importar todas las páginas.

Por cada vuelta, se va a guardar un BATCH de 1000 ordenes en la Base de datos. 

_**OPCIONAL**_ Cuando se concluya la tarea, y todo funcione correctamente, si hubiera tiempo,se creará  una tabla de control, para saber qué páginas han sido importadas, esta tabla debería ser como la siguiente:

| num_page | imported | comments         |
| -------- | -------- | ---------------- |
| 1        | true     | -                |
| 2        | false    | Error cualquiera |
| 3        | true     | -                |
| ........ | ........ | ................ |

De esta manera llevaremos control de las páginas que se han importado, habilitando una sección en UI para poder revisar el proceso de importación, con un botón a la derecha para reintentar la importación de una página concreta.

### Estrategia en BD

- Se usará flyway para crear los parches necesarios a la aplicación
- Se crearán las tablas y relaciones necesarias para tener la información lo mejor organizada posible, intentando evitar duplicidades de datos.
  - Evidentemente lo  sencillo sería una tabla "order" en la que se guarden todos los datos del tirón, pero, si analizo un poco los datos, es posible que se puedan crear tablas para almacenar los tipos especificos. Esto se revisará más adelante cuando se cree el modelo de la Base de Datos sobre el que trabajará la aplicación.

### Arquitectura del código

#### APIS

La aplicación ofrecerá una serie de endpoints REST, para que desde postman, o desde una UI se pueda atacar a la aplicación para obtener los datos necesarios.

Estos endpoints, en principio, **NO ESTARÁN SECURIZADOS** ya que está fuera del scope de la tarea, por lo que no será necesario tener ni un rol, ni una autenticación previa. Si sobrara tiempo, se evaluará si se introduce este cambio. Por ejemplo, podría ser interesante que, el usuario administrador, fuera el único con acceso a la sección de control de importación opcional.

Las apis deberán devolver sus respectivos códigos de error cuando fallen


#### Casos de uso

La aplicación debe trabajar por casos de uso, es decir, en una misma carpeta se encontrará, en principio
- Controller
- Caso de uso

En principio, se contemplarán 4 casos de uso (y tres opcionales):

1. GET que se ejecutará para mostrar el listado de los datos en pantalla (si los hubiera) paginados.
   1. (Opcional) Si sobra tiempo, considerar añadir parámetros de búsqueda para poder filtrar dicho listado.
2. GET que se ejecutará para mostrar los resumenes totales
3. POST para importar los datos. Esto podría lanzar la importación en un JOB en otro hilo, de modo que no se bloquée la UI mientras se realiza la importación.
4. GET para descargar el fichero CSV
5. (Opcional) GET para la pantalla de control de importación
6. (Opcional) POST para la importación de una página concreta desde el panel de control de importación.
7. (Opcional) DELETE para eliminar la base de datos al completo.

#### Servicios

La app contará con una capa de servicios, que se encargará de recibir un objeto del dominio del servicio, y con el, hacer lo que fuera necesario, por ejemplo, guardarlos en Base de Datos, haciendo las transformaciones a las entidades necesarias.

#### Repositorio

La app contará con una capa de conexión a Base de datos, que en principio se trabajará con JPA directamente, aunque si fueran necesarias consultas más complejas, se podrán tratar con HQL.

Esta capa no debe contener ninguna lógica, solo las queries necesarias a la Base de datos. Por esto mismo, debe solo enviar y recibir entidades (Optionals, List o la propia entity)

#### Swagger

Se usará swagger para documentar las apis del proyecto.


## Estrategias a seguir (Frontend)

Esta sección permanecerá de momento a medio analizar, hasta que se tenga listo el backend.

La idea es, trabajar desde REACT para hacer la UI, y atacar los endpoints del Backend de java.

La UI debe ser muy sencilla, no es mi campo, así que es a la que le dedicaré menos tiempo, pero debe contar al menos con lo siguiente:

- Una página de inicio
  - Aquí dentro, debe haber un listado PÁGINADO con las órdenes.
    - No se debe cargar el listado en memoria del cliente, por lo que solo se traerá la paginación de, por ejemplo, 500 ordenes (a valorar).
    - Un resumen con los totales por cada tipo.
    - Un botón para descargar el CSV (solo activo si hubieran datos cargados)
      - Abre un modal, que es quien manda la petición
    - Un botón para importar los datos (Solo activo si no han sido cargados previamente)
      - Abre un modal, que es quien manda la petición
    - (Opcional) un botón para borrar la base de datos
      - Abre un modal, que es quien manda la petición
    - (Opcional) una tabla con las importaciones
      - (Opcional) para cada entrada de la tabla, un botón para reimportar si no se hubiera importado la página.