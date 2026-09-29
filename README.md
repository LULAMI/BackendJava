# Pre-entrega Java - Sistema de gestión TechLab

Aplicación de consola en Java para gestionar productos y pedidos de una tienda de tecnología.

## Autora
Ludmila Mariana Bravo Ruiz Diaz - Comisión 26223

## Funcionalidades
- Agregar productos (nombre, precio y stock)
- Listar productos
- Buscar productos por ID o por nombre y actualizar precio/stock
- Eliminar productos con confirmación
- Crear pedidos con varios productos, con control de stock y cálculo del total
- Al confirmar un pedido se descuenta el stock automáticamente
- Listar pedidos realizados

## Tecnologías
- Java 17
- Colecciones (`ArrayList`)
- Excepciones propias (`StockInsuficienteException`, `ProductoNoEncontradoException`)

## Estructura del proyecto
src/com/techlab/
├── Main.java
├── excepciones/
├── productos/
└── pedidos/

## Cómo ejecutarlo
Desde la raíz del proyecto:

javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out com.techlab.Main

También se puede abrir en IntelliJ, Eclipse o VS Code y ejecutar `Main`.

## Uso
Al iniciar aparece un menú numerado. Se elige una opción escribiendo su número y siguiendo las instrucciones en pantalla.