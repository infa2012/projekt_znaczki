<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<t:template>
    <jsp:attribute name="page_header">
        ${page_title}
    </jsp:attribute>

    <jsp:body>
        <form class="form-horizontal" id="validation_form" method="post">
            <c:if test="${col['id']}">
                <input type="hidden" name="id" value="${col["id"]}"/>
            </c:if>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Nazwa</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Nazwa" value="${(!param["name"].equals("")) ? col["name"] : param["name"]}">                                       
                </div>
            </div>
            
            <c:if test="${col['id']}">
            <div class="form-group">
                <label for="delete" class="col-sm-2 control-label">Usuń kolekcję</label>
                <div class="col-sm-5">
                    <input type="checkbox" class="form-control" id="delete" name="remove" value="false">                                       
                </div>
            </div>
            </c:if>
                
            <script type="text/javascript">
                $('#file-view').val($('#file-control').val());
                
                $('#file-control').change(function() {
                    $('#file-view').val($(this).val());
                });
                
                $(document).ready(function(){
                        $(".img-cont img").each( function(i){
                            if (this.naturalHeight>this.naturalWidth){
                                $(this).addClass("stretch-height");
                            }else{
                                $(this).addClass("stretch-width");
                            }
                        });
                   });
            </script>            

            <br />
                
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5">
                    <button type="submit" class="btn btn-primary">${col["id"]?"Edytuj Kolekcję":"Dodaj Kolekcję"}</button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:template>
