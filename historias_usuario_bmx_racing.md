# Historias de Usuario — Suite de Gestión para BMX Racing

> **Versión:** 0.1 — Elicitación Inicial  
> **Roles cubiertos:** Entrenador (técnico y preparador físico) · Deportista  
> **Alcance:** Módulos prioritarios para la primera iteración incremental del sistema

---

## Índice

1. [Gestión de Perfiles](#1-gestión-de-perfiles)
2. [Gestión de Clubes y Preselección](#2-gestión-de-clubes-y-preselección)
3. [Planificación y Seguimiento de Rutinas de Gimnasio](#3-planificación-y-seguimiento-de-rutinas-de-gimnasio)
4. [Planificación y Seguimiento de Entrenamientos en Pista](#4-planificación-y-seguimiento-de-entrenamientos-en-pista)
5. [Control de Progreso y Estadísticas](#5-control-de-progreso-y-estadísticas)
6. [Gestión de Competencias](#6-gestión-de-competencias)
7. [Plan Nutricional y Salud](#7-plan-nutricional-y-salud)
8. [Comunicación y Notificaciones](#8-comunicación-y-notificaciones)
9. [Dashboard del Deportista](#9-dashboard-del-deportista)
10. [Dashboard del Entrenador](#10-dashboard-del-entrenador)

---

## 1. Gestión de Perfiles

### HU-01 — Registro de deportista

> **Como** entrenador,  
> **quiero** poder registrar a un deportista en el sistema con sus datos básicos (nombre completo, fecha de nacimiento, categoría UCI, peso, talla, club de procedencia y contacto de emergencia),  
> **para** tener un perfil digital centralizado que reemplace el registro en papel y facilite la consulta en cualquier momento.

**Criterios de aceptación sugeridos:**
- El sistema calcula automáticamente la categoría UCI según la fecha de nacimiento del deportista.
- El perfil puede adjuntar foto y documento de identidad.
- El entrenador puede editar el perfil en cualquier momento.

---

### HU-02 — Registro del entrenador

> **Como** administrador del club,  
> **quiero** poder registrar a un entrenador con su información profesional (nombre, especialidad: técnico de pista / preparador físico / ambos, certificaciones y club al que pertenece),  
> **para** diferenciar roles dentro del sistema y asignar correctamente las responsabilidades sobre los deportistas.

---

### HU-03 — Perfil del deportista visible para sí mismo

> **Como** deportista,  
> **quiero** tener acceso a mi propio perfil con mis datos personales, mi categoría, mi club, mi peso actual, mi altura y mis documentos deportivos (licencia, carnet médico),  
> **para** consultarlos en cualquier momento sin depender del entrenador ni de carpetas físicas.

---

### HU-04 — Actualización de datos físicos del deportista

> **Como** entrenador,  
> **quiero** poder actualizar periódicamente el peso y la talla de cada deportista, manteniendo un historial de mediciones con fecha,  
> **para** monitorear la evolución física y ajustar las cargas de entrenamiento de manera acorde.

---

### HU-05 — Asignación de deportista a club o preselección

> **Como** entrenador,  
> **quiero** poder asociar un deportista a mi club y, de forma simultánea, marcarlo como integrante de la Preselección si aplica,  
> **para** gestionar correctamente la doble pertenencia que existe en el BMX Racing sin duplicar su información.

**Nota:** Un deportista puede pertenecer a su club de origen **y** a la Preselección al mismo tiempo. El sistema debe reflejar ambas vinculaciones.

---

## 2. Gestión de Clubes y Preselección

### HU-06 — Creación y configuración del club

> **Como** entrenador,  
> **quiero** poder crear el perfil de mi club con nombre, logo, colores, horarios de entrenamiento habituales y tarifa mensual,  
> **para** contar con una identidad propia dentro del sistema y gestionar de forma independiente mis deportistas y metodología.

---

### HU-07 — Listado de deportistas del club

> **Como** entrenador,  
> **quiero** ver un listado completo de los deportistas de mi club, con filtros por categoría, edad y estado de pago de la mensualidad,  
> **para** tener una visión rápida del estado general del grupo y actuar oportunamente.

---

### HU-08 — Gestión de deportistas en la Preselección

> **Como** entrenador de la Preselección,  
> **quiero** poder agregar deportistas de distintos clubes a la Preselección y planificar sus entrenamientos de manera independiente a los de sus clubes de origen,  
> **para** trabajar el componente representativo del departamento sin interferir con la dinámica interna de cada club.

---

### HU-09 — Control de mensualidades

> **Como** entrenador,  
> **quiero** registrar y consultar el estado de pago de la mensualidad de cada deportista (al día, pendiente, en mora) con fecha de último pago,  
> **para** llevar un control financiero básico del club sin necesidad de hojas de cálculo o registros en papel.

---

## 3. Planificación y Seguimiento de Rutinas de Gimnasio

### HU-10 — Creación de rutina de gimnasio grupal

> **Como** preparador físico,  
> **quiero** poder crear una rutina de gimnasio y asignarla a un grupo completo (todo el club o un subgrupo como la Preselección), especificando los ejercicios, número de series, repeticiones, carga sugerida y tipo de ejercicio (Fuerza, Pliometría, Reacción, Estiramiento, Velocidad),  
> **para** dejar de escribir las rutinas en tableros físicos que se borran y tener el plan accesible para todos los deportistas desde su celular.

**Criterios de aceptación sugeridos:**
- La rutina puede organizarse por días de la semana.
- Cada ejercicio puede incluir notas técnicas (descripción, video de referencia o imagen).
- La rutina puede duplicarse y modificarse para semanas futuras.

---

### HU-11 — Creación de rutina de gimnasio individual

> **Como** preparador físico,  
> **quiero** poder asignar una rutina personalizada a un deportista específico (diferente a la rutina grupal),  
> **para** atender necesidades individuales como recuperación de lesión, trabajo de debilidades específicas o preparación de pico de forma.

---

### HU-12 — Registro de cargas ejecutadas por el deportista

> **Como** deportista,  
> **quiero** poder registrar en cada sesión de gimnasio el peso real con el que ejecuté cada ejercicio (por serie y repetición),  
> **para** llevar un historial propio de mis cargas y no depender de la memoria o de anotaciones en papel.

---

### HU-13 — Registro y seguimiento del 1RM

> **Como** deportista,  
> **quiero** que el sistema registre y actualice automáticamente mi 1RM (una repetición máxima) para los ejercicios de fuerza clave (sentadilla, peso muerto, press de banca, entre otros) cada vez que se realice una medición oficial,  
> **para** tener una referencia clara de mi fuerza máxima actual y poder ver cómo evoluciona en el tiempo.

---

### HU-14 — Consulta de cargas por ejercicio (vista entrenador)

> **Como** preparador físico,  
> **quiero** poder ver, para cada deportista, el historial de cargas registradas por ejercicio a lo largo del tiempo,  
> **para** tomar decisiones informadas sobre la progresión de cargas, detectar estancamientos y evitar lesiones por sobrecarga.

---

### HU-15 — Biblioteca de ejercicios

> **Como** preparador físico,  
> **quiero** contar con una biblioteca de ejercicios predefinidos (con nombre, grupo muscular, tipo y descripción) que pueda usar al crear rutinas, y a la que pueda agregar ejercicios propios,  
> **para** agilizar la creación de rutinas y mantener consistencia en los nombres de los ejercicios entre sesiones.

---

## 4. Planificación y Seguimiento de Entrenamientos en Pista

### HU-16 — Programación de sesión de entrenamiento en pista

> **Como** entrenador técnico,  
> **quiero** poder programar una sesión de entrenamiento en pista para una fecha y hora determinadas, especificando el tipo de trabajo a realizar (trabajo técnico, aceleraciones en subida, aceleraciones en bajada, pliometría, trabajo de salida desde la rampa, entre otros) y los deportistas convocados,  
> **para** que los deportistas reciban el plan con anticipación y lleguen preparados.

---

### HU-17 — Registro de asistencia a entrenamientos

> **Como** entrenador,  
> **quiero** registrar la asistencia de cada deportista a cada sesión de entrenamiento programada,  
> **para** llevar un control de la constancia y correlacionarlo con el progreso físico y técnico.

---

### HU-18 — Registro de Time-Trial por deportista

> **Como** entrenador técnico,  
> **quiero** poder registrar el tiempo de una vuelta completa (Time-Trial) para cada deportista durante una sesión específica, indicando las condiciones (pista seca, pista húmeda, con o sin rivales),  
> **para** tener un indicador cuantitativo de rendimiento en pista que sea comparable entre sesiones.

**Ejemplo:** Sesión del 14/06/2026 — Deportista: Camilo Rengifo — Tiempo: 33.45 s — Condición: Pista seca.

---

### HU-19 — Registro de tiempos parciales (sectores de pista)

> **Como** entrenador técnico,  
> **quiero** poder registrar tiempos por sectores del circuito (salida–primera curva, primera recta, sección rítmica, recta final),  
> **para** identificar en qué parte específica del recorrido cada deportista pierde o gana ventaja y focalizar el trabajo técnico.

---

### HU-20 — Registro de trabajo de salida (gate start)

> **Como** preparador físico y entrenador técnico,  
> **quiero** registrar el tiempo de reacción y los primeros 8 metros de aceleración desde la rampa de salida para cada deportista en cada sesión donde se trabaje este componente,  
> **para** medir la mejora en uno de los factores más determinantes del rendimiento en BMX Racing.

---

## 5. Control de Progreso y Estadísticas

### HU-21 — Gráfica de evolución de tiempos en pista

> **Como** deportista,  
> **quiero** ver una gráfica con la evolución de mis tiempos de Time-Trial a lo largo del tiempo,  
> **para** tener una evidencia visual de mi progreso (o retroceso) y motivarme a seguir mejorando.

---

### HU-22 — Gráfica de evolución de cargas en gimnasio

> **Como** deportista,  
> **quiero** ver una gráfica de la progresión de mis cargas para cada ejercicio clave del gimnasio,  
> **para** saber cuánto he mejorado en fuerza desde que empecé a registrar datos, y establecer nuevas metas realistas.

---

### HU-23 — Vista de progreso por deportista (entrenador)

> **Como** entrenador,  
> **quiero** acceder a un panel de progreso individual de cada deportista que consolide sus tiempos en pista, sus cargas en gimnasio, su asistencia y sus resultados en competencia,  
> **para** tener una visión 360° de cada atleta y fundamentar mis decisiones de planificación con datos reales.

---

### HU-24 — Definición y seguimiento de objetivos

> **Como** entrenador,  
> **quiero** poder definir objetivos cuantificables para cada deportista (por ejemplo: "bajar el Time-Trial a 31.00 s antes del 30 de agosto" o "alcanzar un 1RM de sentadilla de 100 kg"),  
> **para** establecer metas con fecha límite y hacer seguimiento de su cumplimiento dentro del sistema.

---

### HU-25 — Seguimiento de objetivos por parte del deportista

> **Como** deportista,  
> **quiero** ver mis objetivos actuales con una barra o indicador de progreso hacia la meta,  
> **para** tener claridad sobre lo que tengo que lograr, cuánto me falta y en cuánto tiempo.

---

### HU-26 — Registro de logros y hitos

> **Como** entrenador,  
> **quiero** poder registrar logros significativos de un deportista (primer podio, nuevo 1RM personal, primer Time-Trial por debajo de X segundos),  
> **para** crear un historial de hitos que motive al deportista y documente su trayectoria deportiva.

---

## 6. Gestión de Competencias

### HU-27 — Registro de competencias próximas

> **Como** entrenador,  
> **quiero** poder registrar las competencias del calendario (nombre, fecha, lugar, categorías participantes y tipo: local, departamental, nacional, internacional),  
> **para** que tanto yo como mis deportistas tengamos visibilidad de los eventos que se aproximan y podamos planificar la preparación.

---

### HU-28 — Inscripción de deportistas a competencias

> **Como** entrenador,  
> **quiero** poder marcar qué deportistas participarán en cada competencia registrada,  
> **para** llevar control de la nómina y tener un histórico de participaciones por deportista y por evento.

---

### HU-29 — Registro de resultados en competencia

> **Como** entrenador,  
> **quiero** registrar el resultado obtenido por cada deportista en cada competencia en la que participó (posición final, tiempo registrado, fase alcanzada: clasificatoria, semifinal, final),  
> **para** tener un historial de resultados competitivos que me permita evaluar el impacto del entrenamiento sobre el rendimiento real en carrera.

---

### HU-30 — Vista de competencias del deportista

> **Como** deportista,  
> **quiero** ver en mi perfil el listado de competencias en las que he participado, con mis resultados y tiempos registrados en cada una,  
> **para** tener documentada mi trayectoria competitiva y poder compartirla si es necesario (por ejemplo, para aplicar a una convocatoria o beca).

---

## 7. Plan Nutricional y Salud

### HU-31 — Asignación de plan nutricional básico

> **Como** entrenador o preparador físico,  
> **quiero** poder adjuntar o redactar un plan nutricional básico para un deportista o para el grupo completo, incluyendo recomendaciones de macronutrientes, hidratación y momentos de ingesta según el tipo de sesión del día (gym, pista, descanso),  
> **para** complementar el entrenamiento físico con una guía nutricional dentro de la misma plataforma.

---

### HU-32 — Recomendaciones de salud personalizadas

> **Como** deportista,  
> **quiero** recibir recordatorios y recomendaciones de salud contextualizadas según mi plan del día (ejemplo: "Hoy tienes sesión de fuerza — asegúrate de ingerir carbohidratos 2 horas antes" o "Día de pista — hidratación mínima recomendada: 2.5 L"),  
> **para** aplicar buenas prácticas de nutrición y recuperación sin tener que recordarlas de memoria.

---

### HU-33 — Registro de lesiones e historial médico deportivo

> **Como** entrenador,  
> **quiero** poder registrar las lesiones que ha sufrido un deportista (tipo, fecha, tiempo de recuperación, área del cuerpo) y si está en período de recuperación activa,  
> **para** ajustar su plan de entrenamiento y evitar recaídas o agravamientos por sobrecarga.

---

## 8. Comunicación y Notificaciones

### HU-34 — Recordatorio de sesión de entrenamiento

> **Como** deportista,  
> **quiero** recibir una notificación con anticipación (por ejemplo, 2 horas antes) cuando tenga una sesión de entrenamiento programada, con el tipo de trabajo que se hará ese día,  
> **para** llegar preparado, con la indumentaria correcta y habiendo comido adecuadamente.

---

### HU-35 — Notificación de nueva rutina asignada

> **Como** deportista,  
> **quiero** recibir una notificación cuando mi entrenador me asigne una nueva rutina de gimnasio o un plan de entrenamiento individual,  
> **para** consultarla con anticipación y llegar a la sesión sabiendo qué se va a trabajar.

---

### HU-36 — Anuncio del entrenador al grupo

> **Como** entrenador,  
> **quiero** poder enviar un mensaje o anuncio a todos los deportistas del club (o a un subgrupo como la Preselección) desde la plataforma,  
> **para** comunicar cambios de horario, suspensiones de entrenamiento, convocatorias o cualquier información relevante sin depender de grupos de WhatsApp externos.

---

### HU-37 — Recordatorio de pago de mensualidad

> **Como** entrenador,  
> **quiero** que el sistema envíe automáticamente un recordatorio al deportista (o a su acudiente si es menor de edad) cuando su mensualidad esté próxima a vencer o en mora,  
> **para** reducir la gestión manual del cobro y mejorar la puntualidad de los pagos.

---

## 9. Dashboard del Deportista

### HU-38 — Vista general del dashboard del deportista

> **Como** deportista,  
> **quiero** tener una pantalla de inicio (dashboard) que me muestre de forma resumida: mi próxima sesión de entrenamiento, mi rutina de gimnasio del día, mi progreso hacia mis objetivos activos, mi Time-Trial más reciente y mis próximas competencias,  
> **para** tener en un solo vistazo todo lo relevante de mi actividad deportiva sin tener que navegar por múltiples secciones.

---

### HU-39 — Acceso rápido al historial de entrenamientos por fecha

> **Como** deportista,  
> **quiero** poder consultar mis sesiones de entrenamiento anteriores organizadas por fecha, con los detalles de lo que se hizo en cada una (tipo de trabajo, cargas, tiempos),  
> **para** revisar mi historial cuando lo necesite y identificar patrones en mi rendimiento.

---

### HU-40 — Visualización de mis 1RM por ejercicio

> **Como** deportista,  
> **quiero** ver en mi dashboard un listado de mis 1RM actuales para cada ejercicio de fuerza relevante, con la fecha de la última medición,  
> **para** conocer en todo momento mis marcas máximas sin tener que buscar entre registros pasados.

---

## 10. Dashboard del Entrenador

### HU-41 — Vista general del dashboard del entrenador

> **Como** entrenador,  
> **quiero** tener un dashboard que me muestre: los entrenamientos programados para hoy y la semana, un resumen de asistencia del grupo, los deportistas con mensualidad vencida, los objetivos próximos a vencer y los resultados más recientes en competencia,  
> **para** tener control operativo de mi club desde una sola pantalla.

---

### HU-42 — Comparativa de rendimiento entre deportistas

> **Como** entrenador,  
> **quiero** poder comparar los tiempos de Time-Trial o las cargas de un ejercicio específico entre varios deportistas en un período determinado,  
> **para** identificar quién está respondiendo mejor al plan de entrenamiento, quién necesita más atención y cómo se distribuye el nivel del grupo.

---

### HU-43 — Resumen de la sesión de entrenamiento terminada

> **Como** entrenador,  
> **quiero** poder cerrar una sesión de entrenamiento y dejar un resumen con observaciones generales (ej.: "grupo con bajo nivel de energía", "excelente trabajo de salidas", "ajustar carga de sentadilla la próxima semana"),  
> **para** dejar constancia cualitativa de cada sesión y usarla como insumo en la planificación futura.

---

## Notas finales y consideraciones de arquitectura

- **Incrementalidad:** Se recomienda priorizar los módulos 1, 2, 3 y 5 (Perfiles, Clubes, Gimnasio y Progreso) como MVP, dado que aportan valor inmediato y son los más mencionados por los entrenadores durante la elicitación.
- **Multi-club:** El sistema debe soportar desde el inicio la existencia de múltiples clubes independientes, cada uno con su propio entrenador y deportistas, sin que la información se mezcle.
- **Roles y permisos:** Se deben definir permisos claros: el entrenador puede ver y editar toda la información de sus deportistas; el deportista solo puede ver la propia (y registrar sus cargas).
- **Doble vinculación (Club + Preselección):** Esta regla de negocio debe estar clara en el modelo de datos desde el inicio para evitar rediseños costosos.
- **Categorías UCI:** El sistema debe tener codificadas las categorías oficiales (desde 5 años hasta Máster 30+) y calcularlas automáticamente según la fecha de nacimiento, dado que son la base de inscripción a cualquier competencia.

---

*Documento generado en etapa de elicitación. Sujeto a revisión y ampliación con cada ciclo de entrevistas a usuarios reales (entrenadores y deportistas).*
