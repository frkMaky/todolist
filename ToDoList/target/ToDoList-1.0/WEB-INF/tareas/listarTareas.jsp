<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Cabecero -->
<jsp:include page="../paginas/cabecero.jsp" />

<div class="container">
    <ul>
        <c:forEach var="tarea" items="${ tareas }">
            <li class="text-left mt-4">
                <div class="col-12">
                    <form>
                        <div class="row g-3 align-items-center">
                            <div class="col-auto">
                                <span style="font-weight: bold"> ${tarea.idTarea} </span> 
                                <label for="titulo" class="col-form-label">Titulo: <span style="font-weight: bold"> ${tarea.titulo} </span></label> 
                            </div>
                        </div>
                        <div class="row g-3 align-items-center">
                            <div class="col-auto">
                                <label for="estado" class="col-form-label">Estado:</label> <span style="font-weight: bold"> ${tarea.estadoTexto() } </span> 
                                <label for="inicio" class="col-form-label">Inicio:</label> <span style="font-weight: bold"> ${tarea.fechaInicio} </span> 
                                <label for="fin" class="col-form-label">Fin:</label> <span style="font-weight: bold"> ${tarea.fechaFin} </span> 
                            </div>
                        </div>
                                <label for="descripcion" class="col-form-label">Descripcion: <span style="font-weight: bold">${tarea.descripcion}</span></label>

                    </form>
                    <!-- Botones -->
                    <div class="col-12 text-center">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=completar&idTarea=${tarea.idTarea}"> <i class="fa-solid fa-check"></i> </a> <!-- Completar -->
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=cancelar&idTarea=${tarea.idTarea}"> <i class="fa-solid fa-xmark"></i> </a> <!-- Cancelar -->
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idTarea=${tarea.idTarea}" class="m-3"> <i class="fa-solid fa-trash-can"></i> </a> <!-- Eliminar -->
                    </div>
            </li>
        </c:forEach>
    </ul>
</div>

<!-- Form Nueva Tarea-->
<jsp:include page="../tareas/nuevaTarea.jsp" />

<!-- Footer -->
<jsp:include page="../paginas/footer.jsp" />