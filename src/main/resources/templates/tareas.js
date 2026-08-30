const API_URL = "http://localhost:8080";
const token = localStorage.getItem('jwtToken');

document.addEventListener("DOMContentLoaded", () => {
    const rol = obtenerRol();

    // 1. Cargar tareas iniciales
    cargarTareas();

    // 2. Listener Formulario Tareas
    const formTarea = document.getElementById('formTarea');
    if (formTarea) {
        formTarea.addEventListener('submit', crearTarea);
    }

    // 3. Listener y Carga de Desarrolladores
    const seccionDevs = document.getElementById('seccionGestionDevs');
    if (rol === 'ADMINISTRATOR' || rol === 'ROLE_ADMINISTRATOR' || rol === 'PROJECT_MANAGER' || rol === 'ROLE_PROJECT_MANAGER') {
        cargarDesarrolladores();

        const formCrearDev = document.getElementById('formCrearDev');
        if (formCrearDev) {
            formCrearDev.addEventListener('submit', registrarDesarrollador);
        }
    } else if (seccionDevs) {
        seccionDevs.style.display = 'none';
    }
});

function obtenerRol() {
    if (!token) return null;
    try {
        const payloadBase64 = token.split('.')[1];
        const payloadDecodificado = JSON.parse(atob(payloadBase64));
        return payloadDecodificado.rol || payloadDecodificado.authorities?.[0];
    } catch (e) {
        return null;
    }
}

// --- CREAR TAREA ---
async function crearTarea(e) {
    e.preventDefault();

    const nuevaTarea = {
        tituloTarea: document.getElementById('tituloTarea').value,
        descripcionTarea: document.getElementById('descripcionTarea').value,
        estadoActTar: document.getElementById('estadoActTar').value,
        asignadoA: parseInt(document.getElementById('asignadoASelect').value)
    };

    try {
        const res = await fetch(`${API_URL}/api/tareas`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(nuevaTarea)
        });

        if (res.ok) {
            document.getElementById('formTarea').reset();
            cargarTareas();
        } else {
            console.error("Error al crear tarea:", await res.text());
        }
    } catch (err) {
        console.error("Error en la petición POST de tarea:", err);
    }
}

// --- GESTIÓN DE TAREAS ---
async function cargarTareas() {
    try {
        const res = await fetch(`${API_URL}/api/tareas`, {
            headers: { 
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (res.ok) {
            const tareas = await res.json();
            renderizarTarjetas(tareas);
        }
    } catch (err) {
        console.error("Error al obtener tareas:", err);
    }
}

function renderizarTarjetas(tareas) {
    const contenedor = document.getElementById('listaTareasContainer');
    const contador = document.getElementById('contadorTareas');
    if (!contenedor) return;
    contenedor.innerHTML = '';

    const rol = obtenerRol();

    const seccionCrear = document.getElementById('formTarea');
    if (seccionCrear && (rol === 'DEVELOPER' || rol === 'CLIENT' || rol === 'ROLE_DEVELOPER' || rol === 'ROLE_CLIENT')) {
        seccionCrear.closest('section').style.display = 'none';
    }

    const tareasActivas = tareas.filter(t => t.estadoActTar !== 'FINALIZADO' && t.estadoActTar !== 'CANCELADO');

    if (contador) {
        contador.innerText = `No. Tareas: ${tareasActivas.length}`;
    }

    tareasActivas.forEach(t => {
        const card = document.createElement('div');
        card.className = 'tarea-card';
        
        let botonesAccion = '';

        if (rol === 'CLIENT' || rol === 'ROLE_CLIENT') {
            botonesAccion = `
                <button class="btn-status-change" onclick="actualizarTarea(${t.idTarea}, { estadoActTar: 'SOLUCION_PLANIFICADA' })">
                    Solicitar Cierre
                </button>`;
        } else if (rol === 'DEVELOPER' || rol === 'ROLE_DEVELOPER') {
            botonesAccion = `
                <button class="btn-status-change" onclick="actualizarTarea(${t.idTarea}, { estadoActTar: 'FINALIZADO' })">
                    Cerrar Ticket
                </button>`;
        } else {
            botonesAccion = `
                <button class="btn-status-change" onclick="actualizarTarea(${t.idTarea}, { estadoActTar: 'FINALIZADO' })">Cerrar</button>
                <button class="btn-reabrir" onclick="actualizarTarea(${t.idTarea}, { estadoActTar: 'EN_PROCESO' })">Reabrir</button>`;
        }

        card.innerHTML = `
            <div class="tarea-info-main">
                <h3>${t.tituloTarea} <small>(#${t.idTarea})</small></h3>
                <small>Estado: <b>${t.estadoActTar}</b></small>
            </div>
            <div class="tarea-info-accion">
                <p><small>Asignado:</small> <b>${t.asignadoAUser || t.asignadoA || 'N/A'}</b></p>
                ${botonesAccion}
            </div>
        `;
        contenedor.appendChild(card);
    });
}

async function actualizarTarea(idTarea, nuevoEstadoObj) {
    try {
        // 1. Obtener la tarea actual desde el backend para mantener título, descripción, creador y asignado
        const resGet = await fetch(`${API_URL}/api/tareas/${idTarea}`, {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (!resGet.ok) {
            console.error("No se pudo obtener la información actual de la tarea.");
            return;
        }

        const tareaActual = await resGet.json();

        // 2. Armar el payload completo que exige la validación del Backend
        const payloadCompleto = {
            tituloTarea: tareaActual.tituloTarea,
            descripcionTarea: tareaActual.descripcionTarea,
            estadoActTar: nuevoEstadoObj.estadoActTar,
            // Extrae el ID según cómo retorne el JSON de tu backend (entidad completa o ID plano)
            creadoPor: tareaActual.creadoPor?.idUsuario || tareaActual.creadoPorId || tareaActual.creadoPor,
            asignadoA: tareaActual.asignadoA?.idUsuario || tareaActual.asignadoAId || tareaActual.asignadoA
        };

        // 3. Enviar el PUT con la estructura completa validada
        const res = await fetch(`${API_URL}/api/tareas/${idTarea}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(payloadCompleto)
        });

        if (res.ok) {
            cargarTareas();
        } else {
            console.error("Error al actualizar la tarea:", await res.text());
        }
    } catch (err) {
        console.error("Error en la petición PUT:", err);
    }
}

function renderizarTablaDevs(devs) {
    const tbody = document.getElementById('tablaDevsBody');
    if (!tbody) return;
    tbody.innerHTML = '';

    devs.forEach(dev => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${dev.idUsuario || dev.id}</td>
            <td>${dev.nombre}</td>
            <td>${dev.email}</td>
            <td>
                <button class="btn-danger" onclick="eliminarDesarrollador(${dev.idUsuario || dev.id})">Eliminar</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

function poblarSelectDevs(devs) {
    const select = document.getElementById('asignadoASelect');
    if (!select) return;
    select.innerHTML = '';

    devs.forEach(dev => {
        const id = dev.idUsuario || dev.id;
        const option = document.createElement('option');
        option.value = id;
        option.textContent = `${dev.nombre} (ID: ${id})`;
        select.appendChild(option);
    });

    if (devs.length > 0) {
        document.getElementById('asignadoAId').innerText = devs[0].idUsuario || devs[0].id;
    }
}

async function registrarDesarrollador(e) {
    e.preventDefault();

    const nuevoDev = {
        nombre: document.getElementById('devNombre').value,
        email: document.getElementById('devEmail').value,
        rol: 'DEVELOPER'
    };

    try {
        const res = await fetch(`${API_URL}/api/usuarios`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(nuevoDev)
        });

        if (res.ok) {
            document.getElementById('formCrearDev').reset();
            cargarDesarrolladores();
        } else {
            console.error("Error al registrar desarrollador:", await res.text());
        }
    } catch (err) {
        console.error("Error en la petición POST de desarrollador:", err);
    }
}

async function eliminarDesarrollador(idDev) {
    if (!confirm("¿Está seguro de eliminar este desarrollador?")) return;

    try {
        const res = await fetch(`${API_URL}/api/usuarios/${idDev}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (res.ok) {
            cargarDesarrolladores();
        } else {
            console.error("Error al eliminar desarrollador:", await res.text());
        }
    } catch (err) {
        console.error("Error en la petición DELETE de desarrollador:", err);
    }
}
