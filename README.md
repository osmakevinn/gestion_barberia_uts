# 💈 Gestión de Barbería

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![UI Framework](https://img.shields.io/badge/UI-Swing%20%2B%20FlatLaf-blue?logo=java&logoColor=white)](https://github.com/JFormDesigner/FlatLaf)
[![Database](https://img.shields.io/badge/Database-SQLite-003B57?logo=sqlite&logoColor=white)](https://www.sqlite.org/)

Un moderno sistema de escritorio (Desktop App) desarrollado en **Java SE** con una interfaz gráfica estilizada para la administración integral de turnos, barberos, clientes y servicios de una barbería comercial.

---

## 👥 Datos del Proyecto Académico

| Información | Detalle |
| :--- | :--- |
| **Institución** | Unidades Tecnológicas de Santander (UTS) |
| **Asignatura** | Tecnología de Desarrollo de Sistemas Informáticos |
| **Periodo** | I Semestre 2026 |
| **Docente** | Mag. Carlos Adolfo Beltrán Castro |
| **Desarrolladores** | • **Kevin Stewart Osma Bohada** (10053721360)<br>• **Jean Franco Rey Ardila** (1097782023) |

---

## 🚀 Características Principales

* **Interfaz de Usuario Premium:** Look & Feel oscuro y responsivo gracias a la integración nativa de **FlatLaf** con bordes suavizados y placeholders dinámicos.
* **Dashboard Estadístico:** Panel de inicio interactivo con conteo automático en tiempo real de clientes, barberos activos, servicios ofrecidos y citas del día.
* **Próximas Citas Integradas:** Tabla automatizada en el inicio que filtra y centra cronológicamente las citas agendadas para el día actual.
* **Módulos CRUD Completos:** Gestión integral de Barberos y Clientes (Registrar, Listar con buscador dinámico *on-key-released*, Actualizar y Eliminar).
* **Seguridad:** Control de inicio de sesión único (Login corporativo) y cierre de sesión seguro liberando procesos en segundo plano.

---

## 🛠️ Arquitectura y Tecnologías

El proyecto implementa una arquitectura desacoplada y limpia para separar las responsabilidades visuales del acceso a datos:

* **`vista` (UI):** Formularios y paneles construidos sobre `javax.swing` y personalizados estéticamente mediante propiedades de componentes FlatLaf.
* **`Metodos` (Capa de Lógica):** Controladores intermedios encargados de la validación de formularios, limpieza de interfaces y renderizado/centrado de datos en tablas.
* **`control` (DAO):** Patrón de Objeto de Acceso a Datos (*Data Access Object*) aislado para centralizar las transacciones SQL mediante sentencias preparadas (*PreparedStatements*).
* **`modelo` (POJO):** Clases entidad que mapean de forma limpia los registros de la base de datos (Barbero, Cliente, Cita, Servicio).

### Tecnologías Utilizadas:
* **Lenguaje:** Java SE 17
* **Base de Datos:** SQLite con arquitectura embebida (`.db`)
* **Librerías Visuales:** FlatLaf Core & FlatLaf Extras
* **Persistencia:** JDBC (Java Database Connectivity)

---

## 📂 Estructura del Repositorio

```text
gestion_barberia_uts/
├── gestion_barberia/
│   ├── src/
│   │   ├── control/      # Clases de acceso a datos (BarberosDAO, etc.)
│   │   ├── Metodos/      # Lógica de negocio y controladores de UI
│   │   ├── modelo/       # Entidades o POJOs orientados a objetos
│   │   └── vista/        # JPanels y JFrames de la interfaz gráfica
│   ├── lib/              # Librerías y conectores (.jar) requeridos
│   └── nbproject/        # Configuraciones de entorno de NetBeans
├── .gitignore            # Reglas de exclusión de archivos para Git
└── README.md             # Documentación del proyecto
