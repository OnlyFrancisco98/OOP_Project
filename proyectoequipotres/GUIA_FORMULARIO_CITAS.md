# 🚀 Guía Rápida - Formulario de Programación de Citas

## ¿Qué se creó?

Un formulario simple y elegante que:
- ✅ Solo pide el correo del usuario
- ✅ Envía un correo de confirmación
- ✅ Mensaje diciendo "Tu cita ha sido programada"

## 📁 Archivos Creados

```
1. cita.html (Interfaz web)
   └─ Ubicación: src/main/resources/static/cita.html
   └─ Función: Formulario hermoso y responsivo

2. CitaProgramacionController.java (Backend)
   └─ Ubicación: src/main/java/com/uady/psicologia/citas/controller/
   └─ Función: Recibe el correo y envía la confirmación
```

## 🎯 ¿Cómo Usar?

### Paso 1: Obtener Contraseña de Gmail ⚠️ IMPORTANTE

Si aún no lo has hecho:

1. Ve a: https://myaccount.google.com/apppasswords
2. Selecciona: "Correo" ↓
3. Selecciona: "Windows" ↓  
4. Haz clic: "Generar"
5. Copia la contraseña de 16 caracteres

### Paso 2: Actualizar Configuración

Edita: `src/main/resources/application.properties`

Busca esta línea:
```
spring.mail.password=your_app_password_here
```

Reemplaza con tu contraseña:
```
spring.mail.password=xxxx xxxx xxxx xxxx
```

### Paso 3: Ejecutar la Aplicación

```powershell
cd c:\Users\mercu\OneDrive\Documentos\GitHub\OOP_Project\proyectoequipotres
.\mvnw.cmd spring-boot:run
```

### Paso 4: Abrir en el Navegador

Una vez que veas:
```
Started CitasApplication
```

Abre en tu navegador:
```
http://localhost:8080/cita.html
```

## 📝 ¿Qué Hace?

1. **Usuario ingresa su correo** en el formulario
2. **Usuario hace clic** en "Confirmar Cita"
3. **El servidor recibe** el correo en `/api/citas/programar`
4. **Se envía un email** con el mensaje: "Tu cita ha sido programada"
5. **Se muestra un mensaje** de confirmación en pantalla

## 💻 Ejemplo del Correo Recibido

```
Asunto: ✓ Cita Programada

Cuerpo:
¡Hola!

Tu cita ha sido programada exitosamente.

Detalles:
- Fecha: 2025-11-13
- Correo de confirmación: usuario@example.com

Si tienes dudas, contacta con nosotros.

Facultad de Psicología UADY
```

## 🎨 Características del Formulario

✅ **Diseño Moderno** - Interfaz limpia y profesional  
✅ **Responsive** - Se adapta a celular, tablet y escritorio  
✅ **Validación** - Verifica que el correo sea válido  
✅ **Indicadores de Carga** - Muestra spinner mientras se envía  
✅ **Mensajes de Éxito/Error** - Feedback claro al usuario  
✅ **Accesibilidad** - ARIA labels para lectores de pantalla  

## 🔧 Personalizar el Mensaje

Si quieres cambiar el mensaje, edita:

**Archivo:** `CitaProgramacionController.java`

**Busca esta sección:**
```java
emailServicio.sendSimpleEmail(
    email,
    "✓ Cita Programada",
    "¡Hola!\n\n" +
    "Tu cita ha sido programada exitosamente.\n\n" +
    // ... resto del mensaje
);
```

**Modifica el texto** entre las comillas para personalizar.

## 📱 URL del Formulario

Una vez ejecutando, puedes acceder desde:

- **Local:** http://localhost:8080/cita.html
- **Por API directa:** 
  ```
  POST http://localhost:8080/api/citas/programar
  Content-Type: application/json
  
  {
    "email": "usuario@example.com"
  }
  ```

## ❌ Solución de Problemas

### "El correo no se envía"
→ Verifica que hayas actualizado `application.properties` con tu contraseña de Google

### "Puerto 8080 ya está en uso"
→ Ejecuta: `$netstat -ano | findstr :8080` para ver qué proceso lo usa

### "Correo inválido"
→ El formulario rechaza correos mal formados. Ejemplo válido: `usuario@example.com`

### "Error de CORS"
→ El controlador ya tiene `@CrossOrigin(origins = "*")` habilitado

## 📊 Resumen Técnico

| Componente | Detalle |
|-----------|---------|
| **Interfaz** | HTML5 + CSS3 + JavaScript Vanilla |
| **Validación** | input type="email" (lado cliente) |
| **Backend** | Spring Boot REST Controller |
| **Email** | Spring Mail + Gmail SMTP |
| **Puerto** | 8080 (por defecto) |
| **Estado Compilación** | ✅ BUILD SUCCESS |

## ✅ Checklist Final

- [ ] Obtuve la contraseña de aplicación de Gmail
- [ ] Actualicé `application.properties`
- [ ] Ejecuté `.\mvnw.cmd spring-boot:run`
- [ ] Abrí http://localhost:8080/cita.html
- [ ] Ingresé un correo de prueba
- [ ] Recibí el correo de confirmación
- [ ] ¡Perfecto! Sistema funcionando ✓

---

**¿Necesitas personalizar algo más?** Avísame y lo arreglamos. 😊
