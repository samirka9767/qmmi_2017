
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
  <title>Title</title>
  <script type="text/javascript">
    function setImage(val){
      var selectedFile = val.target.files[0];
      var reader = new FileReader();

      var imgtag = document.getElementById("lpPhoto1");
//      imgtag.src = "img/test.jpg";
      imgtag.title = selectedFile.name;
      console.log("selectedFile" + selectedFile.name);

      reader.onload = function(event) {

        imgtag.src = event.target.result;
      };
      reader.readAsDataURL(selectedFile);
    }
  </script>

</head>
<body>
<div class="white-bg">

  <div  class="container_popup padding-10" >
    <form id="frmPhoto" name="frmPhoto" method="post" action="cs1?action=setSession&typ=1" enctype="multipart/form-data" target="hiddenIfFrame1">
    <table class="table_forums" style="width: 80%; margin: 0px auto;">
      <tr>
        <td align="left"  height="40" style="text-align: center;height: 320px;">
          <img height="300"  width="auto" src="img/test.jpg" id="lpPhoto1" name="lpPhoto1" >
        </td>

      </tr>
      <tr height="40" >
        <td  height="40" align="left">
          <div class=" fileform">
          <div id="fileformlabel"></div>
          <div class="selectbutton">Yüklə</div>
          <input id="docFilePH" name="docFilePH" type="file" onchange="setImage(event);" id="upload" name="upload">
        </div>
        </td>
        <td align="left">
          <iframe name ="hiddenIfFrame1" id ="hiddenIfFrame1" style="display: none"></iframe>
        </td>
      </tr>
    </table>
</form>
  </div>
</div>
</body>
</html>
