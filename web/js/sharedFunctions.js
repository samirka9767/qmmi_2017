var contactMessage;
jQuery.fn.getComboContent = function (comboType, optionPrependText, dataUrl, callback, selectType) {
    var combo = $(this);
    var d = {};
    combo.empty("combo =" + combo);
    $.ajax({
        dataType: 'json',
        type: 'POST',
        url: 'cs?action=comboContent&comboType=' + comboType + '&sType=' + selectType,
        data: dataUrl,
        success: function (data) {
            d = data.options;
            if (combo.length > 0) {
                if (optionPrependText) {
                    combo.prepend('<option value="-1"> --' + optionPrependText + ' --</option>');
                }

                $.each(data.options, function (i, d) {
                    var e = $(document.createElement('option'));

                    e.text(d.name);
                    e.val(d.id);
                    $.each(d, function (j, k) {
                        if (j != 'name' && j != 'id') {
                            e.attr(j, k);
                        }
                    });
                    combo.append(e);
                });
            }
        },
        complete: function () {
            if (callback) {
                callback(d);
            }
        }
    });
    //combo.html('');

    return combo.find('option').length;
};

function deleteFromArray(rid) {
    if (mpPartID1 == 10000) {
        deleteFromArrayByRealID(pinfo, rid);
    } else if (mpPartID1 == 3002) {
        deleteFromArrayByRealID(paddress, rid);
    } else if (mpPartID1 == 3012) {
        deleteFromArrayByRealID(pcontact, rid);
    } else if (mpPartID1 == 3005) {
        deleteFromArrayByRealID(peducation, rid);
    } else if (mpPartID1 == 3006) {
        deleteFromArrayByRealID(pparents, rid);
    } else if (mpPartID1 == 50001) {
        deleteFromArrayByRealID(pothdoc, rid);
    } else if (mpPartID1 == 50002) {
        deleteFromArrayByRealID(media, rid);
    } else if (mpPartID1 == 50003) {
        deleteFromArrayByRealID(library, rid);
    } else if (mpPartID1 == 50004) {
        deleteFromArrayByRealID(documents, rid);
    }else if (mpPartID1 == 50006) {
        deleteFromArrayByRealID(referenceFile, rid);
    } else if (mpPartID1 == 50005) {
        deleteFromArrayByRealID(ExampleOperation, rid);
    } else if (mpPartID1 == 3008) {
        deleteFromArrayByRealID(pworkInfo, rid);
    } else if (mpPartID1 == 3010) {
        deleteFromArrayByRealID(pFinalInfo, rid);
    } else if (mpPartID1 == 4001 || mpPartID1 == 4101 || mpPartID1 == 4201) {
        deleteFromArrayByRealID(studGroup, rid);
    } else if (mpPartID1 == 5001) {
        deleteFromArrayByRealID(subjSection, rid);
    } else if (mpPartID1 == 7001) {
        deleteFromArrayByRealID(studPersPlanSubjectInfo, rid);
    }
}
function deleteFromArrayByRealID(myArr, sid) {
    var index = null;
    for (var i = 0; i < myArr.length; i++) {
        if (myArr[i].realID == sid) {
            index = i;
            break;
        }
    }
    if (index !== null) {
        myArr.splice(index, 1);
    }
}
function deleteFromArrayByID(myArr, sid) {
    var index = null;
    for (var i = 0; i < myArr.length; i++) {
        if (myArr[i].id == sid) {
            index = i;
            break;
        }
    }
    if (index !== null) {
        myArr.splice(index, 1);
    }
}
function setComboSelectedIndex1(comboName, value) {
    var comboBox = document.getElementById(comboName);
    for (var index1 = 0; index1 < comboBox.options.length; index1++) {
        if (comboBox[index1].value == value) {
            comboBox[index1].selected = true;
            break;
        }
    }
}
function setComboSelectedIndex2(comboName, text) {

    var comboBox = document.getElementById(comboName);

    for (index1 = 0; index1 < comboBox.options.length; index1++) {


        if (comboBox[index1].text == text) {
            comboBox[index1].selected = true;
            break;
        }
    }
}
function addChild(nn, pid) {
    var tt = '';
    var tt1 = '';
    var ch = 0;
    var kk = '';
    var ll = [];
    var s = '';
    var p = 0;
    var ss = '';
    var k = 0;
    var tip = 0;
    var catTip = 0;
    while (nn.length > 0) {
        ll = [];
        kk = nn[k].formula;
       // console.log(nn[k].formula);
        catTip = nn[k].tip;

        if(nn[k].id != 1091){
            if(catTip == 1017){//nov
                tip = 15;
            }else if(catTip == 1012){ //kat
                tip = 17;
            }else if(catTip == 1013){ // sinif
                tip = 16;
            }else if(catTip == 1015){//janr
                tip = 14;
            }else if(catTip == 1014){//qrup
                tip = 13;
            }else {
                tip = 13;
            }
        }else {
            tip=18;
        }

        tt = tt + ' <li  id="' + nn[k].id + '"  ><a href="#" style=" font-size: '+tip+'px" id="' + nn[k].id + '" onclick="viewInfo(' + nn[k].id + ');">' + nn[k].fname + ' </a>';
        tt1 = tt1 + nn[k].fname;
        //tt = tt + ' <li  id="' + nn[k].id + '"  ><a href="#" id="' + nn[k].id + '" onclick="viewInfo(' + nn[k].id + ');">' + nn[k].fname + ' </a>';
        for (var j = 0; j < lists.length; j++) {
            s = lists[j].formula;
           // console.log("     " +  "s    ->  " +s);
            ss = kk + '*';
           // console.log(" SS  -> "+ss);
            p = s.indexOf(ss);
          //  console.log("P  ->  "+p);
           // alert("Stop1");
            if (p != -1) {
                ll.push(lists[j]);
                deleteFromArrayByID(nn, lists[j].id);
            }
        }

        if (ll.length > 0) {
            tt = tt + '<ul>' + addChild(ll, nn[k].id) + '</ul></li>';
        } else {
            tt = tt + '</li>';
        }
        deleteFromArrayByID(nn, nn[k].id);
    }

    return tt;
}
function addChild1(nn, pid) {
    var tt = '';
    var ch = 0;
    var kk = '';
    var ll = [];
    var s = '';
    var p = 0;
    var ss = '';
    var k = 0;
    var tip = 0;
    var catTip = 0;
    var space = 0;
    while (nn.length > 0) {
        ll = [];
        kk = nn[k].formula;
        // console.log(nn[k].formula);
        catTip = nn[k].tip;

        if(nn[k].id != 1091){
            if(catTip == 1017){
                space = "      ";
                tip = 14;
            }else if(catTip == 1012){
                space = "  ";
                tip = 17;
            }else if(catTip == 1013){
                space ="   ";
                tip = 16;
            }else if(catTip == 1015){
                space = "        ";
                tip = 13;
            }else if(catTip == 1014){
                space = "    ";
                tip = 15;
            }else {
                tip = 14;
            }
        }else {
            tip=18;
        }

        tt = tt + "---"+nn[k].tip+"***"+nn[k].fname+"@@"+nn[k].tip;
        //tt = tt + ' <li  id="' + nn[k].id + '"  ><a href="#" id="' + nn[k].id + '" onclick="viewInfo(' + nn[k].id + ');">' + nn[k].fname + ' </a>';
        for (var j = 0; j < lists.length; j++) {
            s = lists[j].formula;
            // console.log("     " +  "s    ->  " +s);
            ss = kk + '*';
            // console.log(" SS  -> "+ss);
            p = s.indexOf(ss);
            //  console.log("P  ->  "+p);
            // alert("Stop1");
            if (p != -1) {
                ll.push(lists[j]);
                deleteFromArrayByID(nn, lists[j].id);
            }
        }

        if (ll.length > 0) {
            tt = tt + addChild1(ll, nn[k].id);
        } else {
            tt = tt;
        }
        deleteFromArrayByID(nn, nn[k].id);
    }

    return tt;
}

function viewInfo(id) {
    treeID = id;
}
function loadMainDialogHeaderInfo(partID, callback) {

    switch (partID) {
        case 1001:
            if (mNewEdit == 1) {
                $("#gender").getComboContent(6, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {

                    $("#empOrganization").getComboContent(1001, localeLanguages['191'], '&param1=-1&param2=-1&param3=-1', function () {
                        $("#empOrganization").change();
                        if (callback) {
                            callback();
                        }
                    }, 3);
                    //         $("#empOrganization").change();

                }, 4);


            }
            else if (mNewEdit == 2) {
                var empPhoto = "";
                $("#gender").getComboContent(6, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {


                    $("#empOrganization").getComboContent(1001, localeLanguages['191'], '&param1=-1&param2=-1&param3=-1', function () {

                        for (var j = 0; j < employee.length; j++) {

                            $("#fname").val(employee[j].fname);
                            $("#lname").val(employee[j].lname);
                            $("#mName").val(employee[j].mName);
                            $("#hireDate2").val(employee[j].hiredate);
                            $("#fireDate").val(employee[j].firedate);
                            $("#empOrganization").val(employee[j].org_id);
                            $("#empPosition").val(employee[j].position_id);
                            $("#gender").val(employee[j].gendid);
                            $("#pinCode").val(employee[j].pin);
                            $("#empBirthday").val(employee[j].bdate);
                            empPhoto = employee[j].empPhoto;
                        }

                        if (empPhoto != "1") {
                            var html = "";
                            document.getElementById('personImage1').src = 'cs?action=loadPhoto&filePath=' + empPhoto;
                        }
                        $("#empOrganization").change();
                        if (callback) {
                            callback();
                        }
                    }, 3);
                    //         $("#empOrganization").change();
                    if (callback) {
                        callback();
                    }
                }, 4);


                var g = $('#grid');
                var gridSelid = g.jqGrid('getGridParam', 'selrow');
                var photo_url = g.jqGrid('getCell', gridSelid, 'photo_url');
                if (photo_url != "0" && photo_url != "" && photo_url != 0) {
                    document.getElementById("personImage1").src = 'cs?action=loadPhoto&filePath=' + photo_url;
                } else {
                    document.getElementById("personImage1").src = "img/profile-picture.jpg";
                }


            }
            break;
        case 1002:

            $('#documentList').empty();
            var organNameAz;
            var organNameRu;
            var organNameEn;
            var organDescAz;
            var organDescRu;
            var organDescEn;

            if (mNewEdit == 1) {
                if (callback) {
                    callback();
                }
            }
            if (mNewEdit == 2) {
                var picture = "1";
                for (var j = 0; j < eexam.length; j++) {
                    organNameAz = eexam[j].organNameAz;
                    organNameRu = eexam[j].organNameRu;
                    organNameEn = eexam[j].organNameEn;
                    organDescAz = eexam[j].organDescAz;
                    organDescRu = eexam[j].organDescRu;
                    organDescEn = eexam[j].organDescEn;
                    picture = eexam[j].orgPicture;
                }
                $("#cNameAz").val(organNameAz);
                $("#cNameRu").val(organNameRu);
                $("#cNameEn").val(organNameEn);
                $("#CTextAz").val(organDescAz);
                $("#CTextRu").val(organDescRu);
                $("#CTextEn").val(organDescEn);
                if (picture != "1") {
                    document.getElementById('personImage2').src = 'cs?action=loadPhoto&filePath=' + picture;
                }
            }
            break;
        case 1006:

            var orgTypID = 0;
            var orgName;
            var orgDate;
            var orgNote;
            var OrgAddress;
            var orgDesc;


            $("#sOrgTypeID").getComboContent(2, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                if (mNewEdit == 2) {
                    for (var j = 0; j < person.length; j++) {
                        orgTypID = person[j].orgTypID;
                        orgName = person[j].orgName;
                        orgDate = person[j].cDate;
                        orgNote = person[j].orgDesc;
                        OrgAddress = person[j].inOrgAddress;
                        orgDesc = person[j].orgDesc;
                    }
                    setComboSelectedIndex1("sOrgTypeID", orgTypID);
                    $("#sOrgTypeID").change();
                    $("#inOrgName").val(orgName);
                    $("#inOrgDate").val(orgDate);
                    $("#inOrgAddress").val(OrgAddress);
                    $("#inOrgNote").val(orgDesc);

                }
                if (callback) {
                    callback();
                }
            }, 1);

            break;
        case 1007:
            if (mNewEdit == 1) {

                $("#sEmpName").getComboContent(partID, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                    $("#sEmpName").change();
                    $("#uName").val("");
                    $("input[type='password']").val('');
                    $('#uName').attr('readonly', false);
                    $('#passw').attr('readonly', false);
                    $("#chActiv").attr('checked', false);
                    $("#chAdmin").attr('checked', false);
                    $('#listOfButtons').find('input[type=checkbox]:checked').prop("checked", false);
                    if (callback) {
                        callback();
                    }
                }, 6);
            } else {
                if (mNewEdit == 2) {
                    $('#uName').attr('readonly', true);
                    $('#passw').attr('readonly', true);
                }
                if (callback) {
                    callback();
                }
            }
            break;
        case 1008:
            editExamples();
            if (callback) {
                callback();
            }
            break;
        case 1009:
            $("#viewApprove").hide();
            if ($('#btn_viewMain_div').css('display') != 'none') {
            } else {
                $("#viewApprove").show();
            }
            editExamples();
            if (callback) {
                callback();
            }
            break;
        case 1011:
            if ($('#btn_viewMain_div').css('display') != 'none') {
                $("#viewUnApprove").hide();
            } else {
                $("#viewUnApprove").show();
            }
            editExamples();
            if (callback) {
                callback();
            }
            break;
        case 1017:
            editExamples();
            if (callback) {
                callback();
            }
            break;
        case 1010:
            var catTypID;
            var catName;
            var catNameRu;
            var catNameEn;
            var catNote;
            var catNoteRu;
            var catNoteEn;
            var code;
            if (mNewEdit == 2) {
                for (var j = 0; j < category.length; j++) {
                    catTypID = category[j].catTypID;
                    catName = category[j].catName;
                    catNameEn = category[j].catNameEn;
                    catNameRu = category[j].catNameRu;
                    catNoteRu = category[j].catDescRu;
                    catNoteEn = category[j].catDescEn;
                    catNote = category[j].catDesc;
                    code = category[j].code;
                }
            }
            $("#categoryType").getComboContent(7, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                if (mNewEdit == 2) {
                    setComboSelectedIndex1("categoryType", catTypID);
                    $("#categorytName").val(catName);
                    $("#categorytNameRu").val(catNameRu);
                    $("#categorytNameEn").val(catNameEn);
                    $("#categoryDescription").val(catNote);
                    $("#categoryDescriptionRu").val(catNoteRu);
                    $("#categoryDescriptionEn").val(catNoteEn);
                    $("#regCodePart").val(code);

                }
                if (callback) {
                    callback();
                }
            }, 2);

            break;

        case 1012:
            var carryNameAz;
            var ccarryNameRu;
            var carryNameEn;
            var carryDescAz;
            var carryDescRu;
            var carryDescEn;
            if (mNewEdit == 2) {
                var carryPic = "1";
                for (var j = 0; j < peducation.length; j++) {
                    carryNameAz = peducation[j].carryNameAz;
                    ccarryNameRu = peducation[j].carryNameRu;
                    carryNameEn = peducation[j].carryNameEn;
                    carryDescAz = peducation[j].carryDescAz;
                    carryDescRu = peducation[j].carryDescRu;
                    carryDescEn = peducation[j].carryDescEn;
                    carryPic = peducation[j].carryPicture;
                }
                $("#cNameAz").val(carryNameAz);
                $("#cNameRu").val(ccarryNameRu);
                $("#cNameEn").val(carryNameEn);
                $("#CTextAz").val(carryDescAz);
                $("#CTextRu").val(carryDescRu);
                $("#CTextEn").val(carryDescEn);
                if (carryPic != "1") {
                    var html = "";
                    document.getElementById('personImage2').src = 'cs?action=loadPhoto&filePath=' + carryPic;
                }
            }
            if (callback) {
                callback();
            }

            break;

        default :
            if (callback) {
                callback();
            }
    }
}
function loadRightPanelInfo() {
    $("#rightPanelInfo").html('');
    var html = "";
    var g = $('#grid');
    var gridSelid = g.jqGrid('getGridParam', 'selrow');
    var id = g.jqGrid('getCell', gridSelid, 'id');
    rightPanel = [];
    if (partID == 1001) { // employee
        var headerFullname = localeLanguages['181'];
        var headerPosition = localeLanguages['154'];
        var headerEmail = localeLanguages['183'];
        var headerPhone = localeLanguages['184'];
        var fullname = g.jqGrid('getCell', gridSelid, 'fullname');
        var positionname = g.jqGrid('getCell', gridSelid, 'positionname');
        var email = g.jqGrid('getCell', gridSelid, 'email');
        var phone = g.jqGrid('getCell', gridSelid, 'phone');
        var photo_url = g.jqGrid('getCell', gridSelid, 'photo_url');
        var pp = '';
        html =
            ' <div class="righttextb"> ' + headerFullname + '</div> ' +
            ' <div class="righttext">' + fullname + '</div>' +
            ' <div class="righttextb">' + headerPosition + '</div> ' +
            ' <div class="righttext">' + positionname + '</div>' +
            ' <div class="righttextb">' + headerPhone + '</div> ' +
            ' <div class="righttext">' + phone + '</div>' +
            ' <div class="righttextb">' + headerEmail + '</div> ' +
            ' <div class="righttext">' + email + '</div>';

        if (photo_url != '' && photo_url != null) {
            pp = ' <div style="width:100%;text-align:center; margin-bottom:6%; "> ' +
            ' <img id="mainImg" src=cs?action=loadPhoto&filePath=' + photo_url + ' width="40%" height="40%"> ' +
            ' </div> ';
        } else {
            pp = ' <div style="width:100%;text-align:center; margin-bottom:6%; "> ' +
            ' <img src="img/profile-picture.jpg" width="40%" height="40%"> ' +
            ' </div> ';
        }
        $("#rightPanelInfo").html(pp + html);

    } else if (partID == 1002) {
        var headerFullname = localeLanguages['198'];
        var headerDesc = localeLanguages['174'];
        var fullname = g.jqGrid('getCell', gridSelid, 'organname');
        var desc = g.jqGrid('getCell', gridSelid, 'description');
        var photo_url = g.jqGrid('getCell', gridSelid, 'photo_url');
        html =
            ' <div class="righttextb">' + headerFullname + '</div> ' +
            ' <div class="righttext">' + fullname + '</div>' +
            ' <div class="righttextb">' + headerDesc + '</div> ' +
            ' <div class="righttext">' + desc + '</div>';

        if (photo_url != '' && photo_url != null) {
            pp = ' <div style="width:100%;text-align:center; margin-bottom:6%; "> ' +
            ' <img id="mainImg" src=cs?action=loadPhoto&filePath=' + photo_url + ' width="40%" height="40%"> ' +
            ' </div> ';
        } else {
            pp = ' <div style="width:100%;text-align:center; margin-bottom:6%; "> ' +
            ' <img src="img/profile-picture.jpg" width="40%" height="40%"> ' +
            ' </div> ';
        }
        $("#rightPanelInfo").html(pp + html);
    } else if (partID == 1003) {
        var themename = g.jqGrid('getCell', gridSelid, 'themename');
        var name = g.jqGrid('getCell', gridSelid, 'name');
        var description = g.jqGrid('getCell', gridSelid, 'description');

        html =
            ' <div class="righttextb">Theme</div> ' +
            ' <div class="righttext">' + themename + '</div>' +
            ' <div class="righttextb">Document Name</div> ' +
            ' <div class="righttext">' + name + '</div>' +
            ' <div class="righttextb">Description</div> ' +
            ' <div class="righttext">' + description + '</div>';

        $("#rightPanelInfo").html(html);
    } else if (partID == 1008 || partID == 1009 || partID == 1011) {
        var headerCatName = localeLanguages['104'];
        var headerName = localeLanguages['136'];
        var headerStatus = localeLanguages['180'];
        var katname = g.jqGrid('getCell', gridSelid, 'katname');
        var exname = g.jqGrid('getCell', gridSelid, 'exname');
        var status = g.jqGrid('getCell', gridSelid, 'status');

        html =
            ' <div class="righttextb">' + headerCatName + '</div> ' +
            ' <div class="righttext">' + katname + '</div>' +
            ' <div class="righttextb">' + headerName + '</div> ' +
            ' <div class="righttext">' + exname + '</div>' +
            ' <div class="righttextb">' + headerStatus + '</div> ' +
            ' <div class="righttext">' + status + '</div>';
        $("#rightPanelInfo").html(html);
    } else if (partID == 1017) {
        var headerCatName = localeLanguages['104'];
        var headerName = localeLanguages['136'];
        var headerMenu = localeLanguages['164'];
        var katname = g.jqGrid('getCell', gridSelid, 'katname');
        var exname = g.jqGrid('getCell', gridSelid, 'exname');
        var menu = g.jqGrid('getCell', gridSelid, 'menu');

        html =
            ' <div class="righttextb">' + headerMenu + '</div> ' +
            ' <div class="righttext">' + menu + '</div>' +
            ' <div class="righttextb">' + headerCatName + '</div> ' +
            ' <div class="righttext">' + katname + '</div>' +
            ' <div class="righttextb">' + headerName + '</div> ' +
            ' <div class="righttext">' + exname + '</div>';
        $("#rightPanelInfo").html(html);
    } else if (partID == 1007) {
        var headerFullname = localeLanguages['181'];
        var headerPosition = localeLanguages['154'];
        var Username = localeLanguages['169'];
        var fullname = g.jqGrid('getCell', gridSelid, 'fullname');
        var orgname = g.jqGrid('getCell', gridSelid, 'orgname');
        var positionname = g.jqGrid('getCell', gridSelid, 'positionname');
        var user_name = g.jqGrid('getCell', gridSelid, 'user_name');

        html =
            ' <div class="righttextb">' + headerFullname + '</div> ' +
            ' <div class="righttext">' + fullname + '</div>' +
            ' <div class="righttextb">' + headerPosition + '</div> ' +
            ' <div class="righttext">' + positionname + '</div>' +
            ' <div class="righttextb">' + Username + '</div> ' +
            ' <div class="righttext">' + user_name + '</div>'
        ;
        $("#rightPanelInfo").html(html);
    } else if (partID == 1012) {
        var headerFullname = localeLanguages['198'];
        var headerDesc = localeLanguages['174'];
        var fullname = g.jqGrid('getCell', gridSelid, 'carryname');
        var desc = g.jqGrid('getCell', gridSelid, 'description');
        var photo_url = g.jqGrid('getCell', gridSelid, 'photo_url');

        html =
            ' <div class="righttextb">' + headerFullname + '</div> ' +
            ' <div class="righttext">' + fullname + '</div>' +
            ' <div class="righttextb">' + headerDesc + '</div> ' +
            ' <div class="righttext">' + desc + '</div>';

        if (photo_url != '' && photo_url != null) {
            pp = ' <div style="width:100%;text-align:center; margin-bottom:6%; "> ' +
            ' <img id="mainImg" src=cs?action=loadPhoto&filePath=' + photo_url + ' width="40%" height="40%"> ' +
            ' </div> ';
        } else {
            pp = ' <div style="width:100%;text-align:center; margin-bottom:6%; "> ' +
            ' <img src="img/profile-picture.jpg" width="40%" height="40%"> ' +
            ' </div> ';
        }
        $("#rightPanelInfo").html(pp + html);
    }
}
function setComboSelectedIndex1(comboName, value) {
    var comboBox = document.getElementById(comboName);
    for (index1 = 0; index1 < comboBox.options.length; index1++) {
        if (comboBox[index1].value == value) {
            comboBox[index1].selected = true;
            break;
        }
    }
}
function myTrim(x) {
    return x.replace(/^\s+|\s+$/gm, '');
}
function loadMainInfo(url1, callback) {

    question = [];
    documents = [];
    person = [];
    category = [];
    pothdoc = [];
    ExampleOperation = [];
    media = [];
    library = [];
    referenceFile = [];
    documents = [];
    test = [];
    testView = [];
    examRegistration = [];
    examEvaluation = [];
    questionsAnswer = [];
    employee = [];
    examplesMass = [];
    employeeListExam = [];
    examInfoList = [];
    planguageInfo = [];
    pcontact = [];
    peducation = [];
    eexam = [];
    etraining = [];
    if (partID == 1007) {
        var menu = '';
        var k = '';
        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: url1,
            success: function (data) {
                btnL1 = [];
                btnL2 = [];
                $.each(data.dsList, function (i, ds) {

                    if (ds.levelID == 1) {

                        if (i == 0) {

                            k = ds.menuID;
                        }
                        if (menu == '') {
                            menu = ds.menuID;

                        } else if (menu != ds.menuID) {

                            menu = ds.menuID;
                        }
                        btnL1.push({menuID: menu, buttonID: ds.bID});
                    } else if (ds.levelID == 2) {
                        if (i == 0) {
                            k = ds.menuID;
                        }
                        if (menu == '') {
                            menu = ds.menuID;
                        } else if (menu != ds.menuID) {
                            menu = ds.menuID;
                        }
                        btnL2.push({menuID: menu, buttonID: ds.bID});
                    }

                });
            },
            error: function (data) {
            },
            complete: function (jqXHR, textStatus) {
                if (callback) {
                    callback();
                }
            }
        });
    } else {
        console.log("Weird!url1 = " + url1);
        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: url1,
            success: function (data) {
                $.each(data.dsList, function (i, ds) {

                    if (ds.partID == 1) {
                        person.push(ds);
                    } else if (ds.partID == 2) {
                        employee.push(ds);
                    } else if (ds.partID == 3) {
                        category.push(ds);
                    } else if (ds.partID == 4) {
                        peducation.push(ds);
                    } else if (ds.partID == 7000) {
                        examplesMass.push(ds);
                    } else if (ds.partID == 7) {
                        pcontact.push(ds);
                    } else if (ds.partID == 50001) {
                        pothdoc.push(ds);
                    } else if (ds.partID == 50002) {
                        media.push(ds);
                    } else if (ds.partID == 50003) {
                        library.push(ds);
                    } else if (ds.partID == 50004) {
                        documents.push(ds);
                    } else if (ds.partID == 50006) {
                        referenceFile.push(ds);
                    } else if (ds.partID == 50005) {
                        ExampleOperation.push(ds);
                    } else if (ds.partID == 11) {
                        qrPic.push(ds);
                    } else if (ds.partID == 3003) {
                        pcontact.push(ds);
                    } else if (ds.partID == 5) {
                        eexam.push(ds);
                    } else {
                        if (partID == 1003) {
                            documents.push(ds);
                        }
                    }
                });
            },
            error: function (data) {
                console.log("error get full info" + data);
            },
            complete: function (jqXHR, textStatus) {
                console.log("complete get full info");
                if (callback) {
                    callback();
                }
            }
        });
    }

}
function loadEmpList() {
    var orgID = 0;

    orgID = $('#examOrgList :selected').val();

    if (orgID != -1) {
        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: 'cs?action=loadEmpList' + '&orgID=' + orgID,
            success: function (data) {
                $("#mdList").empty();
                $("#selList").empty();
                $.each(data.dsList, function (i, ds) {
                    tf = 0; //False
                    $.each(employeeListExam, function (i, cc) {  //eger telebe listi gride evvelceden elave olunubsa
                        if (cc.realID == ds.id) {
                            tf = 1; //True
                        }
                    });
                    if (tf != 1) { //False
                        $("#mdList").append('<option value="' + ds.id + '">' + ds.full_name + '</option>');
                        employeeList.push({id: ds.id, fname: ds.full_name});
                    }
                });
            },
            error: function (data) {
            },
            complete: function (jqXHR, textStatus) {

            }
        });
    }
}
function loadDependentCombo(comboType, optionPrependText, comboName, selectedComboName, parametr2, paramet3) {
    var subjID = $('#' + selectedComboName + ' :selected').val();
    if (subjID != -1) {
        $('#' + comboName).empty();
        if (paramet3 == 1008 || paramet3 == 1009 || paramet3 == 1011) {
            $(".ui-multiselect.ui-widget.ui-state-default.ui-corner-all").width(200);
            if (comboType == 18) {
                subjID = "";
                $('#' + selectedComboName + ' option:selected ').each(function () {
                    var value = $(this).val() + ',';
                    subjID = subjID + value;
                });
                $('#ExmGroup').multiselect();
                $('#ExmGroup').multiselect('refresh');
                $('#ExmGroup').attr('multiple', 'multiple');
                $('#ExmClass').multiselect();
                $('#ExmClass').multiselect('refresh');
                $('#ExmClass').attr('multiple', 'multiple');
                $('#ExmType').multiselect();
                $('#ExmType').multiselect('refresh');
                $('#ExmType').attr('multiple', 'multiple');
                $('#ExmGenre').multiselect();
                $('#ExmGenre').multiselect('refresh');
                $('#ExmGenre').attr('multiple', 'multiple');

                if (subjID != "") {
                    $('#' + comboName).getComboContent(subjID, '', '&param1=' + localeLanguages['192'] + '&param2=' + parametr2 + '&param3=-1', function () {
                        $('#' + comboName).multiselect({
                            noneSelectedText: localeLanguages['191'],
                            checkAllText: localeLanguages['212'],
                            uncheckAllText: localeLanguages['213'],
                            selectedList: 2,
                            minWidth: '200',
                            click: function (event, ui) {
                                if (ui.checked) {
                                    //$('#multiple_dic_speciality option:selected').attr("status","3");
                                } else {
                                    //ui.attr("status","2");
                                }
                            }
                        });
                        $(".ui-multiselect.ui-widget.ui-state-default.ui-corner-all").width(200);

                        if (mNewEdit == 2) {
                            for (var j = 0; j < examplesMass.length; j++) {
                                var setArrayValList = "";
                                var setEachVal = "";

                                if (comboName === "ExmGroup") {
                                    setArrayValList = examplesMass[j].groupID;
                                } else if (comboName === "ExmClass") {
                                    setArrayValList = examplesMass[j].classID;
                                } else if (comboName === "ExmType") {
                                    setArrayValList = examplesMass[j].typeID;
                                } else if (comboName === "ExmGenre") {
                                    setArrayValList = examplesMass[j].janrID;
                                }
                                setEachVal = setArrayValList.split(';');
                                for (var i = 0; i < setArrayValList.length - 1; i++) {
                                    $('#' + comboName).multiselect("widget").find(":checkbox[value='" + setEachVal[i] + "']").attr("checked", "checked");
                                    $('#' + comboName + " option[value='" + setEachVal[i] + "']").attr("selected", 1);
                                    $('#' + comboName).multiselect("refresh");
                                }
                            }
                        }
                        $('#' + comboName).change();
                        // combosChange(0);
                    }, comboType);
                    $('#' + comboName).empty();
                }
                $(".ui-multiselect.ui-widget.ui-state-default.ui-corner-all").width(200);

            } else {
                console.log("comboName = "+comboName);
                    $('#' + comboName).getComboContent(subjID, optionPrependText, '&param1=' + localeLanguages['192'] + '&param2=' + parametr2 + '&param3=-1', function () {
                        $('#' + comboName).change();
                       // combosChange();
                    }, comboType);
            }

        } else {
            $('#' + comboName).getComboContent(subjID, optionPrependText, '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                var i = 0;
                if (partID == 1001) {
                    if (mNewEdit == 1) {
                        var sect = $("#empPosition :selected").val();
                        setComboSelectedIndex1(comboName, sect);
                    } else if (mNewEdit == 2) {
                        for (var j = 0; j < employee.length; j++) {
                            setComboSelectedIndex1(comboName, employee[j].position_id);
                        }
                    }
                    $('#' + comboName).change();
                }

            }, comboType);
        }

    } else {
        $('#' + comboName).empty();
        if (optionPrependText) {
            $('#' + comboName).prepend('<option value="-1"> --' + optionPrependText + ' --</option>');
            $('#' + comboName).change();
        }
    }

}
function getPress(eventObj) {
    var keycode;
    if (eventObj.keyCode) { //For IE
        keycode = eventObj.keyCode;
    } else if (eventObj.Which) {
        keycode = eventObj.Which;  // For FireFox
    } else keycode = eventObj.charCode;
    if (keycode == 13) { //if the key is the enter
        $('#searchSubmit').click();
        return false;
    }
}
function chnText(val) {
    var url1 = 'cs?action=getGridContent' + setGType();
    model.grid.url = url1;
    if (val.trim() == "") {
        main_grid(model);
    }
}
function setGType() {
    var gTyp;
    if (partID == 1001) {
        gTyp = '&gridType=1';
    } else if (partID == 1012) {
        gTyp = '&gridType=2';
    } else if (partID == 1002) {
        gTyp = '&gridType=3';
    } else if (partID == 1005) {
        gTyp = '&gridType=5';
    } else if (partID == 1007) {
        gTyp = '&gridType=7';
    } else if (partID == 1008) {
        gTyp = '&gridType=4';
    } else if (partID == 1009) {
        gTyp = '&gridType=4';
    } else if (partID == 1011) {
        gTyp = '&gridType=4';
    } else if (partID == 1017) {
        gTyp = '&gridType=8';
    }
    return gTyp;
}

function isValidEmailAddress(emailAddress) {
    var pattern;
    //$('#contList').change(function(){
    var contList = $('#contList  :selected').val();
    contactMessage = '';
    if (contList == 20001 || contList == 30096 || contList == 30097 || contList == 30100) {
        pattern = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
        contactMessage = "Phone number";
    } else if (contList == 1006) {
        pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
        contactMessage = "Email address";
    } else pattern = /.*/;

    //});
    return pattern.test(emailAddress);
}

function contactInfo(dialogName, menuPart) {


    var form = $("form", '#' + dialogName + '');
    var url = form.attr("action");
    var neN = 0;
    neN = newEditmp;
    url = url + '&newEdit=' + newEditmp + '&partID=' + mpPartID1;
    var l1 = 0;


    var a = $('#contVal').val();
    if (isValidEmailAddress(a)) {

        if (neN == 1) {

        } else {
            l1 = currID;
        }
        if (newEditmp == 2) {
            l1 = currID;
        }
        if ((newEditmp != 1 )) {
            url = url + '&realID=' + currID;
        } else {
            url = url + '&realID=' + 0;
        }
        console.log(neN + "=neN=" + l1);
        var ll = pcontact.length;
        if (neN == 1) {
            if (menuPart == 1006) {
                g = $('#structure_newMain_grid');
            } else if (menuPart == 1001) {
                g = $('#employees_newMain_grid');
                //  g = $('#structure_newMain_grid');
                var sel_id = g.jqGrid('getGridParam', 'selrow');
                var tipID = g.jqGrid('getCell', sel_id, 'tipID');
                var contList = $("#contList :selected").val();


            }
            pcontact.push({
                contType: $("#contList :selected").text(), contv: $("#contVal").val(),
                tipID: $("#contList :selected").val(), id: ll + 1, realID: ll + 1
            });
            var g
        } else if (neN == 2) {
            if (menuPart == 1006) {
                g = $('#structure_newMain_grid');
            } else if (menuPart == 1001) {
                g = $('#employees_newMain_grid');
            }

            var sel_id = g.jqGrid('getGridParam', 'selrow');
            var rID = g.jqGrid('getCell', sel_id, 'realID');
            $.each(pcontact, function (i, cc) {

                if (cc.realID == rID) {
                    cc.contType = $("#contList :selected").text();
                    cc.contv = $("#contVal").val();
                    cc.tipID = $("#contList :selected").val();
                    cc.id = ll + 1;
                }

            });
        }
        $.post(url, form.serialize(), function (data) {
            success : {
                tabs_grid();
                $('#' + dialogName).dialog("close");
            }
        });

    } else {
        alert("Sizin  " + contactMessage + " yanlışdır");
        return false;
    }
}
function docInfo() {
    var form = $("form", "#final_exDialog");
    console.log(form);
    console.log("FORM = ");
    console.log($("form", "#final_exDialog").serialize());
    var neN = 0;
    var url = '';
    var docType = 1000;
    console.log("mpPartID1 111 = "+mpPartID1);
    if(mpPartID1 == 50004) {
        docType = $("#documentTypeID option:selected").val();
    }
    console.log("docType"+docType);
    neN = newEditmp;
    url = 'cs1?action=setSession&typ=1&newEdit=' + neN + '&partID=' + mpPartID1+'&docTypeID='+docType;

    var docNameAz = $("#documentNameAZ").val();
    var docNameRu = $("#documentNameRu").val();
    var docNameEn = $("#documentNameEn").val();
    var authorAz = $("#authorAz").val();
    var authorRu = $("#authorRu").val();
    var authorEn = $("#authorEn").val();
    var docDate = $("#docdate").val();
    var docdate = $("#docdate").val();
    var docFileV = $("#upload").val();
    url = url + '&docNameAz=' + docNameAz + '&docNameRu=' + docNameRu + '&docNameEn='
    + docNameEn + '&docDate=' + docDate + '&docFileV=' + docFileV + '&authorAz=' + authorAz + '&authorRu=' + authorRu + '&authorEn=' + authorEn;

    if ((newEditmp == 1 )) {
        url = url + '&id=' + 0;
    }
    var docName = "";
    var author = "";
    if (localeLanguages['192'] == "1000") {
        docName = $("#documentNameAZ").val();
        author = $("#authorAz").val();
    } else if (localeLanguages['192'] == "1001") {
        docName = $("#documentNameRu").val();
        author = $("#authorRu").val();
    } else if (localeLanguages['192'] == "1002") {
        docName = $("#documentNameEn").val();
        author = $("#authorEn").val();
    }


    if (neN == 1) {
        if (mpPartID1 == 50000) {
            var ll = qrPic.length + 1;

            var ll1 = qrPic.length + 1;
            qrPic.push({
                qrPicPAth: qrCodePicture
            });
            url = url + '&realDocCount=' + ll1;
        }
        if (mpPartID1 == 50001) {
            var ll = pothdoc.length + 1;

            var ll1 = pothdoc.length + 1;
            pothdoc.push({
                docdate: $("#docdate").val(),
                docUrl: $("#upload").val(),
                authorAz: $("#authorAz").val(),
                authorRu: $("#authorRu").val(),
                authorEn: $("#authorEn").val(),
                docnameAz: $("#documentNameAZ").val(),
                docnameRu: $("#documentNameRu").val(),
                docnameEn: $("#documentNameEn").val(),
                realID: ll,
                partID: mpPartID1
            });
            url = url + '&realDocCount=' + ll1;
        } else if (mpPartID1 == 50002) {
            var ll = media.length + 1;

            var ll1 = media.length + 1;
            media.push({
                docdate: $("#docdate").val(),
                docUrl: $("#upload").val(),
                docLink: $("#link").val(),
                authorAz: $("#authorAz").val(),
                authorRu: $("#authorRu").val(),
                authorEn: $("#authorEn").val(),
                docnameAz: $("#documentNameAZ").val(),
                docnameRu: $("#documentNameRu").val(),
                docnameEn: $("#documentNameEn").val(),
                realID: ll,
                partID: mpPartID1
            });
            url = url + '&realDocCount=' + ll1;
        } else if (mpPartID1 == 50003) {
            var ll = library.length + 1;

            var ll1 = library.length + 1;
            library.push({
                docdate: $("#docdate").val(),
                docUrl: $("#upload").val(),
                docLink: $("#link").val(),
                authorAz: $("#authorAz").val(),
                authorRu: $("#authorRu").val(),
                authorEn: $("#authorEn").val(),
                link: $("#link").val(),
                docNumber: $("#numb").val(),
                docnameAz: $("#documentNameAZ").val(),
                docnameRu: $("#documentNameRu").val(),
                docnameEn: $("#documentNameEn").val(),
                realID: ll,
                partID: mpPartID1
            });
            url = url + '&realDocCount=' + ll1;
        } else if (mpPartID1 == 50004) {
            var ll = documents.length + 1;
            var ll1 = documents.length + 1;
            documents.push({
                docdate: $("#docdate").val(),
                docUrl: $("#upload").val(),
                docLink: $("#link").val(),
                authorAz: $("#authorAz").val(),
                authorRu: $("#authorRu").val(),
                authorEn: $("#authorEn").val(),
                docNo: $("#numb").val(),
                docnameAz: $("#documentNameAZ").val(),
                docnameRu: $("#documentNameRu").val(),
                docnameEn: $("#documentNameEn").val(),
                isHtmlContain: $("#documentTypeID").val(),
                realID: ll,
                partID: mpPartID1
            });
            url = url + '&realDocCount=' + ll1;
        }else if (mpPartID1 == 50006) {
            var ll = referenceFile.length + 1;
            var ll1 = referenceFile.length + 1;
            referenceFile.push({
                docdate: $("#docdate").val(),
                docUrl: $("#upload").val(),
                docLink: $("#link").val(),
                authorAz: $("#authorAz").val(),
                authorRu: $("#authorRu").val(),
                authorEn: $("#authorEn").val(),
                docNo: $("#numb").val(),
                docnameAz: $("#documentNameAZ").val(),
                docnameRu: $("#documentNameRu").val(),
                docnameEn: $("#documentNameEn").val(),
                isHtmlContain: $("#documentTypeID").val(),
                realID: ll,
                partID: mpPartID1
            });
            url = url + '&realDocCount=' + ll1;
        }

    } else if (neN == 2) {
        var g = $('#examples_newMain_grid');
        var sel_id = g.jqGrid('getGridParam', 'selrow');
        var realID = g.jqGrid('getCell', sel_id, 'realID');
        url = url + '&id=' + realID;
        if (mpPartID1 == 50001) {
            $.each(pothdoc, function (i, pp) {

                if (pp.realID == realID) {
                    pp.docnameAz = $("#documentNameAZ").val();
                    pp.docnameRu = $("#documentNameRu").val();
                    pp.docnameEn = $("#documentNameEn").val();
                    pp.authorAz = $("#authorAz").val();
                    pp.authorRu = $("#authorRu").val();
                    pp.authorEn = $("#authorEn").val();
                    pp.docDate = $("#docdate").val();
                    pp.docUrl = $("#upload").val();
                }
            });
        } else if (mpPartID1 == 50002) {
            $.each(media, function (i, pp) {
                if (pp.realID == realID) {
                    pp.docnameAz = $("#documentNameAZ").val();
                    pp.docnameRu = $("#documentNameRu").val();
                    pp.docnameEn = $("#documentNameEn").val();
                    pp.authorAz = $("#authorAz").val();
                    pp.authorRu = $("#authorRu").val();
                    pp.authorEn = $("#authorEn").val();
                    pp.docDate = $("#docdate").val();
                    pp.docLink = $("#link").val();
                    pp.docUrl = $("#upload").val();
                }
            });
        } else if (mpPartID1 == 50003) {
            $.each(library, function (i, pp) {
                if (pp.realID == realID) {
                    pp.docnameAz = $("#documentNameAZ").val();
                    pp.docnameRu = $("#documentNameRu").val();
                    pp.docnameEn = $("#documentNameEn").val();
                    pp.authorAz = $("#authorAz").val();
                    pp.authorRu = $("#authorRu").val();
                    pp.authorEn = $("#authorEn").val();
                    pp.docdate = $("#docdate").val();
                    pp.docUrl = $("#upload").val();
                    pp.docNo = $("#numb").val();
                    pp.docLink = $("#link").val();
                }
            });
        } else if (mpPartID1 == 50004) {
            $.each(documents, function (i, pp) {
                if (pp.realID == realID) {
                    pp.docnameAz = $("#documentNameAZ").val();
                    pp.docnameRu = $("#documentNameRu").val();
                    pp.docnameEn = $("#documentNameEn").val();
                    pp.authorAz = $("#authorAz").val();
                    pp.authorRu = $("#authorRu").val();
                    pp.authorEn = $("#authorEn").val();
                    pp.docDate = $("#docdate").val();
                    pp.docUrl = $("#upload").val();
                    pp.docNo = $("#numb").val();
                    pp.docLink = $("#link").val();
                    pp.isHtmlContain = $("#documentTypeID").val();
                }
            });
        }

    }


    $('#frmDoc').ajaxForm({
        url: url,
        cache: false,
        beforeSend: function () {
            $("#progress").parent().children(".ui-dialog-titlebar").hide();
            $("#progress").dialog("open");
        },
        uploadProgress: function (event, position, total, percentComplete) {

        },
        success: function (data) {
            $("#progress").dialog("close");
        },
        error: function () {
            console.log("Error while uploading");
            $("#progress").dialog("close");
        }
    });
    $('#frmDoc').submit();

    $.post(url, form.serialize(), function (data) {
        success : {
            tabs_grid();//mpPartID1
        }
    });


}
function editExamples() {
    if (mNewEdit == 1) {
        loadGroupCombo();
    }
    else if (mNewEdit == 2) {
        var QRphoto = "1"; var arr;
        var data,dataRegionID,dataCarryID,dataOrgID,dataCategoryID,dataYuneskoID,overTakenPlaceID,mete;
        for (var j = 0; j < examplesMass.length; j++) {
            $('#registerCode1').val(examplesMass[j].reysterCode);
            $('#exmNameAz').val(examplesMass[j].examNameAz);
            $('#exmNameRu').val(examplesMass[j].examNameRu);
            $('#exmNameEn').val(examplesMass[j].examNameEn);
            $('#eDate').val(examplesMass[j].eDate);
            $('#exmKeyWordAz').val(examplesMass[j].keyWordAz);
            $('#exmKeyWordRu').val(examplesMass[j].keyWordRu);
            $('#exmKeyWordEn').val(examplesMass[j].keyWordEn);
            //$('#OvertakenRegion').val(examplesMass[j].overTakenPlaceID);
            $('#textAz').val(examplesMass[j].textAz);
            $('#textRu').val(examplesMass[j].textRu);
            $('#textEn').val(examplesMass[j].textEn);
            $('#noteAz').val(examplesMass[j].noteAz);
            $('#noteRu').val(examplesMass[j].noteRu);
            $('#noteEn').val(examplesMass[j].noteEn);

            QRphoto = examplesMass[j].qrPhoto;
            dataRegionID = examplesMass[j].regionID;
            dataCarryID = examplesMass[j].carryID;
            dataOrgID = examplesMass[j].orgID;
            dataYuneskoID = examplesMass[j].yuneskoID;
            dataCategoryID = examplesMass[j].categoryID;
            overTakenPlaceID = examplesMass[j].overTakenPlaceID;
            mete = examplesMass[j].mete;

        }
        var html = "";
        if (QRphoto != "1" && QRphoto != 0) {
            html =
                ' <img id = "qrCodePic" name="qrCodePicture" ' + '>';
            $("#qrCodeMain").html(html);
            document.getElementById('qrCodePic').src = 'cs?action=loadPhoto&filePath=' + QRphoto;
        }else{
            $("#qrCodeMain img").remove();
        }

        $('#OvertakenRegion').getComboContent(10, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            setComboSelectedIndex1("OvertakenRegion",overTakenPlaceID);
        }, 4);
        $('#mete').getComboContent(10, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            setComboSelectedIndex1("mete",mete);
        }, 4);
        $('#region').getComboContent(11, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            $('#region').multiselect({
                noneSelectedText: localeLanguages['191'],
                checkAllText: localeLanguages['212'],
                uncheckAllText: localeLanguages['213'],
                selectedList: 2,
                minWidth: '300',
                click: function (event, ui) {
                    if (ui.checked) {
                        //$('#multiple_dic_speciality option:selected').attr("status","3");
                    } else {
                        //ui.attr("status","2");
                    }
                }
            });
            arr = dataRegionID.split(';');
            for (var i = 0; i < dataRegionID.length - 1; i++) {
                $("#region").multiselect("widget").find(":checkbox[value='" + arr[i] + "']").attr("checked", "checked");
                $("#region option[value='" + arr[i] + "']").attr("selected", 1);
                $("#region").multiselect("refresh");
            }
        }, 4);
        $('#cCarriesListMulti').getComboContent('1', '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            $('#cCarriesListMulti').multiselect({
                noneSelectedText: localeLanguages['191'],
                checkAllText: localeLanguages['212'],
                uncheckAllText: localeLanguages['213'],
                selectedList: 2,
                minWidth: '300',
                click: function (event, ui) {
                    if (ui.checked) {
                        //$('#multiple_dic_speciality option:selected').attr("status","3");
                    } else {
                        //ui.attr("status","2");
                    }
                }
            });
            arr = dataCarryID.split(';');
            for (var i = 0; i < dataCarryID.length - 1; i++) {
                $("#cCarriesListMulti").multiselect("widget").find(":checkbox[value='" + arr[i] + "']").attr("checked", "checked");
                $("#cCarriesListMulti option[value='" + arr[i] + "']").attr("selected", 1);
                $("#cCarriesListMulti").multiselect("refresh");
            }
        }, 9);
        $('#cOrganization').getComboContent('1', '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            $('#cOrganization').multiselect({
                noneSelectedText: localeLanguages['191'],
                checkAllText: localeLanguages['212'],
                uncheckAllText: localeLanguages['213'],
                selectedList: 2,
                minWidth: '300',
                click: function (event, ui) {
                    if (ui.checked) {
                        //$('#multiple_dic_speciality option:selected').attr("status","3");
                    } else {
                        //ui.attr("status","2");
                    }
                }
            });
            arr = dataOrgID.split(';');
            for (var i = 0; i < dataOrgID.length - 1; i++) {

                $("#cOrganization").multiselect("widget").find(":checkbox[value='" + arr[i] + "']").attr("checked", "checked");
                $("#cOrganization option[value='" + arr[i] + "']").attr("selected", 1);
                $("#cOrganization").multiselect("refresh");
            }
        }, 10);
        $('#yuneskoDomain').getComboContent(14, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            $('#yuneskoDomain').multiselect({
                noneSelectedText: localeLanguages['191'],
                checkAllText: localeLanguages['212'],
                uncheckAllText: localeLanguages['213'],
                selectedList: 2,
                minWidth: '300',
                click: function (event, ui) {
                    if (ui.checked) {
                        //$('#multiple_dic_speciality option:selected').attr("status","3");
                    } else {
                        //ui.attr("status","2");
                    }
                }
            });
            arr = dataYuneskoID.split(';');
            for (var i = 0; i < dataYuneskoID.length - 1; i++) {
                $("#yuneskoDomain").multiselect("widget").find(":checkbox[value='" + arr[i] + "']").attr("checked", "checked");
                $("#yuneskoDomain option[value='" + arr[i] + "']").attr("selected", 1);
                $("#yuneskoDomain").multiselect("refresh");
            }
        }, 4);
        $("#ExmCategory").getComboContent(1012, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            $('#ExmCategory').multiselect({
                noneSelectedText: localeLanguages['191'],
                checkAllText: localeLanguages['212'],
                uncheckAllText: localeLanguages['213'],
                selectedList: 2,
                minWidth: '300',
                click: function (event, ui) {
                    if (ui.checked) {
                        //$('#multiple_dic_speciality option:selected').attr("status","3");
                    } else {
                        //ui.attr("status","2");
                    }
                }
            });
            arr = dataCategoryID.split(';');
            for (var i = 0; i < dataCategoryID.length - 1; i++) {

                $("#ExmCategory").multiselect("widget").find(":checkbox[value='" + arr[i] + "']").attr("checked", "checked");
                $("#ExmCategory option[value='" + arr[i] + "']").attr("selected", 1);
                $("#ExmCategory").multiselect("refresh");
            }
            $("#ExmCategory").change();

        }, 7);
    }
}
