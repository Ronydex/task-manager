const API_URL = "http://localhost:8080";
const token = localStorage.getItem('jwtToken');

document.addEventListener("DOMContentLoaded", () => {
    cargarTareas();
    
    // Asignación de evento al formulario de creación
    const formTarea = document.getElementById('formTarea');
    if (formTarea) {
        formTarea.addEventListener('submit', crearTarea);
    }
});

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
        } else {
            console.error("Error al obtener tareas. Status:", res.status);
        }
    } catch (err) {
        console.error("Error de red al obtener tareas:", err);
    }
}

function renderizarTarjetas(tareas) {
    const contenedor = document.getElementById('listaTareasContainer');
    const badgeContador = document.getElementById('contadorTareas');

    if (!contenedor) return;

    contenedor.innerHTML = '';

    // Filtrar tareas activas (no completadas)
    const tareasActivas = tareas.filter(t => t.estadoActTar !== 'COMPLETADA' && t.estadoActTar !== 'RESUELTO');

    if (badgeContador) {
        badgeContador.innerText = `No. Tareas: ${tareasActivas.length}`;
    }

    if (tareasActivas.length === 0) {
        contenedor.innerHTML = '<p style="color: white; text-align: center; padding: 20px;">No hay tareas pendientes.</p>';
        return;
    }

    tareasActivas.forEach(t => {
        const card = document.createElement('div');
        card.className = 'tarea-card';
        card.innerHTML = `
            <div class="tarea-info-main">
                <h3>${t.tituloTarea} <small>(#${t.idTarea})</small></h3>
            </div>
            <div class="tarea-info-fecha">
                <p><b>Límite resolución:</b></p>
                <p>${t.fechaSolucion ? t.fechaSolucion : 'Sin asignar'}</p>
            </div>
            <div class="tarea-info-accion">
                <p style="margin-bottom: 5px;"><small>Asignado a:</small> <b>${t.asignadoAUser}</b></p>
                <button class="btn-status-change" onclick="marcarResuelta(${t.idTarea})">
                    Resolver Tarea
                </button>
            </div>
        `;
        contenedor.appendChild(card);
    });
}

async function crearTarea(e) {
    e.preventDefault();

    // ID fijado del usuario creador (del elemento HTML)
    const creadoPorIdVal = document.getElementById('creadoPorId').innerText.trim();
    
    // ID del usuario asignado (si tienes select o input, lo leemos de forma segura)
    const selectAsignado = document.getElementById('asignadoASelect');
    const idAsignadoVal = selectAsignado && selectAsignado.value ? selectAsignado.value : "1";

    // OJO: Los nombres de las propiedades deben coincidir con tu TareaRegistroDTO en Spring Boot
    const payload = {
        tituloTarea: document.getElementById('tituloTarea').value,
        descripcionTarea: document.getElementById('descripcionTarea').value,
        estadoActTar: document.getElementById('estadoActTar').value,
        creadoPorUser: parseInt(creadoPorIdVal),
        asignadoAUser: parseInt(idAsignadoVal)
    };

    try {
        const res = await fetch(`${API_URL}/api/tareas`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(payload)
        });

        if (res.ok) {
            document.getElementById('formTarea').reset();
            cargarTareas(); // Refresca las tarjetas en vivo
        } else {
            console.error("Error al crear tarea. Status:", res.status);
        }
    } catch (err) {
        console.error("Error al crear tarea:", err);
    }
}

async function marcarResuelta(idTarea) {
    try {
        const res = await fetch(`${API_URL}/api/tareas/${idTarea}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({ estadoActTar: 'COMPLETADA' })
        });

        if (res.ok) {
            cargarTareas();
        }
    } catch (err) {
        console.error("Error al actualizar estado:", err);
    }
}
