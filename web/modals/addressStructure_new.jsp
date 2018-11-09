
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
  <title>Title</title>

</head>
<body>
<div id="addToDic_dialog"></div>
<div class="white-bg">

  <div  class="container_popup padding-10" >
    <form >
      <table class="table_forums">
        <tr height="40">
          <td>Ünvanın tipi</td>
          <td  width="220px">
            <select id="aTypeList" name="aTypeList" class="required"
                    onkeydown="return getPress3(event);"></select>
          </td>
          <td>

          </td>
        </tr>
        <tr height="40">
          <td align="left">Ölkə</td>
          <td>
            <select ></select>
          </td>
          <td>
            <button aria-disabled="false" role="button" id="btn_addToDic1" name="btn_addToDic2" data-title-popup="Information"
                    class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                    style="margin:0;">Əlavə et
            </button>
          </td>
        </tr>
        <tr height="40">
          <td >Bölgə</td>
          <td >
            <select  ></select>
          </td>
          <td>
            <button aria-disabled="false" role="button" id="btn_addToDic2" name="btn_addToDic3" data-title-popup="Information"
                    class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                    style="margin:0; ">Əlavə et
            </button>
          </td>
        </tr>
        <tr height="40">
          <td >Şəhər/Rayon</td>
          <td>
            <select></select>
          </td>
          <td>
            <button aria-disabled="false" role="button" id="btn_addToDic3" name="btn_addToDic4" data-title-popup="Information"
                    class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                    style="margin:0;">Əlavə et
            </button>
          </td>
        </tr>
        <tr height="40" id="placeOfResidence_tr">
          <td >Yaşayış yeri</td>
          <td >
            <select ></select>
          </td>
          <td>
            <button aria-disabled="false" role="button" id="btn_addToDic4" name="btn_addToDic5" data-title-popup="Information"
                    class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                    style="margin:0;">Əlavə et
            </button>
          </td>
        </tr>
        <tr height="40" id="street_tr">
          <td align="left">Küçə</td>
          <td align="left">
            <select></select>
          </td>
          <td>
            <button aria-disabled="false" role="button" id="btn_addToDic5" name="btn_addToDic6" data-title-popup="Information"
                    class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                    style="margin:0;">Əlavə et
            </button>
          </td>
        </tr>
        <tr height="40" id="address_tr">
          <td align="left">Ünvan</td>
          <td align="left">
            <input type="text">
          </td>
          <td></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="js/secondDialog.js"></script>
</body>
</html>
