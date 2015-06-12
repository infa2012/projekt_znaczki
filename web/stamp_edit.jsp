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
            <c:if test="${param['id']}">
                <input type="hidden" name="id" value="${param["id"]}"/>
            </c:if>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Nazwa</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Nazwa" value="${(!param["name"].equals("")) ? stamp["name"] : param["name"]}">                                       
                </div>
            </div>
            
            <div class="form-group">
                <label for="print_year" class="col-sm-2 control-label">Rok Wydania</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="print_year" name="print_year" placeholder="Rok Wydania" value="${(!param["print_year"].equals("")) ? stamp["print_year"] : param["print_year"]}">                                       
                </div>
            </div>
                
            <div class="form-group">
                <label for="notes" class="col-sm-2 control-label">Opis</label>
                <div class="col-sm-5">
                    <textarea type="text" class="form-control col-sm-2" id="notes" name="notes" placeholder="Opis" >${(!param["notes"]) ? stamp["notes"] : param["notes"]}</textarea>                                       
                </div>
            </div>
            
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Obrazek</label>

                <div class="col-sm-5">
                    <div class="input-group">
                        <span class="input-group-btn">
                            <button id="file-button" class="btn btn-default" type="button" onclick="$('input[id=file-control]').click();">
                                <span class="glyphicon glyphicon-picture"></span>
                                Wybierz
                            </button>
                        </span>
                        <input type="text" id="file-view" class="form-control" placeholder="..." disabled="true">
                        <input type="file" id="file-control" name="image" style="display: none">
                    </div>
                    <div class="img-cont" style="margin-top: 25px">
                        <img src="img?id=${stamp["id"]}"/>
                    </div>
                </div>
            </div>  


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
                    <button type="submit" class="btn btn-primary">Dodaj Znaczek</button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:template>