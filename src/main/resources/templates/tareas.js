const API_URL = "http://localhost:8080";
const token = localStorage.getItem('jwtToken');

document.addEventListener("DOMContentLoaded", () => {
    cargarTareas();
    
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

    // Filtrar tareas que no estén ni FINALIZADO ni CANCELADO
    const tareasActivas = tareas.filter(t => t.estadoActTar !== 'FINALIZADO' && t.estadoActTar !== 'CANCELADO');

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
                <small style="color: #1a2b3c;">Estado: <b>${t.estadoActTar}</b></small>
            </div>
            <div class="tarea-info-fecha">
                <p><b>Límite resolución:</b></p>
                <p>${t.fechaSolucion ? t.fechaSolucion : 'Sin asignar'}</p>
            </div>
            <div class="tarea-info-accion">
                <p style="margin-bottom: 5px;"><small>Asignado a:</small> <b>${t.asignadoAUser || t.asignadoA || 'N/A'}</b></p>
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

    const creadoPorIdVal = document.getElementById('creadoPorId').innerText.trim();
    const selectAsignado = document.getElementById('asignadoASelect');
    const idAsignadoVal = selectAsignado ? selectAsignado.value : "1";

    const payload = {
        tituloTarea: document.getElementById('tituloTarea').value,
        descripcionTarea: document.getElementById('descripcionTarea').value,
        estadoActTar: document.getElementById('estadoActTar').value, // Envía el Enum correcto
        creadoPor: parseInt(creadoPorIdVal),
        asignadoA: parseInt(idAsignadoVal)
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
            cargarTareas();
        } else {
            const errorText = await res.text();
            console.error(`Error ${res.status} al crear tarea:`, errorText);
        }
    } catch (err) {
        console.error("Error en la petición al crear tarea:", err);
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
            body: JSON.stringify({ estadoActTar: 'FINALIZADO' }) // Envía el Enum FINALIZADO
        });

        if (res.ok) {
            cargarTareas();
        } else {
            console.error(`Error al actualizar estado. Status: ${res.status}`);
        }
    } catch (err) {
        console.error("Error al actualizar estado:", err);
    }
}
