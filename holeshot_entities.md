# Holeshot — Modelo de Entidades JPA

> **Paquete raíz:** `io.holeshot.core`  
> **Versión del modelo:** 1.0  
> **Estándar:** Tercera Forma Normal (3FN)  
> **Convenciones:** Long como PK, soft delete con `deletedAt`, auditoría heredada de `Auditable`

---

## Índice

1. [Clase base — `Auditable`](#1-clase-base--auditable)
2. [Paquete `domain.user`](#2-paquete-domainuser)
3. [Paquete `domain.club`](#3-paquete-domainclub)
4. [Paquete `domain.training`](#4-paquete-domaintraining)
5. [Paquete `domain.test`](#5-paquete-domaintest)
6. [Catálogos por paquete](#6-catálogos-por-paquete)
7. [Resumen de colecciones JPA](#7-resumen-de-colecciones-jpa)

---

## 1. Clase base — `Auditable`

**Paquete:** `io.holeshot.core.domain.shared`

Superclase abstracta que todas las entidades principales extienden. Centraliza los campos de auditoría y evita duplicación en cada tabla.

```java
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;
}
```

**Campos heredados por todas las entidades que la extienden:**

| Campo | Tipo | Descripción |
|---|---|---|
| `createdAt` | `LocalDateTime` | Fecha y hora de creación, no modificable |
| `updatedAt` | `LocalDateTime` | Fecha y hora de última modificación |
| `createdBy` | `Long` | ID del usuario que creó el registro |
| `updatedBy` | `Long` | ID del usuario que hizo la última modificación |

> Requiere `@EnableJpaAuditing` en la clase de configuración principal y un bean `AuditorAware<Long>` que extraiga el ID del usuario autenticado desde el `SecurityContext`.

---

## 2. Paquete `domain.user`

Contiene todas las entidades relacionadas con la identidad del usuario, su perfil deportivo, documentos, mediciones físicas y equipamiento.

---

### 2.1 `User`

Representa la identidad de cualquier persona en el sistema, independientemente de su rol. No contiene datos deportivos ni de club.

**Tabla:** `users`  
**Extiende:** `Auditable`  
**Anotación:** `@Table(name = "users")`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `name` | `String` | `VARCHAR(80)` | `NOT NULL` |
| `lastName` | `String` | `VARCHAR(80)` | `NOT NULL` |
| `email` | `String` | `VARCHAR(150)` | `NOT NULL, UNIQUE` |
| `password` | `String` | `VARCHAR(255)` | `NOT NULL` |
| `birthDate` | `LocalDate` | `DATE` | `NOT NULL` |
| `phoneNumber` | `String` | `VARCHAR(20)` | nullable |
| `documentNumber` | `String` | `VARCHAR(30)` | nullable |
| `deletedAt` | `LocalDateTime` | `TIMESTAMP` | nullable — soft delete |

**Relaciones:**

| Relación | Tipo | Entidad destino | FK | Colección | Justificación de colección |
|---|---|---|---|---|---|
| `userProfile` | `@OneToOne` | `UserProfile` | `mappedBy = "user"` | — | Uno a uno, no aplica colección |
| `enrollments` | `@OneToMany` | `Enrollment` | `mappedBy = "user"` | `List<Enrollment>` | El orden cronológico de inscripciones importa para auditoría; `List` preserva orden de inserción con `@OrderBy("startDate ASC")` |
| `physicalMeasurements` | `@OneToMany` | `PhysicalMeasurement` | `mappedBy = "recordedBy"` | `List<PhysicalMeasurement>` | Historial cronológico ordenado por fecha |
| `injuriesRecorded` | `@OneToMany` | `Injury` | `mappedBy = "recordedBy"` | `List<Injury>` | Historial cronológico |
| `oneRmRecordsRegistered` | `@OneToMany` | `OneRmRecord` | `mappedBy = "recordedBy"` | `List<OneRmRecord>` | Historial cronológico por fecha |
| `testsRecorded` | `@OneToMany` | `Test` | `mappedBy = "recordedBy"` | `List<Test>` | Historial cronológico |
| `exercisesCreated` | `@OneToMany` | `Exercise` | `mappedBy = "createdBy"` | `Set<Exercise>` | No importa el orden; evita duplicados por nombre; `Set` ofrece O(1) en contiene |
| `routinesCreated` | `@OneToMany` | `Routine` | `mappedBy = "createdBy"` | `List<Routine>` | Orden por versión y fecha importa |
| `routineAssignmentsAssigned` | `@OneToMany` | `RoutineAssignment` | `mappedBy = "assignedBy"` | `List<RoutineAssignment>` | Historial cronológico de asignaciones |

---

### 2.2 `UserProfile`

Extiende la identidad del usuario con todos los datos específicamente deportivos. Solo los usuarios con rol de deportista (`ATHLETE`) tienen un perfil asociado.

**Tabla:** `user_profiles`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `documentType` | `String` | `VARCHAR(20)` | `CC \| TI \| PASSPORT` |
| `documentUrl` | `String` | `VARCHAR(500)` | nullable |
| `photoUrl` | `String` | `VARCHAR(500)` | nullable |
| `weight` | `Double` | `DECIMAL(5,2)` | nullable — caché del último valor |
| `height` | `Double` | `DECIMAL(5,2)` | nullable — caché del último valor |

**Relaciones ManyToOne / OneToOne (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `user` | `@OneToOne` | `User` | `user_id` — `UNIQUE` |
| `uciCategory` | `@ManyToOne` | `UciCategory` | `uci_category_id` |

**Relaciones inversas OneToMany / OneToOne:**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `uciLicense` | `@OneToOne` | `UciLicense` | `"userProfile"` | — | Uno a uno |
| `insurance` | `@OneToOne` | `Insurance` | `"userProfile"` | — | Uno a uno |
| `bicycles` | `@OneToMany` | `Bicycle` | `"userProfile"` | `List<Bicycle>` | Un deportista puede tener varias motos; orden por fecha de adquisición con `@OrderBy` |
| `physicalMeasurements` | `@OneToMany` | `PhysicalMeasurement` | `"userProfile"` | `List<PhysicalMeasurement>` | Historial temporal ordenado; se consulta frecuentemente para graficar evolución |
| `injuries` | `@OneToMany` | `Injury` | `"userProfile"` | `List<Injury>` | Historial cronológico; se filtra por `isActive` |
| `loadRecords` | `@OneToMany` | `LoadRecord` | `"userProfile"` | `List<LoadRecord>` | Historial de cargas ejecutadas ordenado por fecha |
| `oneRmRecords` | `@OneToMany` | `OneRmRecord` | `"userProfile"` | `List<OneRmRecord>` | Historial de máximas ordenado por fecha y ejercicio |
| `tests` | `@OneToMany` | `Test` | `"userProfile"` | `List<Test>` | Historial cronológico de pruebas |

---

### 2.3 `UciLicense`

Almacena la licencia UCI del deportista con sus fechas de vigencia. Entidad independiente para facilitar alertas de vencimiento.

**Tabla:** `uci_licenses`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `code` | `String` | `VARCHAR(50)` | `NOT NULL` |
| `expeditionDate` | `LocalDate` | `DATE` | `NOT NULL` |
| `expirationDate` | `LocalDate` | `DATE` | `NOT NULL` |
| `documentUrl` | `String` | `VARCHAR(500)` | nullable |

**Relaciones ManyToOne / OneToOne (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK | Restricción |
|---|---|---|---|---|
| `userProfile` | `@OneToOne` | `UserProfile` | `user_profile_id` | `UNIQUE, NOT NULL` |
| `uciCategory` | `@ManyToOne` | `UciCategory` | `uci_category_id` | `NOT NULL` |

> `UciCategory` referenciada aquí porque la categoría con la que se expide la licencia puede diferir de la categoría actual del perfil (el deportista puede subir de categoría sin renovar la licencia de inmediato).

---

### 2.4 `Insurance`

Póliza de seguro del deportista. Entidad independiente para el módulo de alertas de vencimiento.

**Tabla:** `insurances`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `code` | `String` | `VARCHAR(80)` | `NOT NULL` |
| `entity` | `String` | `VARCHAR(100)` | `NOT NULL` |
| `expeditionDate` | `LocalDate` | `DATE` | `NOT NULL` |
| `expirationDate` | `LocalDate` | `DATE` | `NOT NULL` |
| `documentUrl` | `String` | `VARCHAR(500)` | nullable |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `userProfile` | `@OneToOne` | `UserProfile` | `user_profile_id` — `UNIQUE` |

---

### 2.5 `PhysicalMeasurement`

Historial de mediciones físicas del deportista. Nunca se sobreescribe; cada pesaje genera una nueva fila. El campo `weight` y `height` de `UserProfile` son solo un caché del valor más reciente.

**Tabla:** `physical_measurements`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `weight` | `Double` | `DECIMAL(5,2)` | `NOT NULL` |
| `height` | `Double` | `DECIMAL(5,2)` | `NOT NULL` |
| `date` | `LocalDate` | `DATE` | `NOT NULL` |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `userProfile` | `@ManyToOne` | `UserProfile` | `user_profile_id` |
| `recordedBy` | `@ManyToOne` | `User` | `recorded_by` |

> **Cardinalidad:** `UserProfile (1) → PhysicalMeasurement (N)` · `User (1) → PhysicalMeasurement (N)`

---

### 2.6 `Injury`

Historial de lesiones del deportista. El entrenador lo consulta antes de asignar cargas para evitar recaídas.

**Tabla:** `injuries`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `type` | `String` | `VARCHAR(80)` | `NOT NULL` |
| `bodyArea` | `String` | `VARCHAR(60)` | `NOT NULL` |
| `startDate` | `LocalDate` | `DATE` | `NOT NULL` |
| `estimatedEndDate` | `LocalDate` | `DATE` | nullable |
| `actualEndDate` | `LocalDate` | `DATE` | nullable |
| `isActive` | `Boolean` | `BOOLEAN` | `NOT NULL, DEFAULT TRUE` |
| `notes` | `String` | `TEXT` | nullable |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `userProfile` | `@ManyToOne` | `UserProfile` | `user_profile_id` |
| `recordedBy` | `@ManyToOne` | `User` | `recorded_by` |

> **Cardinalidad:** `UserProfile (1) → Injury (N)` · `User (1) → Injury (N)`

---

### 2.7 `Bicycle`

Equipamiento del deportista. Un deportista puede tener varias motos a lo largo de su carrera.

**Tabla:** `bicycles`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `cranks` | `String` | `VARCHAR(80)` | nullable |
| `gear` | `String` | `VARCHAR(20)` | nullable |
| `rearGear` | `String` | `VARCHAR(20)` | nullable |
| `handlebarInches` | `Double` | `DECIMAL(4,2)` | nullable |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `userProfile` | `@ManyToOne` | `UserProfile` | `user_profile_id` |
| `frameBrand` | `@ManyToOne` | `FrameBrand` | `frame_brand_id` |
| `frameSize` | `@ManyToOne` | `FrameSize` | `frame_size_id` |
| `tireBrand` | `@ManyToOne` | `TireBrand` | `tire_brand_id` |
| `tireSize` | `@ManyToOne` | `TireSize` | `tire_size_id` |

> **Cardinalidad:** `UserProfile (1) → Bicycle (N)` — un deportista puede tener varias motos. Cada moto pertenece a un solo deportista.

---

## 3. Paquete `domain.club`

Contiene las entidades que gobiernan la estructura organizacional: clubes, membresías, roles, permisos, horarios y pagos.

---

### 3.1 `Club`

Representa una organización deportiva. El flag `isDepartmentTeam` identifica a la Preselección, que puede albergar deportistas de otros clubes simultáneamente.

**Tabla:** `clubs`  
**Extiende:** `Auditable`  
**Anotación:** `@SQLRestriction("deleted_at IS NULL")`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `name` | `String` | `VARCHAR(100)` | `NOT NULL, UNIQUE` |
| `logoUrl` | `String` | `VARCHAR(500)` | nullable |
| `monthlyPayment` | `Double` | `DECIMAL(10,2)` | `NOT NULL` |
| `city` | `String` | `VARCHAR(80)` | nullable |
| `email` | `String` | `VARCHAR(150)` | nullable |
| `address` | `String` | `VARCHAR(200)` | nullable |
| `phone` | `String` | `VARCHAR(20)` | nullable |
| `isDepartmentTeam` | `Boolean` | `BOOLEAN` | `NOT NULL, DEFAULT FALSE` |
| `deletedAt` | `LocalDateTime` | `TIMESTAMP` | nullable |

**Relaciones inversas (OneToMany):**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `enrollments` | `@OneToMany` | `Enrollment` | `"club"` | `List<Enrollment>` | Historial de inscripciones ordenado; se filtra por `isActive` |
| `clubSessions` | `@OneToMany` | `ClubSession` | `"club"` | `Set<ClubSession>` | Los horarios son únicos por día; `Set` previene duplicados y el orden no es crítico aquí |
| `routines` | `@OneToMany` | `Routine` | `"club"` | `List<Routine>` | Orden por versión y fecha de creación importa para el historial |
| `tests` | `@OneToMany` | `Test` | `"club"` | `List<Test>` | Historial cronológico de pruebas del club |

---

### 3.2 `Role`

Catálogo de roles posibles dentro de un club. El rol no pertenece al usuario globalmente sino al vínculo `Enrollment`.

**Tabla:** `roles`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `name` | `String` | `VARCHAR(50)` | `NOT NULL, UNIQUE` — `ATHLETE \| COACH \| PHYSICAL_TRAINER` |
| `description` | `String` | `VARCHAR(200)` | nullable |

**Relaciones inversas:**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `rolePermissions` | `@OneToMany` | `RolePermission` | `"role"` | `Set<RolePermission>` | Los permisos de un rol son únicos y no tienen orden relevante; `Set` es ideal |
| `enrollments` | `@OneToMany` | `Enrollment` | `"role"` | `List<Enrollment>` | Consultas de "quiénes tienen este rol" — orden cronológico útil |

---

### 3.3 `Permission`

Catálogo de permisos del sistema. Se define una vez y se asigna a roles.

**Tabla:** `permissions`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `name` | `String` | `VARCHAR(80)` | `NOT NULL, UNIQUE` |
| `description` | `String` | `VARCHAR(200)` | nullable |

**Relaciones inversas:**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `rolePermissions` | `@OneToMany` | `RolePermission` | `"permission"` | `Set<RolePermission>` | Sin duplicados, sin orden relevante |

---

### 3.4 `RolePermission`

Tabla de cruce entre `Role` y `Permission`. Usa clave embebida compuesta.

**Tabla:** `role_permissions`  
**Clave:** `@EmbeddedId RolePermissionId { roleId Long, permissionId Long }`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `RolePermissionId` | — | `@EmbeddedId` |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | `@MapsId` |
|---|---|---|---|
| `role` | `@ManyToOne` | `Role` | `"roleId"` |
| `permission` | `@ManyToOne` | `Permission` | `"permissionId"` |

> **Cardinalidad:** `Role (N) ↔ Permission (M)` — un rol tiene muchos permisos; un permiso puede pertenecer a muchos roles.

```java
@Embeddable
public class RolePermissionId implements Serializable {
    private Long roleId;
    private Long permissionId;
    // equals() y hashCode() obligatorios
}
```

---

### 3.5 `Enrollment`

**La entidad central del modelo.** Representa el vínculo entre un usuario, un club y un rol en ese club. Todo lo que depende del contexto club-usuario se ancla aquí: pagos, asignaciones de rutina, asistencia.

**Tabla:** `enrollments`  
**Extiende:** `Auditable`  
**Restricción única:** `@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "club_id", "role_id"}))`  
**Anotación:** `@SQLRestriction("deleted_at IS NULL")`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `startDate` | `LocalDate` | `DATE` | `NOT NULL` |
| `endDate` | `LocalDate` | `DATE` | nullable |
| `isActive` | `Boolean` | `BOOLEAN` | `NOT NULL, DEFAULT TRUE` |
| `deletedAt` | `LocalDateTime` | `TIMESTAMP` | nullable |

**Relaciones ManyToOne (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `user` | `@ManyToOne` | `User` | `user_id` |
| `club` | `@ManyToOne` | `Club` | `club_id` |
| `role` | `@ManyToOne` | `Role` | `role_id` |

**Relaciones inversas (OneToMany):**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `monthlyPayments` | `@OneToMany` | `MonthlyPayment` | `"enrollment"` | `List<MonthlyPayment>` | Historial cronológico de pagos; se ordena por `date`; necesario recorrer en orden para verificar mora |
| `routineAssignments` | `@OneToMany` | `RoutineAssignment` | `"enrollment"` | `List<RoutineAssignment>` | Historial de rutinas asignadas; orden cronológico por `startDate` |
| `sessionAttendances` | `@OneToMany` | `SessionAttendance` | `"enrollment"` | `List<SessionAttendance>` | Historial de asistencias; se ordena por fecha de sesión |

> **Cardinalidad:** `User (1) → Enrollment (N)` · `Club (1) → Enrollment (N)` · `Role (1) → Enrollment (N)`  
> Un usuario puede tener múltiples `Enrollment` (uno por cada combinación club+rol). La restricción `UNIQUE` previene duplicados.

---

### 3.6 `ClubSession`

Representa los horarios recurrentes de entrenamiento de un club (ej: lunes y miércoles de 3pm a 5pm). Es distinto de una sesión concreta en una fecha específica.

**Tabla:** `club_sessions`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `dayOfWeek` | `Integer` | `SMALLINT` | `NOT NULL` — 1=Lunes ... 7=Domingo |
| `from` | `LocalTime` | `TIME` | `NOT NULL` |
| `to` | `LocalTime` | `TIME` | `NOT NULL` |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `club` | `@ManyToOne` | `Club` | `club_id` |

**Relaciones inversas:**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `attendances` | `@OneToMany` | `SessionAttendance` | `"clubSession"` | `List<SessionAttendance>` | Se recorre para calcular porcentaje de asistencia; orden por enrollment útil |

> **Cardinalidad:** `Club (1) → ClubSession (N)`

---

### 3.7 `SessionAttendance`

Registra si un miembro asistió a una sesión recurrente del club. Usa clave embebida compuesta.

**Tabla:** `session_attendances`  
**Clave:** `@EmbeddedId SessionAttendanceId { clubSessionId Long, enrollmentId Long }`

| Atributo | Tipo Java | Tipo SQL | Descripción |
|---|---|---|---|
| `id` | `SessionAttendanceId` | — | `@EmbeddedId` |
| `attended` | `Boolean` | `BOOLEAN` | nullable — `null` = sin confirmar, `true` = asistió, `false` = ausente |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | `@MapsId` |
|---|---|---|---|
| `clubSession` | `@ManyToOne` | `ClubSession` | `"clubSessionId"` |
| `enrollment` | `@ManyToOne` | `Enrollment` | `"enrollmentId"` |

```java
@Embeddable
public class SessionAttendanceId implements Serializable {
    private Long clubSessionId;
    private Long enrollmentId;
    // equals() y hashCode() obligatorios
}
```

> **Cardinalidad:** `ClubSession (1) → SessionAttendance (N)` · `Enrollment (1) → SessionAttendance (N)`

---

### 3.8 `MonthlyPayment`

Registro de pago de mensualidad asociado a una membresía específica. Un deportista en dos clubes tiene dos flujos de pago independientes.

**Tabla:** `monthly_payments`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `value` | `Double` | `DECIMAL(10,2)` | `NOT NULL` |
| `date` | `LocalDate` | `DATE` | `NOT NULL` |
| `voucherUrl` | `String` | `VARCHAR(500)` | nullable |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `enrollment` | `@ManyToOne` | `Enrollment` | `enrollment_id` |
| `paymentMethod` | `@ManyToOne` | `PaymentMethod` | `payment_method_id` |
| `paymentStatus` | `@ManyToOne` | `PaymentStatus` | `payment_status_id` |

> **Cardinalidad:** `Enrollment (1) → MonthlyPayment (N)` · `PaymentMethod (1) → MonthlyPayment (N)` · `PaymentStatus (1) → MonthlyPayment (N)`

---

## 4. Paquete `domain.training`

Contiene todo lo relacionado con la planificación y ejecución del entrenamiento físico en gimnasio: ejercicios, rutinas, asignaciones y registros de progreso.

---

### 4.1 `Exercise`

Catálogo de ejercicios disponibles. Puede ser global (creado por el sistema) o creado por un entrenador específico.

**Tabla:** `exercises`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `name` | `String` | `VARCHAR(100)` | `NOT NULL` |
| `description` | `String` | `TEXT` | nullable |
| `isGlobal` | `Boolean` | `BOOLEAN` | `NOT NULL, DEFAULT FALSE` |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `exerciseType` | `@ManyToOne` | `ExerciseType` | `exercise_type_id` |
| `muscleGroup` | `@ManyToOne` | `MuscleGroup` | `muscle_group_id` |
| `createdBy` | `@ManyToOne` | `User` | `created_by` |

**Relaciones inversas:**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `routineExercises` | `@OneToMany` | `RoutineExercise` | `"exercise"` | `Set<RoutineExercise>` | Un ejercicio puede estar en muchas rutinas; sin orden relevante desde la perspectiva del ejercicio |
| `oneRmRecords` | `@OneToMany` | `OneRmRecord` | `"exercise"` | `List<OneRmRecord>` | Historial cronológico de 1RM por ejercicio |

> **Cardinalidad:** `ExerciseType (1) → Exercise (N)` · `MuscleGroup (1) → Exercise (N)` · `User (1) → Exercise (N)`

---

### 4.2 `Routine`

Plan de entrenamiento de gimnasio perteneciente a un club. Soporta versionado mediante la relación reflexiva `parentRoutine`.

**Tabla:** `routines`  
**Extiende:** `Auditable`  
**Anotación:** `@SQLRestriction("deleted_at IS NULL")`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `name` | `String` | `VARCHAR(100)` | `NOT NULL` |
| `description` | `String` | `TEXT` | nullable |
| `isActive` | `Boolean` | `BOOLEAN` | `NOT NULL, DEFAULT TRUE` |
| `version` | `Integer` | `SMALLINT` | `NOT NULL, DEFAULT 1` |
| `deletedAt` | `LocalDateTime` | `TIMESTAMP` | nullable |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK | Notas |
|---|---|---|---|---|
| `parentRoutine` | `@ManyToOne` | `Routine` | `parent_routine_id` | nullable — relación reflexiva para versionado |
| `club` | `@ManyToOne` | `Club` | `club_id` | `NOT NULL` |
| `createdBy` | `@ManyToOne` | `User` | `created_by` | `NOT NULL` |

**Relaciones inversas:**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `routineExercises` | `@OneToMany` | `RoutineExercise` | `"routine"` | `List<RoutineExercise>` | El orden de los ejercicios dentro de la rutina importa (`order`); `List` con `@OrderBy("order ASC")` |
| `assignments` | `@OneToMany` | `RoutineAssignment` | `"routine"` | `List<RoutineAssignment>` | Historial cronológico de asignaciones de esta rutina |
| `childVersions` | `@OneToMany` | `Routine` | `"parentRoutine"` | `List<Routine>` | Árbol de versiones derivadas; orden por versión |

> **Cardinalidad:** `Club (1) → Routine (N)` · `User (1) → Routine (N)` · `Routine (1) → Routine (N)` (reflexiva)

---

### 4.3 `RoutineExercise`

Detalle de un ejercicio dentro de una rutina: día, series, repeticiones, carga sugerida y orden de ejecución.

**Tabla:** `routine_exercises`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `dayOfWeek` | `Integer` | `SMALLINT` | `NOT NULL` — 1=Lunes ... 7=Domingo |
| `sets` | `Integer` | `SMALLINT` | `NOT NULL` |
| `repetitions` | `Integer` | `SMALLINT` | `NOT NULL` |
| `suggestedWeight` | `Double` | `DECIMAL(6,2)` | nullable |
| `order` | `Integer` | `SMALLINT` | `NOT NULL` |
| `notes` | `String` | `TEXT` | nullable |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `routine` | `@ManyToOne` | `Routine` | `routine_id` |
| `exercise` | `@ManyToOne` | `Exercise` | `exercise_id` |

**Relaciones inversas:**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `loadRecords` | `@OneToMany` | `LoadRecord` | `"routineExercise"` | `List<LoadRecord>` | Historial de ejecuciones reales de este ejercicio en esta rutina; orden cronológico |

> **Cardinalidad:** `Routine (1) → RoutineExercise (N)` · `Exercise (1) → RoutineExercise (N)`

---

### 4.4 `RoutineAssignment`

Asignación de una rutina a un miembro específico (vía `Enrollment`). Soporta asignaciones grupales con `isGroup`.

**Tabla:** `routine_assignments`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `isGroup` | `Boolean` | `BOOLEAN` | `NOT NULL, DEFAULT FALSE` |
| `startDate` | `LocalDate` | `DATE` | `NOT NULL` |
| `endDate` | `LocalDate` | `DATE` | nullable |
| `isActive` | `Boolean` | `BOOLEAN` | `NOT NULL, DEFAULT TRUE` |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `routine` | `@ManyToOne` | `Routine` | `routine_id` |
| `enrollment` | `@ManyToOne` | `Enrollment` | `enrollment_id` |
| `assignedBy` | `@ManyToOne` | `User` | `assigned_by` |

**Relaciones inversas:**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `loadRecords` | `@OneToMany` | `LoadRecord` | `"routineAssignment"` | `List<LoadRecord>` | Historial completo de cargas ejecutadas bajo esta asignación; orden por fecha y número de serie |

> **Cardinalidad:** `Routine (1) → RoutineAssignment (N)` · `Enrollment (1) → RoutineAssignment (N)` · `User (1) → RoutineAssignment (N)`

---

### 4.5 `LoadRecord`

Registro de una serie ejecutada por el deportista durante una sesión de gimnasio. Es la tabla de mayor volumen de crecimiento en el sistema.

**Tabla:** `load_records`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `date` | `LocalDate` | `DATE` | `NOT NULL` |
| `setNumber` | `Integer` | `SMALLINT` | `NOT NULL` |
| `repsExecuted` | `Integer` | `SMALLINT` | `NOT NULL` |
| `weightExecuted` | `Double` | `DECIMAL(6,2)` | `NOT NULL` |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `routineAssignment` | `@ManyToOne` | `RoutineAssignment` | `routine_assignment_id` |
| `routineExercise` | `@ManyToOne` | `RoutineExercise` | `routine_exercise_id` |
| `userProfile` | `@ManyToOne` | `UserProfile` | `user_profile_id` |

> **Cardinalidad:** `RoutineAssignment (1) → LoadRecord (N)` · `RoutineExercise (1) → LoadRecord (N)` · `UserProfile (1) → LoadRecord (N)`  
> No tiene relaciones inversas `@OneToMany` porque las consultas de progreso se realizan siempre con filtros (por ejercicio, por fecha, por deportista) y nunca se carga la colección completa.

---

### 4.6 `OneRmRecord`

Registro del máximo histórico de una repetición (1RM) por ejercicio y por fecha. Inmutable una vez creado.

**Tabla:** `one_rm_records`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `weight` | `Double` | `DECIMAL(6,2)` | `NOT NULL` |
| `date` | `LocalDate` | `DATE` | `NOT NULL` |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `userProfile` | `@ManyToOne` | `UserProfile` | `user_profile_id` |
| `exercise` | `@ManyToOne` | `Exercise` | `exercise_id` |
| `recordedBy` | `@ManyToOne` | `User` | `recorded_by` |

> **Cardinalidad:** `UserProfile (1) → OneRmRecord (N)` · `Exercise (1) → OneRmRecord (N)` · `User (1) → OneRmRecord (N)`

---

## 5. Paquete `domain.test`

Contiene las entidades para el registro de pruebas de rendimiento: Time-Trial, pruebas de salto, chequeos grupales, y cualquier test que se agregue en el futuro mediante el mecanismo de herencia de `Test`.

---

### 5.1 `Test`

Supertipo que representa cualquier tipo de prueba de rendimiento. Agrupa los datos comunes a todos los tests.

**Tabla:** `tests`  
**Extiende:** `Auditable`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `name` | `String` | `VARCHAR(100)` | `NOT NULL` |
| `description` | `String` | `TEXT` | nullable |
| `date` | `LocalDate` | `DATE` | `NOT NULL` |
| `notes` | `String` | `TEXT` | nullable |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `userProfile` | `@ManyToOne` | `UserProfile` | `user_profile_id` |
| `club` | `@ManyToOne` | `Club` | `club_id` |
| `testType` | `@ManyToOne` | `TestType` | `test_type_id` |
| `recordedBy` | `@ManyToOne` | `User` | `recorded_by` |

**Relaciones inversas:**

| Relación | Tipo | Entidad destino | `mappedBy` | Colección | Justificación |
|---|---|---|---|---|---|
| `timeTrial` | `@OneToOne` | `TimeTrial` | `"test"` | — | Uno a uno; un `Test` de tipo Time-Trial tiene exactamente un `TimeTrial` |

> **Cardinalidad:** `UserProfile (1) → Test (N)` · `Club (1) → Test (N)` · `TestType (1) → Test (N)` · `User (1) → Test (N)`

---

### 5.2 `TimeTrial`

Subtipo de `Test` que almacena los tiempos específicos de una prueba cronométrica en pista. Se vincula a su `Test` padre mediante una relación `@OneToOne`.

**Tabla:** `time_trials`

| Atributo | Tipo Java | Tipo SQL | Restricciones |
|---|---|---|---|
| `id` | `Long` | `BIGINT` | `@Id @GeneratedValue` |
| `totalTime` | `Double` | `DECIMAL(6,3)` | `NOT NULL` — en segundos con milisegundos |
| `gateTime` | `Double` | `DECIMAL(6,3)` | nullable — tiempo desde compuerta hasta 8m |
| `firstStraightTime` | `Double` | `DECIMAL(6,3)` | nullable — primer tramo recto |
| `sector2Time` | `Double` | `DECIMAL(6,3)` | nullable |
| `sector3Time` | `Double` | `DECIMAL(6,3)` | nullable |
| `trackCondition` | `String` | `VARCHAR(20)` | `DRY \| WET \| MUDDY` |

**Relaciones (FK en esta tabla):**

| Atributo | Tipo | Entidad destino | Columna FK |
|---|---|---|---|
| `test` | `@OneToOne` | `Test` | `test_id` — `UNIQUE` |

> **Cardinalidad:** `Test (1) ↔ TimeTrial (1)` — relación uno a uno; `test_id` con restricción `UNIQUE` lo garantiza en base de datos.

---

## 6. Catálogos por paquete

Son entidades de solo lectura después de la carga inicial. No extienden `Auditable` porque no se auditan. Estructura uniforme: `{ id Long @Id @GeneratedValue, name String @Column(unique=true) }`.

| Catálogo | Paquete | Tabla | Descripción |
|---|---|---|---|
| `UciCategory` | `domain.user` | `uci_categories` | JUNIOR, ELITE, MASTER_30, etc. |
| `FrameBrand` | `domain.user` | `frame_brands` | Marcas de cuadro de bicicleta |
| `FrameSize` | `domain.user` | `frame_sizes` | Tallas de cuadro |
| `TireBrand` | `domain.user` | `tire_brands` | Marcas de llanta |
| `TireSize` | `domain.user` | `tire_sizes` | Tallas de llanta (`size` en lugar de `name`) |
| `PaymentMethod` | `domain.club` | `payment_methods` | CASH, TRANSFER, etc. |
| `PaymentStatus` | `domain.club` | `payment_statuses` | ON_TIME, PENDING, OVERDUE |
| `ExerciseType` | `domain.training` | `exercise_types` | STRENGTH, PLYOMETRICS, REACTION, STRETCHING |
| `MuscleGroup` | `domain.training` | `muscle_groups` | LEGS, CORE, UPPER_BODY, etc. |
| `TestType` | `domain.test` | `test_types` | TIME_TRIAL, JUMP_TEST, GROUP_CHECK, etc. |

---

## 7. Resumen de colecciones JPA

Esta tabla consolida la decisión de colección para cada relación `@OneToMany` del modelo, con la justificación técnica.

| Entidad padre | Relación | Colección | Razón |
|---|---|---|---|
| `User` | `enrollments` | `List` | Orden cronológico por `startDate`; índice posicional útil |
| `User` | `exercisesCreated` | `Set` | Sin duplicados; O(1) en `contains`; sin orden relevante |
| `User` | `routinesCreated` | `List` | Orden por versión y fecha importa |
| `User` | `routineAssignmentsAssigned` | `List` | Historial cronológico |
| `User` | `physicalMeasurements` | `List` | Serie temporal ordenada por fecha |
| `User` | `injuriesRecorded` | `List` | Historial cronológico |
| `User` | `oneRmRecordsRegistered` | `List` | Historial cronológico |
| `User` | `testsRecorded` | `List` | Historial cronológico |
| `UserProfile` | `bicycles` | `List` | Orden por fecha de adquisición |
| `UserProfile` | `physicalMeasurements` | `List` | Serie temporal para graficar evolución |
| `UserProfile` | `injuries` | `List` | Historial; se filtra por `isActive` |
| `UserProfile` | `loadRecords` | `List` | Serie temporal de cargas para gráficas |
| `UserProfile` | `oneRmRecords` | `List` | Historial de máximas por fecha |
| `UserProfile` | `tests` | `List` | Historial cronológico de pruebas |
| `Club` | `enrollments` | `List` | Historial de miembros; se filtra por `isActive` |
| `Club` | `clubSessions` | `Set` | Horarios únicos por día; sin orden crítico |
| `Club` | `routines` | `List` | Orden por versión y fecha |
| `Club` | `tests` | `List` | Historial cronológico |
| `Role` | `rolePermissions` | `Set` | Permisos únicos sin orden relevante |
| `Role` | `enrollments` | `List` | Auditoría cronológica |
| `Permission` | `rolePermissions` | `Set` | Sin duplicados, sin orden |
| `Enrollment` | `monthlyPayments` | `List` | Orden cronológico para verificar mora |
| `Enrollment` | `routineAssignments` | `List` | Historial por `startDate` |
| `Enrollment` | `sessionAttendances` | `List` | Historial cronológico de asistencias |
| `ClubSession` | `attendances` | `List` | Recorrido para calcular % de asistencia |
| `Exercise` | `routineExercises` | `Set` | Sin orden relevante desde el ejercicio |
| `Exercise` | `oneRmRecords` | `List` | Historial cronológico de máximas |
| `Routine` | `routineExercises` | `List` | Orden de ejecución crítico (`order ASC`) |
| `Routine` | `assignments` | `List` | Historial cronológico |
| `Routine` | `childVersions` | `List` | Árbol de versiones en orden |
| `RoutineExercise` | `loadRecords` | `List` | Serie temporal de ejecuciones |
| `RoutineAssignment` | `loadRecords` | `List` | Historial completo de la asignación |

### Regla general aplicada

`List` cuando el orden temporal o de ejecución es relevante para la lógica de negocio (historial, gráficas, progresión).  
`Set` cuando los elementos son únicos por naturaleza y el acceso es por pertenencia, no por posición (permisos de un rol, horarios de un club).  
`LinkedList` no se usa en este modelo porque JPA no la soporta como tipo de colección persistida, y las operaciones de inserción en medio de lista no aplican al dominio.  
Ninguna colección usa `@Fetch(FetchType.EAGER)` — todas son `LAZY` por defecto para evitar el problema N+1. Las consultas con joins se manejan desde los repositorios con JPQL o `@EntityGraph`.

---

*Documento generado para el proyecto Holeshot — Suite de gestión para BMX Racing*  
*Revisión del modelo: pendiente de agregar módulos Competition, Objective, Achievement y Notification en la siguiente iteración.*
