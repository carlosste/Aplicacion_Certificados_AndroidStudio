<<<<<<< HEAD
# Taller: Solicitud de Certificados – UCC

## Integrantes
- Ever Wisney Rodriguez Guzman
- Carlos Steven Lozano Quintero

## Descripción
Aplicación móvil desarrollada en Java para la gestión y solicitud de certificados académicos de la Universidad Cooperativa de Colombia. La app permite a los estudiantes registrar sus datos, seleccionar su programa y elegir diversos certificados con validaciones en tiempo real.

## Claves de Extras (Intents)
Se utilizaron las siguientes constantes para el intercambio de datos entre Activities:
- `EXTRA_ID`: Código estudiantil (String).
- `EXTRA_NOMBRE`: Nombre completo del estudiante (String).
- `EXTRA_PROGRAMA`: Programa académico seleccionado (String).
- `EXTRA_MODALIDAD`: Modalidad de estudio (String).
- `EXTRA_CERTIFICADOS`: Lista de certificados elegidos (ArrayList<String>).
- `EXTRA_URGENTE`: Estado del trámite (boolean).

## Componentes de Interfaz Usados
- **MainActivity**: `TextInputLayout`, `TextInputEditText`, `Spinner`, `RadioGroup`, `RadioButton`, `CheckBox` (5 tipos), `SwitchCompat`, `Button`.
- **SummaryActivity**: `TextView`, `ImageView`, `CardView`, `Button`.
- **SubmitActivity**: `TextView`, `ImageView`, `Button`.

## Validaciones Implementadas
1. **Campos Obligatorios**: El nombre y el código no pueden estar vacíos.
2. **Selección de Programa**: Se valida que el usuario no deje la opción por defecto del Spinner.
3. **Modalidad**: Es obligatorio seleccionar una opción (Presencial/Virtual).
4. **Certificados**: Se debe marcar al menos un CheckBox antes de continuar.

## Extensiones y Bonos
- **Cálculo de Costos**: La aplicación calcula un costo base por cada certificado seleccionado y añade un recargo si el trámite se marca como urgente.
- **Intent Implícito**: En la pantalla final, se incluye un botón para compartir el resumen de la solicitud a través de aplicaciones externas (WhatsApp, Correo, etc.) usando `ACTION_SEND`.
- **Diseño Institucional**: Se aplicaron los colores oficiales de la UCC (Verde, Azul y Naranja) y estilos de Material Design.
=======
# Aplicacion_Certificados_AndroidStudio
>>>>>>> 71b31dc75d33708d0d4467639762fe36d2b21165
