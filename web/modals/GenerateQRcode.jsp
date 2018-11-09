<%--
  Created by IntelliJ IDEA.
  User: Samire
  Date: 12/26/2016
  Time: 1:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>QRCODE</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
</head>
<body>
<%--<form id="qrCodeForm" name="qrCodeForm"  method="post" enctype="multipart/form-data">--%>
    <input id="text" type="text" style="width:80%" /><br />
    <div id="qrcode"  style="width:100px; height:100px; margin-top:15px;"></div>
    <div id="append"></div>
<%--</form>--%>
<form id="qrCodeForm" name="qrCodeForm"  method="post" action="cs1?action=setSession&typ=1" enctype="multipart/form-data">
    <div id="qrCodeMoved">

    </div>
</form>
<script type="text/javascript">
  var qrcode = new QRCode(document.getElementById("qrcode"), {
    width : 100,
    height : 100
  });

  function makeCode () {
    var elText = document.getElementById("text");

    if (!elText.value) {
      elText.focus();
      return;
    }

    qrcode.makeCode(elText.value);
  }

  makeCode();

  $("#text").
          on("blur", function () {
            makeCode();
          }).on("keydown", function (e) {
            if (e.keyCode == 13) {
              makeCode();
                transaction();
            }
          });


  $("input").change(function() {
      var src;
    function getSrc() {
         src  = $("img").attr("src");
   //   $("#append").append("<a href='"+src+"'  target='_blank' download='image.png'>hello </a>");
    }
    setTimeout(getSrc,50);


  });
  function transaction (){
      var div = $('#qrcode');
      var img = div.attr('src');
      console.log(img);//Equivalent: $(document.createElement('img'))
      div.attr('src',img);
      div.appendTo('#qrCodeMoved');
      $("#qrcode img").attr('name','qrCodePicture');
  }
    $(document).ready(function(){
            $('#qrCodeMain img').remove();

    })

</script>
</body>
</html>
