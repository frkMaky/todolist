<div class="container">
    <form method="POST" action="${pageContext.request.contextPath}/ServletControlador?accion=insertar">
        <input type="text" name="tituloNuevo" class="form-control" placeholder="T�tulo nueva tarea"/> 
        <textarea id="decripcion" name="descripcionNuevo" rows="5" cols="10" class="form-control" placeholder="Descripci�n nueva tarea"></textarea>
        <button type="submit" class="btn btn-primary"> <i class="fa-solid fa-plus"></i> </button>
    </form>
</div>