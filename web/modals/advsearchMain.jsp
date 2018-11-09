<%@ page import="utils.Util" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
  <title>Title</title>

</head>
<body>
<div id="searchNew_dialog"></div>
<div  class="white-bg">
  <div  class="container_popup padding-10">
    <table style="width:100%" class="table_forums ">
      <tr>
        <td>
          <div class="center-title-4grid">
            <div class="titlePopup"><%=Util.getLocaleText(request, 178)%></div>
            <div class="buttons_container buttons_container_popup">
              <div class="button_block">
                <button  attr_newedit="1" data-name="new" name="btn_searchNew" class="icon_new" id="btn_searchNew"></button>
                <span class="button-text"><%=Util.getLocaleText(request, 109)%></span>
              </div>
              <div class="button_block">
                <button  class="icon_edit" id="btn_editSearchMain" name="btn_newNewAdmin"></button>
                <span class="button-text"><%=Util.getLocaleText(request, 110)%></span>
              </div>
              <div class="button_block">
                <button <%-- data-name="del"--%> id="search-del" name="search-del" class="icon_del"></button>
                <span class="button-text" ><%=Util.getLocaleText(request, 111)%></span>
              </div>


            </div>
          </div>
        </td>
      </tr>
      <tr>
        <td colspan="5">
          <div name="grid_area"  class="grid_area" style="height:300px;">
            <table  id="advsearchMain_grid"></table>
            <div id="advsearchMain_pager"></div>
          </div>
        </td>
      </tr>
    </table>
    <div id="dialog-search_new" title="Ətraflı axtarış " style="display: none;">
      <div class="main">
        <div class="cell">
          <div class="contentm">
            <form id="advForm1" name="advForm1" action="cs?action=makeADVParams" method="post" style="margin: 0 0 0 16px">
              <table class="table_forums">
                <tr height="40">
                  <td width="53">Parametr</td>
                  <td><select id="ADVsearchList" name="ADVsearchList" onchange="changePropertyPartADV();" ></select></td>
                </tr>
              </table>
              <div id="dialog_container">
                <div id="string_container2">
                  <table class="table_forums">
                    <tr height="40">
                      <td width="53px"> Dəyər </td>
                      <td><input type="text" id="paramVal" name="paramVal" class="required" ></td>
                    </tr>
                    <tr  height="40">
                      <td> Şərt </td>
                      <td><select id="condSTList" name="condSTList" >
                        <option value="1">Əvvəlində</option>
                        <option value="2">İstənilən yerdə</option>
                        <option value="3">Sonda</option>
                      </select></td>
                    </tr>
                  </table>
                </div>
                <div id="int_container2">
                  <table class="table_forums">
                    <tr height="40">
                      <td width="53"> Dəyər </td>
                      <td><input type="text" id="paramVal5" name="paramVal5" class="required"  onkeypress="return NumericValidation(event)"></td>
                    </tr>
                    <tr height="40">
                      <td> Şərt </td>
                      <td><select id="condINTList" name="condINTList" >
                        <option value="1">&gt;</option>
                        <option value="2">&lt;</option>
                        <option value="3">=</option>
                        <option value="4">>=</option>
                        <option value="5"><=</option>
                      </select></td>
                    </tr>
                  </table>
                </div>
                <div id="date_container2">
                  <table class="table_forums">
                    <tr height="40">
                      <td width="53"> Dəyər </td>
                      <td><input type="text" id="datepicker2" name ="datepicker2" <%--date="date" data-inputmask="'alias': 'dd-mm-yyyy'"--%> /></td>
                    </tr>
                    <tr height="40">
                      <td> Şərt </td>
                      <td><select id="condDTList" name="condDTList" >
                        <option value="1">&gt;</option>
                        <option value="2">&lt;</option>
                        <option value="3">=</option>
                        <option value="4">>=</option>
                        <option value="5"><=</option>
                      </select></td>
                    </tr>
                  </table>
                </div>
                <div id="list_container2">
                  <table class="table_forums">
                    <tr height="40">
                      <td width="53"> Dəyər </td>
                      <td>
                        <select id="paramList" name="paramList"></select>
                      </td>
                    </tr>
                  </table>
                </div>
                <div id="period_container2">
                  <table class="table_forums">
                    <tr height="40">
                      <td width="53"> Dəyər </td>
                      <td>
                        <table class="table_forums">
                          <tr>

                            <td width="35">
                              <input class="input1" id="inAdvYear" name="inAdvYear"
                                     value="0" onkeypress="return NumValidateA(event,1)"/>
                            </td>
                            <td width="5"  class="tdLabel"> il </td>
                            <td width="35">
                              <input class="input1" id="inAdvMonth" name="inAdvMonth"
                                     value="0" onkeypress="return NumValidateA(event,2)"/>
                            </td>
                            <td width="20" align="center" class="tdLabel"> ay </td>
                            <td width="35">
                              <input class="input1" id="inAdvDay"
                                     name="inAdvDay" value="0"
                                     onkeypress="return NumValidateA(event,3)"/>
                            </td>
                            <td width="20" align="center" class="tdLabel"> gün </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr height="40">
                      <td> Şərt </td>
                      <td><select id="condList" name="condList" >
                        <option value="1">&gt;</option>
                        <option value="2">&lt;</option>
                        <option value="3">=</option>
                        <option value="4">>=</option>
                        <option value="5"><=</option>
                      </select></td>
                    </tr>
                  </table>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div id="dialog-search_del" title="Parametri sil ">
    <div id="main">
      <div id="cell">
        <div id="content">
          <form id="advForm2" name="advForm2" action="cs?action=makeADVParams" method="post" style="margin: 0 0 0 48px">
            <p> <b> Parametri silmək istədiyinizə əminsiniz?</b></p>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="js/popup.js"></script>
<script type="text/javascript">

  $(document).ready(function() {
    $('#datepicker2').focusout(function(){
      $('#paramVal').val($('#datepicker2').val());
    });
    $('.buttoncontainer3 button').each( function( ) {
      var rating = $( this ).text();
      if ( rating == undefined || rating == '' ) {
        rating = 'I have not yet been rated.';
      }else {
        rating = rating;
      }
    });
  });
  var typEd=-1;

  var container_idsADV = {
    '1000':'string_container2',
    '1001':'int_container2',
    '1002':'date_container2',
    '1003':'list_container2',
    '1004':'period_container2',
    '1005':'string_container2'
  };
  function setPropertyFromDatePickerADV(){

    $('#datepicker2').datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat: 'dd-mm-yy',
      onSelect:function(){
        $('#paramVal').val($('#datepicker2').val());
      }
    });
    $('[date="date"]').inputmask();       // -------------islemelidi
  }
  function changePropertyPartADV() {
    var val = $('#ADVsearchList option:selected').attr('id');
    $('#dialog_container div').hide();
    $("#paramVal").val('');
    $("#paramVal5").val('');
    $("#datepicker2").val('');
    console.log("CONTAINER_IDSADV = "+container_idsADV[val]);
    $('#' + container_idsADV[val]).show();
    var url1="";
    var selID = $('#ADVsearchList :selected').val();
    console.log("val = "+val);
    if(val==1002){
      setPropertyFromDatePickerADV();
    }else if(val==1001){
      $("#subjValue").val('1');
    }else if(val==1003) {
      console.log("SELID = "+selID);
      if(selID==50){
        url1='cs?action=loadSubj&id=10003';
      }else{
        url1='cs?action=fillComboByParamID&paramID='+selID+'&langID='+localeLanguages['192'];
      }
      $.ajax({
        type:'GET',
        dataType : 'json',
        url:url1,
        success : function(data){
          $("#paramList").html('');
          $("#paramList").append('<option value= "-1" selected>--- Seçin ---</option>');
          $.each(data.dsList, function(i, ds) {
            $("#paramList").append('<option value="'+ds.id+'">'+ds.full_name+'</option>');
          });
          $("#subjValue").val(selID);
          if(typEd==2) {
            var grid = jQuery('#advsearchMain_grid');
            var sel_id = grid.jqGrid('getGridParam', 'selrow');
            var parVal = grid.jqGrid('getCell', sel_id, 'paramVal');
            setComboSelectedIndex2("paramList",parVal);
          }
        },
        error : function(data){},
        complete : function (jqXHR, textStatus){}
      });
    }
  }

  function deleteRecordByFileNameADV (myArr, fileName/*,maddeC*/) {
    var index = null;
    for (var i =0; i < myArr.length; i++) {
      if (myArr[i].paramID == fileName /*&& myArr[i].maddeC==maddeC*/) {
        index = i;
        break;
      }
    }
    if (index !== null) {
      myArr.splice(index, 1);
    }
  }


  function advGridAllData() {
    seachAllPar = 1;
    console.log("advSearch1.size =" +advSearch1.length)  ;
   // advGridData();
    gridShow();

  }
  function advGridData() {
    $('#advsearchMain_grid').jqGrid('GridUnload');
    $('#advsearchMain_grid').jqGrid({
      datatype:"local",
      colNames: [ 'PARAMETR', 'DƏYƏR', 'ŞƏRT','paramID','opType','curOP','paramCount'],
      colModel: [
        {name: 'paramName', index: 'paramName', sorttype: 'text' },
        {name: 'paramVal', index: 'paramVal', sorttype: 'text' },
        {name: 'paramCond', index: 'paramCond', sorttype: 'text' },
        {name: 'paramID', index: 'paramID', sorttype: 'text',hidden:true},//,hidden:true,hidden:true ,hidden:true
        {name: 'opType', index: 'opType', sorttype: 'text',hidden:true},
        {name: 'curOP', index: 'curOP', sorttype: 'text',hidden:true},
        {name: 'paramCount', index: 'paramCount', sorttype: 'int',hidden:true}
      ],
      rowNum:20,
      rowList:[10, 20, 30, 40, 50, 100],
      height:284,
      width:675,
      sortorder:"asc",
      viewrecords:true,
      gridview:true,
      rownumbers: true ,
      datatype: "local",
      scrollOffset: 0,
      loadonce:true,
      ondblClickRow:function(id) {
        $("#search-edit").click();
      }
    });
    if (advSearch1.length>0) {
      for (var i = 0; i < advSearch1.length; i++) {
        jQuery('#advsearchMain_grid').jqGrid('addRowData', i + 1, advSearch1[i]);
      }
    }
  }

  function NumValidateA(eventObj,vv){
    var keycode;
    if(eventObj.keyCode) //For IE
      keycode = eventObj.keyCode;
    else if(eventObj.Which)
      keycode = eventObj.Which;  // For FireFox
    else keycode = eventObj.charCode; // Other Browser
    if (keycode!=8){ //if the key is the backspace key
      if(keycode == 46 || (keycode >= 48 && keycode <= 57))//if (keycode<48||keycode>57) //if not a number
        return true; // disable key press
      else
        return false; // enable key press
    }
  }

  function close_dialogAdv(combo, dialog) {
    var count = 0;
    $('#' + combo + ' option').each(function() {
      if ($(this).css('display') !== 'none') {
        count++;
      }
    });
    if (count === 0 ){
      if(typEd==1){
        $('#' + dialog).dialog('close');
        alert('Parametrlərin hamısı daxil olunub!');
      }
//    else if(typEd==2){
//        $('#' + dialog).dialog('close');
//        Thread.sleep(200);
//
//    }

    }
  }

  $(function() {
  //  advGridData();
    gridShow();
    setPropertyFromDatePickerADV();

    $( "#dialog-search_del" ).dialog({
      resizable: false,
      autoOpen: false,
      height:250,
      width: 400,
      modal: true,
      buttons: {
        "İmtina et": function() {
          $( this ).dialog( "close" );
        } ,
        "Tətbiq et": function() {
          seachAllPar = 0;
          var val = 0;
          var form = $("form", "#dialog-search_new");var url = "";
          var paramCount=0;
          if(typEd==3){
         //   var val = $('#ADVsearchList option:selected').attr('id').split('_')[1];
            var grid = jQuery('#advsearchMain_grid');
            var sel_id = grid.jqGrid('getGridParam', 'selrow');
            var selected_val = grid.jqGrid('getCell', sel_id, 'paramID');
            // var maddeC = grid.jqGrid('getCell', sel_id, 'maddeC');
            var paramCount = grid.jqGrid('getCell', sel_id, 'paramCount');
            deleteRecordByFileNameADV(advSearch1,selected_val);
          //  advGridData();
            gridShow();
            url = form.attr("action")+"&typ=2"+"&val="+val+"&valID="+selected_val+"&typEd="+typEd+/*'&maddeC='+maddeC+*/'&paramCount='+paramCount;
            $.post(url, form.serialize(), function(data) {success : {console.log("SetParams in Session advSearch")}});
          }else if(typEd==4){
            advSearch1=[]; //advGridData();
            gridShow();
            url = form.attr("action")+"&typ=2"+"&val="+val+"&valID="+selected_val+"&typEd="+typEd+/*'&maddeC='+maddeC+*/'&paramCount='+paramCount;
            $.post(url, form.serialize(), function(data) {success : {
              console.log("SetParams in Session advSearch")}});
          }
          $( this ).dialog( "close" );
        }
      }
    });
    $( "#dialog-search_new" ).dialog({
      resizable: false,
      autoOpen: false,
      height:250,
      width: 400,
      modal: true,
      buttons:[
        {
          text: "İmtina et",
          class:"searchDialogButton",
          click: function() {
            $( this ).dialog( "close" );
          }
        },
        {
          text: "Tətbiq et",
          class:"searchDialogButton",
          click: function() {
            var val = $('#ADVsearchList option:selected').attr('id');
            console.log("val = "+val);
            var url = '';
            var paramVal1 = $("#paramVal").val();
            var parCond = $('#condSTList option:selected').text();
            var parCondV = $('#condSTList option:selected').val();
            if(val==1002){
              parCond = $('#condDTList option:selected').text();
              parCondV = $('#condDTList option:selected').val();
            }else if(val==1003){
              parCond = "=";
              parCondV = $('#paramList option:selected').val();
              paramVal1 = $('#paramList option:selected').text();
            }
            //  var maddeC=0;
            var paramCount = 0;
            $.each (advSearch1,function(i,cc){

              paramCount = paramCount +1;
            });
            if (typEd==1) {
              if(paramCount!=0) { paramCount=paramCount+1;}
              console.log(" I hall =>paramVal1 =/"+paramVal1 +"/; parCondV = "+parCondV)   ;
              if(paramVal1 =='' || parCondV =='-1') {
                alert("Dəyər sahəsini boş buraxmaq olmaz")  ;
                return false;
              }else {
                advSearch1.push({paramName:$('#ADVsearchList option:selected').text(),paramVal:paramVal1,
                  paramCond : parCond,paramID:$('#ADVsearchList option:selected').val(),
                  opType : val,curOP:parCondV,paramCount:paramCount});
                // advGridData();
                gridShow();
                var form = $("form", "#dialog-search_new");
                url = form.attr("action")+"&typ=1"+"&val="+val+'&paramCount='+paramCount;
                $.post(url, form.serialize(), function(data) {success : {console.log("SetParams in Session advSearch")}});
                var ddl = $('#ADVsearchList :selected');
                var sval = ddl.val();
                var arrAdv=[/*10,11,12,13,14,15,16,17,18,19,20,21,22*/];
                console.log("(sval) ="+sval);
                console.log("arrAdv.indexOf(sval) ="+arrAdv.indexOf( parseInt(sval)));
                if(arrAdv.indexOf( parseInt(sval) ) == -1 /*sval !=10 || sval !=11 || sval !=12 || sval !=13 || sval !=14
                 || sval !=19 || sval !=20 || sval !=24 || sval !=53*/  ){
                  //ddl.removeAttr('selected');
                  console.log("sval= "+sval);
                  $('#ADVsearchList option[value=' + sval + ']').hide();
                  for (var i = 0; i < $('#ADVsearchList option').size(); i++) {
                    var nth_child = $('#ADVsearchList :nth-child(' + (i+1) +     ')');
                    if (nth_child.css('display') !== 'none') {
                      nth_child.attr('selected', true);
                      break;
                    }
                  }
                  close_dialogAdv('ADVsearchList','dialog-search_new');
                }
                changePropertyPartADV();
              }
            }else if (typEd==2){
              var grid = jQuery('#advsearchMain_grid');
              var sel_id = grid.jqGrid('getGridParam', 'selrow');
              var selected_val = grid.jqGrid('getCell', sel_id, 'paramID');
              //   maddeC = grid.jqGrid('getCell', sel_id, 'maddeC');
              paramCount = grid.jqGrid('getCell', sel_id, 'paramCount');
              console.log("selected_val = "+selected_val);
              $.each (advSearch1,function(i,cc){
                if(cc.paramID==selected_val && cc.paramCount==paramCount){
                  cc.paramVal=paramVal1;
                  cc.paramCond = parCond;
                  cc.opType = val;
                  cc.curOP=parCondV;
                }
              });
              // advGridData();
              gridShow();
              var form = $("form", "#dialog-search_new");
              var url = form.attr("action")+"&typ=2"+"&val="+val+"&valID="+selected_val+"&typEd="+typEd+/*'&maddeC='+maddeC+*/'&paramCount='+paramCount;
              $.post(url, form.serialize(), function(data) {success : {
                url = form.attr("action")+"&typ=1"+"&val="+val+'&paramCount='+paramCount;
                $.post(url, form.serialize(), function(data) {success : {console.log("SetParams in Session advSearch")}});
              }});
              $( this ).dialog( "close" );
            }
          }
        }
      ]
    });


    $("#btn_searchNew").click(function() { //alert(advSearch1);
      typEd=1;
      var listL = [];var listL1 = [];var chkIn=0;
      $.ajax({
        type:'GET',
        dataType : 'json',
        url:'cs?action=loadSearchList&partID='+partID,
        success:function (data) {
          $("#ADVsearchList").html('');
          $('#ADVsearchList').removeAttr('disabled');
          $.each(data.dsList, function(i, ds) {
            listL.push(ds);
          });
          listL1=advSearch1;
          chkIn=0;
          for (var k = 0; k < listL.length; k++) {
            for (var j=0;j<listL1.length;j++){
              if(listL[k].id==listL1[j].paramID){
                chkIn=1;
              }
            }
            var arrAdv=[/*10,11,12,13,14,15,16,17,18,19,20,21,22*/];
            if (chkIn==0){
              $("#ADVsearchList").append('<option value="'+listL[k].id+'" id="'+listL[k].element_type+'">'+listL[k].full_name+'</option>');
            }else{
              if(arrAdv.indexOf(listL[k].id) !=-1){
                $("#ADVsearchList").append('<option value="'+listL[k].id+'" id="'+listL[k].element_type+'">'+listL[k].full_name+'</option>');
              }
              chkIn=0;
            }
          }
          changePropertyPartADV();
        }
      });
      $("#dialog-search_new").dialog("open");
    });


    $("#btn_editSearchMain").click(function() {
      typEd=2;
      console.log("typEd = "+typEd);
      var grid = jQuery('#advsearchMain_grid');
      var sel_id = grid.jqGrid('getGridParam', 'selrow');
      var selected_val = grid.jqGrid('getCell', sel_id, 'paramID');
      $("#ADVsearchList").html('');
      if (selected_val != false) {
        var parName = grid.jqGrid('getCell', sel_id, 'paramName');
        var parVal = grid.jqGrid('getCell', sel_id, 'paramVal');
        var curOP = grid.jqGrid('getCell', sel_id, 'curOP');
        var opType1 = grid.jqGrid('getCell', sel_id, 'opType');
        console.log("parName = "+parName+" || parVal = "+parVal+" || curOP = "+curOP+" || opType1 = "+opType1);
        $("#ADVsearchList").append('<option value='+selected_val+' id="'+opType1+'">'+parName+'</option>');
        changePropertyPartADV();
        if (opType1==1000 || opType1==1005){
          setComboSelectedIndex1("condSTList",curOP);
          $("#paramVal").val(parVal);
        }else if (opType1==1001){
          setComboSelectedIndex1("condINTList",curOP);
          $("#paramVal5").val(parVal);
        }else if(opType1==1002){
          $("#datepicker2").val(parVal);
          setComboSelectedIndex1("condDTList",curOP);
        }else if(opType1==1003){
          setComboSelectedIndex2("paramList",parVal);
        }else if(opType1==1004){
          var tt = parVal;var pos=tt.indexOf(" il ");
          var mm=tt.substring(0,pos);
          $('#inAdvYear').val(mm);
          tt=tt.substring(pos+4,tt.length);pos=tt.indexOf(" ay ");mm=tt.substring(0,pos);
          $('#inAdvMonth').val(mm);tt=tt.substring(pos+4,tt.length-5);
          $('#inAdvDay').val(tt);
        }
        $( "#dialog-search_new" ).dialog( "open" );
      }else{
        alert('Redaktə üçün heç bir parametr seçilməyib. Zəhmət olmasa seçiminizi edin.');
        return false;
      }
    });

    $("#search-del").click(function() {
      var grid = jQuery('#advsearchMain_grid');typEd=3;
      var sel_id = grid.jqGrid('getGridParam', 'selrow');
      var selected_val = grid.jqGrid('getCell', sel_id, 'paramID');
      console.log("selectedVal = "+selected_val);
      if (selected_val !=false){
        $("#dialog-search_del").dialog("open");
      }else{
        alert('Silmək üçün heç bir parametr seçilməyib. Zəhmət olmasa seçiminizi edin.');
        return false;
      }
    });

    $( "#search-delAll" ).button().click(function() {
      typEd=4; $( "#dialog-search_del" ).dialog( "open" );

    });
  });

</script>
</body>
</html>
