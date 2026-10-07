# Pre-entrega Java - Sistema de gestión TechLab

Aplicación de consola en Java para gestionar productos y pedidos de una tienda de tecnología, aplicando herencia y polimorfismo.

## Autora
Ludmila Mariana Bravo Ruiz Diaz - Comisión 26223

## Funcionalidades
- Agregar productos electrónicos o alimenticios (nombre, precio, stock y dato propio de cada tipo)
- Listar productos mostrando su tipo y detalle específico
- Buscar productos por ID o por nombre y actualizar precio, stock y el atributo propio de cada tipo
- Eliminar productos con confirmación
- Crear pedidos con varios productos, con control de stock y cálculo del total
- Al confirmar un pedido se descuenta el stock automáticamente
- Listar pedidos realizados

## Conceptos de POO aplicados
- Clase abstracta `Producto` con los métodos abstractos `getTipoProducto()` y `getDetalleEspecifico()`
- Herencia con `extends`: `ProductoElectronico` (garantía en meses) y `ProductoAlimenticio` (días para el vencimiento)
- Polimorfismo: una única lista de `Producto` que guarda ambos tipos
- Sobrescritura con `@Override`, uso de `super` y `toString()`
- `instanceof` y casting para actualizar el atributo propio de cada subclase

## Tecnologías
- Java 17
- Colecciones (`ArrayList`)
- Excepciones propias (`StockInsuficienteException`, `ProductoNoEncontradoException`)

## Estructura del proyecto
```
src/com/techlab/
├── Main.java
├── excepciones/
├── productos/
│   ├── Producto.java
│   ├── ProductoElectronico.java
│   ├── ProductoAlimenticio.java
│   └── ProductoService.java
└── pedidos/
```

## Cómo ejecutarlo
Desde la raíz del proyecto (PowerShell):

```
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out com.techlab.Main
```

También se puede abrir en IntelliJ, Eclipse o VS Code y ejecutar `Main`.

## Uso
Al iniciar aparece un menú numerado. Se elige una opción escribiendo su número y siguiendo las instrucciones en pantalla. Al agregar un producto se elige primero su tipo (electrónico o alimenticio).
