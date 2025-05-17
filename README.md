# 🧮 Cotizaciones API

API REST desarrollada con **Java 17**, **Spring Boot 3**, y **MongoDB**, que consume cotizaciones de dólar blue y oficial desde una API externa. Guarda los datos periódicamente y permite consultarlos con filtros, estadísticas y cálculos de variación.

---

## 💡 ¿Por qué este proyecto?

Este proyecto fue diseñado para demostrar:
- Consumo de APIs externas con `WebClient`
- Persistencia con MongoDB
- Programación concurrente con `CompletableFuture`
- Buenas prácticas de arquitectura (controladores, servicios, repositorios)
- Tareas programadas (`@Scheduled`) y manejo de excepciones
- Exposición de endpoints REST de uso real para estadísticas económicas

---

## ⚙️ Tecnologías usadas

- Java 17
- Spring Boot 3.4.5
- Spring Data MongoDB
- WebClient (sin WebFlux)
- Spring Scheduler
- CompletableFuture
- MongoDB local
- IntelliJ IDEA

---

## 🔁 Funcionalidad

- Guarda cotizaciones automáticamente cada 1 hora
- Consulta todas las cotizaciones paginadas
- Filtro por fecha (`from` y `to`)
- Estadísticas: promedio, máximo y mínimo
- Variación porcentual entre dos fechas
- Última cotización del día

---

## 🔗 Ejemplo de endpoints

GET /cotizaciones
GET /cotizaciones/filtrar?from=2025-05-01T00:00&to=2025-05-15T00:00
GET /cotizaciones/estadisticas?from=2025-05-01T00:00&to=2025-05-15T00:00
GET /cotizaciones/variacion?from=2025-05-01T00:00&to=2025-05-15T00:00
GET /cotizaciones/hoy


---

## ▶️ Cómo correrlo

1. Tener MongoDB corriendo localmente (puerto por defecto: `27017`)
2. Clonar este repositorio
3. Ejecutar la clase `CotizacionesApiApplication` desde tu IDE
4. Probar los endpoints usando Postman o navegador

---

## 📍 Notas

- Swagger no fue integrado por razones de compatibilidad entre dependencias.
- Se priorizó mantener la aplicación **estable, funcional y profesional**.
- Se puede complementar con un microproyecto alternativo que documente los endpoints vía Swagger si se requiere.

---

## 👨‍💻 Autor

Luciano Barili – Abogado & Developer en transición al mundo IT  
[linkedin.com/in/lucianobarili](https://www.linkedin.com/in/lucianobarili)

---
