<%@ page import="utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Title</title>

</head>
<body>
<div  class="white-bg">

  <div  class="container_popup padding-10">
    <form id="frmDoc" name="frmDoc" method="post"  enctype="multipart/form-data">
    <table style="width:100%" class="table_forums">

      <tr id="trDocumentType">
        <td><label>Tipi</label>
        </td><td  colspan="3">
        <select id="documentTypeID" name="documentTypeID"></select>
      </td>
      </tr>
      <tr id="" height="35">
        <td id="fileName" width="70"></td>
        <td><input type="text" id="documentNameAZ" name="documentNameAZ" placeholder="AZ"></td>
        <td><input type="text" id="documentNameRu" name="documentNameRu" placeholder="RU"></td>
        <td><input type="text" id="documentNameEn" name="documentNameEn" placeholder="EN"></td>
      </tr>

      <tr id="tr_Author">
        <td id="author"></td>
        <td><input  type="text" id="authorAz" name="authorAz" placeholder="AZ"></td>
        <td><input  type="text" id="authorRu" name="authorRu" placeholder="RU"></td>
        <td><input  type="text" id="authorEn" name="authorEn" placeholder="EN"></td>
      </tr>
      <tr id="trDate1">
        <td id="date1"></td>
        <td  colspan="3"><input  type="text" id="docdate" name="docdate"></td>
      </tr>
      <tr id="trLink"><td><label>Link</label></td><td colspan="3"><a id="docLink"   target="_blank"><input id="link" name="link" type="text"></a></td></tr>
      <tr id="number">
        <td><label>Nömrə</label>
        </td><td  colspan="3">
        <input id="numb" name="numb" type="text">
      </td>
      </tr>
      <tr  height="35">
        <td id="file1"></td>
        <td colspan="3">
           <div class=" fileform">
              <div id="fileformlabel"></div>
              <div class="selectbutton" ><%=Util.getLocaleText(request, 201)%></div>
              <input type="file" onchange="getName(this.value);" id="upload" name="upload">
             </div>
        </td>
      </tr>

    </table>
    </form>
  </div>
</div>

<script>
  $(document).ready(function(){
   /* $('#docdate').datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat: 'dd-mm-yy'
    });*/


  });
  function getName (str) {
    if (str.lastIndexOf('\\')){
      var i = str.lastIndexOf('\\')+1;
    }
    else{
      var i = str.lastIndexOf('/')+1;
    }
    var filename = str.slice(i);
    var uploaded = document.getElementById("fileformlabel");
    uploaded.innerHTML = filename;
  }
</script>
</body>
</html>
