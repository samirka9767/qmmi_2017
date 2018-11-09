
function reportPassport(exampleID,langID,repotType) {
    var form = $('#downloadSubmit');
    form.attr('action', 'rs?action=createPassport&exampleID='+ exampleID +'&langID=' + langID+'&reportType=' + repotType);
    form.submit();
}

function reportMainGrid(gridType,url1,repotType){
    var form = $('#downloadSubmit');
    form.attr('action', 'rs?action=mainGridReport&gridType='+ gridType +url1+'&reportType=' + repotType);
    //form.attr('target', '_blank');
    form.submit();
}

function FullMundericatReport(gridType,url1,repotType){
    var form = $('#downloadSubmit');
    form.attr('action', 'rs?action=FullMundericatReport&gridType='+ gridType +url1+'&reportType=' + repotType);
    form.attr('target', '_blank');
    form.submit();
}

function downloadExamplesFiles(part,filePath){
    var form = $('#downloadSubmit');
    form.attr('action', 'cs?action=opFile&typ=1&filePath='+filePath);
    form.submit();
}